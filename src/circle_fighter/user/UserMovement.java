package circle_fighter.user;

import circle_fighter.file.DataStorage;
import circle_fighter.game.object.objects.Player;
import circle_fighter.game.object.position.MovementVector;
import circle_fighter.game.object.position.VelAngAccMovement;

public class UserMovement {
    private DataStorage storage;
    public UserMovement(DataStorage storage){
        this.storage = storage;
    }

    public MovementVector getMovement(Player player){
        VelAngAccMovement movement = new VelAngAccMovement(player.getPosition(), player.getVector());
        movement.from(storage);
        return movement;
    }
}
