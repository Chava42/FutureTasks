package Sample;

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
