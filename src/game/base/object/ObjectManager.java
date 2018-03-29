package game.base.object;

import interfaces.Renderable;
import interfaces.Updatable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectManager implements Updatable, Renderable{
    private List<GameObject> objects, addedObjects, removedObjects;
    public ObjectManager(){
        objects = new ArrayList<>();
        addedObjects = new ArrayList<>();
        removedObjects = new ArrayList<>();
    }

    @Override
    public void render(Graphics2D g) {
        for(GameObject object : objects){
            object.render(g);
        }
    }

    @Override
    public void tick() {
        for(GameObject object : objects){
            object.tick();
        }
        objects.addAll(addedObjects);
        objects.removeAll(removedObjects);
        addedObjects.clear();
        removedObjects.clear();
    }

    public void addObject(GameObject object){
        addedObjects.add(object);
    }

    public void removeObject(GameObject object){
        removedObjects.add(object);
    }
}
