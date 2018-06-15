package circle_fighter.gfx.color;

import circle_fighter.file.DataStorage;
import circle_fighter.functionaliy.HardSavable;
import circle_fighter.functionaliy.Savable;

import java.awt.*;

public abstract class DynamicColor implements Savable, HardSavable {
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

    public void setBrightness(float brightness) {
        this.brightness = brightness;
    }

    @Override
    public void hardSave(DataStorage storage) {
        storage.setFloat(0, r).setFloat(1, g).setFloat(2, b).setFloat(3, o).setFloat(4, brightness);
    }
    //TODO finish implimenting hard/soft saving and loading for all in game classes
    @Override
    public void hardLoad(DataStorage storage) {
        r = storage.getFloat(0);
        g = storage.getFloat(1);
        b = storage.getFloat(2);
        o = storage.getFloat(3);
        brightness = storage.getFloat(4);
    }
}