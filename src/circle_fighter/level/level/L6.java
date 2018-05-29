package circle_fighter.level.level;

import circle_fighter.game.object.implementations.CharacterObject;
import circle_fighter.game.object.objects.bots.BotM3;
import circle_fighter.game.object.position.Position;
import circle_fighter.gameState.LevelState;
import circle_fighter.level.LevelPlane;

public class L6 extends LevelPlane {
    public L6(LevelState state) {
        super(1920, 1080, state);
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
        new BotM3(new Position(-250, -300), this, 1);
    }
}
