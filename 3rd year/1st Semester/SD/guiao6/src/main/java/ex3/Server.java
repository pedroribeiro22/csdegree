package Aula9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception {
        ServerSocket servsocket = new ServerSocket(12345);
        int i = 0;

        while(true){

            Socket cSock = servsocket.accept();

            Thread t = new Thread(new Worker(cSock,i++));
            t.start();

        }

    }
}
