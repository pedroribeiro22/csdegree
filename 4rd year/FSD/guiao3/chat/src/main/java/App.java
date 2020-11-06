import automation.Populator;
import model.ApplicationContext;
import spullara.nio.channels.FutureServerSocketChannel;
import spullara.nio.channels.FutureSocketChannel;
import utilities.Utilities;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;

public class App {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = new ApplicationContext();
        Populator pop = new Populator(20, context.getLogInManager());
        pop.populateAccounts();
        System.out.println(pop.dumpAccounts());
        FutureServerSocketChannel fsc = new FutureServerSocketChannel();
        fsc.bind(new InetSocketAddress(12345));

        CompletableFuture<FutureSocketChannel> sc = acceptClients(fsc, context);

        while(true) {
            Thread.sleep(100);
        }
    }

    private static void login(ByteBuffer buff, FutureSocketChannel sc, ApplicationContext context) {
        CompletableFuture<Integer> read = sc.read(buff);
        read.thenAccept(i -> {
            if(i == 0) {

            } else {
                buff.flip();
                String[] credentials = Utilities.stringSplitter(Utilities.byteBufferToString(buff));
                boolean loggedIn;
                if(credentials.length == 2) {
                    try {
                        sc.write(Utilities.stringToByteBuffer("Credentials -> username: " + credentials[0] + ", password: " + credentials[1]).duplicate());
                        loggedIn = context.getLogInManager().isLoginValid(credentials[0], credentials[1]);
                    } catch (Exception e) {
                        loggedIn = false;
                    }
                } else {
                    loggedIn = true;
                }
                if(!loggedIn) {
                    sc.write(Utilities.stringToByteBuffer("The login was not successful, please try again!\n"));
                    buff.clear();
                    login(buff, sc, context);
                } else {
                    buff.clear();
                    context.addConnectedClient(sc);
                    read(buff, sc, context);
                }
            }
        });
    }

    private static CompletableFuture<FutureSocketChannel> acceptClients(FutureServerSocketChannel fsc, ApplicationContext context) {
        ByteBuffer buff = ByteBuffer.allocate(1000);
        CompletableFuture<FutureSocketChannel> sc = fsc.accept();
        sc.thenAccept(s -> {
            login(buff, s, context);
            acceptClients(fsc, context);
        });
        return sc;
    }

    private static void read(ByteBuffer buff, FutureSocketChannel sc, ApplicationContext context) {
        CompletableFuture<Integer> read = sc.read(buff);
        read.thenAccept(i -> {
            if(i == 0) {
            } else {
                buff.flip();
                write(buff, context);
                read(buff, sc, context);
            }
        });
    }

    private static void write(ByteBuffer buff, ApplicationContext context) {
        for(FutureSocketChannel fsc : context.getConnectedClients()) {
            fsc.write(buff.duplicate());
        }
        buff.clear();
    }
}
