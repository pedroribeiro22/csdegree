import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Collection;
import java.util.stream.Collectors;

public class Parque {
    String nome;
    Map<String, Lugar> lugares;

    public Parque() {
        this.nome = "";
        this.lugares = new HashMap<>();
    }

    public Parque(Parque parque) {
        this.lugares = parque.getLugares();
    }
    public Parque(String nome) {
        this.nome = nome;
        this.lugares = new HashMap<>();
    }

    public Parque(String nome, Map<String, Lugar> cenas) {
        this.setNome(nome);
        this.setLugares(cenas);
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Map<String, Lugar> getLugares() {
        /* Map<String, Lugar> res = new HashMap<>();
        for(String matricula : lugares.keySet()) {
            Lugar l = lugares.get(matricula);
            res.put(matricula, l.clone());
        }
         */
        /*
        Map<String, Lugar> res = new HashMap<>();
        Collection<Lugar> ls = this.lugares.values();
        for(Lugar l : ls) {
            res.put(l.getMatricula(), l.clone());
        }
        */
        Map<String, Lugar> res = new HashMap<>();
        for(Map.Entry<String, Lugar> par : this.lugares.entrySet()) {
            String matricula = par.getKey();
            Lugar l = par.getValue().clone();
            res.put(matricula, l);
        }
        return res;
    }

    /* Isto não está totalmente bem */
    public void setLugares(Map<String, Lugar> lugares) {
        this.lugares = lugares.values().stream()
                                       .collect(Collectors.toMap(lugar -> lugar.getMatricula(), lugar -> lugar.clone()));
    }

    public Set<String> todasMatriculas() {
        Set<String> novo = this.lugares.keySet().stream().collect(Collectors.toSet());
        return novo;
    }


    public void novoUser(String matricula, Lugar sitio) {
       this.lugares.put(matricula, sitio);
    }

    /*
    public void changeRemainingTime(int time, String matricula) {
        Map<String, Lugar> res = new HashMap<>();
        for(Map.Entry<String, Lugar> par : this.lugares.entrySet()) {
            String matriculaComp = par.getKey();
            if(matriculaComp.equals(matricula))
        }
    }
    */
}

