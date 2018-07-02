package circle_fighter.user.element;

import circle_fighter.file.DataStorage;
import circle_fighter.gfx.color.ColorRegistry;
import circle_fighter.gfx.color.DynamicColor;
import circle_fighter.gfx.color.SolidColor;

import java.util.ArrayList;

public class UserBase extends UserElement {
    public static final int RADIUS = 0;
    private static final TransformFunction radius, inverseRadius;
    static {
        TransformFunction[] functions = addFunctions(0, 25, new ArrayList<>(), new ArrayList<>());
        radius = functions[0];
        inverseRadius = functions[1];
    }

    private DynamicColor inner, outer;

    public UserBase() {
        super(new TransformFunction[]{radius}, new TransformFunction[]{inverseRadius}, new Value(0, 0, 0));
        inner = new SolidColor(255, 0, 0);
        outer = new SolidColor(255, 0, 0);
    }

    public UserBase(DataStorage storage){
        super(new TransformFunction[]{radius}, new TransformFunction[]{inverseRadius}, new Value(0, 0, storage.getFloat(0)));
        this.inner = ColorRegistry.fromID(storage.get(1), storage.getSubStorage(0));
        this.outer = ColorRegistry.fromID(storage.get(2), storage.getSubStorage(1));
    }

    public DynamicColor getInner() {
        return (DynamicColor) inner.clone();
    }

    public DynamicColor getOuter() {
        return (DynamicColor) outer.clone();
    }
}