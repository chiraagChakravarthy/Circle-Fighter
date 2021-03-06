package circle_fighter.gfx.color;

import java.awt.*;

public abstract class DynamicColor {
    protected float r, g, b;
    private float brightness, o;
    public DynamicColor(float r, float g, float b, float o, float brightness) {
        this.r=r;
        this.g=g;
        this.b=b;
        this.o=o;
        this.brightness = brightness;
    }

    public DynamicColor(){
        this(0, 0, 0, 1);
    }

    public DynamicColor(float r, float g, float b, float brightness){
        this(r, g, b, 1, brightness);
    }

    public abstract void tick();

    public float getO() {
        return o;
    }

    public void setO(float o) {
        this.o = o;
    }

    public abstract void reset();

    public Color get(){
        return new Color((int)(r*brightness), (int)(g*brightness), (int)((b*brightness)), (int)(o*255));
    }

    public float getBrightness() {
        return brightness;
    }

    public DynamicColor setBrightness(float brightness) {
        this.brightness = brightness;
        return this;
    }

    public abstract DynamicColor clone();
}