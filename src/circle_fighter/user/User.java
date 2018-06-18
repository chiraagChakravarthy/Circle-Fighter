package circle_fighter.user;

import circle_fighter.file.DataStorage;
import circle_fighter.file.FileManager;
import circle_fighter.game.object.objects.Player;
import circle_fighter.game.object.position.UpdatingPosition;
import circle_fighter.game.plane.PlayerPlane;

import java.util.ArrayList;
import java.util.Arrays;
//TODO make all saved numerical parameters level based, and have a key (level - actual parameter) saved to the resources folder
//TODO make player constructor with user, and have player directly access parameters from user

public class User {

    private DataStorage storage;
    private String name;
    
    public User(String name, String user){
        this.name = name;
        this.storage = new DataStorage().fromString(user);
    }

    public Player getPlayer(PlayerPlane plane){
        return new Player(new UpdatingPosition(0, 0), plane, storage);
    }

    public ArrayList<String > save(){
        return new ArrayList<>(Arrays.asList(name, storage.toString()));
    }
}