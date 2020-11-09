import io.atomix.cluster.messaging.MessagingConfig;
import io.atomix.cluster.messaging.impl.NettyMessagingService;
import io.atomix.utils.net.Address;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Process implements Runnable {

    private int port;
    private Map<Integer, Integer> leaders;

    public Process(int i, Map<Integer, Integer> leaders) {
        this.port = 12345 + i;
        this.leaders = leaders;
    }

    public void run() {

        ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
        NettyMessagingService ms = new NettyMessagingService("nome" + this.port, Address.from(this.port), new MessagingConfig());

        ms.registerHandler("signal", (address, content) -> {
            int messager = ByteBuffer.wrap(content).getInt();
            int currentLeader = leaders.get(this.port);
            if(messager > currentLeader) leaders.put(this.port, messager);
            System.out.println("Hello " + new String(content) + " from " + address);
        }, es);

        ms.start();

        es.schedule(() -> {
            System.out.println("TIMEOUT!");
            System.out.println(this.port + "thinks" + this.leaders.get(this.port) + " is the leader");
        }, 5, TimeUnit.SECONDS);

       for(int i = 0; i < 10; i++) {
           int current = i;
           ms.sendAsync(Address.from("localhost", 12345 + i), "signal", BigInteger.valueOf(ms.address().port()).toByteArray())
                   .thenRun(() -> {
                        System.out.println("Mensagem enviada a " + (12345 + current));
                   }).exceptionally(t -> {
                        t.printStackTrace();
                        return null;
                   });
       }
    }
}
