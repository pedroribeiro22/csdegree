package exceptions;

public class IncorrectCredentials extends Exception {

    public IncorrectCredentials() {
        super("The user credentials you typed in are incorrect!");
    }
}
