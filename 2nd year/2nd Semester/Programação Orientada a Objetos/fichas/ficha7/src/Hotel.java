public abstract class Hotel implements Comparable<Hotel> {

    /**
     * Variáveis de instância
     */
    private String id;
    private String nome;
    private String localidade;
    private int stars;
    private int availableRooms;
    private float roomPrice;

    /**
     * Variáveis globais
     */
    private static int hotelCounter = 0;
    private static int fullCapacity = 120;
    private static int price = 20;

    /**
     * Construtor vazio
     */
    public Hotel() {
        this.id = Integer.toString(++hotelCounter);
        this.nome = " ";
        this.localidade = " ";
        this.stars = 3;
        this.availableRooms = fullCapacity;
        this.roomPrice = price;
    }

    /**
     * Construtor parametrizado
     * @param nome Nome do hotel
     * @param localidade Localidade a que pertence o hotel
     * @param stars Classificação atribuída ao hotel
     * @param availableRooms Número de quartos dispníveis
     * @param roomPrice Preço por quarto
     */
    public Hotel(String nome, String localidade, int stars, int availableRooms, int roomPrice) {
        this.id = Integer.toString(++hotelCounter);
        this.nome = nome;
        this.localidade = localidade;
        this.stars = stars;
        this.availableRooms = availableRooms;
        this.roomPrice = roomPrice;
    }

    public Hotel(Hotel h) {
        this.id = h.getId();
        this.nome = h.getNome();
        this.localidade = h.getLocalidade();
        this.stars = h.getStars();
        this.availableRooms = h.getAvailableRooms();
        this.roomPrice = h.getRoomPrice();
    }

    /**
     * Permite obter o código que identifica o hotel
     * @return String com o código que identifica o hotel
     */
    public String getId() {
        return this.id;
    }

    /**
     * Permite obter o nome do hotel
     * @return String com o nome do hotel
     */
    public String getNome() {
        return nome;
    }

    /**
     * Permite obter a localidade onde se situa o hotel
     * @return String com a localidade onde se situa o hotel
     */
    public String getLocalidade() {
        return localidade;
    }

    /**
     * Permite obter a classificação do hotel
     * @return Classificação do hotel
     */
    public int getStars() {
        return stars;
    }

    /**
     * Permite obter o número de quartos disponíveis
     * @return Número de quartos disponíveis
     */
    public int getAvailableRooms() {
        return availableRooms;
    }

    /**
     * Permite obter o preço por noite de um quarto no hotel
     * @return Preço por noite
     */
    public float getRoomPrice() {
        return roomPrice;
    }

    /**
     * Permite obter o número total de hóteis na cadeia hoteleira
     * @return Número total de hóteis
     */
    public static int getHotelCounter() {
        return hotelCounter;
    }

    /**
     * Permite atribuir um código identificador a um hotel (já vi coisas a fazer mais sentido xD)
     * Muito provavelmente nunca vai ser utilizada
     * @param id Código identificador do hotel
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Permite atribuir um nome a um hotel
     * @param nome Nome a atribuir
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Permite atribuir uma localidade a um hotel
     * @param localidade Localidade a atribuir
     */
    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    /**
     * Permite atribuir uma classificação a um hotel
     * @param stars Classificação a atribuir
     */
    public void setStars(int stars) {
        this.stars = stars;
    }

    /**
     * Permite atribuir um número de quartos disponíveis
     * @param availableRooms Número de quartos disponíveis
     */
    public void setAvailableRooms(int availableRooms) {
        this.availableRooms = availableRooms;
    }

    /**
     * Permite alterar o custo de cada qaurto
     * @param roomPrice Preço por quarto
     */
    public void setRoomPrice(float roomPrice) {
        this.roomPrice = roomPrice;
    }

    /**
     * Método que compara dois objetos do tipo Hotel
     * @param object Objeto a comparar
     * @return 'True' se foram iguais ou 'False' caso contrário
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || this.getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Hotel hotel = (Hotel) object;
        return id == hotel.id &&
                stars == hotel.stars &&
                availableRooms == hotel.availableRooms &&
                java.lang.Float.compare(hotel.roomPrice, roomPrice) == 0 &&
                nome.equals(hotel.nome) &&
                localidade.equals(hotel.localidade);
    }

    /**
     * Método que fornece um representação textual da classe Hotel
     * @return Representação textual da classe
     */
    @java.lang.Override
    public java.lang.String toString() {
        return "Hotel{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", localidade='" + localidade + '\'' +
                ", stars=" + stars +
                ", availableRooms=" + availableRooms +
                ", roomPrice=" + roomPrice +
                '}';
    }

    /**
     * Permite criar uma cópia de um objeto do tipo Hotel
     * @return Cópia criada
     */

    public abstract Hotel clone();

    public abstract float precoNoite();

    public int compareTo(Hotel hotel) {
        return this.nome.compareTo(hotel.getNome());
    }
}