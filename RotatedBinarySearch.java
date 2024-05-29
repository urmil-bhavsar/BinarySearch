public class RotatedBinarySearch {

    // every method inside a static method has to be static too! because the main
    // method is not object dependent so the methods inside it will not be object
    // dependent either
    public static void main(String[] args) {
        int[] arr = { 4, 5, 6, 7, 0, 1, 2 };
        int target = 2;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        System.out.println("pivot " + findPivot(arr));
        System.out.println("ans " + search(arr, target));
    }

    // method for searching in the rotated array
    static int search(int[] nums, int target) {
        // first find the pivot
        int pivot = findPivot(nums);

        // if you did not find the pivot, it means the array is not rotated
        // then perform normal binary search
        if (pivot == -1) {
            return binarySearch(nums, target, 0, nums.length - 1);
        }

        // 3 CASES to find the target using pivot
        // if pivot is found, you have found 2 ascending sorted arrays

        // CASE 1
        if (nums[pivot] == target) {
            System.out.println("inside case 1");
            return pivot;
        }

        // CASE 2 target > start
        // if target is less than start, i.e. we can ignore elements after pivot, as
        // they are smaller than start and the start > target > elements after pivot
        // [4,5,6,7,0,1,2] let target = 6, pivot = 7
        if (target >= nums[0]) {
            // search is 1st half
            System.out.println("inside case 2");

            return binarySearch(nums, target, 0, pivot - 1);
        }

        // CASE 3 target < start
        // we know that all elements from start -> pivot are going to be greater than
        // the target
        // [4,5,6,7,0,1,2] let target = 1, pivot = 7
        // search in the 2nd half
        System.out.println("inside case 3");

        return binarySearch(nums, target, pivot + 1, nums.length - 1);

    }

    // method for normal binary search
    static int binarySearch(int[] arr, int target, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (target < arr[mid]) {
                end = mid - 1;
            } else if (target > arr[mid]) {
                start = mid + 1;
            } else {
                // ans found
                return mid;
            }
        }
        return -1;
    }

    // method to find the pivot element in the array
    static int findPivot(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;

            // 4 cases to find pivot

            // CASE 1
            // what if mid is the last index of the array!?
            // first check if the mid is less than the end or else 'index out of bound'
            // error will be countered
            // if the element at mid is greater than the next element, that is the pivot
            // [3,4,5,2,1] let 5 = mid
            if (mid < end && arr[mid] > arr[mid + 1]) {
                return mid;
            }

            // CASE 2
            // what if mid is the first index of the array!?
            // first check if the mid is greater than start to avoid "index out of bound"
            // error
            // if the element at mid is smaller than the previous element, return the
            // previous element
            // [3,4,1,0] let 1 = mid
            if (mid > start && arr[mid] < arr[mid - 1]) {
                return mid - 1;
            }

            // CASE 3
            // if the mid element is less than or equal to start element, it means that all
            // the elements after mid are smaller than the start, so we can ignore those
            // elements as we want to find the largest element i.e. pivot
            // [3,4,0,1,2] let mid = 0
            if (arr[mid] <= arr[start]) {
                end = mid - 1;
            } else {

                // CASE 4
                // if start < mid, that means pivot lies somewhere after the mid or else if
                // would have been caught in 1st two cases
                // [2,3,4,0,1] let mid = 3
                start = mid + 1;
            }

        }

        return -1;
    }

    // find pivot in an array with duplicate elements
    static int findPivotWithDuplicates(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;

            // 4 cases to find pivot

            // CASE 1
            // what if mid is the last index of the array!?
            // first check if the mid is less than the end or else 'index out of bound'
            // error will be countered
            // if the element at mid is greater than the next element, that is the pivot
            // [3,4,5,2,1] let 5 = mid
            if (mid < end && arr[mid] > arr[mid + 1]) {
                return mid;
            }

            // CASE 2
            // what if mid is the first index of the array!?
            // first check if the mid is greater than start to avoid "index out of bound"
            // error
            // if the element at mid is smaller than the previous element, return the
            // previous element
            // [3,4,1,0] let 1 = mid
            if (mid > start && arr[mid] < arr[mid - 1]) {
                return mid - 1;
            }

            // if elements at middle, start, and end are equal then just skip the duplicates
            // as we want to find the pivot so it is better to skip as many duplicate values
            // as possible because the same element(potential pivot) is present at 3 places
            if (arr[mid] == arr[start] && arr[mid] == arr[end]) {
                // skip the duplicates

                // NOTE: what if these elements at start and end were pivot?

                // check if start is pivot
                if (arr[start] > arr[start + 1]) {
                    return start;
                }
                start++;

                // check whether end is pivot
                if (arr[end] < arr[end - 1]) {
                    return end - 1;
                }
                end--;
            }
            // left side is sorted, so pivot should be in the right
            else if (arr[start] < arr[mid] || (arr[start] == arr[mid] && arr[mid] > arr[end])) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

}
