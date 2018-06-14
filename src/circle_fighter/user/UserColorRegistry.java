package circle_fighter.user;

import circle_fighter.color.DynamicColor;
import circle_fighter.color.Rainbow;
import circle_fighter.color.SolidColor;
import circle_fighter.file.DataStorage;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class UserColorRegistry {
    private static ArrayList<Class<DynamicColor>> colors;
    static {
        colors = new ArrayList(Arrays.asList(Rainbow.class, SolidColor.class));
    }

    public static int toID(Class<DynamicColor> color){
        return colors.indexOf(color);
    }

    public static DynamicColor fromID(int id, DataStorage storage){
        try {
            DynamicColor color = colors.get(id).getConstructor().newInstance();
            color.from(storage);
            return color;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}