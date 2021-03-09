import io.atomix.cluster.messaging.MessagingConfig;
import io.atomix.cluster.messaging.impl.NettyMessagingService;
import io.atomix.utils.net.Address;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import Stubs.AsyncStub;

public class MessageSender {

    private int port;
    private List<Integer> servers = new ArrayList<Integer>();
    private ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
    private NettyMessagingService ms;


    public MessageSender(String[] args, int servers, int port) {
        this.port = port;
        for(int i = 1; i < servers; i++) {
            this.servers.add(Integer.parseInt(args[i]));
        }
        this.ms = new NettyMessagingService("" + port, Address.from(this.port), new MessagingConfig());
        this.ms.start();
    }

    public double balance(int operation_id) {
        CompletableFuture<byte[]> res = AsyncStub.balance(operation_id, this.servers, es, ms);
        byte[] byteContent = res.join();
        return Double.parseDouble(new String(byteContent, StandardCharsets.UTF_8));
    }

    public boolean movement(int operation_id, double value) {
        CompletableFuture<byte[]> res = AsyncStub.movement(operation_id, this.servers, es, ms, value);
        byte[] byteContent = res.join();
        return Boolean.parseBoolean(new String(byteContent, StandardCharsets.UTF_8));
    }

}