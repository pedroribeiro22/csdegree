public class HotelPremium extends Hotel implements CartaoHotel{

    private float taxaLuxo;
    private int pontos;
    s
    public HotelPremium() {
        super();
        this.taxaLuxo = 2;
        this.setRoomPrice(2 * this.getRoomPrice());
    }

    public HotelPremium(float taxaLuxo) {
        super();
        this.taxaLuxo = taxaLuxo;
        super.setRoomPrice(taxaLuxo * this.getRoomPrice());
    }

    public float getTaxaLuxo() {
        return taxaLuxo;
    }

    public void setTaxaLuxo(float taxaLuxo) {
        this.taxaLuxo = taxaLuxo;
    }

    public int getPontos() {
        return this.pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public String toString() {
        return super.toString() + "taxa de luxo: " + this.getTaxaLuxo();
    }

    public float precoNoite() {
        return this.getRoomPrice();
    }
}