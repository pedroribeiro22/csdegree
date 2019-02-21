import java.util.Arrays;
import java.util.Scanner;

public class Ficha2 {

    // Exercício 1
    // a
    public int min(int[] arr) {
        int min = Integer.MAX_VALUE;
        for(int d : arr) if(d < min) min = d;
        return min;
    }

    // b
    public int[] arrayEntre(int[] array, int i, int f) {
        int size = f - i + 1;
        int[] res = new int[size];
        System.arraycopy(array, i, res, 0, size);
        return res;
    }

    //c
    public int[] comuns(int[] arr, int[] arr2) {
        int length = (arr.length > arr2.length) ? arr.length : arr2.length;
        int[] newArr = new int[length];
        int index = 0;
        for(int i = 0; i < arr.length; i++)
            for(int j = 0; j < arr2.length; j++)
                if(arr2[j] == arr[i]) newArr[index++] = arr[i]; index++;
        return newArr;
    }

    // Exercício 2
    //b
    public int somaNotasUc(int[][] notasTurma, int uc) {
        int sum = 0;
        for(int i = 0; i < 5; i++)
            sum += notasTurma[i][uc];
        return sum;
    }

    //c
    public float meanAluno(int[][] notasTurma, int aluno) {
        int sum = 0;
        float mean;
        for(int i = 0; i < 5; i++)
            sum += notasTurma[aluno][i];
        mean = sum / 5;
        return mean;
    }

    //d
    public float meanUC(int[][] notasTurma, int uc) {
        int sum = somaNotasUc(notasTurma, uc);
        float mean = sum / 5;
        return mean;
    }

    //e
    public int highestGrade(int[][] notasTurma) {
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                if(notasTurma[i][j] > max) max = notasTurma[i][j];
        return max;
    }

    //f
    public int lowestGrade(int[][] notasTurma) {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                if(notasTurma[i][j] < min) min = notasTurma[i][j];
        return min;
    }

    //g
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

    //h
    // public String grades(int[][] notasTurma) {
    //     String outp = "";
    //     for(int i = 0; i < 5; i++) {
    //         for(int j = 0; j < 5; j++) {
    //             outp = outp + i + "-> " + j + "; ";
    //         }
    //     }
    //     outp += "\n";
    // }

    //i
    public int highestMeanUC(int[][] notasTurma) {
        float currentMean;
        float highestMean = 0;
        int index = -1;
        for(int i = 0; i < 5; i++) {
            currentMean = meanUC(notasTurma, i);
            if(currentMean > highestMean) {highestMean = currentMean; index = i;}
        }
        return index;
    }

    // Exercício 4
    //a
    public void sortArray(int[] lista) {
        Arrays.sort(lista);
    }

    //b
    public int binarySearch(int[] lista, int goal) {
        return(Arrays.binarySearch(lista, goal));
    }

    // Exercício 6
    // a
    public void readMatrix(int[][] matrix) {
        int holder;
        //vou só percorrer a matriz, a pergunta não faz muito sentido
        for(int i = 0; i < matrix.length; i++)
            for(int j = 0; j < matrix[0].length; j++)
                holder = matrix[i][j];
    }

    //b
    // Vou assumir que as matrizes têm o mesmo tamanho
    public int[][] matrixSum(int[][] m1, int[][] m2) {
        int[][] sum = new int[m1.length][m1[0].length];
        for(int i = 0; i < m1.length; i++)
            for(int j = 0; j < m1[0].length; j++)
                sum[i][j] = m1[i][j] + m2[i][j];
        return sum;
    }

    //c
    public boolean equal(int[][] m1, int[][] m2) {
        boolean r = true;
        for(int i = 0; i < m1.length; i++)
            for(int j = 0; j < m1[0].length; j++)
                if(m1[i][j] != m2[i][j]) r = false;
        return r;
    }

    //d
    public int[][] oposta(int[][] matrix) {
        int[][] oposta = new int[matrix.length][matrix[0].length];
        for(int i = 0; i < matrix.length; i++)
            for(int j = 0; j < matrix[0].length; j++)
                oposta[i][j] = - matrix[i][j];
        return oposta;
    }
}

