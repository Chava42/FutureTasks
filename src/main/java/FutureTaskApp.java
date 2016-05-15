import FutureTaskCore.FutureTask;
import FutureTaskCore.FutureTaskLauncher;

import java.time.ZonedDateTime;

/**
 * Created by mks4b_000 on 5/15/2016.
 */
public class FutureTaskApp {
    public static void main (String[] args) throws InterruptedException {
        System.out.println("Let's launch some tasks");
        FutureTaskLauncher ftl = new FutureTaskLauncher();
        ftl.addTask(new FutureTask(1),ZonedDateTime.now());
        ftl.addTask(new FutureTask(2),ZonedDateTime.now().plusSeconds(3));
        ftl.addTask(new FutureTask(3),ZonedDateTime.now().plusSeconds(5));
        Thread.sleep(7000);//Wait for tasks to finish, since we don't have status.
        ftl.shutdown();

    }
}
