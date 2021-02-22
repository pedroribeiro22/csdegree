package model;

import model.LogInManager;
import spullara.nio.channels.FutureSocketChannel;

import java.util.ArrayList;
import java.util.List;

public class ApplicationContext {

    private List<FutureSocketChannel> connectedClients;
    private LogInManager logInManager;

    public ApplicationContext() {
        this.connectedClients = new ArrayList<>();
        this.logInManager = new LogInManager();
    }

    public List<FutureSocketChannel> getConnectedClients() {
        return this.connectedClients;
    }

    public LogInManager getLogInManager() {
        return this.logInManager;
    }

    public void addConnectedClient(FutureSocketChannel fsc) {
        this.connectedClients.add(fsc);
    }

}
