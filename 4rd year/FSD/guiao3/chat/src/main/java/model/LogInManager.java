package model;

import exceptions.IncorrectCredentials;
import exceptions.NotRegisteredUser;
import exceptions.RegisteredAccount;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class LogInManager {

    private static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digester = MessageDigest.getInstance("SHA-256");
        digester.update(password.getBytes());
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
            this.credentials.put(username, hashPassword(password));
        } else {
            throw new RegisteredAccount();
        }
    }

    public boolean isLoginValid(String username, String password) throws NotRegisteredUser, NoSuchAlgorithmException {
        boolean r = false;
        if(this.credentials.containsKey(username)) {
            System.out.println("Password: " + password);
            String hashedPassword = hashPassword(password);
            String storedHash = this.credentials.get(username);
            System.out.println("Stored: " + storedHash);
            System.out.println("Provided: " + hashPassword(password));
            r = storedHash.equals(hashedPassword);
        } else {
            throw new NotRegisteredUser();
        }
        return r;
    }

    public void editPassword(String username, String currentPassword, String newPassword) throws NotRegisteredUser, IncorrectCredentials, NoSuchAlgorithmException {
        String hashedPassword = LogInManager.hashPassword(currentPassword);
        boolean r = isLoginValid(username, hashedPassword);
        if(r) {
            String newHashedPassword = LogInManager.hashPassword(newPassword);
            this.credentials.put(username, newHashedPassword);
        } else {
            throw new IncorrectCredentials();
        }
    }

}
