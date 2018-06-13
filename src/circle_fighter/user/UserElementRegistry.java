package circle_fighter.user;

import circle_fighter.color.DynamicColor;
import circle_fighter.color.Rainbow;
import circle_fighter.color.SolidColor;
import circle_fighter.file.DataStorage;
import circle_fighter.functionaliy.Saveable;
import circle_fighter.game.object.objects.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class UserElementRegistry {
    private static HashMap<Class<Saveable>, ArrayList<Class<Saveable>>> elements;
    static {
        elements = new HashMap<>();
        put(DynamicColor.class, Rainbow.class, SolidColor.class);
    }

    private static void put(Class<Saveable> type, Class<Saveable>... types){
        elements.put(type, new ArrayList(Arrays.asList(types)));
    }

    public static int iDFor(Class<?> type, Class<DynamicColor> element){
        return elements.get(type).indexOf(element);
    }

    public static Object fromID(Class<?> type, int id, Player player, DataStorage storage){
        try {
            Saveable object = elements.get(type).get(id).getDeclaredConstructor(player.getClass()).newInstance(player);
            object.from(storage);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Class<Saveable>> getElements(Class<Saveable> type){
        return elements.get(type);
    }
}