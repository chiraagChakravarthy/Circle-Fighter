package circle_fighter.user.element;

import circle_fighter.file.DataStorage;
import circle_fighter.user.UserManager;

import java.util.ArrayList;

public class UserMovement extends UserElement {
    public static final int ACC = 0, VEL = ACC+1, ACC_ANG = VEL+1, VEL_ANG = ACC_ANG+1;

    private static TransformFunction[] functions, inverses;

    static {
        ArrayList<TransformFunction> functions = new ArrayList<>(), inverses = new ArrayList<>();
        addFunctions(.02f, 0.1f, functions, inverses);
        addFunctions(.24f, 3f, functions, inverses);
        addFunctions(.0001745f, 0.0008727f, functions, inverses);
        addFunctions(.0024435f, 0.0523598f, functions, inverses);
        UserMovement.functions = functions.toArray(new TransformFunction[functions.size()]);
        UserMovement.inverses = inverses.toArray(new TransformFunction[functions.size()]);
    }

    public UserMovement(DataStorage storage){
        this();
        for (int i = 0; i < values.length; i++) {
            values[i].set(storage.getFloat(i));
        }
    }

    public UserMovement(){
        super(functions, inverses, new Value(0, 5, 0),
                new Value(0, 10, 0),
                new Value(0, 5, 0),
                new Value(0, 10, 0));
    }
}