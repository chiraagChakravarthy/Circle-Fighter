package circle_fighter.level.level;

import circle_fighter.engine.Game;
import circle_fighter.game.object.objects.Bot;
import circle_fighter.game.object.position.Position;
import circle_fighter.gameState.LevelState;
import circle_fighter.level.LevelPlane;

public class Level2 extends LevelPlane {

    public Level2(LevelState state) {
        super(Game.getInstance().getGameWidth(), Game.getInstance().getGameHeight(), state);
    }

    @Override
    public void tick() {
        super.tick();
        if(objectManager.getTeam(1).size()==0)
            win();
        else if(objectManager.getTeam(0).size()==0)
            loose();
    }

    @Override
    public void reset() {
        super.reset();
        new Bot(new Position(-200, 0), this, 1);
        new Bot(new Position(200, 0), this, 1);
    }
}
