import java.time.LocalDate;

public class Lampada {

    private int estado;
    private double totalConsumo;
    private double atualConsumo;
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
        this.totalConsumo = 0;
        this.atualConsumo = 0;
        this.turnedOn = null;
    }

    /**
     * Construtor com o parâmetro `estado`
     * @param estado Estado a iniciar na criação da lâmpada
     */
    public Lampada(int estado) {
        this.estado = estado;
        this.totalConsumo = 0;
        this.atualConsumo = 0;
        if(estado != 0)
            this.turnedOn = LocalDate.now();
        else this.turnedOn = null;
    }

    /**
     * Construtor com o parâmetro `consumo`
     * @param consumo Consumo a iniciar na criação da lâmpada
     */
    public Lampada(double totalConsumo) {
        this.estado = DESLIGADO;
        this.totalConsumo = totalConsumo;
        this.atualConsumo = 0;
        this.turnedOn = null;
    }


    /**
     * Construtor com o parâmetro `instante em que se ligou a lâmpada`
     * @param turnedOn Instante em que a lâmpada foi ligada
     */
    public Lampada(LocalDate turnedOn) {
        this.estado = DESLIGADO;
        this.totalConsumo = 0;
        this.atualConsumo = 0;
        this.turnedOn = turnedOn;
    }

    /**
     * Método que permite obter o estado da lâmpada
     * @return Estado da lâmpada
     */
    public int getEstado() {
        return this.estado;
    }

    /**
     * Método que permite obter o consumo da lâmpada
     * @return Consumo da lâmpada
     */
    public double getTotalConsumo() {
        return this.totalConsumo;
    }

    /**
     * Método que permite obter o consumo atual da lâmpada
     * @return Consumo atual da lâmpada
     */
    public double getAtualConsumo() {
        return this.atualConsumo;
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
    public void setTotalConsumo(double totalConsumo) {
        this.totalConsumo = totalConsumo;
    }

    /**
     * Método que permite definir o consumo atual da lâmpada
     * @param atualConsumo Consumo atual
     */
    public void setAtualConsumo(double atualConsumo) {this.atualConsumo = atualConsumo;}

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
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Lampada lampada = (Lampada) object;
        return  estado == lampada.estado &&
                Double.compare(lampada.totalConsumo, this.totalConsumo) == 0 &&
                Double.compare(lampada.atualConsumo, this.atualConsumo) == 0 &&
                turnedOn.equals(lampada.turnedOn);
    }

    /**
     * Método que permite imprimir o objeto lâmpada
     * @return String que demonstra o objeto
     */
    @Override
    public String toString() {
        return "Lampada{" +
                "estado=" + this.estado +
                ", totalConsumo=" + this.totalConsumo +
                ", atualConsumo=" + this.atualConsumo +
                ", turnedOn=" + turnedOn +
                '}';
    }

    /**
     * Método que permite fazer `reset` ao contador dd consumo atual
     */
    public void reset() {
        this.atualConsumo = 0;
    }
    /**
     * Método que permite alterar o estado da lâmpada para `LIGADO`
     */
    public void lampON() {
        this.setEstado(LIGADO);
    }

    /**
     * Método que permite alterar o estado da lâmpada para `DESLIGADO`
     */
    public void lampOFF() {
        this.setEstado(DESLIGADO);
    }

    /**
     * Método que permite alterar o estado da lâmpada para `ECO`
     */
    public void lampECO() {
        this.setEstado(ECO);
    }

    /**
     * Método que permite obter o consumo total de uma lâmpada
     * @return Consumo total da lâmpada
     */
    public double totalConsumo() {
        return this.getTotalConsumo();
    }

    /**
     * Método que permite obter o consumo de uma lâmpada (após o último reset)
     * @return O consumo da uma lâmpada (após o último reset)
     */
    public double periodoConsumo() {
        return this.getAtualConsumo();
    }
}