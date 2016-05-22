package FutureTaskCore;

/**
 * Created by mks4b_000 on 5/15/2016.
 */

import Utils.DurationConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Created by mks4b_000 on 12/5/2015.
 */
public class FutureTaskLauncher {

    private ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(10);

    private static Logger logger = LogManager.getLogger(FutureTaskLauncher.class);

    private final ChronoUnit chronoUnit;

    public FutureTaskLauncher() {
        this.chronoUnit = ChronoUnit.SECONDS;
    }

    public FutureTaskLauncher(ChronoUnit chronoUnit) {
        boolean err = false;
        try {
            DurationConverter.convert(chronoUnit);

        } catch (IllegalArgumentException e) {
            logger.warn("The ChronoUnit supplied was null, using default of SECONDS", e);
            err=true;
        } catch (UnsupportedOperationException e) {
            logger.warn("The ChronoUnit supplied is not supported, using default of SECONDS", e);
            err=true;
        }

        if (err) {
            this.chronoUnit = ChronoUnit.SECONDS;
        } else {
            this.chronoUnit = chronoUnit;
        }
    }

    public void addTask(FutureTaskPairInterface pair) {
        logger.debug("Added scheduled task to executor");
        executorService.schedule(pair.getTask(), timeUntil(pair.getFutureTime()),
                DurationConverter.convert(chronoUnit));
    }

    public ChronoUnit getChronoUnit() {
        return chronoUnit;
    }

    public void shutdown() {
        executorService.shutdown();
    }

    protected long timeUntil(ZonedDateTime now, ZonedDateTime zt) {
        long diff = now.until(zt, chronoUnit);
        return (diff > 0) ? diff : 0;
    }

    protected long timeUntil(ZonedDateTime zt) {
        ZonedDateTime now = ZonedDateTime.now();
        return timeUntil(now, zt);
    }
}
