import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;
import java.uitl.Iterator;

public class HoteisInc {

    /**
     * Variáveis de instância
     */
    private String nomeCadeia;
    private TreeSet<Hotel> hoteis;

    private static Map<String, Comparator<Hotel>> comparators;

    static {
        comparators = new HashMap<>();
        comparators.put("numQuartos", new ComparatorNumQuartos());
    }

    public static getComparator(String nome) {
        return HoteisInc.comparators.get(nome);
    }
    /**
     * Permite obter o nome da cadeia de hoteis
     * @return String com o nome da cadeia de hóteis
     */
    public String getNomeCadeia() {
        return nomeCadeia;
    }

    /**
     * Permite definir o nome da cadeia de hóteis
     * @param nomeCadeia Nome da cadeia de hóteis
     */
    public void setNomeCadeia(String nomeCadeia) {
        this.nomeCadeia = nomeCadeia;
    }

    /**
     * Permite obter o Map entre chaves e hóteis
     * (Don't really know what this woulb de useful for)
     * @return Correspondência entre códigos e hóteis
     */
    public Set<Hotel> getHoteis() {
        Set<Hotel> ret = new TreeSet<>(nome);
        for(Hotel l : hoteis) {
            ret.add(l);
        }
        return ret;
    }

    /**
     * Permite definir o Map entre chaves e hóteis
     * @param hoteis Map entre chaves e hóteis
     */
    public void setHoteis(TreeSet<Hotel> hoteis) {
        this.hoteis = hoteis.clone();
    }

    /**
     * Permite saber se um hotel com uma determinada chave existe
     * @param cod Chave que identifica o hotel
     * @return 'True' caso o hotel exista ou 'False' caso contrário
     */
    public boolean existeHotel(String nome) {
        return this.hoteis.contains(nome);
    }

    /**
     * Permite saber quantos hoteis possui a cadeia
     * @return Número de hoteis que a cadeia contem
     */
    public int quantos() {
        return this.hoteis.size();
    }

    /**
     * Permite saber o número de hóteis que existem dentro de uma localidade
     * @param localidade Localidade desejada
     * @return Número de hoteis que existem numa determinada localidade
     */
    public int quantos(String localidade) {
        int ret = 0;
        for(Hotel l : this.hoteis) {
            quantos++;
        }
        return quantos;
    }

    /**
     * Permite obter um hotel ao qual corresponde um determinado código
     * @param cod Código de correspondência ao hotel
     * @return Hotel corresponde à chave
     */
    public Hotel getHotel(String nome) {
        Hotel ret = null;
        for(Hotel l : this.getHoteis())
            if(l.getNome().equals(nome)) ret = l.clone();
        return ret;
    }

    /**
     * Permite adicionar um hotel ao map
     * @param t Hotel a adicionar
     */
    public void adiciona(Hotel t) {
        this.hoteis.add(t.clone());
    }

    /**
     * Permite obter a lista de hóteis contidos no map
     * @return Lista de hoteis
     */
    public List<Hotel> getHoteisList() {
        List<Hotel> ret = new ArrayList<>();
        this.hoteis.values().stream().map(p -> ret.add(p.clone()));
        for(Hotel l : this.getHoteis()) {
            ret.add(l.clone());
        }
        return ret;
    }

    /**
     * Permite adicionar um Set de hoteis
     * @param hs Set de hoteis a adicionar
     */
    public TreeSet<Hotel> ordenaHoteis() {
        TreeSet<Hotel> r = new TreeSet<>();
        for(Hotel l : this.hoteis.values())
            r.add(l.clone());
        return r;
    }

    public TreeSet<Hotel> ordenarHoteis(Comparator<Hotel> c) {
        TreeSet<Hotel> r = new TreeSet<>(c);
        for(Hotel l : this.hoteis.values())
            r.add(l.clone());
        return r;
    }

    public Iterator<Hotel> ordenaHoteis(String criterio) {
        Comparator<Hotel> c = HoteisInc.getComparator(criterio);
        TreeSet<Hotel> res = ordenaHoteis(c);
        return res.iterator();
    }


}


