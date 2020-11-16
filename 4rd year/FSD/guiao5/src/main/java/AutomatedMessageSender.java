import io.atomix.cluster.messaging.MessagingConfig;
import io.atomix.cluster.messaging.impl.NettyMessagingService;
import io.atomix.utils.net.Address;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AutomatedMessageSender implements Runnable {

    private int port;
    private List<Integer> connectedPeers;

    public AutomatedMessageSender(final int port, final List<Integer> connectedPeers) {
        this.port = port;
        this.connectedPeers = connectedPeers;
    }

    public void run() {

        ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
        NettyMessagingService ms = new NettyMessagingService("automated man", Address.from(this.port), new MessagingConfig());

        ms.registerHandler("message", (address, content) -> {

        }, es);

        ms.start();

        for(Integer peer : this.connectedPeers) {
            String message = "Automated - this message has origin in Peer[" + peer + "]\n";
            ms.sendAsync(Address.from(peer), "message", message.getBytes())
                    .thenRun(() -> {}).exceptionally(t -> {
                t.printStackTrace();
                return null;
            });
            //sendMessage(ms, message.getBytes(), peer);
        }


    }

    private void sendMessage(final NettyMessagingService ms, final byte[] message, final int destination) {

        ms.sendAsync(Address.from(destination), "message", message)
                .thenRun(() -> {}).exceptionally(t -> {
                    t.printStackTrace();
                    return null;
        });

    }

}
