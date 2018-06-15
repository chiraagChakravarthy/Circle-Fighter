package circle_fighter.game.object.bounds.renderBase;

import circle_fighter.gfx.color.DynamicColor;
import circle_fighter.file.DataStorage;
import circle_fighter.game.object.bounds.CircularBound;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.UpdatingPosition;
import circle_fighter.user.UserColorRegistry;

import java.awt.*;

public class CircularBase extends CircularBound implements RenderBase {
    private DynamicColor inner, outer;
    public CircularBase(UpdatingPosition position, float radius, DynamicColor inner, DynamicColor outer){
        super(position, radius);
        this.inner = inner;
        this.outer = outer;
    }

    public CircularBase(UpdatingPosition position, DataStorage storage){
        super(position, storage.getSubStorage(0));
        inner = UserColorRegistry.fromID(storage.get(0), storage.getSubStorage(1));
        outer = UserColorRegistry.fromID(storage.get(1), storage.getSubStorage(2));
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

    @Override
    public void save(DataStorage storage) {
        super.save(storage.getSubStorage(0));
        storage.set(0, UserColorRegistry.toID(inner.getClass()));
        inner.save(storage.getSubStorage(1));
        storage.set(1, UserColorRegistry.toID(outer.getClass()));
        outer.save(storage.getSubStorage(2));
    }
}