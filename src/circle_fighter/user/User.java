package circle_fighter.user;

import circle_fighter.file.DataStorage;
import circle_fighter.file.FileManager;
import circle_fighter.game.object.objects.Player;
import circle_fighter.game.object.position.UpdatingPosition;
import circle_fighter.game.plane.PlayerPlane;
import circle_fighter.user.element.UserBase;
import circle_fighter.user.element.UserHealth;
import circle_fighter.user.element.UserMovement;
import circle_fighter.user.element.UserTurretManager;

import java.util.ArrayList;
import java.util.Arrays;
//TODO make all saved numerical parameters level based, and have a key (level - actual parameter) saved to the resources folder
//TODO make player constructor with user, and have player directly access parameters from user

public class User {

    private DataStorage storage;
    private String name;
    private UserTurretManager turrets;
    private UserHealth health;
    private UserMovement movement;
    private UserBase base;

    public User(String name, String user){
        this.name = name;
        this.storage = new DataStorage().fromString(user);
        turrets = new UserTurretManager(storage.getSubStorage(0));
        health = new UserHealth(storage.getSubStorage(1));
        movement = new UserMovement(storage.getSubStorage(2));
        base = new UserBase(storage.getSubStorage(3));
    }

    public Player getPlayer(PlayerPlane plane){
        return new Player(new UpdatingPosition(0, 0), plane);
    }

    public ArrayList<String > save() {
        return new ArrayList<>(Arrays.asList(name, storage.toString()));
    }

    public UserTurretManager getTurrets() {
        return turrets;
    }

    public UserHealth getHealth() {
        return health;
    }

    public UserMovement getMovement() {
        return movement;
    }

    public UserBase getBase() {
        return base;
    }
}