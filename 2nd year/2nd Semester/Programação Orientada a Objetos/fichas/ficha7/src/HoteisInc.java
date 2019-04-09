import java.util.Map;

public class HoteisInc {

    private String nomeCadeia;
    /** Codigo hotel -> Hotel **/
    private Map<String, Hotel> hoteis;

    public String getNomeCadeia() {
        return nomeCadeia;
    }

    public void setNomeCadeia(String nomeCadeia) {
        this.nomeCadeia = nomeCadeia;
    }

    public Map<String, Hotel> getHoteis() {
        return hoteis;
    }

    public void setHoteis(Map<String, Hotel> hoteis) {
        this.hoteis = hoteis;
    }

    public boolean existeHotel(String cod) {
        return this.hoteis.containsKey(cod);
    }

    public int quantos() {
        return this.hoteis.size();
    }

    public int quantos(String localidade) {
        return this.hoteis.values().stream.filter(h -> h.getLocalidade().equals(loc)).count();
    }

    public Hotel getHotel(String cod) {
        return this.hoteis.get(cod).clone();
    }

    public void adiciona(Hotel t) {
       this.hoteis.put(t);
    }

    public List<Hotel> getHoteis() {
        List<Hotel> ret = new List<>();
        this.hoteis.values().stream.map(p -> ret.add(p.clone()));
        return ret;
    }

    public void adiciona(Set<Hotel> hs) {
        for(Hotel h : hs) {
            this.hoteis.put(h.getCodigo());
            this.hoteis.put(h.clone());
        }
    }
}