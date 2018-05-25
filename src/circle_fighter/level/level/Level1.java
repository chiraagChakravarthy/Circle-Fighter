package circle_fighter.level.level;

import circle_fighter.engine.Game;
import circle_fighter.game.object.objects.Bot;
import circle_fighter.game.object.position.Position;
import circle_fighter.level.LevelPlane;

public class Level1 extends LevelPlane {
    public Level1() {
        super(Game.getInstance().getGameWidth(), Game.getInstance().getGameHeight());
    }

    @Override
    public void reset() {
        super.reset();
        objectManager.add(new Bot(new Position(Math.random()*Game.getInstance().getGameHeight()/4, Math.random()*Game.getInstance().getGameHeight()), this, 1));
    }
}