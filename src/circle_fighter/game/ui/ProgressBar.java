package circle_fighter.game.ui;

import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.gfx.color.DynamicColor;

import java.awt.*;

public class ProgressBar implements Renderable, Updatable {
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

    @Override
    public void tick() {
        borderColor.tick();
        barColor.tick();
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(borderColor.get());
        g.fillRoundRect((int)x, (int)y, (int)width, (int)height, (int)height/5, (int)height/5);
        g.setColor(barColor.get());
        g.setStroke(new BasicStroke(height/30.0f));
        g.drawRoundRect((int)barX, (int)barY, ((int)barWidth), (int)barHeight, (int)barHeight/5, (int)barHeight/5);
        g.fillRoundRect((int)barX, (int)barY, (int)(barWidth*progress), (int)barHeight, (int)barHeight/5, (int)barHeight/5);
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
}
