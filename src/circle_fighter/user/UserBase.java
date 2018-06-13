package circle_fighter.user;

import circle_fighter.file.DataStorage;
import circle_fighter.game.object.bounds.Bound;

public class UserBase {
    private DataStorage storage;
    //TODO IMPORTANT store properties of all different types, as well as the one currently in use. The one currently in use will be stored in the user class, while the properties necessary to spawn each in will be stored in the sub classes for which the user class is a wrapper.
    public UserBase(DataStorage storage){
        this.storage = storage;
    }

    public Bound getBase(){
        
    }
}