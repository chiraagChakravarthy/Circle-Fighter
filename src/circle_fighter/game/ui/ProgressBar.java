package circle_fighter.game.ui;

import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;

import java.awt.*;

public class ProgressBar implements Renderable, Updatable {
    private double progress, x, y, width, height;
    private Color color;
    public ProgressBar(double x, double y, double width, double height, double progress, Color color){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.progress = progress;
        this.color = color;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics2D g) {

    }
}
