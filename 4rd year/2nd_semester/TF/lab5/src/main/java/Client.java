import io.atomix.cluster.messaging.MessagingConfig;
import io.atomix.cluster.messaging.impl.NettyMessagingService;
import io.atomix.utils.net.Address;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Client {

    public static void main(String[] args) {

        int port = 12345;
        int primary_port = 12347;
        ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
        NettyMessagingService ms = new NettyMessagingService("" + port, Address.from(port), new MessagingConfig());

        ms.registerHandler("change_value_response", (address, content) -> {
            String data = new String(content, StandardCharsets.UTF_8);
            System.out.println(data);
        }, es);

        ms.start();

        for (int i = 0; i < 100; i++) {
            Random rand = new Random();
            int amount = rand.nextInt((100 - (-100)) + 1) - 100;
            CompletableFuture<byte[]> res = new CompletableFuture<>();
            ms.sendAsync(Address.from("localhost", primary_port), "change_value", ("" + amount + "").getBytes(StandardCharsets.UTF_8));
        }
    }


}
