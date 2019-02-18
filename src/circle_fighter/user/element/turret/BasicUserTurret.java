package circle_fighter.user.element.turret;

import circle_fighter.file.DataStorage;
import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.functionality.Damaging;
import circle_fighter.game.object.turret.BasicTurret;
import circle_fighter.game.object.turret.Turret;
import circle_fighter.gfx.color.Blue;
import circle_fighter.gfx.color.ColorRegistry;
import circle_fighter.gfx.color.DynamicColor;

public class BasicUserTurret extends UserTurret {
    private int color;

    public BasicUserTurret(){
        super(4);
        color = ColorRegistry.toID(Blue.class);
    }

    public BasicUserTurret(DataStorage storage){
        super(storage.getSubStorage(0), 1);
        color = storage.get(0);
    }

    public DynamicColor getColor() {
        return ColorRegistry.fromID(color);
    }

    @Override
    public <T extends GameObject & Damaging> Turret newTurret(T object) {
        return new BasicTurret(this, object);
    }

    @Override
    public void save(DataStorage storage) {
        super.save(storage.getSubStorage(0));
        storage.set(0, color);
    }
}
