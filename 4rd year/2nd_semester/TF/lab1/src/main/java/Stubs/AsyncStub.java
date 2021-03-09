package Stubs;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import io.atomix.cluster.messaging.MessagingConfig;
import io.atomix.cluster.messaging.impl.NettyMessagingService;
import io.atomix.utils.net.Address;

public class AsyncStub {

    Integer port;
    List<Integer> destinations;
    ScheduledExecutorService es;
    NettyMessagingService ms;

    public AsyncStub(int port, String[] destinations) {
        this.port = port;
        for(int i = 0; i < destinations.length; i++) {
            Integer destination = Integer.parseInt(destinations[i]);
            this.destinations.add(destination);
        }
        this.es = Executors.newScheduledThreadPool(1);
        this.ms = new NettyMessagingService("financial_operations", Address.from(this.port), new MessagingConfig());
        this.ms.start();
    }

    public CompletableFuture<byte[]> sendAndReceiveAsync(int operation_id, String operation, int amount) {
        CompletableFuture<byte[]> res = new CompletableFuture<byte[]>();
        String customType = operation_id + " " + operation;
        byte[] message = (operation_id + " " + amount).getBytes();
        ms.registerHandler(customType, (address, content) -> {
            res.complete(content);
            ms.unregisterHandler(customType);
        }, es);
        for(Integer destination : destinations) {
            ms.sendAsync(Address.from("localhost", destination), operation, message);
        }
        return res;
    }
    
}
