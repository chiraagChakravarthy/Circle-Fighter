package circle_fighter.game.object.bounds.render_base;

import circle_fighter.gfx.color.DynamicColor;
import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;

public interface RenderBase extends Updatable, Renderable {
    void setColor(DynamicColor... color);
}