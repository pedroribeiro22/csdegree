import io.atomix.cluster.messaging.MessagingConfig;
import io.atomix.cluster.messaging.impl.NettyMessagingService;
import io.atomix.utils.net.Address;

import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Synchronous implements Runnable {

    private int port;
    private Map<Integer, Integer> leaders;

    public Synchronous(int i, Map<Integer, Integer> leaders) {
        this.port = 12345 + i;
        this.leaders = leaders;
        this.leaders.put(this.port, this.port);
    }

    public void run() {

        ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
        NettyMessagingService ms = new NettyMessagingService("nome" + this.port, Address.from(this.port), new MessagingConfig());

        ms.registerHandler("signal", (address, content) -> {
            int messager = new BigInteger(content).intValue();
            int currentLeader = leaders.get(this.port);
            if(messager > currentLeader) leaders.put(this.port, messager);
        }, es);

        ms.start();

        es.schedule(() -> {
            System.out.println("[" +  this.port + "] thinks [" + this.leaders.get(this.port) + "] is the leader");
        }, 5, TimeUnit.SECONDS);

       for(int curr_port : this.leaders.keySet()) {
           ms.sendAsync(Address.from("localhost", curr_port), "signal", BigInteger.valueOf(this.port).toByteArray())
                   .thenRun(() -> {
                        // System.out.println("Mensagem enviada a " + (curr_port));
                   }).exceptionally(t -> {
                        t.printStackTrace();
                        return null;
                   });
       }
    }
}
