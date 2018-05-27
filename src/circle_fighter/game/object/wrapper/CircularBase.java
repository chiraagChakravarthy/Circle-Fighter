package circle_fighter.game.object.wrapper;

import circle_fighter.color.DynamicColor;
import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.game.object.position.Position;

import java.awt.*;

public class CircularBase implements Updatable, Renderable {
    private DynamicColor inner, outer;
    private double radius;
    private Position position;
    public CircularBase(double radius, DynamicColor inner, DynamicColor outer, Position position){
        this.radius = radius;
        this.inner = inner;
        this.outer = outer;
        this.position = position;
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(outer.get());
        g.fillOval((int)(position.getScrX()-radius), (int)(position.getScrY()-radius), (int)(2*radius), (int)(2*radius));
        g.setColor(inner.get());
        g.fillOval((int)(position.getScrX()-radius/2), (int)(position.getScrY()-radius/2), (int)(radius), (int)(radius));
    }

    @Override
    public void tick() {
        inner.tick();
        outer.tick();
    }
}
