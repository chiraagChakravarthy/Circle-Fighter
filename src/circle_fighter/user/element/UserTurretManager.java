package circle_fighter.user.element;

import circle_fighter.file.DataStorage;
import circle_fighter.user.element.turret.UserTurret;

import java.util.ArrayList;

public class UserTurretManager extends UserElement {
    private static TransformFunction[] functions, inverses;
    private static final int[] turretAmounts = {1};
    public static final int TURRETS_SLOTS = 0;
    static {
        functions = new TransformFunction[]{
                vals -> vals[0]
        };
        inverses = new TransformFunction[]{
                vals -> vals[0]
        };

    }

    private ArrayList<UserTurret[]> turrets;

    public UserTurretManager() {
        super(functions, inverses, new Value(1, 10));
        turrets = new ArrayList<>();
    }

    public UserTurretManager(DataStorage storage){
        this();
    }

}