package circle_fighter.game.object.position;

import circle_fighter.file.DataStorage;

import java.awt.*;
import java.util.ArrayList;

public class UpdatingPosition extends Position {
    private ArrayList<OnPositionChanged> listeners;
    public UpdatingPosition(float x, float y, float rotation) {
        super(x, y, rotation);
        listeners = new ArrayList<>();
    }

    public UpdatingPosition(float x, float y) {
        this(x, y, 0);
    }

    public UpdatingPosition(Point point) {
        super(point);
        listeners = new ArrayList<>();
    }

    public UpdatingPosition(Position position) {
        super(position);
        listeners = new ArrayList<>();
    }

    @Override
    public Position setX(float x) {
        super.setX(x);
        update();
        return this;
    }

    @Override
    public UpdatingPosition setY(float y) {
        super.setY(y);
        update();
        return this;
    }

    @Override
    public UpdatingPosition setRotation(float rotation) {
        super.setRotation(rotation);
        update();
        return this;
    }

    @Override
    public UpdatingPosition move(Position position, boolean applyDirection) {
        super.move(position, applyDirection);
        update();
        return this;
    }

    @Override
    public UpdatingPosition apply(Vector vector) {
        super.apply(vector);
        update();
        return this;
    }

    public void addListener(OnPositionChanged listener){
        listeners.add(listener);
        listener.onPositionChanged();
    }

    public void removeListener(OnPositionChanged listener){
        listeners.remove(listener);
    }

    public void clearListeners(){
        listeners.clear();
    }

    private void update(){
        for(OnPositionChanged listener : listeners){
            listener.onPositionChanged();
        }
    }

    @Override
    public void hardLoad(DataStorage storage) {
        super.hardLoad(storage);
        update();
    }

    @Override
    public UpdatingPosition clone() {
        return new UpdatingPosition(this);
    }
}
