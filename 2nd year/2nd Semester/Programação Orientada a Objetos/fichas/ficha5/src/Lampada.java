import java.time.LocalDate;

public class Lampada {

    private int estado;
    private double consumo;
    private LocalDate turnedOn;

    private static final int DESLIGADO = 0;
    private static final int LIGADO = 1;
    private static final int ECO = 2;
    private static final int consumos[] = {0, 2000, 1000};

    /**
     * Construtor vazio
     */
    public Lampada() {
        this.estado = DESLIGADO;
        this.consumo = 0;
        this.turnedOn = null;
    }

    /**
     * Construtor com o parâmetro `estado`
     * @param estado Estado a iniciar na criação da lâmpada
     */
    public Lampada(int estado) {
        this.estado = estado;
        this.consumo = 0;
        if(estado)
            this.turnedOn = LocalDateTime.now();
        else this.turnedOn = null;
    }

    /**
     * Construtor com o parâmetro `cosumo`
     * @param consumo Consumo a iniciar na criação da lâmpada
     */
    public Lampada(double consumo) {
        this.estado = DESLIGADO;
        this.consumo = consumo;
        this.turnedOn = null;
    }

    /**
     * Construtor com o parâmetro `instante em que se ligou a lâmpada`
     * @param turnedOn Instante em que a lâmpada foi ligada
     */
    public Lampada(LocalDate turnedOn) {
        this.estado = DESLIGADO;
        this.consumo = 0;
        this.turnedOn = turnedOn;
    }

    /**
     * Método que permite obter o estado da lâmpada
     * @return Estado da lâmpada
     */
    public int getEstado() {
        this.estado;
    }

    /**
     * Método que permite obter o consumo da lâmpada
     * @return Consumo da lâmpada
     */
    public double getConsumo() {
        return this.consumo;
    }

    /**
     * Método que permite obter o instante em que a lâmpada foi ligada
     * @return Instante em que a lâmpada foi ligada
     */
    public LocalDate getTurnedOn() {
        return this.turnedOn;
    }

    /**
     * Método que permite definir o estado da lâmpada
     * @param estado Estado a definir
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * Método que permite definir o consumo da lâmpada
     * @param consumo Consumo da lâmpada
     */
    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    /**
     * Método que permite definir o instante em que uma lâmpada foi ligada
     * @param turnedOn Instante em que a lãmpada foi ligada
     */
    public void setTurnedOn(LocalDate turnedOn) {
        this.turnedOn = turnedOn;
    }

    /**
     * Método que permite comparar dois objetos (lâmpadas)
     * @param object Objeto a comparar
     * @return True caso sejam iguais ou False caso contrário
     */
    @override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Lampada lampada = (Lampada) object;
        return  estado == lampada.estado &&
                Double.compare(lampada.consumo, consumo) == 0 &&
                turnedOn.equals(lampada.turnedOn);
    }

    /**
     * Método que permite imprimir o objeto lâmpada
     * @return String que demonstra o objeto
     */
    @override
    public String toString() {
        return "Lampada{" +
                "estado=" + estado +
                ", consumo=" + consumo +
                ", turnedOn=" + turnedOn +
                '}';
    }
}