import io.atomix.cluster.messaging.MessagingConfig;
import io.atomix.cluster.messaging.impl.NettyMessagingService;
import io.atomix.utils.net.Address;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Client {

    public static void main(String[] args) {

        List<Integer> destinations = new ArrayList<>();
        int number_of_banks = Integer.parseInt(args[0]);
        for(int i = 1; i <= number_of_banks; i++) {
            destinations.add(Integer.parseInt(args[i]));
        }
        int port = 1500;
        ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
        NettyMessagingService ms = new NettyMessagingService("" + port, Address.from(port), new MessagingConfig());

        ms.registerHandler("balance_response", (address, content) -> {
            String response = new String(content, StandardCharsets.UTF_8);
            System.out.println(response);
        }, es);

        ms.registerHandler("movement_response", (address, content) -> {
            String response = new String(content, StandardCharsets.UTF_8);
            System.out.println(response);
        }, es);
        
        ms.start();

        for(int i = 0; i < 1000; i++) {
            double amount = (Math.random()) * 2000 - 1000;
            for(Integer destination : destinations) {
                sendMessage("movement", ms, ("" + amount  + "").getBytes(StandardCharsets.UTF_8), destination);
                sendMessage("balance", ms, "".getBytes(StandardCharsets.UTF_8), destination);
            }
        }

        try {
            Thread.sleep(2000);
        } catch(Exception e) {
            e.printStackTrace();
        }

        for(Integer destination : destinations) {
            sendMessage("balance", ms, "".getBytes(StandardCharsets.UTF_8), destination);
        }
    }

    private static void sendMessage(final String subject, final NettyMessagingService ms, final byte[] message, final int destination) {
        ms.sendAsync(Address.from("localhost", destination), subject, message)
                .thenRun(() -> {
                }).exceptionally(t -> {
                    t.printStackTrace();
                    return null;
        });
    }
}