package Stubs;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledExecutorService;

import io.atomix.cluster.messaging.impl.NettyMessagingService;
import io.atomix.utils.net.Address;

public class AsyncStub {

    public static CompletableFuture<byte[]> balance(int operation_id, List<Integer> destinations, ScheduledExecutorService es, NettyMessagingService ms) {
        CompletableFuture<byte[]> res = new CompletableFuture<byte[]>();
        ms.registerHandler(operation_id + "balance_response", (address, content) -> {
            res.complete(content);
            ms.unregisterHandler(operation_id + "balance_response");
        }, es);
        for(Integer destination : destinations) {
            ms.sendAsync(Address.from("localhost", destination), "balance", ("" + operation_id + "").getBytes());
        }
        return res;
    }

    public static CompletableFuture<byte[]> movement(int operation_id, List<Integer> destinations, ScheduledExecutorService es, NettyMessagingService ms, double amount) {
        CompletableFuture<byte[]> res = new CompletableFuture<byte[]>();
        ms.registerHandler(operation_id + "movement_response", (address, content) -> {
            res.complete(content);
            ms.unregisterHandler(operation_id + "movement_response");
        }, es);
        for(Integer destination : destinations) {
            ms.sendAsync(Address.from("localhost", destination), "movement", (operation_id + " " + amount).getBytes());
        }
        return res; 
    }
    
}
