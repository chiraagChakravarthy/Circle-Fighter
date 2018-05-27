package circle_fighter.game.object;

import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.game.object.functionality.Damageable;
import circle_fighter.game.object.functionality.Damaging;
import circle_fighter.game.object.implementations.DamageableObject;
import circle_fighter.game.object.implementations.DamagingObject;
import circle_fighter.game.object.implementations.CharacterObject;
import circle_fighter.game.object.implementations.RenderableObject;

import java.awt.*;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectManager implements Updatable, Renderable{
    private final Map<Class<? extends Annotation>, AtomicList> objects;
    private final Map<Class<?>, AtomicList> objectTypes;

    public ObjectManager(){
        objects = new HashMap<>();
        objectTypes = new HashMap<>();

        objectTypes.put(Updatable.class, new AtomicList<Updatable>());
        objects.put(CharacterObject.class, new AtomicList<GameObject>());

        put(RenderableObject.class, new AtomicList<Renderable>(), Renderable.class);
        put(DamagingObject.class, new AtomicList<Damaging>(), Damaging.class);
        put(DamageableObject.class, new AtomicList<Damageable>(), Damageable.class);
    }

    private void put(Class<? extends Annotation> annotation, AtomicList list, Class<?> functionality){
        objects.put(annotation, list);
        objectTypes.put(functionality, list);
    }

    @Override
    public void render(Graphics2D g) {
        for(Renderable object : (List<Renderable>)objectTypes.get(Renderable.class).get()){
            object.render(g);
        }
    }

    @Override
    public void tick() {
        for(Updatable object : (List<Updatable>) objectTypes.get(Updatable.class).get()){
            object.tick();
        }

        for(Damageable damageable : (List<Damageable>)objectTypes.get(Damageable.class).get()){
            for(Damaging damaging : (List<Damaging>)objectTypes.get(Damaging.class).get()){
                damageable.damage(damaging);
            }
        }
        objectTypes.forEach((key, value) -> value.update());
        objects.get(CharacterObject.class).update();
    }

    public void add(GameObject object){
        for(Annotation annotation : object.getClass().getDeclaredAnnotations()){
            Class<? extends  Annotation> type = annotation.annotationType();
            if(objects.keySet().contains(type)) {
                objects.get(type).add(object);
            }
        }
        objectTypes.get(Updatable.class).add(object);
    }

    public void remove(GameObject object){
        for(Annotation annotation : object.getClass().getDeclaredAnnotations()){
            Class<? extends  Annotation> type = annotation.annotationType();
            if(objects.keySet().contains(type))
                objects.get(type).remove(object);
        }
        objectTypes.get(Updatable.class).remove(object);
    }

    public void clearAll(){
        objectTypes.forEach((key, value) -> {
            value.clear();
            value.update();
        });

        objects.get(CharacterObject.class).clear();
        objects.get(CharacterObject.class).update();
    }

    @SafeVarargs
    public final AtomicList<GameObject> getBy(int team, Class<? extends Annotation>... implementations){
        AtomicList<GameObject> objects = new AtomicList<>();
        for(Class<? extends Annotation> implementation : implementations){
            for(GameObject object : (List<GameObject>)this.objects.get(implementation).get()){
                if(object.getTeam()==team) objects.add(object);
            }
        }
        objects.update();
        return objects;
    }
}