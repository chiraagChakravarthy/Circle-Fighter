package circle_fighter.text;

public class Line {
    private String text;
    private int x, y;

    public Line(String text, int x, int y){
        this.text = text;
        this.x = x;
        this.y = y;
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
}
