public class HotelStandard extends Hotel {

    private boolean epocaAlta;

    public HotelStandard() {
        super();
        this.epocaAlta = false;
    }

    public HotelStandard(boolean epocaAlta) {
        this.epocaAlta = epocaAlta;
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

}