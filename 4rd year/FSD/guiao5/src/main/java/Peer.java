import io.atomix.cluster.messaging.MessagingConfig;
import io.atomix.cluster.messaging.impl.NettyMessagingService;
import io.atomix.utils.net.Address;
import org.apache.commons.lang3.SerializationUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Peer implements Runnable {

    private int id;
    private int port;
    private List<Integer> peers;
    private List<Integer> clocks;
    private List<Message> pending;

    public Peer(final int id, final int port, final List<Integer> peers, final int total_peers) {
        this.id = 0;
        this.port = port;
        this.peers = peers;
        this.clocks = new ArrayList<>();
        for(int i = 0; i < total_peers; i++) {
            this.clocks.add(0);
        }
        this.pending = new ArrayList<>();
    }


    public void run() {

       ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
       NettyMessagingService ms = new NettyMessagingService("" + this.port, Address.from(this.port), new MessagingConfig());

       ms.registerHandler("message", (address, content) -> {
           Message m = SerializationUtils.deserialize(content);
           System.out.println(m.getMessage());
           if(check_message(m)) {
               System.out.println("Peer [" + address + "] sent: \"" + m.getMessage() + "\"\n");
               update_clock(m);
               retry();
           } else {
               this.pending.add(m);
           }
       }, es);

       ms.start();

       try {

            /**
            String message;
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while((message = in.readLine()) != null) {
              sendToPeers(ms, message);
            }
             **/

            int i = 0;
            while(i < 1) {
               System.out.println("Sent message");
               String r = "Mensagem default";
               int curr = this.clocks.get(this.id);
               this.clocks.set(this.id, curr + 1);
               sendToPeers(ms, r);
               i++;
            }

       } catch(Exception e) {
           e.printStackTrace();
       }

    }

    public void retry() {

        for(Message m : this.pending) {
            boolean r = check_message(m);
            if(r) {
                update_clock(m);
                System.out.println("Message received: \"" + m.getMessage() + "\"");
                this.pending.remove(m);
            }
        }
    }

    private void update_clock(Message m) {
        List<Integer> received_clock = m.getClocks();
        for(int i = 0; i < this.clocks.size(); i++) {
            int received = received_clock.get(i);
            int local = this.clocks.get(i);
            int chosen = Math.max(received, local);
            this.clocks.set(i, chosen);
        }
    }

    private boolean check_message(Message m) {
        List<Integer> received_clocks = m.getClocks();
        int i = m.getId();
        if(this.clocks.get(i) + 1 == received_clocks.get(i)) {
            for(int j = 0; j < this.clocks.size(); j++) {
                if((this.clocks.get(j) > received_clocks.get(j)) && (i != j))
                    return false;
            }
            return true;
        } else {
            return false;
        }
    }

    private void sendMessage(final NettyMessagingService ms, final byte[] message, final int destination) {

        ms.sendAsync(Address.from("localhost", destination), "message", message)
                .thenRun(() -> {}).exceptionally(t -> {
                    t.printStackTrace();
                    return null;
        });
    }

    private void sendToPeers(final NettyMessagingService ms, final String text) {

        for(Integer peer : this.peers) {
            int curr_clock = this.clocks.get(this.id);
            this.clocks.set(this.id, curr_clock + 1);
            Message message = new Message(this.id, this.clocks, text);
            sendMessage(ms, SerializationUtils.serialize(message), peer);
        }

    }

}

