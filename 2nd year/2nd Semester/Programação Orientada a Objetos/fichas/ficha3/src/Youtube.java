import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Youtube {

    private String nome;
    private byte[] conteudo;
    private LocalDate data;
    private int resolucao;
    private int duracao;
    private String[] comentarios;
    private int likes, dislikes;

    public int index;

    public Youtube() {
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

    public String getNome() {
        return nome;
    }

    public byte[] getConteudo() {
        return conteudo;
    }

    public LocalDate getData() {
        return data;
    }

    public int getResolucao() {
        return resolucao;
    }

    public int getDuracao() {
        return duracao;
    }

    public String[] getComentarios() {
        return comentarios;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setConteudo(byte[] conteudo) {
        this.conteudo = conteudo;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setResolucao(int resolucao) {
        this.resolucao = resolucao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public void setComentarios(String[] comentarios) {
        this.comentarios = comentarios;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

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
