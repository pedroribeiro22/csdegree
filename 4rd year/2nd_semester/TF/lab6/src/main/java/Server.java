import spread.*;
import utils.ElectionCommittee;
import utils.SimpleMessage;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class Server {

    public static void main(String[] args) throws UnknownHostException, SpreadException {

        int total_members = 3;
        int server_id = Integer.parseInt(args[0]);
        final ElectionCommittee election_committee = new ElectionCommittee();
        final boolean[] am_i_the_leader = { false };
        int[] sequence_number = { 1000 };

        SpreadConnection connection = Server.initializeConnection(
                total_members,
                Integer.parseInt(args[0]),
                election_committee,
                am_i_the_leader,
                sequence_number);
        SpreadGroup group = new SpreadGroup();
        group.join(connection, "lab6");
        while(true);
    }

    public static SpreadConnection initializeConnection(
            final int total_members,
            final int server_number,
            final ElectionCommittee election_committee,
            final boolean[] am_i_the_leader,
            final int[] sequence_number
    ) throws UnknownHostException, SpreadException {
        SpreadConnection connection = new SpreadConnection();
        connection.connect(InetAddress.getByName("localhost"), 4802 + server_number, "server" + server_number, false, true);
        connection.add(new AdvancedMessageListener() {

            @Override
            public void regularMessageReceived(SpreadMessage spreadMessage) {
                SimpleMessage message = new SimpleMessage(spreadMessage.getData());

                if(message.isElection()) {
                    election_committee.processElement(spreadMessage.getSender(), message.value());
                    // If I won the election:
                    if(election_committee.isFinished() && election_committee.getLeader().equals(connection.getPrivateGroup().toString())) {
                        am_i_the_leader[0] = true;
                        System.out.println("Im the leader. My sequence number is: " + sequence_number[0]);
                    }
                } else if(message.isState()) {
                    // Receiving my own safe message means every number of the group has already received it also
                    if(am_i_the_leader[0]) {
                        // Answer to the client
                    } else {
                        sequence_number[0] = message.value();
                        System.out.println("Got new state: " + sequence_number[0]);
                    }
                }
            }

            @Override
            public void membershipMessageReceived(SpreadMessage spreadMessage) {
                MembershipInfo info = spreadMessage.getMembershipInfo();
                if(info.isTransition()) {
                    System.out.println(info.getMembers());
                   election_committee.startElection(info.getMembers());
                   int election_number = am_i_the_leader[0] ? Integer.MAX_VALUE : sequence_number[0];
                   SimpleMessage election_message = new SimpleMessage("election", election_number);
                   SpreadMessage spread_message = new SpreadMessage();
                   spread_message.setData(election_message.toBytes());
                   spread_message.setReliable();
                   spread_message.setSafe();
                   spread_message.addGroup("lab6");
                   try {
                       connection.multicast(spreadMessage);
                   } catch(SpreadException e) {
                       e.printStackTrace();
                   }
                   if(am_i_the_leader[0] && (info.getMembers().length < (total_members / 2))) {
                       am_i_the_leader[0] = false;
                   }
                }
            }
        });
        return connection;
    }
}
