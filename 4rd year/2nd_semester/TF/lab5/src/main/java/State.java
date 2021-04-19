import java.io.Serializable;

public class State implements Serializable {

    private int x = 0;

    public int value() {
        return this.x;
    }

    public void change_value(int amount) {
        this.x += amount;
    }
}
