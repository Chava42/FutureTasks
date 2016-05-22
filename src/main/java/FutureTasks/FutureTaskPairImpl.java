package FutureTasks;

import FutureTaskCore.FutureTaskInterface;
import FutureTaskCore.FutureTaskPairInterface;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.*;
import java.time.ZonedDateTime;
import java.util.Base64;

/**
 * Created by mks4b_000 on 5/17/2016.
 * Must be serializable
 */
public class FutureTaskPairImpl implements FutureTaskPairInterface,Serializable {
    ImmutablePair<FutureTaskInterface, ZonedDateTime> pair;

    public FutureTaskPairImpl(FutureTaskInterface runnable,ZonedDateTime plannedTime){
        pair = new ImmutablePair<FutureTaskInterface, ZonedDateTime>(runnable,plannedTime);
    }

    @Override
    public FutureTaskInterface getTask() {
        return pair.getKey();
    }

    @Override
    public ZonedDateTime getFutureTime() {
        return pair.getValue();
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FutureTaskPairImpl that = (FutureTaskPairImpl) o;

        return !(pair != null ? !pair.equals(that.pair) : that.pair != null);

    }

    @Override
    public int hashCode() {
        return pair != null ? pair.hashCode() : 0;
    }
}
