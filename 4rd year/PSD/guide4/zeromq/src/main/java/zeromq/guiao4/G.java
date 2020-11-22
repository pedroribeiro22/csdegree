package zeromq.guiao4;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.nio.ByteBuffer;

public class G {

    public static void main(String[] args) {

        int n_workers = 5;

        try (ZContext context = new ZContext();
             ZMQ.Socket wf_reply = context.createSocket(SocketType.REP);
             ZMQ.Socket clients = context.createSocket(SocketType.ROUTER);
             ZMQ.Socket workers = context.createSocket(SocketType.DEALER);
        ) {
            clients.bind("tcp://*:" + 12345);
            workers.bind("inproc://workers");
            for (int i = 0; i < n_workers; i++)
                new Thread(new Wg(context)).start();
            ZMQ.proxy(clients, workers, null);
        }
    }
}

class Wg extends Thread {

        ZContext context;

        Wg(ZContext context) {
            this.context = context;
        }

        public void run() {

            try(ZMQ.Socket dealer_reply = context.createSocket(SocketType.REP)) {
                dealer_reply.connect("inproc://workers");
                while(true) {
                    byte[] msg = dealer_reply.recv();
                    ByteBuffer wrapped = ByteBuffer.wrap(msg);
                    int num = wrapped.getInt();
                    // run `g` function
                    String res = "" + num;
                    dealer_reply.send(res);
                }
            }
        }

}
