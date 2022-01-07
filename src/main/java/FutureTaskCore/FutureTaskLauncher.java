package FutureTaskCore;

/**
 * Created by mks4b_000 on 5/15/2016.
 */

import Utils.DurationConverter;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Created by mks4b_000 on 12/5/2015.
 */
public class FutureTaskLauncher<T extends FutureCallableResult> {

    private ListeningScheduledExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newSingleThreadScheduledExecutor());

    private static Logger logger = LogManager.getLogger(FutureTaskLauncher.class);

    private final ChronoUnit chronoUnit;

    private final ConcurrentHashMap<String,ScheduledFuture<T>> mapOfFutures = new ConcurrentHashMap<>();

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

    public int numFutures(){
        return mapOfFutures.size();
    }

    public boolean hasFuturesInProgress(){
        for (Future<T> example:mapOfFutures.values()){
            if (!example.isDone()){return true;}
        }
        return false;
    }


    public void printTaskIdsInProgress(){
//        for (Map.Entry<String, String> entry : map.entrySet())
//        {
//            System.out.println(entry.getKey() + "/" + entry.getValue());
//        }
        Set<Map.Entry<String,ScheduledFuture<T>>> foo = mapOfFutures.entrySet();
       for (Map.Entry<String,ScheduledFuture<T>> example:foo){

            if (!example.getValue().isDone()){
                //Want to print the id of the task here.
                System.out.println("Task " + example.getKey() + " is still running");
            }
    }
    }

    public void addTask(FutureTaskPairInterface<T> pair) {
        logger.debug("Added scheduled task to executor");
        ScheduledFuture myResult=executorService.schedule(
                pair.getTask(),
                timeUntil(pair.getFutureTime()),
                DurationConverter.convert(chronoUnit));
        FutureTaskCallableInterface<T> foo= pair.getTask();
        String bar=        pair.getTask().getUniqueIdentifier();
        mapOfFutures.put(pair.getTask().getUniqueIdentifier(),myResult);
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
