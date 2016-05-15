package FutureTaskCore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by mks4b_000 on 12/5/2015.
 */
public class FutureTask implements Runnable{
    int taskNo;

    public FutureTask(int taskNo){
        this.taskNo = taskNo;
    }

    @Override
    public void run() {
        System.out.println("Running task number " + taskNo);
    }

}
