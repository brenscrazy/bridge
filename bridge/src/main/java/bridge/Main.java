package bridge;

import bridge.drawing.AWTDrawingApi;
import bridge.drawing.DrawingApi;
import bridge.drawing.JavaFxDrawingApi;
import bridge.graph.EdgesListGraph;
import bridge.graph.Graph;
import bridge.graph.MatrixGraph;

import java.util.Scanner;

public class Main {

    private final static int WINDOW_HEIGHT = 800;
    private final static int WINDOW_WIDTH = 1000;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        DrawingApi drawingApi = null;
        while (drawingApi == null) {
            System.out.println("Please enter graphic framework to use:\n" +
                    "awt - java awt\n" +
                    "fx - javafx");
            String framework = sc.next().strip();
            switch (framework) {
                case "awt" -> drawingApi = new AWTDrawingApi(WINDOW_WIDTH, WINDOW_HEIGHT);
                case "fx" -> drawingApi = new JavaFxDrawingApi(WINDOW_WIDTH, WINDOW_HEIGHT);
            }
        }
        Graph graph = null;
        while (graph == null) {
            try {
                System.out.println("Please enter a way to define a graph and amount of nodes:\n" +
                        "matrix N - use adjacency matrix\n" +
                        "edges N - list edges");
                String graphType = sc.next().strip();
                int n = sc.nextInt();
                switch (graphType) {
                    case "matrix" -> graph = new MatrixGraph(n, drawingApi);
                    case "edges" -> graph = new EdgesListGraph(n, drawingApi);
                }
                if (graph == null) {
                    continue;
                }
                graph.buildGraphFromConsole(sc);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        graph.drawGraph();
    }

}
