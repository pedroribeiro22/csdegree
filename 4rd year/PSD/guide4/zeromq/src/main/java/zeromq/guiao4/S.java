package zeromq.guiao4;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.nio.ByteBuffer;

public class S {

    public static void main(String[] args) {

        try(ZContext context = new ZContext();
            ZMQ.Socket client_socket = context.createSocket(SocketType.REP);
            ZMQ.Socket wf_socket = context.createSocket(SocketType.PUSH)) {

            client_socket.bind("tcp://*:" + 12345);
            wf_socket.bind("tcp://*:" + 12346);

            new Thread(new Result(context)).start();

            while(true) {
                byte[] msg = client_socket.recv();
                String str_msg = new String(msg);
                wf_socket.send(str_msg);
            }
        }
    }

}

class Result extends Thread {

    ZContext context;

    Result(ZContext context) {
        this.context = context;
    }

   public void run() {

        try(ZMQ.Socket result = context.createSocket(SocketType.PULL)) {
            result.bind("tcp://*:" + 12349);
            while(true) {
                byte[] msg = result.recv();
                ByteBuffer wrapped = ByteBuffer.wrap(msg);
                int num = wrapped.getInt();
            }
        }
   }
}