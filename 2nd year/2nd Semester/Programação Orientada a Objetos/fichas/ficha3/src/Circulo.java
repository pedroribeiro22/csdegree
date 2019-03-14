public class Circulo {

    private double x;
    private double y;
    private double raio;

    /**
     * Construtor não parametrizado
     */
    public Circulo() {
        this.x = 0;
        this.y = 0;
        this.raio = 0;
    }

    /**
     * Construtor parametrizado
     * @param x Coordenada `x` do centro do círculo
     * @param y Coordenada `y` do centro do círculo
     * @param raio Raio do círculo
     */
    public Circulos(double x, double y, double raio) {
        this.x = x;
        this.y = y;
        this.raio = raio;
    }

    /**
     * Método que permite obter a coordenada `x` do centro do círculo
     * @return Coordenada `x` do centro do círculo
     */
    public double getX() {
        return this.x;
    }

    /**
     * Método que permite obter a coordenada `x` do centro do círculo
     * @return Coordenada `x` do centro do círculo
     */
    public double getY() {
        return this.y;
    }

    /**
     * Método que permite obter o raio do círculo
     * @return Raio do círculo
     */
    public double getRaio() {
        return this.raio;
    }

    /**
     * Método que permite definir a coordenada `x` do centro do círculo
     * @param x Coordenada `x` do centro do círculo
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Método que permite definir a coordenada `y` do centro do círculo
     * @param x Coordenada `y` do centro do círculo
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Método que permite definir o raio do círculo
     * @param raio Raio do círculo
     */
    public void setRaio(double raio) {
        this.raio = raio;
    }

    /**
     * Método que permite alterar as coordenadas do centro do círculo
     * @param x Coordenada `x` do centro do círculo
     * @param y Coordenada `y` do centro do círculo
     */
    public void alteraCentro(double x, double y) {
        this.setX(x);
        this.setY(y);
    }

    /**
     * Método que calcula a área do círculo
     * @return Área do círculo
     */
    public double calculaArea() {
        return (Math.PI * Math.pow(this.getRaio(), 2));
    }

    /**
     * Método que calcula o perímetro do círculo
     * @return Perímetro do círculoss
     */
    public double calculaPerimetro() {
        return (2 * Math.PI * this.getRaio());
    }
}
