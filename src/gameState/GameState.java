package gameState;

import interfaces.Renderable;
import interfaces.Updatable;
import interfaces.UserInputListener;

public abstract class GameState implements Updatable, Renderable, UserInputListener{
    protected GameStateManager gsm;

    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public GameStateManager getGsm() {
        return gsm;
    }
}