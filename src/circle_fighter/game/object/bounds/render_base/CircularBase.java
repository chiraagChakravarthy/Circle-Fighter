package circle_fighter.game.object.bounds.render_base;

import circle_fighter.gfx.color.DynamicColor;
import circle_fighter.file.DataStorage;
import circle_fighter.game.object.bounds.CircularBound;
import circle_fighter.game.object.position.UpdatingPosition;
import circle_fighter.gfx.color.ColorRegistry;
import circle_fighter.user.element.UserBase;

import java.awt.*;

public class CircularBase extends CircularBound implements RenderBase {
    private DynamicColor inner, outer;
    public CircularBase(UpdatingPosition position, float radius, DynamicColor inner, DynamicColor outer){
        super(position, radius);
        this.inner = inner;
        this.outer = outer;
    }

    public CircularBase(UpdatingPosition position, UserBase base){
        super(position, base);
        inner = base.getInner();
        outer = base.getOuter();
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

    //TODO make class/library for shader, create shaded interface, then that will be supplied here
    @Override
    public void setColor(DynamicColor... color) {
        inner = color[0];
        outer = color[1];
    }
}