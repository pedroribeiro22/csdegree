package zeromq.guiao5.exercicio1;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        String current_room = "general";

        try(
                ZContext context = new ZContext();
                ZMQ.Socket sub_socket = context.createSocket(SocketType.SUB);
                ZMQ.Socket push_socket = context.createSocket(SocketType.PUSH);
        ) {

            sub_socket.connect("tcp://localhost:" + 12345);
            push_socket.connect("tcp://localhost:" + 12346);

            sub_socket.subscribe(current_room);

            new Thread(new ClientReader(sub_socket)).start();

            while(true) {
                Scanner in = new Scanner(System.in);
                String msg = in.nextLine();
                String[] splitted = msg.split(" ");
                if(splitted.length == 2) {
                    // Possivelmente mudar de sala
                    if(splitted[0].equals("\\room")) {
                        String new_room = splitted[1];
                        sub_socket.unsubscribe(current_room);
                        current_room = new_room;
                        sub_socket.subscribe(current_room);
                    }
                } else {
                    // Mensagem normal
                    byte[] new_msg = (current_room + " " + splitted[0]).getBytes();
                    push_socket.send(new_msg);
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
