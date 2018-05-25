package circle_fighter.gameState;

import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.functionaliy.UserInputListener;

public abstract class GameState implements Updatable, Renderable, UserInputListener{
    protected GameStateManager gsm;
    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public GameStateManager getGsm() {
        return gsm;
    }
}