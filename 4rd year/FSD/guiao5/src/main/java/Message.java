import java.io.Serializable;
import java.util.List;

public class Message implements Serializable {

    private int id;
    private List<Integer> clocks;
    private String message;

    public Message(final int id, final List<Integer> clocks, final String message) {
        this.id = id;
        this.clocks = clocks;
        this.message = message;
    }

    public int getId() {
        return this.id;
    }

    public List<Integer> getClocks() {
        return this.clocks;
    }

    public String getMessage() {
        return this.message;
    }

}
