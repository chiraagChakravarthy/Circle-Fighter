package circle_fighter.game.plane;

import circle_fighter.engine.Game;
import circle_fighter.game.object.objects.bot.TestBot;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.plane.bounds.BoundedBounds;

public class TestPlane extends PlayerPlane {
    public TestPlane(){
        super(new Position(0, 0),
                new BoundedBounds(-Game.getInstance().getGameWidth(), -Game.getInstance().getGameHeight(),
                Game.getInstance().getGameWidth()*2, Game.getInstance().getGameHeight()*2));
        new TestBot(new Position(100, -100), this, 2);
    }

    @Override
    public void tick() {
        super.tick();
    }
}
