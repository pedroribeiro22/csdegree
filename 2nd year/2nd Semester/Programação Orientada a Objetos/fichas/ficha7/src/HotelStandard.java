public class HotelStandard extends Hotel implements CartaoHotel {

    private boolean epocaAlta;
    private int pontos;

    public HotelStandard() {
        super();
        this.epocaAlta = false;
    }

    public HotelStandard(boolean epocaAlta) {
        super();
        this.epocaAlta = epocaAlta;
    }

    public HotelStandard(HotelStandard hs) {
        super(hs);
        this.epocaAlta = hs.isEpocaAlta();
        this.pontos = hs.getPontos();
    }

    public boolean isEpocaAlta() {
        return epocaAlta;
    }

    public void setEpocaAlta(boolean epocaAlta) {
        this.epocaAlta = epocaAlta;
    }


    public float getPreco() {
        float preco = super.getRoomPrice();
        if (epocaAlta == true) preco += 20;
        return preco;
    }

    public int getPontos() {
        return this.pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public float precoNoite() {
        return this.getRoomPrice();
    }

    public HotelStandard clone() {
        return new HotelStandard(this);
    }

}