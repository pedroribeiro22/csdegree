import io.atomix.cluster.messaging.MessagingConfig;
import io.atomix.cluster.messaging.impl.NettyMessagingService;
import io.atomix.utils.net.Address;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Peer implements Runnable {

    private int port;
    private List<Integer> peers;

    public Peer(final int port, final List<Integer> peers) {
       this.port = port;
       this.peers = peers;
    }


    public void run() {

       ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
       NettyMessagingService ms = new NettyMessagingService("" + this.port, Address.from(this.port), new MessagingConfig());

       ms.registerHandler("message", (address, content) -> {
           System.out.println("Peer [" + address + "] sent: \"" + new String(content, StandardCharsets.UTF_8) + "\"\n");
           if(address.port() == 12348) {
              sendToPeers(ms, content);
           }
       }, es);

       ms.start();

       try {

            String message;
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while((message = in.readLine()) != null) {
              sendToPeers(ms, message.getBytes());
            }

       } catch(Exception e) {
           e.printStackTrace();
       }

    }

    private void sendMessage(final NettyMessagingService ms, final byte[] message, final int destination) {

        ms.sendAsync(Address.from("localhost", destination), "message", message)
                .thenRun(() -> {}).exceptionally(t -> {
                    t.printStackTrace();
                    return null;
        });
    }

    private void sendToPeers(final NettyMessagingService ms, final byte[] message) {

        for(Integer peer : this.peers)
            sendMessage(ms, message, peer);

    }

}

