public class Exercicio1 {

    /* a */
    public int arrayMin(int[] a) {
        int min = a[0];
        for(int i = 1; i < a.length; i++)
            if(a[i] < min)
                min = a[i];
        return min;
    }

    /* b */
    public int[] inBetween(int a[], int i, int j) {
        int[] r = new int[j - i + 1];
        System.arraycopy(a, i, r, 0, j-i+1);
        return r;
    }

    /* c */
    public int[] commons(int[] arr, int[] arr2) {
        int length = (arr.length > arr2.length) ? arr.length : arr2.length;
        int[] newArr = new int[length];
        int index = 0;
        for(int i = 0; i < arr.length; i++)
            for(int j = 0; j < arr2.length; j++)
                if(arr[i] == arr2[j])
                    newArr[index++] = arr[i];
        return newArr;
    }

}
