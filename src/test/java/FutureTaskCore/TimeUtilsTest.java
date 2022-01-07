package FutureTaskCore;
import Utils.DurationConverter;
//import org.junit.Assert;
//import org.junit.Test;
//The Ideaj does not work with the jupiter test, even though it works from the command line.
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.RepeatedTest;

import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Created by mks4b_000 on 5/22/2016.
 */

public class TimeUtilsTest {

    //@RepeatedTest(2)
    @Test
    public void TestChronoToTimeUnit(){
        System.out.println("Executing test TestChronoToTimeUnit");
        assertEquals(TimeUnit.DAYS, DurationConverter.convert(ChronoUnit.DAYS));
        assertEquals(TimeUnit.HOURS, DurationConverter.convert(ChronoUnit.HOURS));
        assertEquals(TimeUnit.MINUTES, DurationConverter.convert(ChronoUnit.MINUTES));
        assertEquals(TimeUnit.SECONDS, DurationConverter.convert(ChronoUnit.SECONDS));
        assertEquals(TimeUnit.MICROSECONDS, DurationConverter.convert(ChronoUnit.MICROS));
        assertEquals(TimeUnit.MILLISECONDS, DurationConverter.convert(ChronoUnit.MILLIS));
        assertEquals(TimeUnit.NANOSECONDS, DurationConverter.convert(ChronoUnit.NANOS));
        try {
            DurationConverter.convert((ChronoUnit)null);
        }catch (IllegalArgumentException e){

            assertNotNull(e);
        }
        try {
            DurationConverter.convert(ChronoUnit.DECADES);
        } catch (UnsupportedOperationException e){
            assertNotNull(e);
        }
    }



    @RepeatedTest(value=3)
    public void DummyTest(){
        System.out.println("this is a second dummy test");
    }

    @Test
    public void TestTimeUnitToChrono(){
        System.out.println("Executing test TestTimeUnitToChrono");
        assertEquals(ChronoUnit.DAYS, DurationConverter.convert(TimeUnit.DAYS));
        assertEquals(ChronoUnit.HOURS, DurationConverter.convert(TimeUnit.HOURS));
        assertEquals(ChronoUnit.MINUTES, DurationConverter.convert(TimeUnit.MINUTES));
        assertEquals(ChronoUnit.SECONDS, DurationConverter.convert(TimeUnit.SECONDS));
        assertEquals(ChronoUnit.MICROS, DurationConverter.convert(TimeUnit.MICROSECONDS));
        assertEquals(ChronoUnit.MILLIS, DurationConverter.convert(TimeUnit.MILLISECONDS));
        assertEquals(ChronoUnit.NANOS, DurationConverter.convert(TimeUnit.NANOSECONDS));
        try {
            DurationConverter.convert((TimeUnit)null);
        }catch (IllegalArgumentException e){
            assertNotNull(e);
        }
    }
    @Test
    public void testJSON(){
        try {

            ObjectMapper mapper = new ObjectMapper();
            String json = "{\"name\":\"mkyong\", \"age\":29}";


            Map<String, Object> map;

            // convert JSON string to Map
            map = mapper.readValue(json, new TypeReference<Map<String, String>>(){});
            System.out.println(json);
            System.out.println(map);

           // String jsonArray = "[{\"name\":\"mkyong\", \"age\":29}]";
            String jsonArray = "[{\"name\":\"mkyong\", \"age\":29, \"listy\":[29,30,31]}]";

            //ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree(jsonArray);
            System.out.println("The json object is: " + actualObj);



            // convert JSON string to Map
            map = mapper.readValue(json, new TypeReference<Map<String, String>>(){});
            System.out.println(json);
            System.out.println(map);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestStringArrayEvals(){
        //For two independently constructed lists:
        List<String> listA = Arrays.asList("a","b","c");
        String myNewC = new String("c");
        List<String> listB = Arrays.asList("a","b",myNewC);
        compareCollections(listA,listB);
        List<String> listC = Arrays.asList("b","a","c");
        List<String> listD = Arrays.asList("b","a","c","d");
        compareCollections(listA,listC);
        //The same list?
        compareCollections(listA,listA);
        compareCollections(listA,listD);
        Set<String> setA = new HashSet<>(Arrays.asList("b","a","c"));
        compareCollections(listA,setA);
    }

    public <T> void compareCollections(Collection<T> collection1, Collection<T> collection2){
        System.out.println("collection1" + collection1);
        System.out.println("collection2" + collection2);
        //ListA and ListB are not the same
        System.out.print("Is collection1 == collection2 ");
        System.out.println (collection1==collection2);
        //ListA has the elements of collection2.
        System.out.println("Size of collection1 " + collection1.size() + " Size of collection2 " + collection2.size());
        System.out.println("collection1 contains collection2 elements? ");
        for (T element:collection2){
            System.out.println(collection1.contains(element));
        }
        //ContainsAll is true
        System.out.println("Is containsAll true? " + collection1.containsAll(collection2));
        //Nevertheless, contains is false
        System.out.println("Is contains true? " + collection1.contains(collection2));
    }
}
