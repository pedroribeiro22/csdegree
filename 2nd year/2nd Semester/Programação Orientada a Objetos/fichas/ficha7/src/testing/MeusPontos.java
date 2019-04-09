import java.util.List;
import java.util.ArrayList;

public class MeusPontos {
    private List<Ponto> pontos;

    public MeusPontos() {
        this.pontos = new ArrayList<>();
    }

    public void adicionaPonto(Ponto p) {
        this.pontos.add(p.clone());
    }

    public int getX() {
        return this.x;
    }

    public int somaX() {
        int sum = 0;
        for(Ponto p : pontos)
            sum += p.getX();
    }
    return sum;

}