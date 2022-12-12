package bridge.drawing;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class JavaFxDrawingApi extends Application implements DrawingApi{

    private static final List<Shape> circles = new ArrayList<>();
    private static final List<Shape> lines = new ArrayList<>();

    private static int width = 500;
    private static int height = 500;

    public JavaFxDrawingApi() {}

    public JavaFxDrawingApi(int width, int height) {
        JavaFxDrawingApi.width = width;
        JavaFxDrawingApi.height = height;
    }


    @Override
    public int getDrawingAreaWidth() {
        return width;
    }

    @Override
    public int getDrawingAreaHeight() {
        return height;
    }

    @Override
    public void drawCircle(float x, float y, float r) {
        circles.add(new Circle(x, y, r));
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        lines.add(new Line(x1, y1, x2, y2));
    }

    @Override
    public void display() {
        Application.launch(JavaFxDrawingApi.class);
    }

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Canvas canvas = new Canvas( width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().addAll(lines);
        root.getChildren().addAll(circles);
        stage.setScene(new Scene(root));
        stage.setWidth(width);
        stage.setHeight(height);
        stage.show();
    }
}
