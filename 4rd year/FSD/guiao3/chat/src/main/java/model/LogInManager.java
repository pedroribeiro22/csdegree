package model;

import exceptions.IncorrectCredentials;
import exceptions.NotRegisteredUser;
import exceptions.RegisteredAccount;
import utilities.Utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class LogInManager {

    private static String hashPassword(String input) throws NoSuchAlgorithmException {
       MessageDigest digester = MessageDigest.getInstance("SHA-256");
       digester.update(input.getBytes());
       return new String(digester.digest());
    }

    private HashMap<String, String> credentials;

    public LogInManager() {
        this.credentials = new HashMap<>();
    }

    public Map<String, String> getAccounts() {
        return this.credentials;
    }

    public void createAccount(String username, String password) throws RegisteredAccount, NoSuchAlgorithmException {
        if(!this.credentials.containsKey(username)) {
            this.credentials.put(username, LogInManager.hashPassword(password));
        } else {
            throw new RegisteredAccount();
        }
    }

    public boolean isLoginValid(String username, String password) throws NotRegisteredUser, NoSuchAlgorithmException {
        boolean r;
        if(this.credentials.containsKey(username)) {
            String newLineStripped = Utilities.removeNewLine(password);
            r = LogInManager.hashPassword(newLineStripped).equals(this.credentials.get(username));
        } else {
            throw new NotRegisteredUser();
        }
        return r;
    }

    public void editPassword(String username, String currentPassword, String newPassword) throws NotRegisteredUser, IncorrectCredentials, NoSuchAlgorithmException {
        boolean r = isLoginValid(username, LogInManager.hashPassword(currentPassword));
        if(r) {
            this.credentials.put(username, newPassword);
        } else {
            throw new IncorrectCredentials();
        }
    }

}
