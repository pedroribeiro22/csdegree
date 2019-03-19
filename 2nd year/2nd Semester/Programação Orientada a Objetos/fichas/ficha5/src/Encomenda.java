package src;

import java.util.ArrayList;
import java.util.List;

public class Encomenda {

    private List<LinhaEncomenda> encomenda;
    private String nome;
    private int nif;
    private String moeda;
    private int nEncomenda;
    private LocalDate data;

    public List<LinhaEncomeda> getEncomenda() {
        List<LinhaEncomenda> res = new ArrayList<>();
        encomenda.stream().forEach(e -> {res.add(e);});
        return res;
    }

    public setEncomenda(List<LinhaEncomenda> lista) {
        this.encomenda = new ArrayLista<LinhaEncomenda>();
        for(LinhaEncomenda l : encomenda)
            this.encomenda.add(l);

    }
    public Encomenda() {
        this.encomenda = new ArrayList<LinhaEncomenda>();
    }

    public Encomenda(List<LinhaEncomenda> lista) {
        setEncomenda(lista);
    }

    public Encomenda(Encomenda lista) {
        this.encomenda = lista.getEncomenda();
    }

    public double calculaValorTotal() {
        return encomenda.stream().calculaValorLinhaEnc().sum();
    }

    public double calculaValorDesc() {
        return encomenda.stream().calculaValorLinhaDesc().sum();
    }

    public int numeroTotalProdutos() {
        return (int) encomenda.stream().getQuantidade().sum();
    }





}