import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) {

        int total_peers = 3;
        List<Integer> peers_ports = new ArrayList<>(Arrays.asList(12345, 12346, 12347));

        for(int i = 0; i < 3; i++) {
            new Thread(new Peer(i, 12345 + i, peers_ports, total_peers)).start();
        }

    }
}
