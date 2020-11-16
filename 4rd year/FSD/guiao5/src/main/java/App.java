import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {

        List<Integer> peers_ports = new ArrayList<>();
        peers_ports.add(12345);
        peers_ports.add(12346);
        peers_ports.add(12347);

        for(int i = 0; i < 3; i++) {
            new Thread(new Peer(12345 + i, peers_ports)).start();
        }

        new Thread(new AutomatedMessageSender(12348, peers_ports)).start();

    }
}
