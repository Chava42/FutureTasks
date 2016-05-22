package FutureTaskCore;
import Utils.DurationConverter;
import org.junit.Assert;
import org.junit.Test;

import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

/**
 * Created by mks4b_000 on 5/22/2016.
 */
public class TimeUtilsTest {
    @Test
    public void TestChronoToTimeUnit(){
        Assert.assertEquals(TimeUnit.DAYS,DurationConverter.convert(ChronoUnit.DAYS));
        Assert.assertEquals(TimeUnit.HOURS,DurationConverter.convert(ChronoUnit.HOURS));
        Assert.assertEquals(TimeUnit.MINUTES,DurationConverter.convert(ChronoUnit.MINUTES));
        Assert.assertEquals(TimeUnit.SECONDS,DurationConverter.convert(ChronoUnit.SECONDS));
        Assert.assertEquals(TimeUnit.MICROSECONDS,DurationConverter.convert(ChronoUnit.MICROS));
        Assert.assertEquals(TimeUnit.MILLISECONDS,DurationConverter.convert(ChronoUnit.MILLIS));
        Assert.assertEquals(TimeUnit.NANOSECONDS,DurationConverter.convert(ChronoUnit.NANOS));
        try {
            DurationConverter.convert((ChronoUnit)null);
        }catch (IllegalArgumentException e){
            Assert.assertNotNull(e);
        }
        try {
            DurationConverter.convert(ChronoUnit.DECADES);
        } catch (UnsupportedOperationException e){
            Assert.assertNotNull(e);
        }
    }

    @Test
    public void TestTimeUnitToChrono(){
        Assert.assertEquals(ChronoUnit.DAYS,DurationConverter.convert(TimeUnit.DAYS));
        Assert.assertEquals(ChronoUnit.HOURS,DurationConverter.convert(TimeUnit.HOURS));
        Assert.assertEquals(ChronoUnit.MINUTES,DurationConverter.convert(TimeUnit.MINUTES));
        Assert.assertEquals(ChronoUnit.SECONDS,DurationConverter.convert(TimeUnit.SECONDS));
        Assert.assertEquals(ChronoUnit.MICROS,DurationConverter.convert(TimeUnit.MICROSECONDS));
        Assert.assertEquals(ChronoUnit.MILLIS,DurationConverter.convert(TimeUnit.MILLISECONDS));
        Assert.assertEquals(ChronoUnit.NANOS,DurationConverter.convert(TimeUnit.NANOSECONDS));
        try {
            DurationConverter.convert((TimeUnit)null);
        }catch (IllegalArgumentException e){
            Assert.assertNotNull(e);
        }
    }
}
