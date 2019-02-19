
public class Alunos {
    //a
    //
    //b
    static int somaUnidadeCurricular(int[][] notasTurma, int uc) {
        int res = 0;
        for(int i = 0; i < 5; i++)
            res += notasTurma[i][uc];
        return res;
    }
}
