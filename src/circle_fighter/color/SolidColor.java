package circle_fighter.color;

import circle_fighter.file.DataStorage;

public class SolidColor extends DynamicColor {
    public SolidColor(float r, float g, float b) {
        super(r, g, b, 1);
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
    public void to(DataStorage storage) {
        super.to(storage);
    }

    @Override
    public void from(DataStorage storage) {
        super.from(storage);
    }
}
