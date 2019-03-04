import java.time.LocalDate;

public class Exercicio3 {

    /* a */
    public void insereData(LocalDate data, LocalDate[] lista, int occupied) {
        if(occupied < lista.length) lista[occupied++] = data;
    }

    /* b */
    public String arrayToString(LocalDate[] lista) {
        String output = "";
        for(int i = 0; i < lista.length; i++)
            output.concat(lista[i].toString());
        return output;
    }
}
