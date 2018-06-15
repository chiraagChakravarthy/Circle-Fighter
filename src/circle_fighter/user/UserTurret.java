package circle_fighter.user;

import circle_fighter.gfx.color.DynamicColor;
import circle_fighter.file.DataStorage;
import circle_fighter.game.object.objects.Player;
import circle_fighter.game.object.turret.Turret;

public class UserTurret {
    private DataStorage storage;
    public UserTurret(DataStorage storage){
        this.storage = storage;
    }

    public Turret getTurret(Player player){
        DataStorage colorStorage = storage.getSubStorage(0);
        DynamicColor color = UserColorRegistry.fromID(colorStorage.get(0), colorStorage.getSubStorage(0));
        return new Turret(player, storage.get(0), storage.get(1), storage.get(2), storage.get(4), color);
    }
}