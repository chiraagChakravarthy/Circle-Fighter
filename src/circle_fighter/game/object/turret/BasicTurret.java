package circle_fighter.game.object.turret;

import circle_fighter.engine.Game;
import circle_fighter.game.object.GameObject;
import circle_fighter.game.object.bounds.render_base.PolygonBase;
import circle_fighter.game.object.objects.Bullet;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.Vector;
import circle_fighter.gfx.color.DynamicColor;
import circle_fighter.gfx.color.SolidColor;
import circle_fighter.user.element.turret.BasicUserTurret;
import circle_fighter.user.element.turret.UserTurret;

import java.awt.*;

public class BasicTurret extends Turret {
    private static final float LENGTH = 50, GIRTH = 5;
    private static final Position[] baseTemplate;
    static {
        baseTemplate = new Position[]{new Position(0, -GIRTH/2), new Position(0, GIRTH/2), new Position(LENGTH, GIRTH/2), new Position(LENGTH, -GIRTH/2)};
    }

    private boolean reloaded;
    private DynamicColor color;
    private PolygonBase base;

    public BasicTurret(BasicUserTurret turret, GameObject object) {
        super(turret, object);
        reloaded = true;
        color = turret.getColor();
        base = new PolygonBase(color, object.getPosition(), baseTemplate);
    }

    public BasicTurret(GameObject object){
        super(object, 0, 1);
        color = new SolidColor(0, 0, 255);
        reloaded = true;
        base = new PolygonBase(color, object.getPosition(), baseTemplate);
    }

    @Override
    public void tick() {
        super.tick();
        color.tick();
        float relativeAngle = (float) Math.max(
                Math.min(
                        object.getPosition().clone().scrPosition().angleTo(
                                new Position(
                                        Game.getInstance().mouseLocation()
                                )
                        ) -Math.PI-object.getPosition().getRotation(), getMaximumAng()
                ), -getMaximumAng()
        );
        setRelativeAng(relativeAngle);
        if(reloaded){
            reloaded = false;
            float angle = angle();
            new Bullet(object.getPosition().clone().apply(new Vector((float)Math.cos(angle)*LENGTH, (float)Math.sin(angle)*LENGTH, 0)), object.getPlane(), 10, 2, object.getTeam());
        }
    }

    @Override
    protected void onReload() {
        reloaded = true;
    }

    @Override
    public void render(Graphics2D g) {
        base.render(g);
    }
}