package circle_fighter.user.element.turret;

import circle_fighter.file.DataStorage;
import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.functionality.Damaging;
import circle_fighter.game.object.turret.Turret;
import circle_fighter.user.element.TransformFunction;
import circle_fighter.user.element.UserElement;
import circle_fighter.user.element.Value;

import java.util.ArrayList;
//TODO IMMEDIATLY begin work on shop system
public abstract class UserTurret extends UserElement {
    private static TransformFunction[] functions, inverses;
    public static final int MAX_ANG = 0, RELOAD_RATE = MAX_ANG+1;
    static {
        ArrayList<TransformFunction> functions = new ArrayList<>(), inverses = new ArrayList<>();
        addFunctions(.062831f, (float) 0, functions, inverses);
        UserTurret.functions = functions.toArray(new TransformFunction[1]);
        UserTurret.inverses = inverses.toArray(new TransformFunction[1]);
    }
    //TODO add rotation rate parameter and code for rotation rate in the turret class
    public UserTurret(float reloadRateMultiplier){
        this(null, reloadRateMultiplier);
    }

    public UserTurret(DataStorage storage, float reloadRateMultiplier){
        super(storage==null?null:storage.getSubStorage(0), merge(UserTurret.functions, new TransformFunction[]{vals -> (vals[0]*0.15f+1)*reloadRateMultiplier}),
                merge(UserTurret.inverses, new TransformFunction[]{vals -> (vals[0]/reloadRateMultiplier-1)/0.15f}),
                new Value(0, 50, 0), new Value(0, 60, 0));
    }

    public UserTurret(float reloadRateMultiplier, TransformFunction[] functions, TransformFunction[] inverses, Value... values) {
        this(null, reloadRateMultiplier, functions, inverses, values);
    }

    public UserTurret(DataStorage storage, float reloadRateMultiplier, TransformFunction[] functions, TransformFunction[] inverses, Value... values) {
        super(storage.getSubStorage(0), merge(UserTurret.functions, new TransformFunction[]{vals -> (vals[0]*0.15f+1)*reloadRateMultiplier}, functions),
                merge(UserTurret.inverses, new TransformFunction[]{vals -> (vals[0]/reloadRateMultiplier-1)/0.15f}, inverses),
                values, new Value(0, 50), new Value(0, 60));
    }

    public abstract <T extends GameObject & Damaging> Turret newTurret(T object, float radius);

    @Override
    public void save(DataStorage storage) {
        super.save(storage.getSubStorage(0));
    }
}