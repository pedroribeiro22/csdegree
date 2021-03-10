import BankImplementations.Bank;
import BankImplementations.BankNaive;
import io.atomix.cluster.messaging.MessagingConfig;
import io.atomix.cluster.messaging.impl.NettyMessagingService;
import io.atomix.utils.net.Address;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Server {

    public static void main(String[] args) {

        int port = Integer.parseInt(args[0]);
        Bank banco = new BankNaive();
        int account_id = 0;

        ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
        NettyMessagingService ms = new NettyMessagingService("" + port, Address.from(port), new MessagingConfig());

        ms.registerHandler("balance", (address, content) -> {
            String requestIdentifier = new String(content, StandardCharsets.UTF_8).split(" ")[0];
            int balance = banco.balance(account_id);
            System.out.println(requestIdentifier + " requested a balance check");
            ms.sendAsync(address, requestIdentifier + " balance_response", ("" + balance).getBytes());
        }, es);

        ms.registerHandler("movement", (address, content) -> {
            String requestIdentifier = new String(content, StandardCharsets.UTF_8).split(" ")[0];
            int amount = Integer.parseInt(new String(content, StandardCharsets.UTF_8).split(" ")[1]);
            System.out.println(requestIdentifier + " request a movement of: " + amount + "€");
            boolean done = banco.movement(account_id, amount);
            byte[] response;
            if(done) {
                response = "The movement was performed succesfuly".getBytes(StandardCharsets.UTF_8);
            } else {
                response = "The movement could not be performed".getBytes(StandardCharsets.UTF_8);
            }
            ms.sendAsync(address, requestIdentifier + " movement_response", response);
        }, es);

        ms.start();

        int[] counter = { 0 };
        es.scheduleAtFixedRate(() -> {
            System.out.println(counter[0] + ":Server " + port + " has " + banco.balance(account_id) + "€");
            counter[0]++;
        }, 0, 2, TimeUnit.SECONDS);
    }
}
