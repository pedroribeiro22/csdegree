public class Exercicio6 {

    /* a */
    public void readMatrix(int[][] matrix) {
        int holder;
        for(int i = 0; i < matrix.length; i++)
            for(int j = 0; j < matrix[0].length; j++)
                holder = matrix[i][j];
    }

    /* b */
    public int[][] matrixSum(int[][] m1, int[][] m2) {
        int[][] sum = new int[m1.length][m1[0].length];
        for(int i = 0; i < m1.length; i++)
            for(int j = 0; j < m1[0].length; j++)
                sum[i][j] = m1[i][j] + m2[i][j];
        return sum;
    }

    /* c */
    public boolean equal(int[][] m1, int[][] m2) {
        boolean r = true;
        for(int i = 0; i < m1.length; i++)
            for(int j = 0; j < m1[0].length; j++)
                if(m1[i][j] != m2[i][j]) r = false;
        return r;
    }

    /* d */
    public int[][] oposta(int[][] matrix) {
        int[][] oposta = new int[matrix.length][matrix[0].length];
        for(int i = 0; i < matrix.length; i++)
            for(int j = 0; j < matrix[0].length; j++)
                oposta[i][j] = -matrix[i][j];
        return oposta;
    }
}
