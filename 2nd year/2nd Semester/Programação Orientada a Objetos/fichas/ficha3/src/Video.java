import java.time.LocalDate;
import java.time.tempora.ChronoUnit;

public class Video {
    private String nome;
    private byte[] conteudo;
    private LocalDate data;
    private int resolucao;
    private int duracao;
    private String[] comentarios;
    private int likes, dislikes;

    public int index;

    public Video() {
        this.nome = "N/A";
        this.conteudo = new byte[100];
        this.data = LocalDate.now();
        this.resolucao = 1080;
        this.duracao = 0;
        this.comentarios = new String[100];
        this.likes = 0;
        this.dislikes = 0;
        this.index = 0;
    }

    //TODO: construtores, get, set, clone, equals, toString

    public void insertComment(String comment) {
        if(index < comentarios.length) {
            comentarios[index] = comment;
            index++;
        }
    }

    public long qtsDiasDepois() {
        LocalDate agora = LocalDate.now();
        long dias = data.until(agora, ChronoUnit.DAYS);
        return dias;
    }

    public String render() {
        return String.valueOf(conteudo);
    }
}
