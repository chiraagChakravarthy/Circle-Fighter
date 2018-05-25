package circle_fighter.level.level.tutorial;

import circle_fighter.engine.Game;
import circle_fighter.gameState.LevelState;
import circle_fighter.level.LevelPlane;
import circle_fighter.level.level.tutorial.stages.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Tutorial extends LevelPlane {
    private ArrayList<TutorialStage> stages;
    private int stage;
    public Tutorial(LevelState state) {
        super(Game.getInstance().getGameWidth(), Game.getInstance().getHeight(), state);
        stages = new ArrayList<>();
        stages.add(new Stage1(this));
        stages.add(new Stage2(this));
        stages.add(new Stage3(this));
        stages.add(new Stage4(this));
        stage = 0;
    }

    @Override
    public void tick() {
        super.tick();
        stages.get(stage).tick();
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        stages.get(stage).render(g);
    }

    @Override
    public void reset() {
        super.reset();
        stage = -1;
        advance();
    }

    @Override
    public void keyPressed(int k) {
        switch (k){
            case KeyEvent.VK_ESCAPE:
                state.setState(LevelState.SubState.MENU);
                state.setMenu(3);
                break;
            default:
                super.keyPressed(k);
        }
    }

    public void advance(){
        stage++;
        if(stage>=stages.size()) {
            win();
            state.setMenu(0);
        }
        else {
            stages.get(stage).reset();
        }
    }
}
