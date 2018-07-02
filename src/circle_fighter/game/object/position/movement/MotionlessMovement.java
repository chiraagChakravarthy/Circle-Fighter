package circle_fighter.game.object.position.movement;

import circle_fighter.file.DataStorage;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.UpdatingPosition;
import circle_fighter.game.object.position.Vector;

public class MotionlessMovement extends MovementVector {

    public MotionlessMovement(UpdatingPosition position, Vector vector) {
        super(position, vector);
    }

    @Override
    public void tick() {

    }

    @Override
    public void save(DataStorage storage) {

    }
}
