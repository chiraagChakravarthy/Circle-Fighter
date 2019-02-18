package circle_fighter.user.element;

import circle_fighter.file.DataStorage;
import circle_fighter.functionaliy.Savable;
import circle_fighter.user.User;

public class UserLevel implements Savable{
    private float exp;
    private User user;
    public UserLevel(User user){
        exp = 0;
        this.user = user;
    }

    public UserLevel(DataStorage storage, User user){
        exp = storage.getFloat(0);
        this.user = user;
    }

    public int getLevel(){
        float exp = this.exp;
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
        boolean leveledUp = level < getLevel();
        if (leveledUp){
            user.setUpgradePoints(user.getUpgradePoints()+1);
        }
        return level < getLevel();
    }

    @Override
    public void save(DataStorage storage) {
        storage.setFloat(0, exp);
    }
}