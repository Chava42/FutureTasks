package FutureTaskCore;

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
    private FutureTaskLauncher ftl;
    private static int fiveSeconds = 5;
    private static int sevenSecondsInMillis = 7000;
    private static int twoSecondsInMillis = 2000;

    @Before
    public void setUp() {
        ftl = new FutureTaskLauncher();
    }

    @Test
    public void testTimeUntil() {
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime after5Seconds = now.plus(fiveSeconds, ChronoUnit.SECONDS);
        ZonedDateTime before5Seconds = now.minus(fiveSeconds, ChronoUnit.SECONDS);
        Assert.assertEquals(5, ftl.timeUntil(now, after5Seconds));
        Assert.assertEquals(0, ftl.timeUntil(now, now));
        //Test that if a time is in the past, the timeUntil is 0
        Assert.assertEquals(0, ftl.timeUntil(now, before5Seconds));
    }

    @Test
    public void testTaskMinimal() throws InterruptedException {
        //TODO - Once we have a callback, this can be verified
        FutureTask ft0 = new FutureTask(0);
        FutureTask ft1 = new FutureTask(1);
        FutureTask ft2 = new FutureTask(2);
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime after5Seconds = now.plus(fiveSeconds, ChronoUnit.SECONDS);
        ZonedDateTime before5Seconds = now.minus(fiveSeconds, ChronoUnit.SECONDS);

        ftl.addTask(ft0, after5Seconds);
        ftl.addTask(ft1, before5Seconds);
        ftl.addTask(ft2, now);
    }

    @Test
    public void testTaskRun() throws InterruptedException {
        FutureTask ft0 = Mockito.mock(FutureTask.class);
        FutureTask ft1 = Mockito.mock(FutureTask.class);
        FutureTask ft2 = Mockito.mock(FutureTask.class);
        ZonedDateTime now = ZonedDateTime.now();
        ftl.addTask(ft1, now);
        ftl.addTask(ft0, now.plusSeconds(5));
        ftl.addTask(ft2, now.minusSeconds(5));
        Thread.sleep(twoSecondsInMillis);
        verify(ft1, times(1)).run();
        verify(ft2, times(1)).run();
        Thread.sleep(sevenSecondsInMillis);
        verify(ft0, times(1)).run();
    }

    @Test
    public void testTaskRun2() throws InterruptedException {
        FutureTask test = Mockito.mock(FutureTask.class);
        ZonedDateTime now = ZonedDateTime.now();
        ftl.addTask(test, now);
        ftl.addTask(test, now.minusSeconds(5));
        ftl.addTask(test, now.plusSeconds(5));
        Thread.sleep(sevenSecondsInMillis);
        verify(test, times(3)).run();
    }

    @Test
    public void testMidi() throws InterruptedException {
        MidiTask test = Mockito.mock(MidiTask.class);
        ftl.addTask(test, ZonedDateTime.now());
        ftl.addTask(test, ZonedDateTime.now());
        ftl.addTask(test, ZonedDateTime.now().plusSeconds(5));
        verify(test, times(2)).run();
        Thread.sleep(sevenSecondsInMillis);
        verify(test, times(3)).run();
    }

    @Test
    public void testMidiReal() throws InterruptedException {
        //int channel, int note, int volume, int duration
        MidiTask test = new MidiTask(0, 60, 80, 2000);
        ftl.addTask(test, ZonedDateTime.now());
        Thread.sleep(twoSecondsInMillis);
    }
}

