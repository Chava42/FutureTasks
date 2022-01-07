package FutureTasks;

import FutureTaskCore.FutureCallableResult;
import FutureTaskCore.FutureTaskCallableInterface;
import FutureTaskCore.FutureTaskPairInterface;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.*;
import java.time.ZonedDateTime;

/**
 * Created by mks4b_000 on 5/17/2016.
 * Must be serializable
 */
public class FutureTaskPairImpl<T extends FutureCallableResult> implements FutureTaskPairInterface<T>,Serializable {
    ImmutablePair<FutureTaskCallableInterface<T>, ZonedDateTime> pair;

    public FutureTaskPairImpl(FutureTaskCallableInterface<T> callable,ZonedDateTime plannedTime){
        pair = new ImmutablePair<FutureTaskCallableInterface<T>, ZonedDateTime>(callable,plannedTime);
    }

    @Override
    public FutureTaskCallableInterface<T> getTask() {
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

        FutureTaskPairImpl<T> that = (FutureTaskPairImpl<T>) o;

        return !(pair != null ? !pair.equals(that.pair) : that.pair != null);

    }

    @Override
    public int hashCode() {
        return pair != null ? pair.hashCode() : 0;
    }
}
