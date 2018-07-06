package circle_fighter.gfx.color;

public class Red extends SolidColor {

    public Red() {
        super(255, 0, 0);
    }

    @Override
    public DynamicColor clone() {
        return new Red();
    }
}
