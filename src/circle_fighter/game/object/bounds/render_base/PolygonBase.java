package circle_fighter.game.object.bounds.render_base;

import circle_fighter.game.object.bounds.PolygonBound;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.UpdatingPosition;
import circle_fighter.gfx.color.DynamicColor;

import java.awt.*;

public class PolygonBase extends PolygonBound implements RenderBase {
    private Polygon polygon;
    private DynamicColor color;

    public PolygonBase(DynamicColor color, UpdatingPosition position, Position[] relative) {
        super(position, relative);
        this.color = color;
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
    public void onPositionChanged() {
        super.onPositionChanged();
        reconfigurePolygon();
    }

    public void setColor(DynamicColor color) {
        this.color = color;
    }

    //TODO refer to same TODO in Circular Base
    @Override
    public void setColor(DynamicColor... color) {
        this.color = color[0];
    }

    @Override
    public void onOffsetsChanged() {
        super.onOffsetsChanged();
        reconfigurePolygon();
    }

    private void reconfigurePolygon(){
        Position[] absolute = getAbsolute();
        if(absolute != null && absolute[0] != null) {
            int[] x = new int[absolute.length],
                    y = new int[absolute.length];
            for (int i = 0; i < absolute.length; i++) {
                x[i] = (int) absolute[i].getScrX();
                y[i] = (int) absolute[i].getScrY();
            }
            polygon = new Polygon(x, y, absolute.length);
        }
    }
}
