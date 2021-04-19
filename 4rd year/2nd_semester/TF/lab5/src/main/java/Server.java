import io.atomix.cluster.messaging.MessagingConfig;
import io.atomix.cluster.messaging.impl.NettyMessagingService;
import io.atomix.utils.net.Address;
import org.apache.commons.lang3.SerializationUtils;
import spread.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Server {

    public static void main(String[] args) throws UnknownHostException, SpreadException {

        State state = new State();
        int server_number = Integer.parseInt(args[0]);
        int client_port = 12345;
        final int port = 12347;
        final boolean[] leader = {false};
        final List<SpreadGroup> lookout = new ArrayList<>();
        final String[] name = {"#server" + server_number + "#alfa"};
        SpreadConnection connection = new SpreadConnection();
        connection.connect(InetAddress.getByName("localhost"), 4803, "server" + server_number, false, true);
        final NettyMessagingService[] ms = new NettyMessagingService[1];

        connection.add(new AdvancedMessageListener() {

            @Override
            public void regularMessageReceived(SpreadMessage spreadMessage) {
                System.out.println("Recebi");
                String fromWho = spreadMessage.getSender().toString();
                byte[] data = spreadMessage.getData();
                String msgData = new String(data, StandardCharsets.UTF_8);
                int request_id = Integer.parseInt(msgData);
                if (fromWho.equals(name[0])) {
                    System.out.println("Recebido");
                    ms[0].sendAsync(Address.from("localhost", client_port), "change_value_response", ("Request " + request_id + "was successfully performed").getBytes(StandardCharsets.UTF_8));
                }
            }

            @Override
            public void membershipMessageReceived(SpreadMessage spreadMessage) {
                MembershipInfo info = spreadMessage.getMembershipInfo();
                if (info.isCausedByLeave() || info.isCausedByDisconnect()) {
                    SpreadGroup left = info.getLeft();
                    if (lookout.size() == 1 && lookout.contains(left)) {
                        leader[0] = true;
                        ms[0] = initializeConnection(port, state, connection);
                    }
                    lookout.remove(left);
                }
                if (info.isCausedByJoin()) {
                    SpreadGroup entered = info.getJoined();
                    if (entered.toString().equals(name[0])) {
                        SpreadGroup[] curr = info.getMembers();
                        for (SpreadGroup member : curr) {
                            if (!member.toString().equals(name[0])) {
                                lookout.add(member);
                            }
                        }
                    }
                    if (lookout.size() == 0) {
                        leader[0] = true;
                        ms[0] = initializeConnection(port, state, connection);
                    }
                }
            }
        });

        SpreadGroup group = new SpreadGroup();
        group.join(connection, "lab5");
        while (true) ;

    }

    public static NettyMessagingService initializeConnection(int port, State state, SpreadConnection connection) {
        ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
        NettyMessagingService ms = new NettyMessagingService("" + port, Address.from(port), new MessagingConfig());

        ms.registerHandler("change_value", (address, content) -> {
            String messageData = new String(content, StandardCharsets.UTF_8);
            String[] bits = messageData.split(" ");
            int id = Integer.parseInt(bits[0]);
            int amount = Integer.parseInt(bits[1]);
            state.change_value(amount);
            byte[] new_state = SerializationUtils.serialize(state);
            SpreadMessage msg = new SpreadMessage();
            msg.setData((id + " " + Arrays.toString(new_state)).getBytes(StandardCharsets.UTF_8));
            msg.setSafe();
            msg.addGroup("lab5");
            try {
                connection.multicast(msg);
            } catch (SpreadException e) {
                e.printStackTrace();
            }
        }, es);

        ms.start();

        return ms;

    }
}
