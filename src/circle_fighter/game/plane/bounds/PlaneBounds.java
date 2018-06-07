package circle_fighter.game.plane.bounds;

import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.position.Position;

public abstract class PlaneBounds{
    public abstract float exceedsLeftBy(GameObject object);
    public abstract float exceedsRightBy(GameObject object);
    public abstract float exceedsTopBy(GameObject object);
    public abstract float exceedsBottomBy(GameObject object);
    public abstract Position getPositionOffset(Position targetOffset);
}
