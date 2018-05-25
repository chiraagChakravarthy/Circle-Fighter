package circle_fighter.game.object;

import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.game.object.functionality.Damageable;
import circle_fighter.game.object.functionality.Damaging;
import circle_fighter.game.object.implementations.DamageableObject;
import circle_fighter.game.object.implementations.DamagingObject;
import circle_fighter.game.object.implementations.RenderableObject;

import java.awt.*;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectManager implements Updatable, Renderable{
    private final Map<Class<? extends Annotation>, AtomicList> objects;
    private final Map<Class<?>, AtomicList> objectTypes;
    private final Map<Integer, AtomicList<GameObject>> teams;

    public ObjectManager(){
        objects = new HashMap<>();
        objectTypes = new HashMap<>();
        teams = new HashMap<>();

        objectTypes.put(Updatable.class, new AtomicList<Updatable>());
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
        teams.forEach((key, value)->value.update());
    }

    public void add(GameObject object){
        for(Annotation annotation : object.getClass().getDeclaredAnnotations()){
            Class<? extends  Annotation> type = annotation.annotationType();
            if(objects.keySet().contains(type))
                objects.get(type).add(object);
        }
        if(!teams.containsKey(object.getTeam()))
            teams.put(object.getTeam(), new AtomicList<>());
        teams.get(object.getTeam()).add(object);
        objectTypes.get(Updatable.class).add(object);
    }

    public void remove(GameObject object){
        for(Annotation annotation : object.getClass().getDeclaredAnnotations()){
            Class<? extends  Annotation> type = annotation.annotationType();
            if(objects.keySet().contains(type))
                objects.get(type).remove(object);
        }
        teams.get(object.getTeam()).remove(object);
        objectTypes.get(Updatable.class).remove(object);
    }

    public void clearAll(){
        objectTypes.forEach((key, value) -> {
            value.clear();
            value.update();
        });
        teams.forEach((key, value)->{
            value.clear();
            value.update();
        });
    }

    public AtomicList<GameObject> getTeam(int team){
        return teams.get(team);
    }
}