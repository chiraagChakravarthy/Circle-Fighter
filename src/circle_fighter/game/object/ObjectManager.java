package circle_fighter.game.object;

import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.game.object.functionality.Damageable;
import circle_fighter.game.object.functionality.Damaging;
import circle_fighter.game.object.properties.DamageableObject;
import circle_fighter.game.object.properties.DamagingObject;
import circle_fighter.game.object.properties.RenderableObject;
import circle_fighter.game.object.properties.UpdatableObject;

import java.awt.*;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.List;

public class ObjectManager implements Updatable, Renderable{
    private final Map<Annotation, AtomicList> objects;
    private final Map<Class<?>, AtomicList> objectTypes;

    public ObjectManager(){
        objects = new HashMap<>();
        objectTypes = new HashMap<>();
        put(getClass().getAnnotation(UpdatableObject.class), new AtomicList<Updatable>(), Updatable.class);
        put(getClass().getAnnotation(RenderableObject.class), new AtomicList<Renderable>(), Renderable.class);
        put(getClass().getAnnotation(DamagingObject.class), new AtomicList<Damaging>(), Damaging.class);
        put(getClass().getAnnotation(DamageableObject.class), new AtomicList<Damageable>(), Damageable.class);
    }

    private void put(Annotation annotation, AtomicList list, Class<?> functionality){
        objects.put(annotation, list);
        objectTypes.put(functionality, list);
    }

    @Override
    public void render(Graphics2D g) {
        for(Renderable object : (List<Renderable>)objects.get(getClass().getAnnotation(RenderableObject.class)).get()){
            object.render(g);
        }
    }

    @Override
    public void tick() {
        for(Updatable object : (List<Updatable>) objectTypes.get(Updatable.class).get()){
            object.tick();
        }
        for(Damageable damageable : (List<Damageable>)objectTypes.get(Damageable.class)){

        }
        objects.entrySet().forEach((x)-> x.getValue().update());
    }

    public void remove(GameObject object){

    }
}
