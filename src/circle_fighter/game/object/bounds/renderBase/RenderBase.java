package circle_fighter.game.object.bounds.renderBase;

import circle_fighter.gfx.color.DynamicColor;
import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Savable;
import circle_fighter.functionaliy.Updatable;

public interface RenderBase extends Updatable, Renderable, Savable {
    void setColor(DynamicColor... color);
}