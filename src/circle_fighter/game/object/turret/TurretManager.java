package circle_fighter.game.object.turret;

import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.functionality.Damaging;
import circle_fighter.game.object.position.Position;
import circle_fighter.user.element.UserTurretManager;

import java.awt.*;

public class TurretManager implements Updatable, Renderable {
    private final Turret[] turrets;
    private final GameObject object;
    private boolean triggered;
    public <T extends GameObject & Damaging> TurretManager(UserTurretManager manager, T object, float radius){
        this.object = object;
        turrets = new Turret[manager.getSlotAmount()];
        for (int i = 0; i < turrets.length; i++) {
            turrets[i] = manager.getTurretAt(i).newTurret(object, radius);
        }
        triggered = false;
    }

    public TurretManager(GameObject object, Turret... turrets){
        this.turrets = turrets;
        this.object = object;
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

    public void setTarget(Position position) {
        for(Turret turret : turrets){
            turret.setRelativeAng(turret.getTurretPosition().angleTo(position)-object.getPosition().getRotation());
        }
    }

    public void trigger(){
        if(!triggered) {
            for (Turret turret : turrets) {
                turret.reload();
            }
            triggered = true;
        }
    }

    public void stop(){
        for(Turret turret : turrets){
            turret.stop();
        }
        triggered = false;
    }
}
