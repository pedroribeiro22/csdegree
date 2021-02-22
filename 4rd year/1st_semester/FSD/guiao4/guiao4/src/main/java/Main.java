import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<Integer, Integer> leaders = new HashMap<>();
        Thread[] threads = new Thread[5];
        for(int i = 0; i < 5; i++) threads[i] = new Thread(new Asynchronous(12345 + i, leaders));
        for(int i = 0; i < 5; i++) threads[i].start();
    }
}
