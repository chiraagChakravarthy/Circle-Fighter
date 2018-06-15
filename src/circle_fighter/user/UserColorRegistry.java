package circle_fighter.user;

import circle_fighter.gfx.color.DynamicColor;
import circle_fighter.gfx.color.Rainbow;
import circle_fighter.gfx.color.SolidColor;
import circle_fighter.file.DataStorage;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

public class UserColorRegistry {
    private static ArrayList<Class<? extends DynamicColor>> colors;
    static {
        colors = new ArrayList(Arrays.asList(Rainbow.class, SolidColor.class));
    }

    public static int toID(Class<? extends DynamicColor> color){
        return colors.indexOf(color);
    }

    public static DynamicColor fromID(int id, DataStorage storage){
        try {
            return colors.get(id).getConstructor(DataStorage.class).newInstance(storage);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}     