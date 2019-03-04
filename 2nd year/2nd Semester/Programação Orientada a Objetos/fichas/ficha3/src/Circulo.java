public class Circulo {

    private double x;
    private double y;
    private double raio;

    public Circulo() {
        this.x = 0;
        this.y = 0;
        this.raio = 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRaio() {
        return raio;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setRaio(double raio) {
        this.raio = raio;
    }

    public Circulo(double x, double y, double raio) {
        this.x = x;
        this.y = y;
        this.raio = raio;
    }

    public void alteraCentro(double x, double y) {
        this.setX(x);
        this.setY(y);
    }

    public double calculaArea() {
        return(Math.PI * Math.pow(this.getRaio(), 2));
    }

    public double calculaPerimetro() {
        return(2 * Math.PI * this.getRaio());
    }

    public boolean equals(Object o) {
        if(o == this) return true;
        if((o == null) || o.getClass() != this.getClass()) return false;
        Circulo c = (Circulo) o;
        return (this.x == c.getX());
    }

    public String toString() {
        return "Centro: " + "(" + this.x + " ," + this.y + ")" + "\n Raio: " + this.raio;
    }

    public Circulo clone() {
        return new Circulo(this.getX(), this.getY(), this.getRaio());
    }
}
