import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static void main(String[] args) {

        List<Socket> connectedClients = new ArrayList<Socket>();

        try {

            // Criação do server na porta dada como argumento
            ServerSocket s = new ServerSocket(12345);

            // Aceitação contínua de conexões
            while(true) {

                Socket sock = s.accept();

                // Adds the new client to the list of connected clients
                connectedClients.add(sock);

                BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));

                String message;

                while((message = in.readLine()) != null) {

                    // Sending the received message to every connected  client
                    // Includes the client that just sent it, we might want to avoid it
                    for(Socket client : connectedClients) {

                        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                        out.println(message);

                    }
                }
            }

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
