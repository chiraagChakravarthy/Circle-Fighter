package circle_fighter.game.object.turret;

import circle_fighter.game.object.GameObject;
import circle_fighter.user.element.turret.UserTurret;

import java.awt.*;

public class BlankTurret extends Turret {

    public BlankTurret(UserTurret turret, GameObject object) {
        super(turret, object);
    }

    @Override
    protected void onReload() {

    }

    @Override
    public void render(Graphics2D g) {

    }
}
