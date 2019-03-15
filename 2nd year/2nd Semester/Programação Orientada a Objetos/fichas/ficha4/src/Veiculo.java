import java.time.LocalDate;

public class Veiculo {

    private static final int DESLIGADO = 0;
    private static final int LIGADO = 1;
    private static final double DEFAULT_REGENERATION = 0.75;
    private int status;
    private String marca;
    private String modelo;
    private int ano;
    private double velMedia;
    private double kms;
    private double consumo;
    private double kmsSinceReset;
    private double regenerationFactor;

    /**
     * Construtor não parametrizado
     */
    public Veiculo() {
        this.status = LIGADO;
        this.marca = "";
        this.modelo = "";
        this.ano = LocalDate.now().getYear();
        this.velMedia = 0;
        this.kms = 0;
        this.consumo = 0;
        this.kmsSinceReset = 0;
        this.regenerationFactor = DEFAULT_REGENERATION;
    }

    /**
     * Construtor parametrizado
     * @param marca Marca do veículo
     * @param modelo Modelo do veículo
     * @param ano Ano de construção do veículo
     * @param velMedia Velocidade média do veículo
     * @param kms Quilómetros percorridos pelo veículo
     * @param consumo Consumo média do veículo (/100km)
     * @param kmsSinceReset Quilómetros realizados desde o último `reset`
     * @param regenerationFactor Fator de regenaração de energia ao travar
     */
    public Veiculo(String marca,
                   String modelo,
                   int ano,
                   double velMedia,
                   double kms,
                   double consumo,
                   double kmsSinceReset,
                   double regenerationFactor) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.velMedia = velMedia;
        this.kms = kms;
        this.consumo = consumo;
        this.kmsSinceReset = kmsSinceReset;
        this.regenerationFactor = regenerationFactor;
    }

    /**
     * Método que permite obter o estado do veículo
     * @return Estado do veículo
     */
    public int getStatus() {
        return this.status;
    }

    /**
     * Método que permite obter a marca do veículo
     * @return Marca do veículo
     */
    public String getMarca() {
        return this.marca;
    }

    /**
     * Método que permite obter a modelo do veículo
     * @return Modelo do veículo
     */
    public String getModelo() {
        return this.modelo;
    }

    /**
     * Método que permite obter o ano de construção do veículo
     * @return Ano de construção do veículo
     */
    public int getAno() {
        return this.ano;
    }

    /**
     * Método que permite obter a velocidade média do veículo
     * @return Velocidade média do veículo
     */
    public double getVelMedia() {
        return this.velMedia;
    }

    /**
     * Método que permite obter os quilómetros feitos pelo veículo
     * @return Quilómetros feitos pelo veículo
     */
    public double getKms() {
        return this.kms;
    }

    /**
     * Método que permite obter o consumo do veículo (/100km)
     * @return Consumo do veículo
     */
    public double getConsumo() {
        return this.consumo;
    }

    /**
     * Méotodo que permite obter os quilómetros feitos pelo veículo desde o último `reset`
     * @return Quilómetros feitos pelo veículo desde o último `reset`
     */
    public double getKmsSinceReset() {
        return this.kmsSinceReset;
    }

    /**
     * Método que permite obter o fator de regeneração de energia do veículo aquando de uma travagem
     * @return Fator de regeneração de energia do veículo aquando de uma travagem
     */
    public double getRegenerationFactor() {
        return this.regenerationFactor;
    }

    /**
     * Método que permite definir o estado do veículo
     * @param status Estado a definir
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Método que permite definir a marca do veículo
     * @param marca Marca a definir
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Método que permite definir o modelo do veículo
     * @param modelo Modelo a definir
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Método que permite definir o ano de construção de um veículo
     * @param ano Ano de construção do veículo
     */
    public void setAno(int ano) {
        this.ano = ano;
    }

    /**
     * Método que permite definir a velocidade média do veículo
     * @param velMedia Velocidade média do veículo
     */
    public void setVelMedia(double velMedia) {
        this.velMedia = velMedia;
    }

    /**
     * Método que permite definir o número de quilómetros realizados pelo veículo
     * @param kms Número de quilómetros realizados pelo veículo
     */
    public void setKms(double kms) {
        this.kms = kms;
    }

    /**
     * Método que permite definir o consumo do veículo (/100km)
     * @param consumo Consumo do veículo
     */
    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    /**
     * Método que permite definir o número de quilómetros realizados pelo veículo desde o último `reset`
     * @param kmsSinceReset Quilómetros realizados pelo veículo desde o último `reset`
     */
    public void setKmsSinceReset(double kmsSinceReset) {
        this.kmsSinceReset = kmsSinceReset;
    }

    /**
     * Método que permite definir o fator de regeneração de energia aquando de uma travagem
     * @param regenerationFactor Fator de regeneração de energia aquando de uma travagem
     */
    public void setRegenerationFactor(double regenerationFactor) {
        this.regenerationFactor = regenerationFactor;
    }

    /**
     * Método que permite comparar 2 veículos
     * @param object Veículo a comparar
     * @return True caso sejam iguais ou False caso contrário
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Veiculo veiculo = (Veiculo) object;
        return  this.status == veiculo.status &&
                this.ano == veiculo.ano &&
                Double.compare(veiculo.velMedia, this.velMedia) == 0 &&
                Double.compare(veiculo.kms, this.kms) == 0 &&
                Double.compare(veiculo.consumo, this.consumo) == 0 &&
                Double.compare(veiculo.kmsSinceReset, this.kmsSinceReset) == 0 &&
                Double.compare(veiculo.regenerationFactor, this.regenerationFactor) == 0 &&
                marca.equals(veiculo.marca) &&
                modelo.equals(veiculo.modelo);
    }

    /**
     * Método que permite clonar um objeto do tipo `Veículo`
     * @return Obejto clonado
     */
    @Override
    public Veiculo clone() {
        Veiculo novo = new Veiculo();
        novo.status = this.getStatus();
        novo.marca = this.getMarca();
        novo.modelo = this.getModelo();
        novo.ano = this.getAno();
        novo.velMedia = this.getVelMedia();
        novo.kms = this.getKms();
        novo.consumo = this.getConsumo();
        novo.kmsSinceReset = this.getKmsSinceReset();
        novo.regenerationFactor = this.getRegenerationFactor();
        return novo;
    }

    /**
     * Método que liga o carro
     */
    public void ligaCarro() {
        this.setStatus(LIGADO);
    }

    /**
     * Método que desliga carro
     */
    public void desligaCarro() {
        this.setStatus(DESLIGADO);
    }

    /**
     * Método que permite atualizar os contadores de quilómetros do carro após um `reset`
     */
    public void resetUltimaViagem() {
        this.setKms(this.getKms() + this.getKmsSinceReset());
        this.setKmsSinceReset(0);
    }

    /**
     * Método que permite avançar o carro determinados metros a determinado a determinada velocidade
     * @param metros Número de metros a avançar
     * @param velocidade Velocidade a que o carro se movimenta
     */
    public void avancaCarro(double metros, double velocidade) {
        double currentKms = metros / 1000;
        this.setKms(this.getKms() + currentKms);
        this.setKmsSinceReset(this.getKmsSinceReset() + currentKms);
        /* Vou considerar uma coisa bastante estúpida, but doesn't really matter */
        /* Assumindo que temos uma velocidade média, podemos fazer a média dessa velocidade com a velocidade
        apresentada neste trecho deste percurso, ou seja: velMedia(no percurso) = arg(velocidade); velMedia(atualizada) =
        (velMedia + velMedia(no percurso)) / 2;
         */
        double newAvgVel = this.getVelMedia();
        newAvgVel = (newAvgVel + velocidade) / 2;
        this.setVelMedia(newAvgVel);
    }

    /**
     * Método que permite avançar o carro determinados metros (a travar)
     * @param metros Número de metros a avançar
     */
    public void travaCarro(double metros) {
        double atenuacaoConsumo = this.getRegenerationFactor() * metros;
        this.setConsumo(this.getConsumo() - atenuacaoConsumo);
        double currentKms = metros / 1000;
        this.setKms(this.getKms() + currentKms);
        this.setKmsSinceReset(this.getKmsSinceReset() + currentKms);
    }
}