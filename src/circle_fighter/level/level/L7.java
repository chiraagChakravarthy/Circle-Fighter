package circle_fighter.level.level;

import circle_fighter.engine.Game;
import circle_fighter.game.object.implementations.CharacterObject;
import circle_fighter.game.object.objects.bots.BotM2;
import circle_fighter.game.object.objects.bots.BotM3;
import circle_fighter.game.object.position.Position;
import circle_fighter.gameState.LevelState;
import circle_fighter.level.LevelPlane;

public class L7 extends LevelPlane {
    public L7(LevelState state) {
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
        for (int i = 0; i < 3; i++) {
            double radians = i/3.0*Math.PI*2;
            new BotM3(new Position((float)Math.cos(radians)*(i+1)*150, (float)Math.sin(radians)*(i+1)*150), this, 1);
        }
    }
}
