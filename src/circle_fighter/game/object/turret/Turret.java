package circle_fighter.game.object.turret;

import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.game.object.GameObject;
import circle_fighter.user.element.turret.UserTurret;

public abstract class Turret implements Updatable, Renderable {
    //TODO refactor all instances of shoot rate to reload rate
    protected GameObject object;
    private final float maximumAng, reloadRate;
    protected float relativeAng;
    private long lastTime;
    protected float delta;
    private boolean reloading;

    public Turret(UserTurret turret, GameObject object){
        this.object = object;
        maximumAng = turret.functions[UserTurret.MAX_ANG].perform(turret.get(UserTurret.MAX_ANG));
        reloadRate = turret.functions[UserTurret.RELOAD_RATE].perform(turret.get(UserTurret.RELOAD_RATE));
    }

    public Turret(GameObject object, float maximumAng, float reloadRate){
        this.object = object;
        this.maximumAng = maximumAng;
        this.reloadRate = reloadRate;
    }

    public float getRelativeAng() {
        return relativeAng;
    }

    public void setRelativeAng(float relativeAng) {
        this.relativeAng = Math.max(Math.min(relativeAng, maximumAng), -maximumAng);
    }

    public float getMaximumAng() {
        return maximumAng;
    }

    @Override
    public void tick() {
        if(reloading) {
            long now = System.currentTimeMillis();
            delta += (now-lastTime)*reloadRate;
            lastTime = now;
            if(delta>1){
                onReload();
                delta--;
            }
        }
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

    protected float angle(){
        return relativeAng + object.getPosition().getRotation();
    }
}
