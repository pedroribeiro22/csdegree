package exceptions;

public class RegisteredAccount extends Exception {

    public RegisteredAccount() {
        super("This username is already associated with another account!");
    }
}
