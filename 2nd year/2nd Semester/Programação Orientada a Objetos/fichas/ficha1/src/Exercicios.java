import java.util.Date;

public class Exercicios {

    /* Exercício 2 */
    /* 1 */
    public double celsiusToFarenheit(double graus) {
        return(1.8 * graus + 32);
    }

    /* 2 */
    public int maximoNumeros(int a, int b) {
        return(a > b ? a : b);
    }

    /* 3 */
    public String criaDescricaoConta(String nome, double saldo) {
        return("Nome: " + saldo + "\n" + "Saldo: " + saldo);
    }

    /* 4 */
    public double eurosParaLibras(double valor, double taxaConversao) {
        return(valor * taxaConversao);
    }

    /* 5 */
    public double mean(int a, int b) {
        return((a + b) / 2);
    }

    /* 6 */
    public long factorial(int num) {
        int multiplier = num - 1;
        while(multiplier > 0) {
            num *= multiplier;
            multiplier--;
        }
        return (long)(multiplier);
    }

    /* 7 */
    public long tempoGasto() {
       Date before = new Date();
       int hours = before.getHours();
       int minutes = before.getMinutes();
       int seconds = before.getSeconds();
       this.factorial(5000);
       Date after = new Date();
       int afterHours = after.getHours();
       int afterMinutes = after.getMinutes();
       int afterSeconds = after.getSeconds();
       long delta = (afterHours * 60 * 60 + afterMinutes * 60 + afterSeconds)
                     -
               (hours * 60 * 60 + minutes * 60 + seconds);
       return delta;
    }
}

