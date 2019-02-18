package circle_fighter.user.element;

import circle_fighter.file.DataStorage;
import circle_fighter.functionaliy.Savable;

public class UserLevel implements Savable{
    private float exp;

    public UserLevel(){
        exp = 0;
    }

    public UserLevel(DataStorage storage){
        exp = storage.getFloat(0);
    }

    public int getLevel(){
        float exp = (float) 50;
        int level = 0;
        while (exp > 0){
            exp -= Math.pow(level, 1.5)*50;
            if(exp>0)level++;
        }
        return level;
    }

    public float getExpPoints(){
        return exp;
    }

    public boolean addExp(float exp){
        int level = getLevel();
        this.exp += exp;
        return level < getLevel();
    }

    @Override
    public void save(DataStorage storage) {
        storage.setFloat(0, exp);
    }
}