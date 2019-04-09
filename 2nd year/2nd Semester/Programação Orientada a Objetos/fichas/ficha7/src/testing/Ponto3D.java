public class Ponto3D extends Ponto {

    private int z;

    /**
     * Construtor vazio
     */
    public Ponto3D() {
        super();
        this.z = 0;
    }

    /**
     * Construtor por parâmetros
     * @param x
     * @param y
     * @param z
     */
    public Ponto3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    public ponto3D(Ponto3D ponto) {
        super(ponto);
        this.z = ponto.getZ();
    }

    public int getZ() {
        return this.z;
    }

    @Override
    public boolean equals(Object o) {
       if(o == this) return true;
       if(o == null || o.getClass() != this.getClass()) return false;
       Ponto3D p = (Ponto3D) o;
       return super.equals(p) && p.getZ() == this.z;
    }

    @Override
    public String toString() {
        super.toString() + "z" + this.z;
    }

    public void movePonto(int x, int y, int z) {
        super.movePonto(x, y);
        this.z = z;
    }

    public Ponto3D clone() {
        return new Ponto3D(this);
    }

}