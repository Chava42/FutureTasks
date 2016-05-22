package FutureTaskCore;

import java.io.Serializable;

/**
 * Created by mks4b_000 on 5/22/2016.
 * Needs to be both Runnable and Serializable
 * as we will run it and transfer it through files/sockets.
 */
public interface FutureTaskInterface extends Runnable, Serializable {
}
