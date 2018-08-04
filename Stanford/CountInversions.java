import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CountInversions {
    public static long bruteForceCount(int[] arr, int n) {
        long invCount = 0;
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                if (arr[i] > arr[j])
                    invCount++;
            }
        }
        return invCount;
    }

/*
    public static long sortAndCount(int[] arr, long begin, long end) {
        if (arr.length == 1) {
            return 0;
        }
        else {
            long x = sortAndCount(arr, begin, begin + (end - begin) / 2);
            long y = sortAndCount(arr, begin + (end - begin) / 2 + 1, end);
            long z = mergeAndCountSplitInv(arr);

            return x + y + z;
        }
    }

    private static long mergeAndCountSplitInv(int[] arr) {
        return 0;
    }
*/

    public static long mergeSortAndCount(int arr[], int n) {
        int[] temp = new int[n];
        return _mergeSort(arr, temp, 0, n-1);
    }

    private static long _mergeSort(int[] arr, int[] temp, int left, int right) {
        int mid = 0;
        long invCount = 0;
        if (right > left) {
            mid = (right + left) / 2;

            invCount = _mergeSort(arr, temp, left, mid);
            invCount += _mergeSort(arr, temp, mid+1, right);

            invCount += merge(arr, temp, left, mid+1, right);
        }
        return invCount;
    }

    private static long merge(int[] arr, int[] temp, int left, int mid, int right) {
        int i, j, k;
        long invCount = 0;

        i = left;
        j = mid;
        k = left;

        while ((i <= mid-1) && (j <= right)) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            }
            else {
                temp[k++] = arr[j++];
                invCount += mid - i;
            }
        }

        // copy remaining elements of left subarray (if exists) to temp
        while (i <= mid-1) temp[k++] = arr[i++];

        // copy remaining elements of right subarray (if exists) to temp
        while (j <= right) temp[k++] = arr[j++];

        // copy back the merged elements to original array
        for (i = left; i <= right ; i++) {
            arr[i] = temp[i];
        }

        return invCount;
    }

    public static void main(String[] args) throws FileNotFoundException {
        int[] arr = new int[100000];
        Scanner in = new Scanner(new File("src/Stanford/Integers.txt"));
        for (int i = 0; i < arr.length; i++) {
            arr[i] = in.nextInt();
        }

        // int[] testArr = {1, 20, 6, 4, 5};
        // System.out.println("Number of Inversions in " + Arrays.toString(testArr) + " is: " + bruteForceCount(testArr, testArr.length));
        // System.out.println("Number of Inversions are " + bruteForceCount(arr, arr.length));
        System.out.println("Number of Inversions are " + mergeSortAndCount(arr, arr.length));
    }
}
