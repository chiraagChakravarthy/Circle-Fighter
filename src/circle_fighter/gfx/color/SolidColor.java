package circle_fighter.gfx.color;

import circle_fighter.file.DataStorage;

public class SolidColor extends DynamicColor {
    public SolidColor(float r, float g, float b) {
        super(r, g, b, 1);
    }

    public SolidColor(DataStorage storage){
        super(storage.getFloat(0), storage.getFloat(1), storage.getFloat(2), 1, 1);
    }

    @Override
    public void tick() {
        //ignored
    }

    @Override
    public void reset() {
        //ignored
    }

    @Override
    public void save(DataStorage storage) {
        storage.setFloat(0, r).setFloat(1, g).setFloat(2, b);
    }
}
