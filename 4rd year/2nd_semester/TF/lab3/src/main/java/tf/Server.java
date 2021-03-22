package tf;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import spread.AdvancedMessageListener;
import spread.MembershipInfo;
import spread.SpreadConnection;
import spread.SpreadException;
import spread.SpreadGroup;
import spread.SpreadMessage;
import tf.bank.BankNaive;

public class Server {

    public static void main(String[] args) throws Exception {
        final boolean[] initialized_state = { false };
        List<SpreadMessage> queuedMessages = new ArrayList<SpreadMessage>();
        final BankNaive bank = new BankNaive(1);
        int port = 4803;
        String serverNumber = "server" + Integer.parseInt(args[0]);
        final SpreadConnection conn = Server.getConnection(port, serverNumber);
        conn.add(new AdvancedMessageListener() {
            @Override
            public void regularMessageReceived(SpreadMessage msg) {
                processMessage(conn, initialized_state, msg, bank, queuedMessages);
            }

            @Override
            public void membershipMessageReceived(SpreadMessage msg) {
                MembershipInfo info = msg.getMembershipInfo();
                if (info.isRegularMembership() && info.isCausedByJoin()) {
                    // Há mais membros e eu estou ativo -> mando
                    if ((info.getMembers().length > 1 && initialized_state[0] == true)
                            || (info.getMembers().length == 1 && initialized_state[0] == false)) {
                        conveyState(conn, info.getJoined(), bank);
                    }
                }
            }
        });
        SpreadGroup group = new SpreadGroup();
        group.join(conn, "lab2");
        ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
        es.scheduleAtFixedRate(() -> {
            System.out.println("Current Balance: " + bank.balance(0));
        }, 0, 2, TimeUnit.SECONDS);

        while (true)
            ;
    }

    public static SpreadConnection getConnection(int port, String groupName)
            throws SpreadException, UnknownHostException {
        SpreadConnection conn = new SpreadConnection();
        conn.connect(InetAddress.getByName("localhost"), port, groupName, false, true);
        return conn;
    }

    public static void processMessage(SpreadConnection conn, boolean[] state, SpreadMessage msg, BankNaive bank,
            List<SpreadMessage> mq) {
        byte[] data = msg.getData();
        String message = new String(data, StandardCharsets.UTF_8);
        String[] messageBits = message.split(" ");
        if (messageBits[0].equals("state")) {
            int balance = Integer.parseInt(messageBits[1]);
            bank.setBalance(0, balance);
            state[0] = true;
            processQueuedMessages(conn, mq, bank);
        } else {
            if (state[0] == false) {
                mq.add(msg);
                return;
            } else {
                processableMessage(conn, msg, bank);
            }
        }
    }

    public static void processableMessage(SpreadConnection conn, SpreadMessage msg, BankNaive bank) {
        SpreadMessage rep = new SpreadMessage();
        byte[] data = msg.getData();
        String message = new String(data, StandardCharsets.UTF_8);
        String[] messageBits = message.split(" ");
        if (messageBits.length == 2 && messageBits[1].equals("balance")) {
            // Balance
            int balance = bank.balance(0);
            String reply = messageBits[0] + " " + balance;
            rep.setData(reply.getBytes(StandardCharsets.UTF_8));
        } else {
            // Movement
            int account_movement = Integer.parseInt(messageBits[2]);
            boolean mov = bank.movement(0, account_movement);
            String reply = messageBits[0] + " " + mov;
            rep.setData(reply.getBytes(StandardCharsets.UTF_8));
        }
        rep.setReliable();
        rep.addGroup(msg.getSender());
        try {
            conn.multicast(rep);
        } catch (SpreadException e) {
            e.printStackTrace();
        }
    }

    public static void processQueuedMessages(SpreadConnection conn, List<SpreadMessage> msgs, BankNaive bank) {
        for (SpreadMessage msg : msgs) {
            processableMessage(conn, msg, bank);
        }
    }

    public static void conveyState(SpreadConnection conn, SpreadGroup group, BankNaive bank) {
        SpreadMessage state = new SpreadMessage();
        String data = "state " + bank.balance(0);
        byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
        state.setData(dataBytes);
        state.setReliable();
        state.addGroup(group);
        try {
            conn.multicast(state);
        } catch (SpreadException e) {
            e.printStackTrace();
        }
    }
}