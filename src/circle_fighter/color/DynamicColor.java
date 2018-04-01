package circle_fighter.color;

import java.awt.*;

public abstract class DynamicColor {
    protected double r, g, b;
    private double brightness;
    public DynamicColor(double r, double g, double b, double brightness) {
        this.r=r;
        this.g=g;
        this.b=b;
        this.brightness = brightness;
    }

    public abstract void tick();

    public Color get(){
        return new Color((int)(r*brightness), (int)(g*brightness), (int)((b*brightness)));
    }
}
