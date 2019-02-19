import java.util.LocalDate;
import java.util.Arrays;

public class Data {

    public void insereData(LocalDate data, LocalData[] arr) {
        int length = arr.length;
        LocalDate[] holder = new LocalDate[length + 1];
        System.arraycopy(data, 0, holder, length)
    }
}