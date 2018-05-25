package circle_fighter.color;

public class SolidColor extends DynamicColor {
    public SolidColor(double r, double g, double b) {
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
