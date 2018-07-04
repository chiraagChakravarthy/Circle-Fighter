package circle_fighter.user.element.turret;

import circle_fighter.file.DataStorage;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

public class UserTurretRegistry {
    private static ArrayList<Class<? extends UserTurret>> turrets;

    static {
        turrets = new ArrayList(Arrays.asList(BasicUserTurret.class));
    }

    public static int toID(Class<? extends UserTurret> turret) {
        return turrets.indexOf(turret);
    }

    public static UserTurret fromID(int id, DataStorage storage) {
        try {
            return turrets.get(id).getConstructor(DataStorage.class).newInstance(storage);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
