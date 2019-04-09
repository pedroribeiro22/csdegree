public class Hotel {

    private String id;
    private String nome;
    private String localidade;
    private int stars;
    private int availableRooms;
    private float roomPrice;

    private static int hotelCounter = 0;
    private static int fullCapacity = 120;
    private static int price = 20;

    public Hotel() {
        this.id = ++hotelCounter;
        this.nome = " ";
        this.localidade = " ";
        this.stars = 3;
        this.availableRooms = fullCapacity;
        this.roomPrice = price;
    }

    public Hotel(String nome, String localidade, int stars, int availableRooms, int roomPrice) {
        this.id = ++hotelCounter;
        this.nome = nome;
        this.localidade = localidade;
        this.stars = stars;
        this.availableRooms = availableRooms;
        this.roomPrice = roomPrice;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getLocalidade() {
        return localidade;
    }

    public int getStars() {
        return stars;
    }

    public int getAvailableRooms() {
        return availableRooms;
    }

    public float getRoomPrice() {
        return roomPrice;
    }

    public static int getHotelCounter() {
        return hotelCounter;
    }

    public static int getFullCapacity() {
        return fullCapacity;
    }

    public static int getPrice() {
        return price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public void setAvailableRooms(int availableRooms) {
        this.availableRooms = availableRooms;
    }

    public void setRoomPrice(float roomPrice) {
        this.roomPrice = roomPrice;
    }

    public static void setHotelCounter(int hotelCounter) {
        Hotel.hotelCounter = hotelCounter;
    }

    public static void setFullCapacity(int fullCapacity) {
        Hotel.fullCapacity = fullCapacity;
    }

    public static void setPrice(int price) {
        Hotel.price = price;
    }

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

   // hashcode

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

    public Hotel clone() {
        return new Hotel(this);
    }
}