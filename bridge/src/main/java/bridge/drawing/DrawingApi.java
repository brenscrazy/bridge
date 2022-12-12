package bridge.drawing;

public interface DrawingApi {
    int getDrawingAreaWidth();
    int getDrawingAreaHeight();

    void drawCircle(float x, float y, float r);

    void drawLine(int x1, int y1, int x2, int y2);

    void display();
}
