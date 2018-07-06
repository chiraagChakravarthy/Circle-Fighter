package circle_fighter.user.element;

import circle_fighter.file.DataStorage;
import circle_fighter.functionaliy.Savable;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.*;

public class UserElement implements Savable{
    protected static TransformFunction[] addFunctions(float coefficient, float min, ArrayList<TransformFunction> functions, ArrayList<TransformFunction> inverse){
        functions.add(vals -> coefficient*vals[0]+min);
        inverse.add(vals -> (vals[0]-min)/coefficient);
        return new TransformFunction[]{functions.get(functions.size()-1), inverse.get(inverse.size()-1)};
    }

    protected static TransformFunction[] merge(TransformFunction[]... values){
        ArrayList<TransformFunction> valueList = new ArrayList<>();
        for(TransformFunction[] valueArray : values){
            valueList.addAll(Arrays.asList(valueArray));
        }
        return valueList.toArray(new TransformFunction[valueList.size()]);
    }

    protected Value[] values;

    public final TransformFunction[] functions, inverses;

    public UserElement(DataStorage storage, TransformFunction[] functions, TransformFunction[] inverses, Value[] values, Value... valuesList){
        this.functions = functions;
        this.inverses = inverses;
        this.values = new Value[values.length + valuesList.length];
        System.arraycopy(valuesList, 0, this.values, 0, valuesList.length);
        System.arraycopy(values, 0, this.values, valuesList.length, values.length);
        if(storage != null){
            for (int i = 0; i < values.length; i++) {
                values[i].set(storage.getFloat(i));
            }
        }
    }

    public UserElement(DataStorage storage, TransformFunction[] functions, TransformFunction[] inverses, Value... valuesList){
        this(storage, functions, inverses, new Value[0], valuesList);
    }

    public UserElement(){
        this(null, new TransformFunction[0], new TransformFunction[0]);
    }

    public float get(int index){
        return values[index].get();
    }

    @Override
    public void save(DataStorage storage){
        for (int i = 0; i < values.length; i++) {
            storage.setFloat(i, values[i].get());
        }
    }

    public TransformFunction[] getFunctions() {
        return functions;
    }

    public TransformFunction[] getInverses() {
        return inverses;
    }

    public boolean set(int index, float val) {
        return values[index].set(val);
    }

    public Value getValue(int index) {
        return values[index];
    }
}