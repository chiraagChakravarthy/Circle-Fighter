package circle_fighter.user;

import circle_fighter.file.DataStorage;
import circle_fighter.game.object.objects.Player;
import circle_fighter.game.object.wrapper.Health;

public class UserHealth {
    private DataStorage storage;
    public UserHealth(DataStorage storage){
        this.storage = storage;
    }

    public Health getHealth(Player player){

    }
}
