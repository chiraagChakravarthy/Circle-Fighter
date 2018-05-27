package circle_fighter.level.level;

import circle_fighter.engine.Game;
import circle_fighter.game.object.implementations.CharacterObject;
import circle_fighter.game.object.objects.bots.BotM3;
import circle_fighter.game.object.objects.turret.TurretBotM1;
import circle_fighter.game.object.position.Position;
import circle_fighter.gameState.LevelState;
import circle_fighter.level.LevelPlane;

public class L6 extends LevelPlane {
    public L6(LevelState state) {
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
        for (int i = 0; i < 50; i++) {
            double radians = i/50.0*Math.PI*2;
            new TurretBotM1(new Position(Math.cos(radians)*600, Math.sin(radians)*600), this, 1);
        }
    }
}
