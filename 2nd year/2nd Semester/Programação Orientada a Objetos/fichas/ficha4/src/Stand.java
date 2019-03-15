public class Stand {

    private Veiculo[] colecao;
    private int colecaoSize;

    /**
     * Construtor vazio
     */
    public Stand() {
        this.colecao = new Veiculo[0];
        this.colecaoSize = 0;
    }

    /**
     * Construtor parametrizado
     * @param paramColecao Coleção de veículos inicial
     * @param colecaoSize Tamanho da coleção
     */
    public Stand(Veiculo[] paramColecao, int colecaoSize) {
        for(int i = 0; i < colecaoSize; i++)
            this.colecao[i] = paramColecao[i].clone();
        this.colecaoSize = colecaoSize;
    }

    /**
     * Método que permite obter a coleção de veículos do stand
     * @return Coleção de veículos do stand
     */
    public Veiculo[] getColecao() {
        Veiculo[] novo = new Veiculo[this.colecaoSize];
        for(int i = 0; i < colecaoSize; i++)
            novo[i] = this.colecao[i].clone();
        return novo;
    }

    /**
     * Método que permite obter o tamanho da coleção de veículos do stand
     * @return Tamanho da coleção de veículos do stand
     */
    public int getColecaoSize() {
        return this.colecaoSize;
    }

    /**
     * Método que permite alterar a coleção de veículos do Stand
     * @param paramColecao Coleção de veículos do stand
     * @param colecaoSize Tamanho da coleção de veículos do stand
     */
    public void setColecao(Veiculo[] paramColecao, int colecaoSize) {
        for(int i = 0; i < colecaoSize; i++)
            this.colecao[i] = paramColecao[i].clone();
        this.colecaoSize = colecaoSize;
    }

    /**
     * Método que permite verificar se dois stands são iguais
     * @param object Stand a comparar
     * @return True caso sejam iguais ou False caso contrário
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Stand stand = (Stand) object;
        return  colecaoSize == stand.colecaoSize &&
                java.util.Arrays.equals(colecao, stand.colecao);
    }

    /**
     * Método que permite clonar um stand
     * @return Novo stand (clone)
     */
    @Override
    public Stand clone() {
        Stand novo = new Stand();
        novo.setColecao(this.colecao, this.colecaoSize);
        return novo;
    }
}
