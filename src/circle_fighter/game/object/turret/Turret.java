package circle_fighter.game.object.turret;

import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.functionality.Damageable;
import circle_fighter.game.object.functionality.Damaging;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.UpdatingPosition;
import circle_fighter.user.element.turret.UserTurret;

public abstract class Turret implements Updatable, Renderable, Damaging {
    //TODO refactor all instances of shoot rate to reload rate
    protected GameObject object;
    private final float maximumAng, reloadRate;
    private float relativeAng;
    private float delta;
    private boolean reloading;
    private UpdatingPosition turretPosition;
    public <T extends GameObject & Damaging> Turret(UserTurret turret, T object){
        this.object = object;
        turretPosition = new UpdatingPosition(object.getPosition());
        maximumAng = turret.getFunctions()[UserTurret.MAX_ANG].perform(turret.get(UserTurret.MAX_ANG));
        reloadRate = turret.getFunctions()[UserTurret.RELOAD_RATE].perform(turret.get(UserTurret.RELOAD_RATE));
        reloading = false;
        delta = 0;
    }

    public <T extends GameObject & Damaging> Turret(T object, float maximumAng, float reloadRate){
        this.object = object;
        turretPosition = new UpdatingPosition(object.getPosition());
        this.maximumAng = maximumAng;
        this.reloadRate = reloadRate;
        reloading = false;
        delta = 0;
    }

    public float getRelativeAng() {
        return relativeAng;
    }

    public void setRelativeAng(float relativeAng) {
        relativeAng %= 2*Math.PI;
        relativeAng = relativeAng<0? (float) (Math.PI * 2 + relativeAng) :relativeAng;
        this.relativeAng = Math.min(Math.max(relativeAng>Math.PI? (float) (relativeAng-Math.PI*2) :relativeAng, -maximumAng), maximumAng);
    }

    public float getMaximumAng() {
        return maximumAng;
    }

    @Override
    public void tick() {
        turretPosition.setX(object.getPosition().getX());
        turretPosition.setY(object.getPosition().getY());
        turretPosition.setRotation(object.getPosition().getRotation());
        turretPosition.move(new Position(25, 0), true);
        turretPosition.setRotation(turretPosition.getRotation()+relativeAng);
        if(reloading) {
            if(delta >= 1) {
                while (delta >= 1) {
                    onReload();
                    delta--;
                }
                reloading = false;
            }
        }
        else {
            delta = Math.min(delta, 1);
        }
        delta += reloadRate/60;
    }

    protected abstract void onReload();

    public boolean reload(){
        if(isReloading()){
            return false;
        }
        reloading = true;
        return true;
    }

    public boolean isReloading() {
        return reloading;
    }

    public void stop(){
        reloading = false;
    }

    protected float angle(){
        return relativeAng + object.getPosition().getRotation();
    }

    public float getDelta() {
        return delta;
    }

    public UpdatingPosition getTurretPosition() {
        return turretPosition;
    }

    @Override
    public void onKill(Damageable damageable) {
        System.out.println("1");
        ((Damaging)object).onKill(damageable);
    }
}
