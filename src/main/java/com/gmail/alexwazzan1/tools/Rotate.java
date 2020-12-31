package com.gmail.alexwazzan1.tools;

public class Rotate {

    public static int[][] rotateMatrix(int[][] matrix, int degree) {

        int row = matrix.length;
        int col = matrix[0].length;
        int[][] rotated = null;

        for (int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if (degree == 90) {
                    if (rotated == null) rotated = new int[col][row];
                    rotated[col - j - 1][i] = matrix[i][j];
                }

                if (degree == 180) {
                    if (rotated == null) rotated = new int[row][col];
                    rotated[row - i - 1][col - j - 1] = matrix[i][j];
                }

                if (degree == 270) {
                    if (rotated == null) rotated = new int[col][row];
                    rotated[j][row - i - 1] = matrix[i][j];
                }
            }
        }

        return rotated;
    }

}
