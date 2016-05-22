package FutureTaskCore;

import java.time.ZonedDateTime;

/**
 * Created by mks4b_000 on 5/17/2016.
 */
public interface FutureTaskPairInterface {

    /**
     * Return the runnable associated with the Future task
     * @return
     */
    FutureTaskInterface getTask();
    /**
     * Return the future time at which the Future task should be run.
     * @return
     */
    ZonedDateTime getFutureTime();

}
