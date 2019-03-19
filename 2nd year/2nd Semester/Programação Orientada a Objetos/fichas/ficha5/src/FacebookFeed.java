package src;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class FacebookFeed {

    private List<FBPost> feed;

    /**
     * Construtor não parametrizado
     */
    public FacebookFeed() {
        this.feed = new ArrayList<>();
    }

    /**
     * Construtor parametrizado
     */
    public FacebookFeed(List<FBPost> posts) {
        this.setFeed(posts);
    }

    /**
     * Método que permite obter a lista de posts correspondente ao feed
     * @return Lista de posts correspondentes ao feed
     */
    public List<FBPost> getFacebookFeed() {
        List<FBPost> res = new ArrayList<>();
        feed.stream().forEach(e -> {res.add(e);});
        return res;
    }

    /**
     * Método que permite definir um feed a partir de uma lista de posts
     * @param posts Lista dep posts
     */
    public void setFeed(List<FBPost> posts) {
        for(FBPost s : posts)
            this.feed.add(s.clone());
    }

    /**
     * Método que permite adicionar um post ao feed
     * @param s
     */
    public void add(FBPost s) {
        this.feed.add(s);
    }

    /**
     * Método que permite comparar dois objetos do tipo `FacebookFeed`
     * @param object Objeto a comparar
     * @return True caso sejam iguais ou False caso contrário
     */
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        FacebookFeed that = (FacebookFeed) object;
        return java.util.Objects.equals(feed, that.feed);
    }

    /**
     * Método que permite obter uma representação textual do objeto `FacebookFeed`
     * @return Representação textual do objeto `FacebookFeed`
     */
    @Override
    public String toString() {
        return "FacebookFeed{" +
                "feed=" + feed +
                '}';
    }

    /**
     * Método que permite clonar um objeto do tipo `FacebookFeed`
     * @return Clone do objeto
     */
    @Override
    public FacebookFeed clone() {
        FacebookFeed novo = new FacebookFeed(this.getPosts());
        return novo;
    }

    /**
     * Método que permite o número de posts que um determinado utilizador tem no feed
     * @param user Nome de usuário
     * @return Número de posts correspondentes a esse utilizador
     */
    public int nrPosts(String user) {
        return (int) feed.stream().filter(um -> user.equals(um.getUsername())).count();
    }

    /**
     * Método que permite determinar a lista de posts correspondentes a um determinado utilizador
     * @param user Nome de usuário
     * @return Lista de posts correspondetes ao usuário
     */
    public List<FBPost> postsOf(String user) {
        List<FBPost> novo = new ArrayList<>();
        this.feed.stream().filter(um -> user.equals(um.getUsername())).forEach(e -> {novo.add(e);});
        return novo;
    }
    
    /**
     * 
     */
    public List<FBPost> postsOf(String user, LocalDateTime inicio, LocalDateTime fim) {
        List<FBPost> novo = new ArrayList<>();
        this.feed.stream().filter(um -> um.getData().isAfter(inicio) &&
                                  um.getData().isBefore(fim) &&
                                  um.getUsername().equals(user)).forEach(e -> {novo.add(e);});
        return novo;
    }
   
    
    public FBPost getPost(int id) {
        return  this.feed.stream().filter(p -> p.getId() == id)
                                   .findFirst().get().clone();
    }
   // utilizar o metodo acima mas sem o clone() e faze-la private
    // Nao existe minimo encapsulamento
    // Para existir tenho de remover o FBPost e voltar a mete-lo no feed
    public void comment(FBPost post, String comentario) {
        int id = feef.indexOf(post);
        FBPost p = this.feed.get(ind);
        List<String> cms = p.getComentarios();
        cms.add(comentario);
        p.setComentarios(cms);
    }
}

