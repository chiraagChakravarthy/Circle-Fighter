package circle_fighter.gfx.color;

public class Blue extends SolidColor {

    public Blue() {
        super(0, 0, 255);
    }

    @Override
    public DynamicColor clone() {
        return new Blue();
    }
}
