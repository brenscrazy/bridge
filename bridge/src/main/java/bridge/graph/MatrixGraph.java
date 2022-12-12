package bridge.graph;

import bridge.drawing.DrawingApi;

import java.util.Scanner;

public class MatrixGraph extends Graph{

    private boolean[][] matrix;

    public MatrixGraph(int nodeAmount, DrawingApi drawingApi) {
        super(nodeAmount, drawingApi);
        matrix = new boolean[nodeAmount][nodeAmount];
    }


    @Override
    protected void drawLines() {
        for (int i = 0; i < nodeAmount; i++) {
            for (int j = i; j < nodeAmount; j++) {
                if (matrix[i][j] || matrix[j][i]) {
                    connectCircles(i, j);
                }
            }
        }
    }

    @Override
    public void buildGraphFromConsole(Scanner sc) {
        System.out.println("Please enter graph matrix where 1 stands for having edge and 0 for not having:");
        for (int i = 0; i < nodeAmount; i++) {
            for (int j = 0; j < nodeAmount; j++) {
                int edge = sc.nextInt();
                matrix[i][j] = edge == 1;
            }
        }
        validateMatrix(matrix);
    }

    private void validateMatrix(boolean[][] matrix) {
        if (matrix.length != nodeAmount) {
            throw new IllegalArgumentException("Matrix height should be equal to amount of nodes which is " + nodeAmount
                    + ". But actual height is " + matrix.length + ".");
        }
        for (int i = 0; i < nodeAmount; i++) {
            boolean[] arr = matrix[i];
            if (arr.length != nodeAmount) {
                throw new IllegalArgumentException("Every line of matrix should have " + nodeAmount + ". But line number" +
                        + i + " has length equal to " + arr.length + ".");
            }
        }
    }

}
