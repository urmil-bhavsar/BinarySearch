// how to apply binary search in a sorted matrix

// the goal is to minimize the no. of rows to be searched
// the time complexity will be O(logn + logn)

import java.util.Arrays;

public class BinarySearchInSortedMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
                { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 },
                { 13, 14, 15, 16 }
        };

        int target = 16;
        System.out.println(Arrays.toString(search(matrix, target)));
    }



    static int[] search(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length; // be cautious, matrix may be empty

        if (rows == 1) {
            return binarySearch(matrix, 0, 0, cols - 1, target);
        }

        int rowStart = 0;
        int rowEnd = rows - 1;
        int colMid = cols / 2;

        // run the loop till 2 rows are remaining
        while (rowStart < (rowEnd - 1)) { // while this is true it will have more than 2 rows because for having only 2
                                          // rows i.e. row 0 & 1, 1 & 2, 2 & 3, 3 & 4, this condition will be satisfied

            int mid = rowStart + (rowEnd - rowStart) / 2;
            System.out.println(matrix[mid][colMid]);
            if (matrix[mid][colMid] == target) {
                return new int[] { mid, colMid };
            }

            if (matrix[mid][colMid] < target) {
                rowStart = mid;
            } else {
                rowEnd = mid;
            }
        }

        // now we have two rows after the above loop
        // check whether the target is in the column of 2 rows

        // check if the 1st element in the middle column is the answer
        if (matrix[rowStart][colMid] == target) {
            return new int[] { rowStart, colMid };
        }
        // check if the 2nd element in the middle column is the answer
        if (matrix[rowStart + 1][colMid] == target) {
            return new int[] { rowStart + 1, colMid };
        }

        // if answer not found in the middle column, try to search in the other 4 row
        // halfves

        // { 1, 2, 3, 4 },
        // { 5, 6, 7, 8 },
        // { 9, 10, 11, 12 },
        // { 13, 14, 15, 16 }
        // in the above matrix after the above loop only 1st 2 rows will be remaining
        // { 1, 2, 3, 4 },
        // { 5, 6, 7, 8 },
        // here {2,6} is the mid column
        // {1}: 1st half, {5}: 2nd half, {3,4}: 3rd half, {7,8}: 4th half

        // search in 1st half
        if (target <= matrix[rowStart][colMid - 1]) {
            return binarySearch(matrix, rowStart, 0, colMid - 1, target);
        }

        // search in 2nd half
        // check if the target is greater than the 1st element of 2nd half and less than
        // the last element of the 2nd half
        if (target >= matrix[rowStart][colMid + 1] && target <= matrix[rowStart][cols - 1]) {
            return binarySearch(matrix, rowStart, colMid + 1, cols - 1, target);
        }

        // search in the 3rd half
        if (target <= matrix[rowStart + 1][colMid - 1]) {
            return binarySearch(matrix, rowStart + 1, 0, colMid - 1, target);
        } else {
            // else search in the 4th half
            return binarySearch(matrix, rowStart + 1, colMid + 1, cols - 1, target);
        }

    }





    // perform binary search in the row provided between the columns provided
    static int[] binarySearch(int[][] matrix, int row, int colStart, int colEnd, int target) {
        while (colStart <= colEnd) {

            // find the middle
            int mid = colStart + (colEnd - colStart) / 2;

            if (matrix[row][mid] == target) {
                return new int[] { row, mid };
            }
            if (matrix[row][mid] < target) {
                colStart = mid + 1;
            } else {
                colEnd = mid - 1;
            }
        }

        return new int[] { -1, -1 };
    }
}
