package circle_fighter.gameState;

import circle_fighter.engine.KeyBindManager;
import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.functionaliy.UserInputListener;

public abstract class GameState implements Updatable, Renderable, UserInputListener{
    protected GameStateManager gsm;
    protected KeyBindManager keyBinds;
    public GameState(GameStateManager gsm, KeyBindManager keyBinds) {
        this.gsm = gsm;
        this.keyBinds = keyBinds;
    }

    public GameStateManager getGsm() {
        return gsm;
    }

    public KeyBindManager getKeyBinds() {
        return keyBinds;
    }
}