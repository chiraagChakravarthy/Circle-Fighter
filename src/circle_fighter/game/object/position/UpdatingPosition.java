package circle_fighter.game.object.position;

import circle_fighter.file.DataStorage;

import java.awt.*;
import java.util.ArrayList;

public class UpdatingPosition extends Position {
    private static ArrayList<UpdatingPosition> updatingPositions;
    private static float lastXOffset, lastYOffset;

    static {
        updatingPositions = new ArrayList<>();
        lastXOffset = xOffset + 1;
        lastYOffset = yOffset + 1;
    }

    public static void tick(){
        if(lastXOffset != xOffset || lastYOffset != yOffset){
            onOffsetsChanged();
        }
    }

    private static void onOffsetsChanged(){
        lastXOffset = xOffset;
        lastYOffset = yOffset;
        for (int i = 0; i < updatingPositions.size(); i++) {
            updatingPositions.get(i).updateOffsets();
        }
    }

    private ArrayList<OnPositionChanged> listeners;
    public UpdatingPosition(float x, float y, float rotation) {
        super(x, y, rotation);
        updatingPositions.add(this);
        listeners = new ArrayList<>();
    }

    public UpdatingPosition(float x, float y) {
        this(x, y, 0);
    }

    public UpdatingPosition(Point point) {
        super(point);
        updatingPositions.add(this);
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

    public void update(){
        for(OnPositionChanged listener : listeners){
            listener.onPositionChanged();
        }
    }

    public void updateOffsets(){
        for(OnPositionChanged listener : listeners){
            listener.onOffsetsChanged();
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
