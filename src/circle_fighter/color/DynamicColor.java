package circle_fighter.color;

import circle_fighter.file.DataStorage;
import circle_fighter.functionaliy.Saveable;
import circle_fighter.game.object.objects.Player;

import java.awt.*;

public abstract class DynamicColor implements Saveable{
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

    public DynamicColor(Player player){
        //ignored constructor. Necessary for user element registry
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

    public void from(DataStorage storage){
        storage.set(0, Float.floatToIntBits(r)).set(1, Float.floatToIntBits(g)).set(2, Float.floatToIntBits(b)).set(3, Float.floatToIntBits(o)).set(4, Float.floatToIntBits(brightness));
    }

    public void to(DataStorage storage){
        r = Float.intBitsToFloat(storage.get(0));
        g = Float.intBitsToFloat(storage.get(1));
        b = Float.intBitsToFloat(storage.get(2));
        o = Float.intBitsToFloat(storage.get(3));
        brightness = Float.intBitsToFloat(storage.get(4));
    }
}
