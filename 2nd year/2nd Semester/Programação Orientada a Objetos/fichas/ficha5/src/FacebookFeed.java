import java.util.List;
import java.util.ArrayList;

public class FacebookFeed {

    private List<Post> feed;

    /**
     * Construtor não parametrizado
     */
    public FacebookFeed() {
        this.feed = new ArrayList<>();
    }

    /**
     * Construtor parametrizado
     */
    public FacebookFeed(List<Post> posts) {
        this.setFeed(posts);
    }

    /**
     * Método que permite obter a lista de posts correspondente ao feed
     * @return Lista de posts correspondentes ao feed
     */
    public List<Post> getFacebookFeed() {
        List<Post> res = new ArrayList<>();
        feed.stream().forEach(e -> {res.add(e);});
        return res;
    }

    /**
     * Método que permite definir um feed a partir de uma lista de posts
     * @param posts Lista dep posts
     */
    public void setFeed(List<Post> posts) {
        for(Post s : posts)
            this.feed.add(s);
    }

    /**
     * Método que permite adicionar um post ao feed
     * @param s
     */
    public void add(Post s) {
        this.feed.add(s);
    }

    /**
     * Método que permite comparar dois objetos do tipo `FacebookFeed`
     * @param object Objeto a comparar
     * @return True caso sejam iguais ou False caso contrário
     */
    @Override
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
        FacebookFeed novo = new FacebookFeed();
        for(Post s : this.feed)
            novo.add(s.clone());
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
    public List<Post> postsOf(String user) {
        List<Post> novo = new ArrayList<>();
        this.feed.stream().filter(um -> user.equals(um.getUsername())).forEach(e -> {novo.add(e);});
        return novo;
    }

    /**
     * Método que permite determinar o post que tem o número identificador passado como argumento
     * @param id Número identificador do post
     * @return Post com o número identificador passado como argumento
     */
    public Post getPost(int id) {
        Post novo = new Post();
        for(int i = 0; i < this.feed.size(); i++)
            if(id == this.feed.get(i).getPostID()) novo = new Post(this.feed.get(i).getUsername(), this.feed.get(i).getContent());
        return novo;
    }
}

