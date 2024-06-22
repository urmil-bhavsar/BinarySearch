public class PeakIndexInMountainArray {
    public static void main(String[] args) {
        int[] arr = { 2, 3, 4, 5, 10, 20, 10, 5, 3 };
        System.out.println("ans:" + " " + peakIndexInMountainArray(arr) + " " + arr[peakIndexInMountainArray(arr)]);
    }

    static int peakIndexInMountainArray(int[] arr) {

        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] > arr[mid + 1]) {
                // you are in the decreasing part of the array

                // this might be the answer, but look at the left portion of the array
                // this is why end != mid -1
                end = mid;
            } else {
                // you are in the ascending part of the array
                start = mid + 1; // because we know that mid+1 element > mid element
                // so mid+1 might be the answer but also look at the right portion
            }
        }
        // in the end, start == end and pointing to the largest number because of the 2
        // checks

        // start and end are always trying to find the max element in the above 2 checks

        // hence, when they are pointing to just one element, that is the max number and
        // hence the answer

        return start; // or return end as both are equal
    }
}