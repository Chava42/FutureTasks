package Sample;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
//Version 1.x
//import org.apache.log4j.Logger;
//Version 2.x
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;



/**
 * Created by mks4b_000 on 12/5/2015.
 */
public class FutureTaskLauncher {
    private ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(10);
//    private static Logger logger = Logger.getLogger(FutureTaskLauncher.class);
    private static Logger logger = LogManager.getLogger(FutureTaskLauncher.class);
    public FutureTaskLauncher (){
    }
    public void addTask(Runnable ft, ZonedDateTime zt){
        executorService.schedule(ft, timeUntil(zt), TimeUnit.SECONDS);
        logger.debug("This is from the main executor");

    }
    protected long timeUntil(ZonedDateTime now, ZonedDateTime zt){
        long diff = now.until(zt, ChronoUnit.SECONDS);

        return (diff >0)?diff:0;
    }

    protected long timeUntil(ZonedDateTime zt){
        ZonedDateTime now = ZonedDateTime.now();
        return timeUntil(now,zt);
    }
}
