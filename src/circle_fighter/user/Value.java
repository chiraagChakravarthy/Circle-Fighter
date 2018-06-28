package circle_fighter.user;

public class Value {
    private final float min, max;
    private float val;
    public Value(float min, float max){
        this(min, max, min);
    }

    public Value(float min, float max, float val){
        this.min = min;
        this.max = max;
        this.val = val;
    }
    public float get(){
        return val;
    }

    public boolean set(float val){
        if(val == Math.max(Math.min(val, max), min)) {
            this.val = val;
            return true;
        }
        return false;
    }
}
