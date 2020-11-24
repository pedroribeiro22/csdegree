package zeromq.guiao5;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.nio.charset.StandardCharsets;

public class ChatServer {

    public static void main(String[] args) {

        try(
                ZContext context = new ZContext();
                ZMQ.Socket pub_socket = context.createSocket(SocketType.PUB);
                ZMQ.Socket pull_socket = context.createSocket(SocketType.PULL);
        ) {

            pub_socket.bind("tcp://*:" + 12345);
            pull_socket.bind("tcp://*:" + 12346);

            while(true) {
                byte[] msg = pull_socket.recv();
                String str_msg = new String(msg, StandardCharsets.UTF_8);
                System.out.println("Received \"" + str_msg + "\"");
                pub_socket.send(msg);
            }
        }
    }
}
