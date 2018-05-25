package circle_fighter.level.level.tutorial;

import circle_fighter.engine.Game;
import circle_fighter.game.object.position.Position;
import circle_fighter.game.plane.PlayerPlane;
import circle_fighter.game.plane.bounds.BoundedBounds;
import circle_fighter.gameState.LevelState;
import circle_fighter.level.level.tutorial.stages.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Tutorial extends PlayerPlane {
    private ArrayList<TutorialStage> stages;
    private int stage;
    private LevelState state;
    public Tutorial(LevelState state) {
        super(new Position(0, 0), new BoundedBounds(-Game.getInstance().getGameWidth()/2, -Game.getInstance().getHeight()/2, Game.getInstance().getWidth(), Game.getInstance().getGameHeight()));
        stages = new ArrayList<>();
        stages.add(new Stage1(this));
        stages.add(new Stage2(this));
        stages.add(new Stage3(this));
        stages.add(new Stage4(this));
        stage = 0;
        this.state = state;
        reset();
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
            state.setState(LevelState.SubState.MENU);
            state.setMenu(0);
        }
        else {
            stages.get(stage).reset();
        }
    }
}
