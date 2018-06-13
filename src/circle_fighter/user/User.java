package circle_fighter.user;

import circle_fighter.file.DataStorage;
import circle_fighter.game.object.objects.Player;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.wrapper.Turret;
import circle_fighter.game.plane.PlayerPlane;

public class User {
    private UserTurret turret;
    private DataStorage storage;
    private UserBase base;
    public User(DataStorage storage){
        this.storage = storage;
        turret = new UserTurret(storage);
        base = new UserBase(storage);
    }

    public Player getPlayer(PlayerPlane plane){
        return new Player(new Position(0, 0), plane, this);
    }

    public Turret getTurret(Player player){
        return turret.getTurret(player);
    }
}