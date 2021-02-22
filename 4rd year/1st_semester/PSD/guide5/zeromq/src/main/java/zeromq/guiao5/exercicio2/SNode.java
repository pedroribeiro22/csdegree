package zeromq.guiao5.exercicio2;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SNode {

    static List<Integer> publisherNodes = new ArrayList(Arrays.asList(12346, 12348, 12350));

    public static void main(String[] args) {

        try(
                ZContext context = new ZContext();
                ZMQ.Socket in = context.createSocket(SocketType.SUB);
                ZMQ.Socket out = context.createSocket(SocketType.PUB);

        ) {

            if(args.length < 2) System.exit(-1);

            int readPort = Integer.valueOf(args[0]);
            int writePort = Integer.valueOf(args[1]);

            for(int i : SNode.publisherNodes)
               in.connect("tcp://localhost:" + i);

            out.bind("tcp://*:" + writePort);
            in.subscribe("".getBytes());

            while(true) {
                byte[] buf = in.recv();
                out.send(buf);
            }
        }
    }
}
