package circle_fighter.game.ui;

import circle_fighter.gfx.color.DynamicColor;
import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;

import java.awt.*;

public class ProgressBar implements Renderable, Updatable {
    private static final double BORDER_PROPORTION = 0.1;
    private double progress, x, y, width, height, barX, barWidth, barY, barHeight;
    private DynamicColor borderColor, barColor;
    public ProgressBar(double x, double y, double width, double height, double progress, DynamicColor borderColor, DynamicColor barColor){
        this.width = width;
        this.height = height;
        this.progress = progress;
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
        g.fillRect((int)x, (int)y, (int)width, (int)height);
        g.setColor(barColor.get());
        g.fillRect((int)barX, (int)barY, (int)(barWidth*progress), (int)barHeight);
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public void setBarColor(DynamicColor barColor) {
        this.barColor = barColor;
    }

    public void setBorderColor(DynamicColor borderColor) {
        this.borderColor = borderColor;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
        barX = (width-barWidth)/2+x;
    }

    public void setY(double y) {
        this.y = y;
        barY = (height-barHeight)/2+y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
