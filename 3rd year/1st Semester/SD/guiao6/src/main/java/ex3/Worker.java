package Aula9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Worker implements Runnable {

    private Socket socket;
    private int id;


    public Worker(Socket s,int id ){
        this.socket = s;
        this.id = id;
    }

    public void run(){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream());

            String r;
            System.out.println("Client " + (id+1) + " connected");
            while((r=in.readLine()) != null){
                System.out.println("Processed " + r);
                pw.println("Client " + (id+1) + "| " + "Receiving " + r);
                pw.flush();
            }
            System.out.println("Client " + (id+1) + " disconnected");
            socket.shutdownInput();
            socket.shutdownOutput();
            socket.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
