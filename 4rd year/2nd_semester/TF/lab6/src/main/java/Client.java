import spread.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class Client {

    public static void main(String[] args) throws UnknownHostException, SpreadException {
        SpreadConnection connection = Client.initializeConnection("client");
        SpreadGroup group = new SpreadGroup();
        group.join(connection, "lab6");
        SpreadMessage message = new SpreadMessage();
        message.setData("hello world".getBytes(StandardCharsets.UTF_8));
        message.setSafe();
        message.addGroup("lab6");
        try {
            connection.multicast(message);
        } catch(SpreadException e) {
            e.printStackTrace();
        }
        while(true);
    }

    public static SpreadConnection initializeConnection(final String name_within_group) throws UnknownHostException, SpreadException {
        SpreadConnection connection = new SpreadConnection();
        connection.connect(InetAddress.getByName("localhost"), 4803, name_within_group, false, true);
        connection.add(new AdvancedMessageListener() {

            @Override
            public void regularMessageReceived(SpreadMessage spreadMessage) {

            }

            @Override
            public void membershipMessageReceived(SpreadMessage spreadMessage) {

            }
        });
        return connection;
    }
}
