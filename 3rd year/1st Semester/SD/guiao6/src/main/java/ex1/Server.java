package ex1;

import view.Terminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static ServerSocket socket;

    public static void main(String[] args) throws Exception {

        Server.welcome();

        try {
            Server.socket = new ServerSocket(Connection.port);
            Terminal.info("Server on port " + Connection.port);

        } catch(IOException e) {
            e.printStackTrace();
        }

        int i = 1;
        while(true) {
            try {
                Terminal.info("Waiting for connection...");
                Socket clientServer = Server.socket.accept();
                Terminal.info("Connection " + i + " established!");
                new Thread(new Connection(clientServer, i)).start();

            } catch(IOException e) {
                e.printStackTrace();
            }
            i++;
        }

    }


    public static void welcome() {
        Terminal.clear();
        System.out.println("> Welcome to the server\n");
    }
}
