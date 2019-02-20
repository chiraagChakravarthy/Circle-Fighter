package circle_fighter.gfx.text;

public class Line {
    private String text;
    private int x, y, lineWidth;

    public Line(String text, int x, int y, int lineWidth){
        this.text = text;
        this.x = x;
        this.y = y;
        this.lineWidth = lineWidth;
    }

    public String getText() {
        return text;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getLineWidth() {
        return lineWidth;
    }
}
