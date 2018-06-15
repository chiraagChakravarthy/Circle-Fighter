package circle_fighter.user;

import circle_fighter.file.DataStorage;
import circle_fighter.game.object.objects.Player;
import circle_fighter.game.object.wrapper.Health;

public class UserHealth {
    private DataStorage storage;
    public UserHealth(DataStorage storage){
        this.storage = storage;
    }

    //TODO instead of manually supplying values, create method in health class of manually setting relative parameters based off of the base.
    //TODO make a bound of some sort a requirement of game objects
    public Health getHealth(Player player){
        Health health = new Health(player.getPosition(), 50, 10, -50);
        health.from(storage);
        return health;
    }
}