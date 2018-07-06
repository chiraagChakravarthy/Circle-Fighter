package circle_fighter.user.element;

import circle_fighter.file.DataStorage;

import java.util.ArrayList;

public class UserHealth extends UserElement {
    public static final int INVULNERABILITY_MULTIPLIER = 0, MAX_HEALTH = 1, REGENERATION_RATE = 2;
    private static TransformFunction[] functions, inverses;
    static {
        ArrayList<TransformFunction> functions = new ArrayList<>(), inverses = new ArrayList<>();
        addFunctions(.2f, 1f, functions, inverses);
        addFunctions(3.166666f, 5f, functions, inverses);
        addFunctions(0.3333333f, 0f, functions, inverses);
        UserHealth.functions = functions.toArray(new TransformFunction[functions.size()]);
        UserHealth.inverses = inverses.toArray(new TransformFunction[functions.size()]);
    }
    public UserHealth(){
        this(null);
    }

    public UserHealth(DataStorage storage){
        super(storage, functions, inverses, new Value(0, 30, 0),
                new Value(0, 30, 0),
                new Value(0, 30, 0));
    }
}