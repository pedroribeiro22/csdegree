import BankImplementations.Bank;
import BankImplementations.BankNaive;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class OperationRequester {

    public static void main(String[] args) {

        int port = Integer.parseInt(args[2]);
        List<Integer> destinations = new ArrayList();
        ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
        NettyMessagingService ms = new NettyMessagingService("" + port, Address.from(port), new MessagingConfig());

        ms.registerHandler("balance_response", (address, content) -> {
        }, es);

        ms.registerHandler("movement_response", (address, content) -> {
        }, es);
        
        ms.start();

        Rand rand = new Random();
        for(int i = 0; i < 1000; i++) {
            int op = rand.nextInt(1);
            if(op == 1) {
                for(Integer destination : destinations) {
                sendMessage("balance", ms, "".getBytes(), destination);
                }
            } else {
                double amount = (Math.random()) * (1000);
                for(Integer destination : destinations) {
                    sendMessage("movement", ms, "" + amount + "".getBytes(), destination);
                }
            }
        }

    }

    private void sendMessage(final String subject, final NettyMessagingService ms, final byte[] message, final int destination) {
        ms.sendAsync(Address.from("localhost", destination), subject, message)
                .thenRun(() -> {
                }).exceptionally(t -> {
                    t.printStackTrace();
                    return null;
        });
    }
}