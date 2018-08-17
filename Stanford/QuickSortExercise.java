package Stanford;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class QuickSortExercise {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/Stanford/QuickSort.txt"));
        int[] arr = new int[10000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = in.nextInt();
        }
        int[] arrcpy1 = Arrays.copyOf(arr, arr.length);
        int[] arrcpy2 = Arrays.copyOf(arr, arr.length);
        int[] arrcpy3 = Arrays.copyOf(arr, arr.length);

        System.out.println("Showing number of comparisons depending on pivot choices.");
        System.out.println("First element as a pivot: " + countQuickSortComparisions(arr, 0, arr.length-1, 0));
        System.out.println("Last element as a pivot: " + countQuickSortComparisions(arrcpy1, 0, arrcpy1.length-1, 1));
        System.out.println("Median of Three as a pivot: " + countQuickSortComparisions(arrcpy2, 0, arrcpy2.length-1, 2));
        System.out.println("Random pivot choice: " +countQuickSortComparisions(arrcpy3, 0, arrcpy3.length-1, 3));
        System.out.println("arr sorted? " + isSorted(arr));
        System.out.println("arrcpy1 sorted? " + isSorted(arrcpy1));
        System.out.println("arrcpy2 sorted? " + isSorted(arrcpy2));
        System.out.println("arrcpy3 sorted? " + isSorted(arrcpy3));
    }


    // Pivot Flags:
    // 0 = 1st element of (sub)array
    // 1 = last element of (sub)array
    // 2 = median of three
    public static long countQuickSortComparisions(int[] arr, int left, int right, int pivotFlag) {
        long comparisons = right - left;
        int pivotIndex = partitionAndCount(arr, left, right, pivotFlag);
        if (pivotIndex - 1 > left)
            comparisons += countQuickSortComparisions(arr, left, pivotIndex - 1, pivotFlag);
        if (pivotIndex + 1 < right)
            comparisons += countQuickSortComparisions(arr, pivotIndex + 1, right, pivotFlag);
        return comparisons;
    }

    private static int partitionAndCount(int[] arr, int left, int right, int pivotFlag) {
        int pivotIndex = pivotFlag == 0 ? left : right;
        if (pivotFlag == 2) pivotIndex = medianOfThree(arr, left, right);
        if (pivotFlag == 3) pivotIndex = randomPivot(left, right);
        int pivot = arr[pivotIndex];

        int tempSwap = arr[pivotIndex];
        arr[pivotIndex] = arr[left];
        arr[left] = tempSwap;

        int i = left + 1;
        for (int j = left + 1; j <= right; j++) {

            if (arr[j] < pivot) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
        }

        int temp = arr[i-1];
        arr[i-1] = arr[left];
        arr[left] = temp;

        return i-1;
    }


    private static int medianOfThree(int[] arr, int left, int right) {
        int one = arr[left];
        int two = arr[right];
        int three = arr[(left+right)/2];

        int[] a = new int[]{one, two, three};
        Arrays.sort(a);
        if (a[1] == one)
            return left;
        else if (a[1] == two)
            return right;
        else
            return (left+right)/2;
    }


    private static int randomPivot(int left, int right) {
        Random rand = new Random();
        return rand.nextInt(right - left + 1) + left;
    }

    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            if (arr[i] > arr[i+1])
                return false;
        }
        return true;
    }
}
