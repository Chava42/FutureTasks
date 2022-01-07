package FutureTasks;

import FutureTaskCore.FutureCallableResult;
import FutureTaskCore.FutureTaskCallableInterface;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by mks4b_000 on 12/5/2015.
 */
public class FutureTask<T extends FutureCallableResult> implements FutureTaskCallableInterface<T>, Serializable {

    //This is final so that the FutureTask is immutable and therefore threadsafe.
    private final int taskNo;
    private Class<T> myClass;

    public FutureTask(int taskNo,Class<T> realT){
        this.taskNo = taskNo;
        this.myClass= realT;
    }

    //URHERE - just for test but not working now.
    public FutureTask<T> getFutureTask(int taskNo, Class<T> realT){
        return (new FutureTask(taskNo,realT));
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
    public T call() throws Exception {
        System.out.println("Running task number " + taskNo);
        //http://stackoverflow.com/questions/299998/instantiating-object-of-type-parameter
        //http://stackoverflow.com/questions/22494183/how-to-create-in-best-way-an-instance-from-the-class-object?lq=1
        //myTObject;
        //URHERE
       // return myClass.build(Integer.toString(taskNo));
        //http://www.javapractices.com/topic/TopicAction.do?Id=113
        return null;
    }

    @Override
    public String getUniqueIdentifier() {
        return Integer.toString(taskNo);
    }
}
