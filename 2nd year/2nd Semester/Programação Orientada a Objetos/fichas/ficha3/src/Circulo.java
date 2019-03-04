import java.lang.Math;

public class Circulo {

    private double x;
    private double y;
    private double raio;

    public Circulo() {
        this.x = 0;
        this.y = 0;
        this.raio = 1;
    }


    // Retorna coordenada x
    public double getX() {
        return this.x;
    }

    // Retorna coordenada y
    public double getY() {
        return this.y;
    }

    // Retorna o raio
    public double getRaio() {
        return this.raio;
    }

    // Altera o valor de x
    public void setX(double x) {
        this.x = x;
    }

    // Altera o valor de x
    public void setY(double y) {
        this.y = y;
    }

    public Circulo(Circulo g) {
        this.x = g.getX();
        this.y = g.getY();
        this.raio = g.getRaio();
    }

    // Altera o valor do raio
    public void setRaio(double raio) {
        this.raio = raio;
    }

    // Altero o centro
    public void alteraCentro(double x, double y) {
        setX(x);
        setY(y);
    }

    // Calcula a área
    public double calculaArea() {
        double area;
        area = Math.PI * Math.pow(raio, 2);
        return area;
    }

    public double perimetro() {
        return Math.PI * raio * 2;
    }

    // Clonar um círculo
    public Circulo clone() {
        return new Circulo(this);
    }

    public String toString() {
        return "X = " + x + "; Y =" + y + "; RAIO = " + raio;
    }

    // Comparação de objetos
    public boolean equals(Object o) {

        if(o == this) return true;

        if((o == null) || (this.getClass() != o.getClass())) return false;

        Circulo c = (Circulo) o;
        if(c.getX() == this.getX() && c.getY() == this.getY() && c.getRaio() == this.getRaio())
            return true;

        return false;
    }
}
