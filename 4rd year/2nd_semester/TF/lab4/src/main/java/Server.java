import io.atomix.cluster.messaging.MessagingConfig;
import io.atomix.cluster.messaging.impl.NettyMessagingService;
import io.atomix.utils.net.Address;
import spread.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Server {

    public static void main(String[] args) throws UnknownHostException, SpreadException {
        final int server_number = Integer.parseInt(args[0]);
        final boolean[] leader = { false };
        final List<SpreadGroup> lookout = new ArrayList<>();
        final String[] name = { "#server" +  server_number + "#alfa"};
        SpreadConnection connection = new SpreadConnection();
        connection.connect(InetAddress.getByName("localhost"), 4803, "server" + server_number, false, true);
        final ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
        final NettyMessagingService ms = new NettyMessagingService("" + 12345, Address.from(12345), new MessagingConfig());
        connection.add(new AdvancedMessageListener() {

            @Override
            public void regularMessageReceived(SpreadMessage spreadMessage) {
                // nao interessa para ja
            }

            @Override
            public void membershipMessageReceived(SpreadMessage msg) {
                MembershipInfo info = msg.getMembershipInfo();
                if(info.isCausedByLeave() || info.isCausedByDisconnect()) {
                    SpreadGroup left = info.getLeft();
                    if (lookout.size() == 1 && lookout.contains(left)) {
                        leader[0] = true;
                        initializeConnection(12345);
                    }
                    lookout.remove(left);
                }
                if(info.isCausedByJoin()) {
                    SpreadGroup entered = info.getJoined();
                    if (entered.toString().equals(name[0])) {
                        SpreadGroup[] curr = info.getMembers();
                        for(SpreadGroup member : curr) {
                            if(!member.toString().equals(name[0])) {
                               lookout.add(member);
                            }
                        }
                    }
                    if(lookout.size() == 0) {
                        leader[0] = true;
                        initializeConnection(12345);
                    }
                }
                if(leader[0]) {
                    System.out.println("Leader!");
                } else {
                    System.out.println("Not Leader!");
                }
            }
        });
        SpreadGroup group = new SpreadGroup();
        group.join(connection, "lab4");
        while(true);
    }

    public static void initializeConnection(int port) {
        ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
        NettyMessagingService ms = new NettyMessagingService("" + port, Address.from(port), new MessagingConfig());

        ms.registerHandler("message", (address, content) -> {
            ms.sendAsync(address, "answer", "ola".getBytes(StandardCharsets.UTF_8));
        }, es);

        ms.start();

    }

}
