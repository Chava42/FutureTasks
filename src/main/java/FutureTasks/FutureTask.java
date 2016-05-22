package FutureTasks;

import FutureTaskCore.FutureTaskInterface;

import java.io.Serializable;

/**
 * Created by mks4b_000 on 12/5/2015.
 */
public class FutureTask implements FutureTaskInterface, Serializable {
    int taskNo;

    public FutureTask(int taskNo){
        this.taskNo = taskNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FutureTask that = (FutureTask) o;

        return taskNo == that.taskNo;
    }

    @Override
    public int hashCode() {
        return taskNo;
    }

    @Override
    public Object call() throws Exception {
        System.out.println("Running task number " + taskNo);
        return null;
    }
}
