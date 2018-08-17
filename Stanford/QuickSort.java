package Stanford;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class QuickSort {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/Stanford/QuickSort.txt"));
        int[] arr = new int[10000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = in.nextInt();
        }
        quicksort(arr, 0, arr.length-1);
        System.out.println(isSorted(arr));
        int[] testarr = new int[]{5,2,4,16164,6431,4631,6431,641,164,4361,531,321,125,1253,5231,1523,5231,5231,153,41164,6431,641,436};
        quicksort(testarr, 0, testarr.length-1);
        System.out.println(isSorted(testarr));
    }

    public static void quicksort(int[] arr, int left, int right) {
        int index = partition(arr, left, right);
        if (index - 1 > left)
            quicksort(arr, left, index - 1);
        if (index + 1 < right)
            quicksort(arr, index + 1, right);
    }

    private static int partition(int[] arr, int left, int right) {
        Random rand = new Random();
        int pivotIndex = rand.nextInt(right-left) + left;
        int pivot = arr[pivotIndex];

        // swap pivot element and arr[left] to make partition easier
        int tempSwap = arr[pivotIndex];
        arr[pivotIndex] = arr[left];
        arr[left] = tempSwap;

        int i = left + 1;
        for(int j = left + 1; j <= right; j++) {
            if (arr[j] < pivot) {
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
                i++;
            }
        }
        // put the pivot at the right place
        int temp = arr[left];
        arr[left] = arr[i-1];
        arr[i-1] = temp;

        // i-1 is the current pivot index
        return i-1;
    }


    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            if (arr[i] > arr[i+1])
                return false;
        }
        return true;
    }
}
