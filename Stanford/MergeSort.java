public class MergeSort {
    public static int[] mergesort(int[] arr, int beginning, int end) {
        if (arr.length == 0 || beginning > end)
            return new int[]{};

        if (beginning == end)
            return new int[]{arr[end]};

        int[] left = mergesort(arr, beginning, beginning + (end - beginning) / 2);
        int[] right = mergesort(arr, beginning + (end - beginning) / 2 + 1, end);
        return mergeArray(left, right);
    }

    private static int[] mergeArray(int[] left, int[] right) {
        int leftIter = 0, rightIter = 0;
        int[] res = new int[left.length + right.length];
        for (int i = 0; i < left.length+right.length; i++) {
            if (leftIter < left.length && rightIter < right.length) {
                res[i] = (left[leftIter] < right[rightIter]) ? left[leftIter++] : right[rightIter++];
            }
            else if (leftIter < left.length) {
                res[i] = left[leftIter++];
            }
            else {
                res[i] = right[rightIter++];
            }
        }
        return res;
    }

    private static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            if (arr[i] > arr[i+1])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] testSortArray = new int[50];
        for(int i = 0; i < testSortArray.length; i++){
            testSortArray[i] = (int)(Math.random()*100);
        }
        System.out.println("Unsorted:");
        for(int i = 0; i < testSortArray.length; i++){
            System.out.print(testSortArray[i] + " ");
        }
        System.out.println();
        System.out.println("Sorted:");
        testSortArray = mergesort(testSortArray, 0, testSortArray.length - 1);
        for(int i = 0; i < testSortArray.length; i++){
            System.out.print(testSortArray[i] + " ");
        }
        System.out.println();
        System.out.println("Sorted? " + isSorted(testSortArray));
    }
}
