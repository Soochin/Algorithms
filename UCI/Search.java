import java.util.Random;

public class Search {
    public static int sequentialSearch(int[] arr, int x) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x)
                return arr[i];
        }
        return -1; // no match
    }

    private static int[] shuffleArray(int[] arr) {
        Random rng = new Random();
        for (int i = 0; i < arr.length; i++) {
            int randPos = rng.nextInt(arr.length);
            int temp = arr[i];
            arr[i] = arr[randPos];
            arr[randPos] = temp;
        }
        return arr;
    }

    public static int randomizedSequentialSearch(int[] arr, int x) {
        return sequentialSearch(shuffleArray(arr), x);
    }

    public static int binarySearch(int[] arr, int x) {
        int low = 0;
        int high = arr.length - 1;
        int mid;

        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] < x)
                low = mid + 1;
            else if (arr[mid] > x)
                high = mid - 1;
            else
                return arr[mid];
        }
        return -1; // no match
    }

    public static int binarySearchR(int[] arr, int x, int low, int high) {
        if (low > high) return -1; // no match

        int mid = (low + high) / 2;
        if (arr[mid] < x)
            return binarySearchR(arr, x, mid + 1, high);
        else if (arr[mid] > x)
            return binarySearchR(arr, x, low, mid - 1);
        else
            return arr[mid];
    }

    public static int[] generateRandomArray() {
        Random rng = new Random();
        int[] arr = new int[50];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rng.nextInt(50);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr1 = generateRandomArray();
        int[] arr2 = generateRandomArray();
        int[] arr3 = generateRandomArray();
        int[] arr4 = generateRandomArray();

        System.out.println(sequentialSearch(arr1, 10));
        System.out.println(randomizedSequentialSearch(arr2, 20));
        System.out.println(binarySearch(arr3, 30));
        System.out.println(binarySearchR(arr4, 40, 0, arr4.length - 1));
    }
}
