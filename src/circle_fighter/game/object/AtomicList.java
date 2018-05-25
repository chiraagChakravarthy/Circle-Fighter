package circle_fighter.game.object;

import java.util.ArrayList;
import java.util.List;

public class AtomicList<T> {
    private List<T> list, added, removed;
    public AtomicList(){
        list = new ArrayList<>();
        added = new ArrayList<>();
        removed = new ArrayList<>();
    }

    public List<T> get() {
        return list;
    }

    public void add(T object){
        added.add(object);
    }

    public void remove(T object){
        removed.add(object);
    }

    public void clear(){
        removed.addAll(list);
    }

    public void update(){
        list.addAll(added);
        list.removeAll(removed);
        added.clear();
        removed.clear();
    }

    public int size() {
        return list.size();
    }
}