import java.time.LocalDateTime;

public class Post {

    /* Contador global de posts */
    /* Necessário para que todos os posts tenham um `postID` diferente */
    private static int postCounter = 0;

    private int postID;
    private String username;
    private LocalDateTime creation;
    private String content;
    private int likeCount;
    private String[] comments;

    /**
     * Construtor não parametrizada
     */
    public Post() {
        this.postID = Post.postCounter;
        this.setUsername("unknown");
        this.setCreation(LocalDateTime.now());
        this.setContent("");
        this.setLikeCount(0);
        this.setComments(new String[0]);
        Post.postCounter++;
    }

    /**
     * Construtor parametrizado
     * @param username Nome do usuário criador do post
     * @param content Conteúdo do post
     */
    public Post(String username, String content) {
        this.postID = Post.postCounter;
        this.setUsername(username);
        this.setCreation(LocalDateTime.now());
        this.setContent(content);
        this.setLikeCount(0);
        this.setComments(new String[0]);
        Post.postCounter++;
    }

    /**
     * Método que permite obter o número identificador único de post
     * @return Número identificador único de post
     */
    public int getPostID() {
        return this.postID;
    }

    /**
     * Método que permite obter o username do usuário que criou o post
     * @return Nome do usuário que criou o post
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Método que permite obter o instante me que foi criado o post
     * @return Instante em que foi criado o post
     */
    public LocalDateTime getCreationDate() {
        return this.creation;
    }

    /**
     * Método que permite obter o conteúdo do post
     * @return Conteúdo do post
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Método que permite o obter o estado do contador de likes de um post
     * @return Estado do contador de likes de um post
     */
    public int getLikeCount() {
        return this.likeCount;
    }

    /**
     * Método que permite obter a lista de comentários associados a um post
     * @return Lista de comentários associados a um post
     */
    public String[] getComments() {
        return this.comments;
    }

    /**
     * Método que permite definir o número único identificador do post
     * @param postID Número único identificador do post
     */
    public void setPostID(int postID) {
        this.postID = postID;
    }

    /**
     * Método que permite definir o nome do usuário criador do post
     * @param username Nome do usuário criador do post
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Método que permite definir o instante de criação do post
     * @param creation Instante de criação do post
     */
    public void setCreation(LocalDateTime creation) {
        this.creation = creation;
    }

    /**
     * Método que permite definir o conteúdo de um post
     * @param content Conteúdo de um post
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Método que permite definir o contador de likes
     * @param likeCount Contador de likes
     */
    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * Método que permite definir a lista de comentários de um post
     * @param argComments Lista de comentários de um post
     */
    public void setComments(String[] argComments) {
        this.comments = argComments.clone();
    }

    /**
     * Método que permite comparar dois objetos do tipo `post`
     * @param object Objeto a comparar
     * @return True caso sejam iguais ou False caso contrário
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Post post = (Post) object;
        return  postID == post.postID &&
                likeCount == post.likeCount &&
                java.util.Objects.equals(username, post.username) &&
                java.util.Objects.equals(creation, post.creation) &&
                java.util.Objects.equals(content, post.content) &&
                java.util.Arrays.equals(comments, post.comments);
    }

    /**
     * Método que fornece uma representação textual dos objetos do tipo `post`
     * @return Representação textual do objeto do tipo `post`
     */
    @Override
    public String toString() {
        return "Post{" +
                "postID=" + postID +
                ", username='" + username + '\'' +
                ", creation=" + creation +
                ", content='" + content + '\'' +
                ", likeCount=" + likeCount +
                ", comments=" + java.util.Arrays.toString(comments) +
                '}';
    }

    /**
     * Método que permite clonar um objeto do tipo `post`
     * @return Clone do objeto
     */
    @Override
    public Post clone() {
        Post novo = new Post();
        novo.postID = this.getPostID();
        novo.username = this.getUsername();
        novo.creation = this.getCreationDate();
        novo.content = this.getContent();
        novo.likeCount = this.getLikeCount();
        novo.comments = this.getComments().clone();
        return novo;
    }
}