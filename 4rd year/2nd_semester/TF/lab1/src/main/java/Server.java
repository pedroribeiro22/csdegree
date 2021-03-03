import BankImplementations.Bank;
import BankImplementations.BankNaive;
import io.atomix.cluster.messaging.MessagingConfig;
import io.atomix.cluster.messaging.impl.NettyMessagingService;
import io.atomix.utils.net.Address;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Server {

    public static void main(@org.jetbrains.annotations.NotNull String[] args) {

        int port = Integer.parseInt(args[0]);
        Bank banco = new BankNaive();

        ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
        NettyMessagingService ms = new NettyMessagingService("" + port, Address.from(port), new MessagingConfig());

        ms.registerHandler("balance", (address, content) -> {
            System.out.println("A balance operation was requested");
            double balance = banco.balance();
            int target_port = address.port();
            sendMessage("balance_response", ms, ("" + balance + "").getBytes(StandardCharsets.UTF_8), target_port);
        }, es);

        ms.registerHandler("movement", (address, content) -> {
            int target_port = address.port();
            String message = new String(content, StandardCharsets.UTF_8);
            double value = Double.parseDouble(message);
            System.out.println("A movement operation was requested | Value: " + value);
            boolean done = banco.movement(value);
            byte[] response;
            if(done) {
                response = "The movement was performed succesfuly".getBytes(StandardCharsets.UTF_8);
            } else {
                response = "The movement could not be performed".getBytes(StandardCharsets.UTF_8);
            }
            sendMessage("movement_response", ms, response, target_port);
        }, es);

        ms.start();
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
