package FutureTaskCore;

import FutureTasks.FutureTask;
import FutureTasks.FutureTaskPairImpl;
import Midi.MidiTask;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Created by mks4b_000 on 12/5/2015.
 */


public class FutureTaskLauncherTest {
    private FutureTaskLauncher futureTaskLauncher;
    private static int fiveSeconds = 5;
    private static int sevenSecondsInMillis = 7000;
    private static int twoSecondsInMillis = 2000;

    @Before
    public void setUp() {
        futureTaskLauncher = new FutureTaskLauncher();
    }

    @Test
    public void testTimeUntil() {
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime after5Seconds = now.plus(fiveSeconds, ChronoUnit.SECONDS);
        ZonedDateTime before5Seconds = now.minus(fiveSeconds, ChronoUnit.SECONDS);
        Assert.assertEquals(5, futureTaskLauncher.timeUntil(now, after5Seconds));
        Assert.assertEquals(0, futureTaskLauncher.timeUntil(now, now));
        //Test that if a time is in the past, the timeUntil is 0
        Assert.assertEquals(0, futureTaskLauncher.timeUntil(now, before5Seconds));
    }

    @Test
    public void testlocalTaskLauncherConstructors(){
        FutureTaskLauncher localTaskLauncher1 = new FutureTaskLauncher(ChronoUnit.MILLIS);
        Assert.assertEquals(ChronoUnit.MILLIS,localTaskLauncher1.getChronoUnit());
        FutureTaskLauncher localTaskLauncher2 = new FutureTaskLauncher(ChronoUnit.YEARS);
        Assert.assertEquals(ChronoUnit.SECONDS,localTaskLauncher2.getChronoUnit());
        FutureTaskLauncher localTaskLauncher3 = new FutureTaskLauncher((ChronoUnit) null);
        Assert.assertEquals(ChronoUnit.SECONDS,localTaskLauncher3.getChronoUnit());
    }

    @Test
    public void testTaskMinimal() throws InterruptedException {
        //Real execution of the FutureTask class.
        FutureTask ft0 = new FutureTask(0);
        futureTaskLauncher.addTask(new FutureTaskPairImpl(ft0, ZonedDateTime.now()));
    }

    @Test
    public void testTaskRun() throws InterruptedException {
        FutureTask ft0 = Mockito.mock(FutureTask.class);
        FutureTask ft1 = Mockito.mock(FutureTask.class);
        FutureTask ft2 = Mockito.mock(FutureTask.class);
        ZonedDateTime now = ZonedDateTime.now();
        futureTaskLauncher.addTask(new FutureTaskPairImpl(ft1, now));
        futureTaskLauncher.addTask(new FutureTaskPairImpl(ft0, now.plusSeconds(5)));
        futureTaskLauncher.addTask(new FutureTaskPairImpl(ft2, now.minusSeconds(5)));
        Thread.sleep(twoSecondsInMillis);
        verify(ft1, times(1)).run();
        verify(ft2, times(1)).run();
        Thread.sleep(sevenSecondsInMillis);
        verify(ft0, times(1)).run();
    }

    @Test
    public void testMidiReal() throws InterruptedException {
        //int channel, int note, int volume, int duration
        MidiTask test = new MidiTask(0, 60, 80, 2000);
        futureTaskLauncher.addTask(new FutureTaskPairImpl (test, ZonedDateTime.now()));
        Thread.sleep(twoSecondsInMillis);
    }
}

