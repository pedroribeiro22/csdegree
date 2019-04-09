public class HotePremium extends Hotel {

    private float taxaLuxo;

    public HotelPremium() {
        super();
        this.taxaLuxo = 2;
        this.setRoomPrice(2 * super.getRoomPrice());
    }

    public HotelPremium(float taxaLuxo) {
        super();
        this.taxaLuxo = taxaLuxo;
        super.setRoomPrice(taxaLuxo * super.getRoomPrice());
    }

    public float getTaxaLuxo() {
        return taxaLuxo;
    }

    public void setTaxaLuxo(float taxaLuxo) {
        this.taxaLuxo = taxaLuxo;
    }

    public String toString() {
        return super.toString() + "taxa de luxo: " + this.getTaxaLuxo();
    }
}