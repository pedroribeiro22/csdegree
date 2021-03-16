package tf;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import spread.BasicMessageListener;
import spread.SpreadConnection;
import spread.SpreadException;
import spread.SpreadGroup;
import spread.SpreadMessage;
import tf.bank.BankNaive;

public class Server {

    public static void main(String[] args ) throws Exception {
        final BankNaive bank = new BankNaive(1);
        int port = Integer.parseInt(args[0]); 
        String myName = args[1];
        final SpreadConnection conn = Server.getConnection(port, myName);
        conn.add(new BasicMessageListener() {
            @Override
            public void messageReceived(SpreadMessage msg) {
               byte[] data = msg.getData(); 
               String message = new String(data, StandardCharsets.UTF_8);
               String[] messageBits = message.split(" ");
               SpreadMessage rep = new SpreadMessage();
               if(messageBits.length == 2) {
                   // Balance
                   int balance = bank.balance(0);
                   String reply = messageBits[0] + " " + balance;
                   rep.setData(reply.getBytes(StandardCharsets.UTF_8));
               } else {
                   // Movement
                   int account_movement = Integer.parseInt(messageBits[2]);
                   boolean mov = bank.movement(0, account_movement); 
                   String reply = messageBits[0] + " " + mov;
                   rep.setData(reply.getBytes(StandardCharsets.UTF_8));
               }
               rep.setReliable();
               rep.addGroup(msg.getSender());
               try {
                   conn.multicast(rep);
               }
               catch(SpreadException e) {
                   e.printStackTrace();
               }
            }
        });
        SpreadGroup group = new SpreadGroup();
        group.join(conn, "lab2");

        ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
        es.scheduleAtFixedRate(() -> {
           System.out.println("Current Balance: " + bank.balance(0));
        }, 0, 2, TimeUnit.SECONDS);

        while(true);
    }

    public static SpreadConnection getConnection(int port, String groupName) throws SpreadException, UnknownHostException {
       SpreadConnection conn = new SpreadConnection(); 
       conn.connect(InetAddress.getByName("localhost"), port, groupName, false, false);
       return conn;
    }
} 