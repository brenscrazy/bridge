package bridge.drawing;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

public class AWTDrawingApi  extends Frame implements DrawingApi {

    private final List<Ellipse2D> circles = new ArrayList<>();
    private final List<Line> lines = new ArrayList<>();

    public AWTDrawingApi(int width, int height) {
        setSize(width, height);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D ga = (Graphics2D)g;
        ga.setPaint(Color.black);
        lines.forEach(line -> ga.drawLine(line.getX1(), line.getY1(), line.getX2(), line.getY2()));
        for (int i = 0; i < circles.size(); i++) {
            ga.setPaint(new Color(180, 0, (int) Math.floor((i + 1.0) / circles.size() * 255)));
            ga.fill(circles.get(i));
        }
    }

    @Override
    public int getDrawingAreaWidth() {
        return getWidth();
    }

    @Override
    public int getDrawingAreaHeight() {
        return getHeight();
    }

    @Override
    public void drawCircle(float x, float y, float r) {
        circles.add(new Ellipse2D.Float(x - r, y - r, 2 * r, 2 * r));
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        lines.add(new Line(x1, y1, x2, y2));
    }

    @Override
    public void display() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        AWTDrawingApi frame = new AWTDrawingApi(500, 500);
        frame.drawCircle(1, 2, 20);
        frame.drawLine(1, 2, 100, 100);
        frame.setVisible(true);
    }

    private static class Line {

        private final int x1;
        private final int y1;
        private final int x2;
        private final int y2;

        private Line(float x1, float y1, float x2, float y2) {
            this.x1 = Math.round(x1);
            this.y1 = Math.round(y1);
            this.x2 = Math.round(x2);
            this.y2 = Math.round(y2);
        }

        public int getY1() {
            return y1;
        }

        public int getX1() {
            return x1;
        }

        public int getX2() {
            return x2;
        }

        public int getY2() {
            return y2;
        }
    }

}
