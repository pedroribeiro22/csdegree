package exceptions;

public class InexistentAccount extends Exception {

    public InexistentAccount() {
        super("There isn't an account with the identifier provided.");
    }
}
