import FutureTasks.*;
import FutureTaskCore.FutureTaskLauncher;
import FutureTasks.FutureTask;

import java.io.*;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;


/**
 * Created by mks4b_000 on 5/15/2016.
 */
public class FutureTaskApp {
    public static void main (String[] args) throws InterruptedException, IOException {
        System.out.println("Let's launch some tasks");
        FutureTaskLauncher ftl = new FutureTaskLauncher();
        ftl.addTask(new FutureTaskPairImpl(new FutureTask(1,FutureCallableResultImpl.class),ZonedDateTime.now()));
        ftl.addTask(new FutureTaskPairImpl(new FutureTask(2,FutureCallableResultImpl.class),ZonedDateTime.now().plusSeconds(3)));
        ftl.addTask(new FutureTaskPairImpl(new FutureTask(3,FutureCallableResultImpl.class),ZonedDateTime.now().plusSeconds(5)));

        System.out.println("Number of tasks  is: " + ftl.numFutures());
        URL classesRootDir = ftl.getClass().getProtectionDomain().getCodeSource().getLocation();
        System.out.println("classpath of class:" + classesRootDir);
        boolean running = true;
        while (running == true){
            running = ftl.hasFuturesInProgress();
            System.out.println("Are tasks still running? " + running);
            System.out.println("List running tasks: ");
            ftl.printTaskIdsInProgress();

            Thread.sleep(1000);
        }

        ftl.shutdown();
        //output file setup
        FileWriter fileWriter = new FileWriter("src/main/resources/propertyCompare");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        //compare two property files
        File File1 = new File("src/main/resources/propertyFile1.properties");
        InputStream sourceStream1 = new FileInputStream(File1);
        Properties properties1= new Properties();
        properties1.load(sourceStream1);
        Set<String> property1KeySet=properties1.stringPropertyNames();

        File File2 = new File("src/main/resources/propertyFile2.properties");
        InputStream sourceStream2 = new FileInputStream(File2);
        Properties properties2= new Properties();
        properties2.load(sourceStream2);
        Set<String> property2KeySet=properties2.stringPropertyNames();

        System.out.println("Property1KeySet" + property1KeySet );
        System.out.println("Property2KeySet" + property2KeySet );

        Set<String> inSet1NotSet2=FutureTaskApp.inSet1NotSet2(property1KeySet,property2KeySet);
        System.out.println("Properties in File1 not in File2 " + inSet1NotSet2);
        printWriter.print("Properties in File1 not in File2 " + inSet1NotSet2 + "\n");
        Set<String> inSet2NotSet1=FutureTaskApp.inSet1NotSet2(property2KeySet,property1KeySet);
        System.out.println("Properties in File2 not in File1 " + inSet2NotSet1);
        printWriter.print("Properties in File2 not in File1 " + inSet2NotSet1 + "\n");
        Set<String> inBoth=FutureTaskApp.inBothSet1AndSet2(property1KeySet,property2KeySet);
        System.out.println("Properties in both File1 and File2 " + inBoth);
        printWriter.print("Properties in both File1 and File2 " + inBoth + "\n");
        printWriter.close();
    }

    public static Set<String> inSet1NotSet2(Set<String> set1, Set<String> set2){
        Set<String> set1copy = new HashSet<>(set1);
        set1copy.removeAll(set2);
        return(set1copy);
    }

    public static Set<String> inBothSet1AndSet2(Set<String> set1, Set<String> set2){
        Set<String> set1copy = new HashSet<>(set1);
        set1copy.retainAll(set2);
        return(set1copy);
    }

}
