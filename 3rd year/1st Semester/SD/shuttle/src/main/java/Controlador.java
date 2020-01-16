public interface Controlador {

    public void requisita_viagem(int origem, int destino);

    public void espera(int destino);

    public void notifyPartida();

    public void notifyChegada();
}
