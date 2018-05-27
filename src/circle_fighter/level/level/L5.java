package circle_fighter.level.level;

import circle_fighter.engine.Game;
import circle_fighter.game.object.implementations.CharacterObject;
import circle_fighter.game.object.objects.bots.BotM2;
import circle_fighter.game.object.position.Position;
import circle_fighter.gameState.LevelState;
import circle_fighter.level.LevelPlane;

public class L5 extends LevelPlane {
    public L5(LevelState state) {
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
        for (int i = 0; i < 5; i++) {
            double radians = 2* Math.PI*i/5;
            new BotM2(new Position(Math.cos(radians)*300, Math.sin(radians)*300), this, 1);
        }
    }
}
