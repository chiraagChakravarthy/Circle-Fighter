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

    private String name;
    private UserTurretManager turrets;
    private UserHealth health;
    private UserMovement movement;
    private UserBase base;
    private int playerLevel, upgradePoints, parts;

    public User(String name, String user){
        this.name = name;
        DataStorage storage = new DataStorage().fromString(user);
        parts = storage.get(0);
        upgradePoints = storage.get(1);
        playerLevel = storage.get(2);
        turrets = new UserTurretManager(storage.getSubStorage(0));
        health = new UserHealth(storage.getSubStorage(1));
        movement = new UserMovement(storage.getSubStorage(2));
        base = new UserBase(storage.getSubStorage(3));
    }

    public User(String name){
        this.name = name;
        turrets = new UserTurretManager();
        health = new UserHealth();
        movement = new UserMovement();
        base = new UserBase();
        playerLevel = 0;
        upgradePoints = 0;
        parts = 0;
    }

    public ArrayList<String > save() {
        DataStorage storage = new DataStorage();
        storage.set(0, parts).set(1, upgradePoints).set(2, playerLevel);
        turrets.save(storage.getSubStorage(0));
        health.save(storage.getSubStorage(1));
        movement.save(storage.getSubStorage(2));
        base.save(storage.getSubStorage(3));
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

    public int getParts() {
        return parts;
    }

    public int getUpgradePoints() {
        return upgradePoints;
    }

    public void setParts(int parts) {
        this.parts = parts;
    }

    public void setUpgradePoints(int upgradePoints) {
        this.upgradePoints = upgradePoints;
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public String getName() {
        return name;
    }
}