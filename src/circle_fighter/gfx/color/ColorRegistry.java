package circle_fighter.gfx.color;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

public class ColorRegistry {
    private static ArrayList<Class<? extends DynamicColor>> colors;

    static {
        colors = new ArrayList(Arrays.asList(Rainbow.class, Red.class, Blue.class));
    }

    public static int toID(Class<? extends DynamicColor> color) {
        return colors.indexOf(color);
    }

    public static DynamicColor fromID(int id) {
        try {
            return colors.get(id).getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}