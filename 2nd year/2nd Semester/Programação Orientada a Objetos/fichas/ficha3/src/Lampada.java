import java.time.LocalDate;

public class Lampada {

    private int estado;
    private double consumo;
    private LocalDate turnedOn;

    public static final int DESLIGADO = 0;
    public static final int LIGADO = 1;
    public static final int ECO = 2;
    public static final int consumos[] = {0, 2000, 1000};

    public Lampada() {
        this.estado = DESLIGADO;
        this.turnedOn = null;
        this.consumo = 0;
    }

    public int getEstado() {
        return estado;
    }

    public double getConsumo() {
        return consumo;
    }

    public LocalDate getTurnedOn() {
        return turnedOn;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    public void setTurnedOn(LocalDate turnedOn) {
        this.turnedOn = turnedOn;
    }

    public void lampON() {
        this.setEstado(LIGADO);
        this.setConsumo(consumos[LIGADO]);
        this.setTurnedOn(LocalDate.now());
    }

    public void lampOFF() {
        this.setEstado(DESLIGADO);
        this.setConsumo(consumos[DESLIGADO]);
    }
}
