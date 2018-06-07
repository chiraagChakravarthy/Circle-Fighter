package circle_fighter.game.plane.bounds;

import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.position.Position;

public class UnboundedBounds extends PlaneBounds {
    @Override
    public float exceedsLeftBy(GameObject object) {
        return 0;
    }

    @Override
    public float exceedsRightBy(GameObject object) {
        return 0;
    }

    @Override
    public float exceedsTopBy(GameObject object) {
        return 0;
    }

    @Override
    public float exceedsBottomBy(GameObject object) {
        return 0;
    }

    @Override
    public Position getPositionOffset(Position targetOffset) {
        return targetOffset.clone();
    }
}
