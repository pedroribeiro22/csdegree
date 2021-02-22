package zeromq.guiao5.exercicio2;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class Client {

    static List<Integer> pubNodes = new ArrayList(Arrays.asList(12345, 12346, 12347));
    static List<Integer> subNodes = new ArrayList(Arrays.asList(12348, 12349, 12350));

    private static void main(String[] args) {

        String current_room = "general";

        try(
                ZContext context = new ZContext();
                ZMQ.Socket subSocket = context.createSocket(SocketType.SUB);
                ZMQ.Socket pubSocket = context.createSocket(SocketType.PUB);
        ) {

            // We need a random generator
            Random rand = new Random(System.currentTimeMillis());
            int toSubscribe = rand.nextInt(Client.subNodes.size());
            int toPublish = rand.nextInt(Client.pubNodes.size());

            subSocket.connect("tcp://localhost:" + Client.subNodes.get(toSubscribe));
            pubSocket.connect("tcp://localhost:" + Client.pubNodes.get(toPublish));

            subSocket.subscribe(current_room);

            new Thread(new ClientReader(subSocket)).start();

            while(true) {
                Scanner in = new Scanner(System.in);
                String msg = in.nextLine();
                String[] splitted = msg.split(" ");
                if(splitted.length == 2) {
                    // mudar de sala
                    if(splitted[0].equals("\\room")) {
                        String new_room = splitted[1];
                        subSocket.unsubscribe(current_room);
                        current_room = new_room;
                        subSocket.subscribe(current_room);
                    }
                } else {
                    // Mensagem normal
                    byte[] new_msg = (current_room + " " + splitted[0]).getBytes();
                    pubSocket.send(new_msg);
                }
            }
        }
    }
}

class ClientReader implements Runnable {

    private ZMQ.Socket sub_socket;

    public ClientReader(ZMQ.Socket sub_socket) {
        this.sub_socket = sub_socket;
    }

    public void run() {

        while(true) {
            byte[] msg = this.sub_socket.recv();
            String str_msg = new String(msg, StandardCharsets.UTF_8);
            System.out.println("Received \"" + str_msg + "\" from the broker");
        }
    }

}
