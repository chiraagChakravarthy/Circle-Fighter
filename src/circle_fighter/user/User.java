package circle_fighter.user;

import circle_fighter.engine.Game;
import circle_fighter.file.DataStorage;
import circle_fighter.user.element.*;

import java.util.ArrayList;
import java.util.Arrays;

public class User {
    private String name;
    private UserTurretManager turrets;
    private UserHealth health;
    private UserMovement movement;
    private UserBase base;
    private UserLevel level;
    private int upgradePoints, parts, levelUnlocked;

    public User(String name, String user){
        this.name = name;
        DataStorage storage = new DataStorage().fromString(user);
        parts = storage.get(0);
        upgradePoints = storage.get(1);
        levelUnlocked = storage.get(2);
        turrets = new UserTurretManager(storage.getSubStorage(0));
        health = new UserHealth(storage.getSubStorage(1));
        movement = new UserMovement(storage.getSubStorage(2));
        base = new UserBase(storage.getSubStorage(3));
        level = new UserLevel(storage.getSubStorage(4), this);
    }

    public User(String name){
        this.name = name;
        turrets = new UserTurretManager();
        health = new UserHealth();
        movement = new UserMovement();
        base = new UserBase();
        level = new UserLevel(this);
        upgradePoints = Game.DEBUG?1000:0;
        parts = 0;
    }

    public ArrayList<String> save() {
        DataStorage storage = new DataStorage();
        storage.set(0, parts);
        storage.set(1, upgradePoints);
        storage.set(2, levelUnlocked);
        turrets.save(storage.getSubStorage(0));
        health.save(storage.getSubStorage(1));
        movement.save(storage.getSubStorage(2));
        base.save(storage.getSubStorage(3));
        level.save(storage.getSubStorage(4));
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

    public int getLevelUnlocked() {
        return levelUnlocked;
    }

    public void setLevelUnlocked(int levelUnlocked) {
        this.levelUnlocked = levelUnlocked;
    }

    public UserLevel getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }
}