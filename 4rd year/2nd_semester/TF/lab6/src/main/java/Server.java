import spread.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Server {

    public static void main(String[] args) throws UnknownHostException, SpreadException {
        final int server_number = Integer.parseInt(args[0]);
        final int[] number_of_servers = {Integer.parseInt(args[1])};
        final int[] counter = { 0 };
        final int[] leader = { 0 };
        final boolean[] received = new boolean[number_of_servers[0]];
        final int[] counters = new int[number_of_servers[0]];
        for(int i = 0; i < number_of_servers[0]; i++)
           received[i] = false;
        final SpreadConnection[] connection = { new SpreadConnection() } ;
        connection[0].connect(InetAddress.getByName("localhost"), 4803, "server" + server_number, false, true);

        connection[0].add(new AdvancedMessageListener() {

            @Override
            public void regularMessageReceived(SpreadMessage spreadMessage) {
                byte[] data = spreadMessage.getData();
                String msg = new String(data, StandardCharsets.UTF_8);
                String[] bits = msg.split(" ");
                received[Integer.parseInt(bits[0])] = true;
                counters[Integer.parseInt(bits[0])] = Integer.parseInt(bits[1]);
            }

            @Override
            public void membershipMessageReceived(SpreadMessage spreadMessage) {
                counter[0]++;
                MembershipInfo info = spreadMessage.getMembershipInfo();
                if(info.isTransition()) {

                } else {
                    if(info.isCausedByLeave() || info.isCausedByDisconnect()) {
                        number_of_servers[0]--;
                    }
                    SpreadMessage msg = new SpreadMessage();
                    msg.setData((server_number + " " + counter).getBytes(StandardCharsets.UTF_8));
                    msg.setSafe();
                    msg.addGroup("lab6");
                    try {
                        connection[0].multicast(msg);
                    } catch(SpreadException e) {
                        e.printStackTrace();
                    }
                    while(!allTrue(received, number_of_servers[0]));
                    int new_leader = electedLeader(counters, number_of_servers[0]);
                    clearReceived(received, number_of_servers[0]);
                    leader[0] = new_leader;
                }
            }
        });

        SpreadGroup group = new SpreadGroup();
        group.join(connection[0], "lab6");

        ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
        es.scheduleAtFixedRate(() -> {
            System.out.println("Acknowledged leader: " + leader[0]);
        }, 0, 2, TimeUnit.SECONDS);

        while(true);
    }

    public static boolean allTrue(final boolean[] array, int size) {
        for(int i = 0; i < size; i++) {
            if(! array[i]) return false;
        }
        return true;
    }

    public static int electedLeader(final int[] counters, int size) {
        int max = counters[0];
        int leader = 0;
        for(int i = 1; i < size; i++) {
            if(counters[i] > max) {
                max = counters[i];
                leader = i;
            }
        }
        return leader;
    }

    public static void clearReceived(final boolean[] received, final int size) {
        for(int i = 0; i < size; i++) received[i] = false;
    }
}
