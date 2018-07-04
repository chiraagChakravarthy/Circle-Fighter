package circle_fighter.gfx.color;

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
}
