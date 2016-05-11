package Midi;

/**
 * Created by mks4b_000 on 12/8/2015.
 */
public enum Notes {
    MID_C(60), MID_E(64),  MID_G(67);
    private int value;

    private Notes(int value) {
        this.value = value;
    }
    public int getValue(){
      return (value);
    }
}

//enum map
//import java.util.EnumMap;
//
//public class Program {
//
//    enum Importance {
//        Low, Medium, High, Critical
//    }
//
//    public static void main(String[] args) {
//
//        // Create an EnumMap.
//        EnumMap<Importance, String> e = new EnumMap<>(Importance.class);
//        e.put(Importance.Low, "=Low");
//        e.put(Importance.High, "=High");
//
//        // Get values from the map.
//        String value1 = e.get(Importance.Low);
//        String value2 = e.get(Importance.High);
//
//        System.out.println(value1);
//        System.out.println(value2);
//    }
//}