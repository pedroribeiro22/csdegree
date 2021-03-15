package tf;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import spread.BasicMessageListener;
import spread.SpreadConnection;
import spread.SpreadException;
import spread.SpreadMessage;

public class Client {
   
    private static void main(String[] args) throws Exception {
       Random rand = new Random();
       final Map<Integer, Boolean> answeredRequests = new HashMap<Integer, Boolean>();
       int port = Integer.parseInt(args[0]);
       String myName = args[1]; 
       final SpreadConnection conn = Client.getConnection(port, myName);
       conn.add(new BasicMessageListener() {
            @Override
            public void messageReceived(SpreadMessage msg) {
                byte[] data = msg.getData();
                String message = new String(data, StandardCharsets.UTF_8);
                String[] messageBits = message.split(" ");
                int reqId = Integer.parseInt(messageBits[0]);
                answeredRequests.put(reqId, true);
                System.out.println("A resposta obtida para o pedido " + messageBits[0] + " foi: " + messageBits[1]);
            }
       });
       for(int i = 0; i < 10000; i++) {
           int requestType = rand.nextInt(2); 
           SpreadMessage msg = new SpreadMessage();
           answeredRequests.put(i, false);
           if(requestType == 0) {
                String message = i + " balance";
                msg.setData(message.getBytes(StandardCharsets.UTF_8));
           } else {
                int accountMovement = (int) (Math.round(50 * (-2.0 + rand.nextFloat() * 4.0)));
                String message = i + " movement " + accountMovement; 
                msg.setData(message.getBytes(StandardCharsets.UTF_8));
           }
           msg.setSafe();
           msg.addGroup("lab2");
           conn.multicast(msg);
       }
    }

    public static SpreadConnection getConnection(int port, String groupName) throws SpreadException, UnknownHostException {
        SpreadConnection conn = new SpreadConnection(); 
        conn.connect(InetAddress.getByName("localhost"), port, groupName, false, false);
        return conn;
     }
}
