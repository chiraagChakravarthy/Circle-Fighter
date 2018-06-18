package circle_fighter.game.ui;

import circle_fighter.file.DataStorage;
import circle_fighter.functionaliy.Savable;
import circle_fighter.gfx.color.DynamicColor;
import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.gfx.color.ColorRegistry;

import java.awt.*;

public class ProgressBar implements Renderable, Updatable, Savable {
    private static final float BORDER_PROPORTION = 0.1f;
    private float progress, x, y, width, height, barX, barWidth, barY, barHeight;
    private DynamicColor borderColor, barColor;
    public ProgressBar(float x, float y, float width, float height, DynamicColor borderColor, DynamicColor barColor){
        this.width = width;
        this.height = height;
        this.progress = 1;
        this.borderColor = borderColor;
        this.barColor = barColor;
        barWidth = width*(1- BORDER_PROPORTION);
        barHeight = height-width*BORDER_PROPORTION;
        setX(x);
        setY(y);
    }

    public ProgressBar(DataStorage storage){
        this.width = storage.get(0);
        this.height = storage.get(1);
        progress = 1;
        borderColor = ColorRegistry.fromID(storage.get(2), storage.getSubStorage(0));
        barColor = ColorRegistry.fromID(storage.get(3), storage.getSubStorage(1));
        barWidth = width*(1- BORDER_PROPORTION);
        barHeight = height-width*BORDER_PROPORTION;
        setX(storage.getFloat(4));
        setY(storage.getFloat(5));
    }

    @Override
    public void tick() {
        borderColor.tick();
        barColor.tick();
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(borderColor.get());
        g.fillRect((int)x, (int)y, (int)width, (int)height);
        g.setColor(barColor.get());
        g.fillRect((int)barX, (int)barY, (int)(barWidth*progress), (int)barHeight);
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public void setBarColor(DynamicColor barColor) {
        this.barColor = barColor;
    }

    public void setBorderColor(DynamicColor borderColor) {
        this.borderColor = borderColor;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
        barX = (width-barWidth)/2+x;
    }

    public void setY(float y) {
        this.y = y;
        barY = (height-barHeight)/2+y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    @Override
    public void save(DataStorage storage) {

    }
}
