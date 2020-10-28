import java.util.ArrayList;
import java.util.LinkedList;

public class ContactList {
    private LinkedList<Contact> contacts;

    public ContactList() {
        contacts = new LinkedList<>();
    }

    public int getNumberOfContacts() {
        return contacts.size();
    }

    public Contact getContactByName(String name) {
        for (Contact c : contacts)
            if (c.getName() == name)
                return c;
        return null;
    }
}
