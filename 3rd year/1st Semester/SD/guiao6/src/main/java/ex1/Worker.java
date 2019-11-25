package ex1;

import java.net.ServerSocket;

public class Worker implements Runnable {

    private ServerSocket s;

    public Worker(ServerSocket s) {
        this.s = s;
    }

    public void run() {
    }
}
