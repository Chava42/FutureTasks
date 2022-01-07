package FutureTaskCore;

import FutureTasks.FutureCallableResultImpl;
import FutureTasks.FutureTask;
import FutureTasks.FutureTaskPairImpl;
import Utils.SerializationHelperUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.time.ZonedDateTime;

/**
 * Created by mks4b_000 on 5/21/2016.
 */
public class FutureTaskPairImplTest {
    @Test
    public void testTaskMinimal(){
        FutureTask futureTask = new FutureTask(6, FutureCallableResultImpl.class);
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        FutureTaskPairImpl futureTaskPairImpl = new FutureTaskPairImpl(futureTask,zonedDateTime);
        Assert.assertEquals(futureTaskPairImpl.getTask(), futureTask);
        Assert.assertEquals(futureTaskPairImpl.getFutureTime(), zonedDateTime);
        //Test serialization
        String serializedFutureTaskImpl1 = null;
        try {
            serializedFutureTaskImpl1 = SerializationHelperUtils.toString((Serializable) futureTaskPairImpl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Assert.assertEquals(futureTaskPairImpl,SerializationHelperUtils.fromString(serializedFutureTaskImpl1));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSerialization() throws IOException {

        FutureTaskPairImpl futureTaskPairImpl1 = new FutureTaskPairImpl(new FutureTask(6,FutureCallableResultImpl.class), ZonedDateTime.now());
        FileOutputStream fileOut =
                new FileOutputStream("test.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(futureTaskPairImpl1);
        out.close();
        fileOut.close();
        System.out.printf("Serialized data is saved in test.ser");


        FutureTaskPairImpl futureTaskPairImpl2;
        try {
            FileInputStream fileIn = new FileInputStream("test.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            futureTaskPairImpl2 = (FutureTaskPairImpl) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("FutureTaskPairImpl class not found");
            c.printStackTrace();
            return;
        } finally {
            File file = new File("test.ser");
            Files.deleteIfExists(file.toPath());
        }
        Assert.assertEquals(futureTaskPairImpl1, futureTaskPairImpl2);
    }

}
