package Sample;

import Midi.MidiTask;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
//log4j 1.x
//import org.apache.log4j.Logger;
//Version 2.x
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
/**
 * Created by mks4b_000 on 12/5/2015.
 */



public class FutureTaskLauncherTest {
    FutureTaskLauncher ftl;
    private static int timeFive = 5;
    private static int timeFiveMillis = 5000;
    private static ZonedDateTime now = ZonedDateTime.now();
    private static ZonedDateTime after5Seconds = now.plus(timeFive, ChronoUnit.SECONDS);
    private static ZonedDateTime before5Seconds = now.minus(timeFive, ChronoUnit.SECONDS);
//    private static Logger logger = Logger.getLogger(FutureTaskLauncherTest.class);
    private static Logger logger=LogManager.getLogger(FutureTaskLauncherTest.class);
 // = LogManager.getLogger();

//    @Mock
//    FutureTask ft3 = new FutureTask(3);
    @Before
    public void setUp()
    {
        ftl = new FutureTaskLauncher();

    }
    @Test
    public void testTimeUntil()  {
        Assert.assertEquals(5,ftl.timeUntil(now, after5Seconds));
        Assert.assertEquals(0,ftl.timeUntil(now,now));
        Assert.assertEquals(0,ftl.timeUntil(now,before5Seconds));
        logger.debug("This is a test");
    }

    @Test
    public void testTaskMinimal() throws InterruptedException {

        FutureTask ft0 = new FutureTask(0);
        FutureTask ft1 = new FutureTask(1);
        FutureTask ft2 = new FutureTask(2);
        ftl.addTask(ft0, after5Seconds);
        ftl.addTask(ft1, before5Seconds);
        ftl.addTask(ft2, now);
        Thread.sleep(timeFiveMillis);
    }

    @Test
    public void testTaskRun() throws InterruptedException {
       FutureTask test = Mockito.mock(FutureTask.class);
        ftl.addTask(test, now);
        ftl.addTask(test, after5Seconds);
        Thread.sleep(2000);
        verify(test, times(1)).run();
    }

    @Test
    public void testTaskRun2() throws InterruptedException {
        FutureTask test = Mockito.mock(FutureTask.class);
        ftl.addTask(test, now);
        ftl.addTask(test, now);
        ftl.addTask(test, after5Seconds);
        Thread.sleep(timeFiveMillis);
        verify(test, times(3)).run();
    }

    @Test
    public void testMidi() throws InterruptedException {
        MidiTask test = Mockito.mock(MidiTask.class);
        ftl.addTask(test, now);
        ftl.addTask(test, now);
        ftl.addTask(test, after5Seconds);
        verify(test, times(2)).run();
        Thread.sleep(timeFiveMillis);
        verify(test, times(3)).run();
    }

    @Test
    public void testMidiReal() throws InterruptedException {
        //int channel, int note, int volume, int duration
        MidiTask test = new MidiTask(0, 60, 80, 2000);
        // test.run();
        ftl.addTask(test, now);
        Thread.sleep(2000);
    }
}

