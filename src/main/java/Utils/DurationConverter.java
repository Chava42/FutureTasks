package Utils;

import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

/**
 * Created by mks4b_000 on 5/22/2016.
 */
public final class DurationConverter {

    public static TimeUnit convert(ChronoUnit chronoUnit) {
        if (chronoUnit == null) {
            throw new IllegalArgumentException("ChronoUnit cannot be null");
        }
        switch (chronoUnit) {
            case DAYS:
                return TimeUnit.DAYS;
            case HOURS:
                return TimeUnit.HOURS;
            case MINUTES:
                return TimeUnit.MINUTES;
            case SECONDS:
                return TimeUnit.SECONDS;
            case MICROS:
                return TimeUnit.MICROSECONDS;
            case MILLIS:
                return TimeUnit.MILLISECONDS;
            case NANOS:
                return TimeUnit.NANOSECONDS;
            default:
                throw new UnsupportedOperationException("Conversion of the Chronounit " + chronoUnit.toString() + " to TimeUnit is not supported");
        }
    }

    public static ChronoUnit convert(TimeUnit timeUnit) {
        if (timeUnit == null) {
            throw new IllegalArgumentException("TimeUnit cannot be null");
        }
        switch (timeUnit) {
            case DAYS:
                return ChronoUnit.DAYS;
            case HOURS:
                return ChronoUnit.HOURS;
            case MINUTES:
                return ChronoUnit.MINUTES;
            case SECONDS:
                return ChronoUnit.SECONDS;
            case MICROSECONDS:
                return ChronoUnit.MICROS;
            case MILLISECONDS:
                return ChronoUnit.MILLIS;
            case NANOSECONDS:
                return ChronoUnit.NANOS;
            default:
                throw new UnsupportedOperationException("Unrecognized TimeUnit " + timeUnit.toString() + " is not supported");

        }
    }
}
