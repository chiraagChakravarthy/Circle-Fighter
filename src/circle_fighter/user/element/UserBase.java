package circle_fighter.user.element;

import circle_fighter.file.DataStorage;
import circle_fighter.gfx.color.*;

import java.util.ArrayList;

public class UserBase extends UserElement {
    public static final int RADIUS = 0;
    private static final TransformFunction radius, inverseRadius;
    static {
        TransformFunction[] functions = addFunctions(0, 25, new ArrayList<>(), new ArrayList<>());
        radius = functions[0];
        inverseRadius = functions[1];
    }

    private int inner, outer;

    public UserBase() {
        super(null, new TransformFunction[]{radius}, new TransformFunction[]{inverseRadius}, new Value(0, 0, 0));
        inner = ColorRegistry.toID(Red.class);
        outer = ColorRegistry.toID(Red.class);
    }

    public UserBase(DataStorage storage){
        super(storage.getSubStorage(0), new TransformFunction[]{radius}, new TransformFunction[]{inverseRadius}, new Value(0, 0));
        this.inner = storage.get(0);
        this.outer = storage.get(1);
    }

    @Override
    public void save(DataStorage storage) {
        super.save(storage.getSubStorage(0));
        storage.set(0, inner).set(1, outer);
    }

    public DynamicColor getInner() {
        return ColorRegistry.fromID(inner);
    }

    public DynamicColor getOuter() {
        return ColorRegistry.fromID(outer);
    }
}