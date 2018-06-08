package circle_fighter.game.object.bounds.renderBase;

import circle_fighter.color.DynamicColor;
import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.game.object.bounds.CircularBound;
import circle_fighter.game.object.position.Position;

import java.awt.*;

public class CircularBase extends CircularBound implements Updatable, Renderable {
    private DynamicColor inner, outer;
    public CircularBase(Position position, double radius, DynamicColor inner, DynamicColor outer){
        super(position, radius);
        this.inner = inner;
        this.outer = outer;
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(outer.get());
        g.fillOval((int)(centerPoint.getScrX()-radius), (int)(centerPoint.getScrY()-radius), (int)(2*radius), (int)(2*radius));
        g.setColor(inner.get());
        g.fillOval((int)(centerPoint.getScrX()-radius/2), (int)(centerPoint.getScrY()-radius/2), (int)(radius), (int)(radius));
    }

    @Override
    public void tick() {
        inner.tick();
        outer.tick();
    }
}
