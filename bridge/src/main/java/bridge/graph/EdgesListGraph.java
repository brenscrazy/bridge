package bridge.graph;

import bridge.drawing.DrawingApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EdgesListGraph extends Graph {

    private final List<int[]> edges;

    public EdgesListGraph(int nodeAmount, DrawingApi drawingApi) {
        super(nodeAmount, drawingApi);
        edges = new ArrayList<>();
    }

    private void validateEdges(List<int[]> edges) {
        for (int[] edge : edges) {
            if (edge.length != 2) {
                throw new IllegalArgumentException("Every edge should contain 2 numbers");
            }
            if (edge[0] < 1 || edge[0] > nodeAmount || edge[1] < 1 || edge[1] > nodeAmount) {
                throw new IllegalArgumentException("Every node number should be >= 1 and <= " + nodeAmount);
            }
        }
    }

    @Override
    protected void drawLines() {
        for (int[] edge : edges) {
            int x = edge[0] - 1;
            int y = edge[1] - 1;
            connectCircles(x, y);
        }
    }

    @Override
    public void buildGraphFromConsole(Scanner sc) {
        System.out.println("Please enter pair of numbers to define edges of the graph. When you are done type -1");
        int x = sc.nextInt();
        while (x != -1) {
            int y = sc.nextInt();
            edges.add(new int[]{x, y});
            x = sc.nextInt();
        }
        validateEdges(edges);
    }
}
