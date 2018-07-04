package circle_fighter.game.object.turret;

import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.bounds.render_base.PolygonBase;
import circle_fighter.game.object.objects.Bullet;
import circle_fighter.game.object.position.Position;
import circle_fighter.gfx.color.DynamicColor;
import circle_fighter.gfx.color.SolidColor;
import circle_fighter.user.element.turret.BasicUserTurret;

import java.awt.*;

public class BasicTurret extends Turret {
    private static final float LENGTH = 30.0f, GIRTH = 5.0f;
    private static final Position[] baseTemplate;
    static {
        baseTemplate = new Position[]{new Position(0, -GIRTH/2), new Position(0, GIRTH/2), new Position(LENGTH, GIRTH/2), new Position(LENGTH, -GIRTH/2)};
    }

    private DynamicColor color;
    private PolygonBase base;

    public BasicTurret(BasicUserTurret turret, GameObject object) {
        super(turret, object);
        color = turret.getColor();
        base = new PolygonBase(color, getTurretPosition(), baseTemplate);
    }

    public BasicTurret(GameObject object){
        super(object, (float) Math.PI, 1);
        color = new SolidColor(0, 0, 255);
        base = new PolygonBase(color, getTurretPosition(), baseTemplate);
    }

    @Override
    public void tick() {
        super.tick();
        color.tick();
    }

    @Override
    protected void onReload() {
        float angle = getRelativeAng()+object.getPosition().getRotation();
        new Bullet(getTurretPosition().clone().setRotation(angle).move(new Position(LENGTH, 0), true), object.getPlane(), 10, 2, object.getTeam());
    }

    @Override
    public void render(Graphics2D g) {
        base.render(g);
    }
}