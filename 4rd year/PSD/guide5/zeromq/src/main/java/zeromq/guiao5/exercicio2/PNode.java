package zeromq.guiao5.exercicio2;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class PNode {

    public static void main(String[] args) {

        try(
                ZContext context = new ZContext();
                ZMQ.Socket in = context.createSocket(SocketType.PULL);
                ZMQ.Socket out = context.createSocket(SocketType.PUB);

        ) {

            if(args.length < 2) System.exit(-1);

            int readPort = Integer.valueOf(args[0]);
            int writePort = Integer.valueOf(args[1]);

            in.bind("tcp://*:" + readPort);
            out.bind("tcp://*:" + writePort);

            while(true) {
                byte[] buf = in.recv();
                out.send(buf);
            }
        }
    }
}
