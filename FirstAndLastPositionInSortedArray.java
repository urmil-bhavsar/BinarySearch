import java.util.Arrays;

public class FirstAndLastPositionInSortedArray {

    public static void main(String[] args) {
        int[] ans = { -1, -1 };
        int[] arr = { 5, 7, 7, 8, 8, 10 };
        int target = 8;

        int start = binarySearch(arr, target, true);
        if (start != -1) {
            int end = binarySearch(arr, target, false);
            ans[0] = start;
            ans[1] = end;
        }

        System.out.println(Arrays.toString(ans));
    }

    // start index fo the target

    public static int binarySearch(int[] arr, int target, boolean findFirstIndex) {
        int start = 0;
        int end = arr.length - 1;
        int ans = -1; // ans is the index of the potential answer

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (target < arr[mid]) {
                end = mid - 1;
            } else if (target > arr[mid]) {
                start = mid + 1;
            } else {
                ans = mid; // potential ans found
                if (findFirstIndex) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        return ans;
    }
}
