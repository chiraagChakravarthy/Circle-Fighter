package circle_fighter.game.object.bounds.renderBase;

import circle_fighter.color.DynamicColor;
import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.game.object.bounds.PolygonBound;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.UpdatingPosition;

import java.awt.*;

public class PolygonBase extends PolygonBound implements RenderBase {
    private Polygon polygon;
    private DynamicColor color;

    public PolygonBase(DynamicColor color, UpdatingPosition position, Position[] relative) {
        super(position, relative);
        this.color = color;
    }

    public PolygonBase(Position position){
        super(position);
    }


    @Override
    public void render(Graphics2D g) {
        if(polygon != null){
            g.setColor(color.get());
            g.fill(polygon);
        }
    }

    @Override
    public void tick() {
        color.tick();
    }

    @Override
    protected void update() {
        super.update();
        int[] x = new int[absolute.length],
                y = new int[absolute.length];
        for (int i = 0; i < absolute.length; i++) {
            x[i] = (int) absolute[i].getX();
            y[i] = (int) absolute[i].getY();
        }
        polygon = new Polygon(x, y, absolute.length);
    }

    public void setColor(DynamicColor color) {
        this.color = color;
    }

    //TODO refer to same TODO in Circular Base
    @Override
    public void setColor(DynamicColor... color) {
        this.color = color[0];
    }
}
