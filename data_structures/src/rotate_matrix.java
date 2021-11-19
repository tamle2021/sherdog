/*
Given an n x n 2D matrix representing an image, perform rotation on image by 90 degrees (clockwise).
One has to rotate the image in-place, which means to modify the input 2D matrix directly.
Do not allocate another 2D matrix to do the rotation.

**** rotate matrix or image ****
 */
import java.util.Arrays;

class RotateMatrix {
    public static void main(String[] args) {
        RotateMatrix rm = new RotateMatrix();
        int[][] matrix = {{4,2,7,3},{22,0,5,6},{3,11,8,9},{7,6,13,5}};

        System.out.println("**** rotate matrix or image ****");
        System.out.println("before rotate: ");
        System.out.println(Arrays.deepToString(matrix));
        rm.start(matrix);
        System.out.println("after rotate: ");
        System.out.println(Arrays.deepToString(matrix));

    }

    public boolean start(int[][] matrix) {
        if (matrix.length == 0 || matrix.length != matrix[0].length)
            return false;
        int n = matrix.length;
        for (int layer = 0; layer < n / 2; layer++) {
            int first = layer;
            int last = n - 1 - layer;
            for (int i = first; i < last; i++) {
                int offset = i - first;
                int top = matrix[first][i];
                matrix[first][i] = matrix[last - offset][first];
                matrix[last - offset][first] = matrix[last][last - offset];
                matrix[last][last - offset] = matrix[i][last];
                matrix[i][last] = top;
            }
        }
        return true;
    }
}
