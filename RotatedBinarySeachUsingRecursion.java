public class RotatedBinarySeachUsingRecursion {

    public static void main(String[] args) {
        int[] arr = { 4, 5, 6, 7, 0, 1, 2 };
        int target = 2;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
        System.out.println();
        System.out.println("pivot " + findPivot(arr, 0, arr.length - 1));
        System.out.println("ans " + search(arr, target));
    }

    static int search(int[] nums, int target) {
        int pivot = findPivot(nums, 0, nums.length - 1);

        if (pivot == -1) {
            return binarySearch(nums, target, 0, nums.length - 1);
        }

        if (nums[pivot] == target) {
            return pivot;
        }

        if (target >= nums[0]) {
            return binarySearch(nums, target, 0, pivot - 1);
        }

        return binarySearch(nums, target, pivot + 1, nums.length - 1);
    }

    static int binarySearch(int[] arr, int target, int start, int end) {
        if (start > end) {
            return -1;
        }

        int mid = start + (end - start) / 2;

        if (target < arr[mid]) {
            return binarySearch(arr, target, start, mid - 1);
        } else if (target > arr[mid]) {
            return binarySearch(arr, target, mid + 1, end);
        } else {
            return mid;
        }
    }

    static int findPivot(int[] arr, int start, int end) {
        if (start > end) {
            return -1;
        }

        int mid = start + (end - start) / 2;

        if (mid < end && arr[mid] > arr[mid + 1]) {
            return mid;
        }

        if (mid > start && arr[mid] < arr[mid - 1]) {
            return mid - 1;
        }

        if (arr[mid] <= arr[start]) {
            return findPivot(arr, start, mid - 1);
        }

        return findPivot(arr, mid + 1, end);
    }
}
