package circle_fighter.user;

import circle_fighter.file.DataStorage;
import circle_fighter.game.object.bounds.Bound;
import circle_fighter.game.object.bounds.renderBase.CircularBase;
import circle_fighter.game.object.bounds.renderBase.PolygonBase;
import circle_fighter.game.object.position.Position;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

public class UserBaseRegistry {
    private static ArrayList<Class<? extends Bound>> bounds;
    static {
        bounds = new ArrayList(Arrays.asList(CircularBase.class, PolygonBase.class));
    }

    public static int toID(Class<Bound> bound){
        return bounds.indexOf(bound);
    }

    public static Bound fromID(int id, DataStorage storage, Position position){
        try {
            Bound bound = bounds.get(id).getConstructor(Position.class).newInstance(position);
            bound.from(storage);
            return bound;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}