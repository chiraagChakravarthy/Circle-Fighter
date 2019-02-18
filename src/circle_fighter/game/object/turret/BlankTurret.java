package circle_fighter.game.object.turret;

import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.functionality.Damaging;
import circle_fighter.user.element.turret.UserTurret;

import java.awt.*;

public class BlankTurret extends Turret {

    public <T extends GameObject & Damaging> BlankTurret(UserTurret turret, T object) {
        super(turret, object);
    }

    @Override
    protected void onReload() {

    }

    @Override
    public void render(Graphics2D g) {

    }

    @Override
    public int getTeam() {
        return object.getTeam();
    }
}
