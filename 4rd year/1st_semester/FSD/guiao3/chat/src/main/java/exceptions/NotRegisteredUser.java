package exceptions;

public class NotRegisteredUser extends Exception {

    public NotRegisteredUser() {
        super("The username you entered is not associated with any registered user!");
    }
}
