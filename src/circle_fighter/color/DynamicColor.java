package circle_fighter.color;

import java.awt.*;

public abstract class DynamicColor {
    protected double r, g, b;
    private double brightness, o;
    public DynamicColor(double r, double g, double b, double o, double brightness) {
        this.r=r;
        this.g=g;
        this.b=b;
        this.o=o;
        this.brightness = brightness;
    }

    public DynamicColor(double r, double g, double b, double brightness){
        this(r, g, b, 1, brightness);
    }

    public abstract void tick();

    public double getO() {
        return o;
    }

    public void setO(double o) {
        this.o = o;
    }

    public abstract void reset();

    public Color get(){
        return new Color((int)(r*brightness), (int)(g*brightness), (int)((b*brightness)), (int)(o*255));
    }
}
