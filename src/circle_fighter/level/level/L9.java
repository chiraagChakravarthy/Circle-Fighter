package circle_fighter.level.level;

import circle_fighter.game.object.implementations.CharacterObject;
import circle_fighter.game.object.objects.bots.BotM3;
import circle_fighter.game.object.objects.bots.BotM4;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.object.position.UpdatingPosition;
import circle_fighter.gameState.LevelState;
import circle_fighter.level.LevelPlane;

public class L9 extends LevelPlane {
    public L9(LevelState state) {
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
        for (int i = 0; i < 2; i++) {
            double radians = i/4.0*Math.PI/2;
            new BotM4(new UpdatingPosition((float)(Math.cos(radians)*400), (float)(Math.sin(radians)*400)), this, 1);
        }

        new BotM3(new UpdatingPosition(-200, -200), this, 1);
        new BotM3(new UpdatingPosition(200, 200), this, 1);
    }
}
