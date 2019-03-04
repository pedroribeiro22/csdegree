public class Exercicio2 {

    /* b */
    public int somaNotasUC(int[][] notasTurma, int uc) {
        int sum = 0;
        for(int i = 0; i < 5; i++)
            sum += notasTurma[i][uc];
        return sum;
    }

    /* c */
    public float meanAluno(int[][] notasTurma, int aluno) {
        int sum = 0;
        float mean;
        for(int i = 0; i < 5; i++)
            sum += notasTurma[aluno][i];
        mean = sum / 5;
        return mean;
    }

    /* d */
    public float meanUC(int[][] notasTurma, int uc) {
        int sum = this.somaNotasUC(notasTurma, uc);
        float mean = sum / 5;
        return mean;
    }

    /* e */
    public int highestGrade(int[][] notasTurma) {
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                if(notasTurma[i][j] > max) max = notasTurma[i][j];
        return max;
    }

    /* f */
    public int lowestGrade(int[][] notasTurma) {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                if(notasTurma[i][j] < min) min = notasTurma[i][j];
        return min;
    }

    /* g */
    public int[] gradesAboveArgument(int[][] notasTurma, int argument) {
        int maxPossibleLength = 5 * 5;
        int[] grades = new int[maxPossibleLength];
        int index = 0;
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                if(notasTurma[i][j] > argument) grades[index++] = notasTurma[i][j];
        System.arraycopy(grades, 0, grades, 0, index);
        return grades;
    }

    /* i */
    public int highestMeanUC(int[][] notasTurma) {
        float currentMean;
        float highestMean = 0;
        int index = -1;
        for(int i = 0; i < 5; i++) {
            currentMean = meanUC(notasTurma, i);
            if(currentMean > highestMean) {
                highestMean = currentMean;
                index = i;
            }
        }
        return index;
    }
}
