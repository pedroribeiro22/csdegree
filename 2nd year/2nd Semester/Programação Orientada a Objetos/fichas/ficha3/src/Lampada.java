import java.time.LocalDate;

public class Lampada {
    private int estado;
    private double consumo;
    private LocalDate turnedOn;

    // DEFINES
    public static final int DESLIGADO = 0;
    public static final int LIGADO = 1;
    public static final int ECO = 2;
    public static final int consumos[] = {0, 2000, 1000};

    // Cria uma nova lâmpada
    public Lampada() {
        this.estado = DESLIGADO;
        this.turnedOn = null;
        this.consumo = 0;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setConsumo(int estado) {
        this.consumo = consumos[estado];

    }

    public void setDate(LocalDate data) {
        this.turnedOn = data;
    }

    public int getEstado() {
        return this.estado;
    }

    public double getConsumo() {
        return this.consumo;
    }

    public LocalDate getUptime() {
        return this.turnedOn;
    }

    public void lampON() {
        this.setEstado(LIGADO);
        this.setConsumo(consumos[LIGADO]);
        this.setDate(LocalDate.now());
    }

    public void lampOFF() {
        this.setEstado(DESLIGADO);
        this.setConsumo(consumos[DESLIGADO]);
    }

    public double totalConsumo() {}
}
