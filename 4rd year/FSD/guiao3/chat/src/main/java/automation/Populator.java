package automation;

import exceptions.RegisteredAccount;
import model.LogInManager;

import java.security.NoSuchAlgorithmException;

public class Populator {

    private int clients_count;
    private LogInManager logInManager;

    public Populator(int clients_count, LogInManager logInManager) {
        this.clients_count = clients_count;
        this.logInManager = logInManager;
    }

    public void populateAccounts() throws RegisteredAccount, NoSuchAlgorithmException {
        for(int i = 0; i < this.clients_count; i++) {
            String usernamePassword = "client" + i;
            this.logInManager.createAccount(usernamePassword, usernamePassword);
        }
    }

    public String dumpAccounts() {
        StringBuilder sb = new StringBuilder("");
        for(String key : this.logInManager.getAccounts().keySet()) {
           String hashedPassword = this.logInManager.getAccounts().get(key);
           sb.append("Username: " + key +", password: " + hashedPassword + " \n");
        }
        sb.append("\n");
        return sb.toString();
    }

}
