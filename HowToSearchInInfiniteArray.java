public class HowToSearchInInfiniteArray {
    public static void main(String[] args) {
        // suppose this is an infinite array and we don't know its length
        int[] arr = { 3, 5, 7, 9, 10, 90, 100, 130, 140, 160, 170 };

        int target = 90;
        System.out.println("ans:" + " " + ans(arr, target));
    }

    // method to find the ans
    static int ans(int[] arr, int target) {
        // first find the range
        // first start with a box of size 2
        int start = 0;
        int end = 1;

        // condition for the target to lie in the range
        while (target > arr[end]) {

            // this will be the new start
            int newStart = end + 1;

            // end = previous end + sizeOfBox * 2
            // end = end + (end - (start - 1)) * 2
            end = end + (end - start + 1) * 2;
            start = newStart;
        }
        return (binarySearch(arr, target, start, end));
    }

    static int binarySearch(int[] arr, int target, int start, int end) {
        while (start <= end) {
            // find the middle element
            // we do it like this instead of (start + end)/2 so as the index doesnot go out of bound in case of a long array
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
        // if no answer found
        return -1;
    }
}
