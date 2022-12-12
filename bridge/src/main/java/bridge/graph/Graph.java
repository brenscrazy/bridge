package bridge.graph;

import bridge.drawing.DrawingApi;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Graph {

    private final int RADIUS = 15;
    private final int DISTANCE_OF_NODES_FROM_CENTER = 200;

    protected final int nodeAmount;
    private final List<Point2D.Float> middles = new ArrayList<>();
    private final DrawingApi drawingApi;

    public Graph(int nodeAmount, DrawingApi drawingApi) {
        if (nodeAmount < 0) {
            throw new IllegalArgumentException("Amount of nodes should be >= 0");
        }
        this.nodeAmount = nodeAmount;
        this.drawingApi = drawingApi;
        defineMiddles();
    }

    private void defineMiddles() {
        int centerX = drawingApi.getDrawingAreaWidth() / 2;
        int centerY = drawingApi.getDrawingAreaHeight() / 2;
        for (int i = 0; i < nodeAmount; i++) {
            float x = (float) (centerX + Math.cos(Math.PI / 2 - Math.PI / nodeAmount * i * 2 ) * DISTANCE_OF_NODES_FROM_CENTER);
            float y = (float) (centerY - Math.sin(Math.PI / 2 - Math.PI / nodeAmount * i * 2 ) * DISTANCE_OF_NODES_FROM_CENTER);
            middles.add(new Point2D.Float(x, y));
        }

    }

    public void drawGraph() {
        drawCircles();
        drawLines();
        drawingApi.display();
    }

    protected abstract void drawLines();

    protected void drawCircles() {
        middles.forEach(point2D -> drawingApi.drawCircle(
                (float) point2D.getX(), (float) point2D.getY(), RADIUS));
    }

    protected void connectCircles(int i, int j) {
        Point2D.Float point1 = middles.get(i);
        Point2D.Float point2 = middles.get(j);
        drawingApi.drawLine((int) point1.getX(), (int) point1.getY(), (int) point2.getX(), (int) point2.getY());
    }

    public abstract void buildGraphFromConsole(Scanner sc);

}
