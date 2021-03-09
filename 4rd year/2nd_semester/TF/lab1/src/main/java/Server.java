import BankImplementations.Bank;
import BankImplementations.BankNaive;
import io.atomix.cluster.messaging.MessagingConfig;
import io.atomix.cluster.messaging.impl.NettyMessagingService;
import io.atomix.utils.net.Address;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Server {

    public static void main(String[] args) {

        int port = Integer.parseInt(args[0]);
        Bank banco = new BankNaive();

        ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
        NettyMessagingService ms = new NettyMessagingService("" + port, Address.from(port), new MessagingConfig());

        ms.registerHandler("balance", (address, content) -> {
            String requestIdentifier = new String(content, StandardCharsets.UTF_8).split(" ")[0];
            double balance = banco.balance();
            ms.sendAsync(address, requestIdentifier + "balance_response", BigInteger.valueOf((long) balance).toByteArray());
        }, es);

        ms.registerHandler("movement", (address, content) -> {
            String requestIdentifier = new String(content, StandardCharsets.UTF_8).split(" ")[0];
            double amount = Double.parseDouble(new String(content, StandardCharsets.UTF_8).split(" ")[1]);
            boolean done = banco.movement(amount);
            byte[] response;
            if(done) {
                response = "The movement was performed succesfuly".getBytes(StandardCharsets.UTF_8);
            } else {
                response = "The movement could not be performed".getBytes(StandardCharsets.UTF_8);
            }
            ms.sendAsync(address, requestIdentifier + "movement_response", response);
        }, es);

        ms.start();

        int[] counter = { 0 };
        es.scheduleAtFixedRate(() -> {
            System.out.println(counter[0] + ":Server " + port + " has " + banco.balance() + "€");
            counter[0]++;
        }, 0, 2, TimeUnit.SECONDS);
    }
}
