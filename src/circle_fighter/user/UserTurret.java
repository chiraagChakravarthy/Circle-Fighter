package circle_fighter.user;

import circle_fighter.color.DynamicColor;
import circle_fighter.color.SolidColor;
import circle_fighter.file.DataStorage;
import circle_fighter.game.object.objects.Player;
import circle_fighter.game.object.wrapper.Turret;

public class UserTurret {
    private DataStorage storage;
    public UserTurret(DataStorage storage){
        this.storage = storage;
    }

    public Turret getTurret(Player player){
        DataStorage colorStorage = storage.getSubStorage(0);
        DynamicColor color = colorStorage.get(0)==0?new SolidColor(colorStorage.get(1), colorStorage.get(2), colorStorage.get(3))
        return new Turret(player, storage.get(0), storage.get(1), storage.get(2), storage.get(4));
    }
}
