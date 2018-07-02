package circle_fighter.user.element;

import circle_fighter.file.DataStorage;
import circle_fighter.functionaliy.Savable;

import java.util.ArrayList;
import java.util.Arrays;

public class UserElement implements Savable{
    protected static TransformFunction[] addFunctions(float coefficient, float min, ArrayList<TransformFunction> functions, ArrayList<TransformFunction> inverse){
        functions.add(vals -> coefficient*vals[0]+min);
        inverse.add(vals -> (vals[0]-min)/coefficient);
        return new TransformFunction[]{functions.get(functions.size()-1), inverse.get(inverse.size()-1)};
    }

    protected static <T> T[] merge(T[]... values){
        ArrayList<T> valueList = new ArrayList<>();
        for(T[] valueArray : values){
            valueList.addAll(Arrays.asList(valueArray));
        }
        return (T[])valueList.toArray();
    }

    protected Value[] values;
    public final TransformFunction[] functions, inverses;
    public UserElement(TransformFunction[] functions, TransformFunction[] inverses, Value[] values, Value... valuesList){
        this.functions = functions;
        this.inverses = inverses;
        this.values = new Value[values.length + valuesList.length];
        System.arraycopy(values, 0, this.values, 0, values.length);
        System.arraycopy(valuesList, 0, this.values, values.length, valuesList.length);
    }

    public UserElement(TransformFunction[] functions, TransformFunction[] inverses, Value... valuesList){
        this(functions, inverses, new Value[0], valuesList);
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
}