package circle_fighter.gameState;

import circle_fighter.engine.KeyBindManager;
import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.functionaliy.UserInputListener;
import circle_fighter.user.UserManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

public class GameStateManager implements Renderable, Updatable, UserInputListener{
    public static final int MENU_STATE = 0, PLAY_STATE = 1;
    private ArrayList<GameState> states;
    private int gameState;
    private UserManager users;
    public GameStateManager(KeyBindManager keyBinds) {
        gameState = MENU_STATE;
        users = new UserManager();
        states = new ArrayList<>();
        registerStates(keyBinds);
    }

    public void tick() {
        states.get(gameState).tick();
    }

    public void render(Graphics2D g) {
        states.get(gameState).render(g);
    }

    public void keyPressed(int k) {
        states.get(gameState).keyPressed(k);
    }

    public void keyReleased(int k) {
        states.get(gameState).keyReleased(k);
    }

    public void mousePressed(MouseEvent e) {
        states.get(gameState).mousePressed(e);
    }

    public void mouseReleased(MouseEvent e) {
        states.get(gameState).mouseReleased(e);
    }

    @Override
    public void mouseScrolled(MouseWheelEvent e) {
        states.get(gameState).mouseScrolled(e);
    }

    public void setGameState(int gameState) {
        if (gameState < states.size()&&gameState>=0)
            this.gameState = gameState;
        else
            System.out.println("GameState " + gameState + " does not exist.");
    }

    public UserManager getUsers() {
        return users;
    }

    private void registerStates(KeyBindManager keyBinds) {
        states.add(new MenuState(this, keyBinds));
        states.add(new LevelState(this, keyBinds));
        states.add(new UserLobbyState(this, keyBinds));
    }
}
