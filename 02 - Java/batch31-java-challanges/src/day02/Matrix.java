package day02;

public class Matrix {

    public static void main(String[] args) {
        // displayMatrix(matrixDiagonal1(5));
        // displayMatrix(matrixDiagonal2(5, 5));
        // displayMatrix(matrixDiagonal3(7));
        displayMatrix(matrixDiagonal4(8));
    }

    public static void displayMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.printf("%3d", matrix[row][col]);
            }

            System.out.println();
        }
    }

    public static int[][] matrixDiagonal1(int n) {
        int[][] matrix = new int[n][n];

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (row == col) {
                    matrix[row][col] = row + 1;
                } else if (row > col) {
                    matrix[row][col] = 20;
                } else {
                    matrix[row][col] = 10;
                }
            }
        }

        return matrix;
    }

    public static int[][] matrixDiagonal2(int n, int m) {
        int[][] matrix = new int[n][m];
        int counter = n;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (row == col) {
                    matrix[row][col] = counter;
                    counter--;
                } else if (row > col) {
                    matrix[row][col] = 10;
                } else {
                    matrix[row][col] = 20;
                }
            }
        }

        return matrix;
    }

    public static int[][] matrixDiagonal3(int n) {
        int[][] matrix = new int[n][n];

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (row == 0) {
                    matrix[row][col] = col;
                } else if (col == 0) {
                    matrix[row][col] = row;
                } else if (row == matrix.length - 1) {
                    matrix[row][col] = row + col;
                } else if (col == matrix.length - 1) {
                    matrix[row][col] = row + col;
                }
            }
        }

        return matrix;
    }

    public static int[][] matrixDiagonal4(int n) {
        int[][] matrix = new int[n][n];

        for (int row = 0; row < matrix.length - 1; row++) {
            for (int col = 0; col < matrix[row].length - 1; col++) {
                matrix[row][col] = row + col;
                matrix[row][n - 1] += matrix[row][col];
                matrix[n - 1][col] += matrix[row][col];
            }
        }

        for (int i = 0; i < n - 1; i++) {
            matrix[n - 1][n - 1] += matrix[n - 1][i];
        }

        return matrix;
    }
}
