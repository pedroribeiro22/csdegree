import java.util.*;

public class GestaoEncomenda {

    /**
     * Alínea d)
     */
    public void removeEncomenda(Integer codEnc) {
        this.encomendas.remove(codEnc);
    }

    /**
     * Alínea e)
     */
    public Integer encomendaComMaisProdutos() {
        TreeSet<Encomenda> aux = new TreeSet<>((e1, e2) -> (e1.numProdutos()) - e2.numProdutos());
        for(Encomenda e : this.encomendas.values())
            aux.add(e);
        return aux.last()).getEnc();
    }

    /**
     * Alínea f)
     * Usamos values() por causa do Map
     * ((e)->e.getNEnc()) = (Encomenda :: getNEnc) -> Só funciona para métodos sem parâmetros
     */
    public Set<Integer> encomendasComProduto(String codProd) {
        return this.encomendas.values().stream().filter(e -> e.existeNaEncomenda(codProd)).map(Encomenda :: getEnc).collect(Collectors.toCollection(TreeSet :: new));
    }

    /**
     * Alínea g)
     */
    public Set<Integer> encomendasAposData(LocalDate d) {
       return this.encomedas.values().stream().filter(e -> e.getData().isAfter(d)).map(Encomenda :: getEnc).collect(Collectors.toSet());
    }

    /**
     * Alínea h)
     */
    public Set<Encomenda> encomendasValorDecrescente() {
        TreeSet<Encomenda> aux = new TreeSet<Encomenda>((e1, e2) -> (int)(e2.calculaValorTotal() - e1.calculaValorTotal()));
        for(Encomenda e : this.encomendas.values())
            aux.add(e.clone());
        return aux;
    }

    /**
     * Alínea i)
     */
    public Map<String, List<String>> encomendasDeProduto() {
        Map<String, List<Integer>> aux = new HashMap();
        for(Encomenda e : this.encomendas.values()) {
            List<String> lprods = e.getListaProdutos();
            for(String codProd : lprods) {
                if(!aux.containsKey(codProd)) {
                    aux.put(codProd, new ArrayList<Integer>());
                }
                List<Integer> ls = aux.get(codProd);
                ls.add(e.getEnc());
            }
        }
        return aux;
    }

}