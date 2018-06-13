package circle_fighter.user;

import circle_fighter.color.DynamicColor;
import circle_fighter.color.Rainbow;
import circle_fighter.color.SolidColor;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class UserColorRegistry {
    private static ArrayList<Class<DynamicColor>> colors;
    static {
        colors = new ArrayList(Arrays.asList(Rainbow.class, SolidColor.class));
    }

}
