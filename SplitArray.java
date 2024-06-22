public class SplitArray {

    // https://leetcode.com/problems/split-array-largest-sum/submissions/1296906134/
    public static void main(String[] args) {
        int[] arr = { 7, 2, 5, 8, 10 };
        int m = 3;
        int ans = splitArray(arr, m);
        System.out.println(ans);
    }

    public static int splitArray(int[] nums, int m) {
        int start = 0;
        int end = 0;

        for (int i = 0; i < nums.length; i++) {
            // start will be the max element in the array
            // if we want to split array in N parts, N will be the array's length, [1,2,3] =
            // [1][2][3] so ans will be 3
            start = Math.max(start, nums[i]);

            // if we want to split the array in one part, ans will be the sum of all
            // elements of that array, [1,2,3] => 6
            end += nums[i];
        }

        // binary search
        while (start < end) {
            // try for the middle as the potential answer
            int mid = start + (end - start) / 2;

            // calculate how many pieces you can divide this in with this max sum
            int sum = 0;
            int pieces = 1;
            for (int num : nums) {
                if (sum + num > mid) {
                    // you cannot add this in this subarray, make new subrray
                    // say you add this num in new subarray, then sum = num
                    sum = num;
                    pieces++;
                } else {
                    sum += num;
                }
            }

            if (pieces > m) {
                // suppose sum = 9, arr = [7,2,5,8,10], so 4 pieces, but m = 2
                start = mid + 1;
            } else {
                // suppose sum = 10, arr = [1,2,3,4], so 1 piece, but m = 2
                end = mid;
            }

        }
        return end; // here start == end
    }

}
