package circle_fighter.game.object.position.movement;

import circle_fighter.file.DataStorage;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.UpdatingPosition;
import circle_fighter.game.object.position.Vector;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

public class MovementRegistry {
    private static ArrayList<Class<? extends MovementVector>> movements;
    static {
        movements = new ArrayList(Arrays.asList(MultiDirectionalMovement.class, OmniDirectionalMovement.class, VelAccMovement.class, VelAngAccMovement.class, MotionlessMovement.class));
    }

    public static int toID(Class<? extends MovementVector> movement){
        return movements.indexOf(movement);
    }

    public static MovementVector fromID(int id, DataStorage storage, UpdatingPosition position, Vector vector){
        try {
            return movements.get(id).getConstructor(UpdatingPosition.class, Vector.class, DataStorage.class).newInstance(position, vector, storage);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
