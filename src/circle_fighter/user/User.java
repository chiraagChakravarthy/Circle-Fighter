package circle_fighter.user;

import circle_fighter.file.DataStorage;
import circle_fighter.file.FileManager;
import circle_fighter.game.object.objects.Player;
import circle_fighter.game.object.position.UpdatingPosition;
import circle_fighter.game.plane.PlayerPlane;

import java.util.ArrayList;
import java.util.Arrays;
//TODO make all saved numerical parameters level based, and have a key (level - actual parameter) saved to the resources folder
//TODO meke player constructor with user, and have player directly access parameters from user

public class User {
    private static String USER_DEFAULT;

    public static void init(){
        USER_DEFAULT = FileManager.readFromFile("res/user_default.txt").get(0);
    }

    private DataStorage storage;
    private String name;
    
    public User(String name, String user){
        this.name = name;
        this.storage = new DataStorage().fromString(user==null?USER_DEFAULT:user);

    }

    public Player getPlayer(PlayerPlane plane){
        Player player =  new Player(new UpdatingPosition(0, 0), plane);
        player.save(storage);
        return player;
    }

    public ArrayList<String > save(){
        return new ArrayList<>(Arrays.asList(name, storage.toString()));
    }
}