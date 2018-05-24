package circle_fighter.gameState;

import circle_fighter.game.plane.Plane;
import circle_fighter.level.menu.DeathMenu;
import circle_fighter.level.menu.LevelMenu;
import circle_fighter.level.menu.PauseMenu;
import circle_fighter.level.menu.WinMenu;
import circle_fighter.menu.base.Menu;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

public class LevelState extends GameState {
    private int level, menu;
    private ArrayList<Plane> levels;
    private ArrayList<Menu> menus;
    private SubState state;

    public LevelState(GameStateManager gsm) {
        super(gsm);
        level = 0;
        menu = 0;

        levels = new ArrayList();

        menus = new ArrayList<>();
        menus.add(new LevelMenu(this));
        menus.add(new DeathMenu(this));
        menus.add(new WinMenu(this));
        menus.add(new PauseMenu(this));

        state = SubState.MENU;
    }

    @Override
    public void render(Graphics2D g) {
        switch (state){
            case MENU:
                menus.get(menu).render(g);
                break;
            case LEVEL:
                levels.get(level).render(g);
                break;
        }
    }

    @Override
    public void tick() {
        switch (state){
            case MENU:
                menus.get(menu).tick();
                break;
            case LEVEL:
                levels.get(level).tick();
                break;
        }
    }

    @Override
    public void keyPressed(int k) {
        switch (state){
            case MENU:
                menus.get(menu).keyPressed(k);
                break;
            case LEVEL:
                levels.get(level).keyPressed(k);
                break;
        }
    }

    @Override
    public void keyReleased(int k) {
        switch (state){
            case MENU:
                menus.get(menu).keyReleased(k);
                break;
            case LEVEL:
                levels.get(level).keyReleased(k);
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (state){
            case MENU:
                menus.get(menu).mousePressed(e);
                break;
            case LEVEL:
                levels.get(level).mousePressed(e);
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (state){
            case MENU:
                menus.get(menu).mouseReleased(e);
                break;
            case LEVEL:
                levels.get(level).mouseReleased(e);
                break;
        }
    }

    @Override
    public void mouseScrolled(MouseWheelEvent e) {
        switch (state){
            case MENU:
                menus.get(menu).mouseScrolled(e);
                break;
            case LEVEL:
                levels.get(level).mouseScrolled(e);
                break;
        }
    }

    public SubState getState() {
        return state;
    }

    public void setState(SubState state) {
        this.state = state;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMenu() {
        return menu;
    }

    public void setMenu(int menu) {
        this.menu = menu;
    }

    private enum SubState {
        MENU,
        LEVEL
    }
}
