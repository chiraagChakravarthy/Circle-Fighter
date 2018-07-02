package circle_fighter.game.object.turret;

import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.game.object.GameObject;
import circle_fighter.user.element.UserTurretManager;

import java.awt.*;

public class TurretManager implements Updatable, Renderable {
    private Turret[] turrets;
    public TurretManager(UserTurretManager manager, GameObject object){
        //TODO make this constructor
    }

    public TurretManager(Turret... turrets){
        this.turrets = turrets;
    }

    @Override
    public void render(Graphics2D g) {
        for(Turret turret : turrets){
            turret.render(g);
        }
    }

    @Override
    public void tick() {
        for(Turret turret : turrets){
            turret.tick();
        }
    }
}
