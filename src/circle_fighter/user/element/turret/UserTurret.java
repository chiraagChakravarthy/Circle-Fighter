package circle_fighter.user.element.turret;

import circle_fighter.file.DataStorage;
import circle_fighter.gfx.color.DynamicColor;
import circle_fighter.user.element.TransformFunction;
import circle_fighter.user.element.UserElement;
import circle_fighter.user.element.Value;

public class UserTurret extends UserElement {
    private static TransformFunction maxAng, maxAngInvese;
    public static final int MAX_ANG = 0, RELOAD_RATE = 1;
    static {
        maxAng = vals->vals[0]*.062831f;
        maxAngInvese = vals ->vals[0]/.062831f;
    }

    public UserTurret(float shootRateMultiplier, TransformFunction[] functions, TransformFunction[] inverses, Value... values) {
        super(merge(new TransformFunction[]{maxAng, vals -> (vals[0]*0.15f+1)*shootRateMultiplier}, functions),
                merge(new TransformFunction[]{maxAngInvese, vals -> (vals[0]/shootRateMultiplier-1)/0.15f}, inverses),
                values, new Value(0, 50, 0), new Value(0, 60, 0));
    }

    public UserTurret(DataStorage storage, float shootRateMultiplier, TransformFunction[] functions, TransformFunction[] inverses, Value... values) {
        super(merge(new TransformFunction[]{maxAng, vals -> (vals[0]*0.15f+1)*shootRateMultiplier}, functions),
                merge(new TransformFunction[]{maxAngInvese, vals -> (vals[0]/shootRateMultiplier-1)/0.15f}, inverses),
                values, new Value(0, 50), new Value(0, 60));
        for (int i = 0; i < this.values.length; i++) {
            this.values[i].set(storage.getFloat(i));
        }
    }

    @Override
    public void save(DataStorage storage) {
        super.save(storage);
    }
}