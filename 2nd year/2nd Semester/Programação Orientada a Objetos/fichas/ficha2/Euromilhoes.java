import java.lang.Math;

public class Euromilhoes {

    public int[] generateNumbers() {
        int[] numeros = new int[5];
        for(int i = 0; i < 5; i++)
            numeros[i] = (int)(Math.random() * 50 + 1);
        return numeros;
    }

    public int[] generateStars() {
        int[] estrelas = new int[2];
        for(int i = 0; i < 2; i++)
            estrelas[i] = (int)(Math.random() * 9 + 1);
        return estrelas;
    }
}
