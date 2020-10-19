import javax.sound.midi.SysexMessage;
import java.io.*;
import java.net.Socket;
import java.util.List;

public class Session implements Runnable {

    private int id;
    private Socket socket;
    private BufferedReader in;
    private List<Socket> connectedClients;

    public Session(final int id, final Socket socket, final List<Socket> connectedClients) throws IOException {
        this.id = id;
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.connectedClients = connectedClients;
    }

    public void run(){

        try {

            while (true) {

                String message;

                while ((message = this.in.readLine()) != null) {

                   System.out.println(message);

                   // Send the message to every connected client
                   for(int i = 0; i < this.connectedClients.size(); i++) {

                       PrintWriter out = new PrintWriter(new OutputStreamWriter(this.connectedClients.get(i).getOutputStream()), true);
                       out.println("Yo Biatch: " + message);

                   }


                }
            }

        } catch(IOException e) {

            e.printStackTrace();

        }
    }
}
