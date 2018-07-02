package circle_fighter.user.element.turret;

import circle_fighter.file.DataStorage;
import circle_fighter.gfx.color.ColorRegistry;
import circle_fighter.gfx.color.DynamicColor;
import circle_fighter.gfx.color.SolidColor;
import circle_fighter.user.element.TransformFunction;

public class BasicUserTurret extends UserTurret {
    private DynamicColor color;

    public BasicUserTurret(){
        super(1, new TransformFunction[0], new TransformFunction[0]);
        color = new SolidColor(0, 0, 255);
    }

    public BasicUserTurret(DataStorage storage){
        super(storage.getSubStorage(0), 1, new TransformFunction[0], new TransformFunction[0]);
        color = ColorRegistry.fromID(storage.get(0), storage.getSubStorage(1));
    }

    public DynamicColor getColor() {
        return color;
    }
}
