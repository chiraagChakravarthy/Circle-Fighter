package circle_fighter.game.object;

import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.game.object.functionality.Damageable;
import circle_fighter.game.object.functionality.Damaging;

import java.awt.*;

public class ObjectManager implements Updatable, Renderable{
    private final AtomicList<Updatable> updatableObjects;
    private final AtomicList<Renderable> renderableObjects;
    private final AtomicList<Damageable> damageableObjects;
    private final AtomicList<Damaging> damagingObjects;

    public ObjectManager(){
        updatableObjects = new AtomicList<>();
        renderableObjects = new AtomicList<>();
        damageableObjects = new AtomicList<>();
        damagingObjects = new AtomicList<>();
    }

    @Override
    public void render(Graphics2D g) {
        for(Renderable object : renderableObjects.get()){
            object.render(g);
        }
    }

    @Override
    public void tick() {
        for(Updatable object : updatableObjects.get()){
            object.tick();
        }
        for(Damageable damageable : damageableObjects.get()){

        }
        damagingObjects.update();
        damageableObjects.update();
        updatableObjects.update();
        renderableObjects.update();
    }

    public AtomicList<Updatable> getUpdatableObjects() {
        return updatableObjects;
    }

    public AtomicList<Renderable> getRenderableObjects() {
        return renderableObjects;
    }

    public AtomicList<Damageable> getDamageableObjects() {
        return damageableObjects;
    }

    public AtomicList<Damaging> getDamagingObjects() {
        return damagingObjects;
    }

    public void remove(GameObject object){

    }
}
