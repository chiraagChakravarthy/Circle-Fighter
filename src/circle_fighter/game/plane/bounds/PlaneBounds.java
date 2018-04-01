package circle_fighter.game.plane.bounds;

import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.position.Position;

public abstract class PlaneBounds{
    public abstract double exceedsLeftBy(GameObject object);
    public abstract double exceedsRightBy(GameObject object);
    public abstract double exceedsTopBy(GameObject object);
    public abstract double exceedsBottomBy(GameObject object);
    public abstract Position getPositionOffset(Position targetOffset);
}
