package circle_fighter.user;

import circle_fighter.file.DataStorage;
import circle_fighter.game.object.bounds.Bound;
import circle_fighter.game.object.objects.Player;
import circle_fighter.game.object.position.MovementVector;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.turret.Turret;
import circle_fighter.game.object.wrapper.Health;
import circle_fighter.game.plane.PlayerPlane;
//TODO make all saved numerical parameters level based, and have a key (level - actual parameter) saved to the resources folder

public class User {
    private UserTurret turret;
    private DataStorage storage;
    private UserBase base;
    private UserHealth health;
    private UserMovement movement;
    private String name;
    
    public User(DataStorage storage){
        this.storage = storage;
        turret = new UserTurret(storage.getSubStorage(0));
        base = new UserBase(storage.getSubStorage(1));
        movement = new UserMovement(storage.getSubStorage(2));
        health = new UserHealth(storage.getSubStorage(3));
    }

    public Player getPlayer(PlayerPlane plane){
        return new Player(new Position(0, 0), plane, this);
    }

    public Turret getTurret(Player player){
        return turret.getTurret(player);
    }

    public Health getHealth(Player player){
        return health.getHealth(player);
    }

    public Bound getBase(Player player){
        return base.getBase(player);
    }

    public MovementVector getMovement(Player player){
        return movement.getMovement(player);
    }
}