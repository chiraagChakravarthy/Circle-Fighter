package circle_fighter.level.level;

import circle_fighter.engine.Game;
import circle_fighter.game.object.implementations.CharacterObject;
import circle_fighter.game.object.objects.bots.BotM1;
import circle_fighter.game.object.objects.bots.BotM2;
import circle_fighter.game.object.position.Position;
import circle_fighter.gameState.LevelState;
import circle_fighter.level.LevelPlane;

public class L4 extends LevelPlane {

    public L4(LevelState state) {
        super(Game.getInstance().getGameWidth(), Game.getInstance().getGameHeight(), state);
    }

    @Override
    public void tick() {
        super.tick();
        if(objectManager.getBy(1, CharacterObject.class).size()==0)
            win();
        else if(objectManager.getBy(0, CharacterObject.class).size()==0)
            loose();
    }

    @Override
    public void reset() {
        super.reset();
        new BotM2(new Position(300, 300), this, 1);
        new BotM2(new Position(-300, -300), this, 1);
        new BotM1(new Position(300, -300), this, 1);
        new BotM1(new Position(-300, 300), this, 1);
    }
}
