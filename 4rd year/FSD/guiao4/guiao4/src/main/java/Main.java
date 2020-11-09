import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<Integer, Integer> leaders = new HashMap<>();
        Thread[] threads = new Thread[10];
        for(int i = 0; i < 10; i++) threads[i] = new Thread(new Process(12345 + i, leaders));
        for(int i = 0; i < 10; i++) threads[i].start();
    }
}
