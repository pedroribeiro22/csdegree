import java.util.Arrays;

public class Telemovel {

    private String brand;
    private String model;
    private int resolutionX;
    private int resolutionY;
    private int textStorage;
    private int mediaStorage;
    private int photosStorage;
    private int appsStorage;
    private int occupiedStorage;
    private int storagedPhotos;
    private int installedApps;
    private String[] apps;

    /**
     * Construtor vazio
     */
    public Telemovel() {
        this.brand = "OnePlus";
        this.model = "6T";
        this.resolutionX = 1080;
        this.resolutionY = 2340;
        this.textStorage = 20;
        this.photosStorage = 40;
        this.appsStorage = 20;
        this.occupiedStorage = 0;
        this.storagedPhotos = 0;
        this.installedApps = 0;
        this.apps = new String[20];
    }

    /**
     * Construtor parametrizado
     * @param brand Marca do telemóvel
     * @param model Modelo do telenóvel
     * @param resolutionX Resolução horizontal do telémovel
     * @param resolutionY Resolução vertical do telemóvel
     * @param textStorage Espaço de armazenamento dedicado a mensagens de texto
     * @param mediaStorage Espaço de armazenamente dedicado a media
     * @param photosStorage Espaço de armazenamento dedicado a fotos
     * @param appsStorage Espaço de armazenamento dedicado a aplicações
     * @param occupiedStorage Espaço de armazenamento ocupado
     * @param storagedPhotos Número de fotos armazenadas
     * @param installedApps Número de aplicações instaladas
     * @param apps Lista de aplicações instaladas
     */
    public Telemovel(String brand,
                     String model,
                     int resolutionX,
                     int resolutionY,
                     int textStorage,
                     int mediaStorage,
                     int photosStorage,
                     int appsStorage,
                     int occupiedStorage,
                     int storagedPhotos,
                     int installedApps,
                     String[] apps) {
        this.brand = brand;
        this.model = model;
        this.resolutionX = resolutionX;
        this.resolutionY = resolutionY;
        this.textStorage = textStorage;
        this.photosStorage = photosStorage;
        this.appsStorage = appsStorage;
        this.occupiedStorage = occupiedStorage;
        this.storagedPhotos = storagedPhotos;
        this.installedApps = installedApps;
        this.apps = apps;
    }

    /**
     * Método que permite saber qual a marca do telemóvel
     * @return Marca do telemóvel
     */
    public String getBrand() {
        return this.brand;
    }

    /**
     * Método que permite saber qual o modelo do telemóvel
     * @return Modelo do telemóvel
     */
    public String getModel() {
        return this.model;
    }

    /**
     * Método que permite saber qual a resolução horizontal do telemóvel
     * @return Resolução horizontal do telemóvel
     */
    public int getResolutionX() {
        return this.resolutionX;
    }

    /**
     * Método que permite saber qual a resolução vertical do telemóvel
     * @return Resolução vertical do telemóvel
     */
    public int getResolutionY() {
        return this.resolutionY;
    }

    /**
     * Método que permite saber qual o armazenamento disponível para mensagens de texto
     * @return Armazento disponível para mensagens de texto
     */
    public int getTextStorage() {
        return this.textStorage;
    }

    /**
     * Método que permite saber qual o armazenamento disponível para fotos
     * @return Armazento disponível para fotos
     */
    public int getPhotosStorage() {
        return this.photosStorage;
    }

    /**
     * Método que permite saber qual o armazenamento disponível para aplicações
     * @return Armazento disponível para aplicações
     */
    public int getAppsStorage() {
        return this.appsStorage;
    }

    /**
     * Método que permite saber qual o espaço de armazenamento ocupado
     * @return Espaço de armazenamento ocupado
     */
    public int getOccupiedStorage() {
        return this.occupiedStorage;
    }

    /**
     * Método que permite saber qual o número de fotos armazenadas
     * @return Número de fotos armazenadas
     */
    public int getStoragedPhotos() {
        return this.storagedPhotos;
    }

    /**
     * Método que permite saber qual o número de aplicações instaladas
     * @return Número de aplicações instaladas
     */
    public int getInstalledApps() {
        return this.installedApps;
    }

    /**
     * Método que permite saber quais as aplicações instaladas
     * @return Aplicações instaladas
     */
    public String[] getApps() {
        return this.apps;
    }

    /**
     * Método que permite definir a marca de um telemóvel
     * @param brand Marca do telemóvel
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Método que permite definir o modelo de um telemóvel
     * @param model Modelo do telemóvel
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Método que permite definir a resolução horizontal de um telemóvel
     * @param resolutionX Resolução horizontal do telemóvel
     */
    public void setResolutionX(int resolutionX) {
        this.resolutionX = resolutionX;
    }

    /**
     * Método que permite definir a resolução vertical de um telemóvel
     * @param resolutionY Resolução vertical do telemóvel
     */
    public void setResolutionY(int resolutionY) {
        this.resolutionY = resolutionY;
    }

    /**
     * Método que permite definir o espaço de armazenamento dedicado a mensagens de texto de um telemóvel
     * @param textStorage Espaço destinado a mensagens de texto
     */
    public void setTextStorage(int textStorage) {
        this.textStorage = textStorage;
    }

    /**
     * Método que permite definir o espaço de armazenamento dedicado a mnedia de um telemóvel
     * @param textStorage Espaço destinado a media
     */
    public void setMediaStorage(int mediaStorage) {
        this.mediaStorage = mediaStorage;
    }

    /**
     * Método que permite definir o espaço de armazenamento dedicado a fotos de um telemóvel
     * @param textStorage Espaço destinado a fotos
     */
    public void setPhotosStorage(int photosStorage) {
        this.photosStorage = photosStorage;
    }

    /**
     * Método que permite definir o espaço de armazenamento dedicado a aplicações de um telemóvel
     * @param textStorage Espaço destinado a aplicações
     */
    public void setAppsStorage(int appsStorage) {
        this.appsStorage = appsStorage;
    }

    /**
     * Método que permite definir o espaço de armazenamento ocupado de um telemóvel
     * @param occupiedStorage Espaço de armazenamento ocupado
     */
    public void setOccupiedStorage(int occupiedStorage) {
        this.occupiedStorage = occupiedStorage;
    }

    /**
     * Método que permite definir o número de fotos armazenadas num telemóvel
     * @param storagedPhotos Número de fotos armazenadas
     */
    public void setStoragedPhotos(int storagedPhotos) {
        this.storagedPhotos = storagedPhotos;
    }

    /**
     * Método que permite definir o número de aplicações instaladas num telemóvel
     * @param installedApps Número de aplicações instaladas
     */
    public void setInstalledApps(int installedApps) {
        this.installedApps = installedApps;
    }

    /**
     * Método que permite definir quais as aplicações instaladas num telemóvel
     * @param apps Aplicações instaladas no telemóvel
     */
    public void setApps(String[] apps) {
        this.apps = apps;
    }

    /**
     * Método que permite comparar dois objetos (telemóveis)
     * @param object Telemóvel a comparar com o presente telemóvel
     * @return True caso sejam iguais ou False caso contrário
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Telemovel telemovel = (Telemovel) object;
        return  this.resolutionX == telemovel.resolutionX &&
                this.resolutionY == telemovel.resolutionY &&
                this.textStorage == telemovel.textStorage &&
                this.mediaStorage == telemovel.mediaStorage &&
                this.photosStorage == telemovel.photosStorage &&
                this.appsStorage == telemovel.appsStorage &&
                this.occupiedStorage == telemovel.occupiedStorage &&
                this.storagedPhotos == telemovel.storagedPhotos &&
                this.installedApps == telemovel.installedApps &&
                this.brand.equals(telemovel.brand) &&
                this.model.equals(telemovel.model) &&
                java.util.Arrays.equals(apps, telemovel.apps);
    }

    /**
     * Método que fornece uma representação textual de um objeto do tipo `Telemóvel`
     * @return String com a representação textual do objeto
     */
    @Override
    public String toString() {
        return "Telemovel{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", resolutionX=" + resolutionX +
                ", resolutionY=" + resolutionY +
                ", textStorage=" + textStorage +
                ", mediaStorage=" + mediaStorage +
                ", photosStorage=" + photosStorage +
                ", appsStorage=" + appsStorage +
                ", occupiedStorage=" + occupiedStorage +
                ", storagedPhotos=" + storagedPhotos +
                ", installedApps=" + installedApps +
                ", apps=" + java.util.Arrays.toString(apps) +
                '}';
    }

    /**
     * Método que permite saber se existe espaço livre suficiente para armazenar um ficheiro
     * @param bytes Espaço ocupado pelo ficheiro (em bytes)
     * @return True caso exista espaço ou False caso contrário
     */
    public boolean existeEspaco(int bytes) {
        int fullSpace = this.getTextStorage() + this.getPhotosStorage() + this.getAppsStorage();
        int occupiedSpace = this.getOccupiedStorage() + this.getStoragedPhotos() + this.getInstalledApps();
        int freeSpace = fullSpace - occupiedSpace;
        return (bytes < freeSpace);
    }

    /**
     * Método que permite instalar uma aplicação no telemóvel
     * @param nome Nome da aplicação
     * @param tamanho Tamanho ocupado pela aplicação
     */
    public void instalaApp(String nome, int tamanho) {
        if(this.installedApps < this.appsStorage) {
            String[] newApps = Arrays.copyOf(this.apps, this.apps.length + 1);
            newApps[this.apps.length] = nome;
            this.apps = newApps;
            this.installedApps++;
        }
    }
}
