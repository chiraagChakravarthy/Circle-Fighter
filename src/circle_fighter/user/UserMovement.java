package circle_fighter.user;

import circle_fighter.file.DataStorage;
import circle_fighter.functionaliy.Savable;

public class UserMovement implements Savable {
    public static final int ACC = 0, VEL = 1, ACC_ANG = 2, VEL_ANG = 3;

    public static final TransformFunction[] functions, inverses;
    static {
        functions = new TransformFunction[]{
                val -> val/10f+0.1f,
                val -> val+3,
                val -> val*0.000872664626f,
                val -> val*0.05235987756f
        };
        inverses = new TransformFunction[]{
                val -> val*30f,
                val -> val/3.0f,
                val -> val/0.000872664626f,
                val -> val/0.05235987756f
        };
    }

    private Value[] values;

    public UserMovement(DataStorage storage){
        values = new Value[]{
                new Value(1, 20),
                new Value(1, 20),
                new Value(1, 20),
                new Value(1, 20)
        };
        for (int i = 0; i < values.length; i++) {
            values[i].set(storage.getFloat(i));
        }
    }

    public UserMovement(){
        values = new Value[]{
                new Value(0, 5, 0),
                new Value(0, 10, 0),
                new Value(0, 5, 0),
                new Value(0, 10, 0)
        };
    }

    @Override
    public void save(DataStorage storage) {
        for (int i = 0; i < values.length; i++) {
            storage.setFloat(i, values[i].get());
        }
    }
}
