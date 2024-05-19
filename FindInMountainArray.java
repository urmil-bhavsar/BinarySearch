public class FindInMountainArray {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 5, 3, 1 };
        int target = 5;
        int ans = search(arr, target);
        System.out.println(ans);
    }

    static int search(int[] arr, int target) {
        int peak = PeakIndexInMountainArray(arr);
        // first try to search in the first part of the array
        int firstTry = orderAgnosticBS(arr, target, 0, peak);
        if (firstTry != -1) {
            return firstTry;
        }

        // otherwise try to search in the second half
        return orderAgnosticBS(arr, target, peak + 1, arr.length - 1);

    }

    static int PeakIndexInMountainArray(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] > arr[mid + 1]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    static int orderAgnosticBS(int[] arr, int target, int start, int end) {

        // find whether the array is sorted in ascending or descending order
        boolean isAsc = arr[start] < arr[end];

        while (start <= end) {
            // find the middle element
            int mid = start + (end - start) / 2;

            if (arr[mid] == target) {
                return mid;
            }

            if (isAsc) {
                if (target < arr[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (target > arr[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        return -1;
    }
}
