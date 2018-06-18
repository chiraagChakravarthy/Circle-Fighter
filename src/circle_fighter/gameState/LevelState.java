package circle_fighter.gameState;

import circle_fighter.gfx.background.Background;
import circle_fighter.gfx.color.Rainbow;
import circle_fighter.engine.Game;
import circle_fighter.engine.KeyBindManager;
import circle_fighter.game.plane.Plane;
import circle_fighter.level.level.*;
import circle_fighter.level.level.tutorial.Tutorial;
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
    private int level, menu, highestLevel;
    private ArrayList<Plane> levels;
    private ArrayList<Menu> menus;
    private SubState state;
    private Background background;

    public LevelState(GameStateManager gsm, KeyBindManager keyBinds) {
        super(gsm, keyBinds);
        level = 0;
        menu = 0;
        levels = new ArrayList();
        levels.add(new Tutorial(this));
        levels.add(new L1(this));
        levels.add(new L2(this));
        levels.add(new L3(this));
        levels.add(new L4(this));
        levels.add(new L5(this));
        levels.add(new L6(this));
        levels.add(new L7(this));
        levels.add(new L8(this));
        levels.add(new L9(this));
        levels.add(new L10(this));
        menus = new ArrayList<>();
        menus.add(new LevelMenu(this, levels.size()-1));
        menus.add(new DeathMenu(this));
        menus.add(new WinMenu(this));
        menus.add(new PauseMenu(this));
        state = SubState.MENU;
        background = new Background.PlainBackground(new Rainbow(0.5f, 10));
        highestLevel = Game.DEBUG?levels.size():0;
    }

    @Override
    public void render(Graphics2D g) {
        switch (state){
            case MENU:
                background.render(g);
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
                background.tick();
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
                background.keyPressed(k);
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
                background.keyReleased(k);
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
                background.mousePressed(e);
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
                background.mouseReleased(e);
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
                background.mouseScrolled(e);
                break;
            case LEVEL:
                levels.get(level).mouseScrolled(e);
                break;
        }
    }

    public Plane getCurrentLevel(){
        return levels.get(level);
    }

    public int getHighestLevel() {
        return highestLevel;
    }

    public void setHighestLevel(int highestLevel) {
        this.highestLevel = Math.max(this.highestLevel, highestLevel);
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
        if(level<levels.size()&&level>=0) {
            this.level = level;
            levels.get(level).reset();
        }
        else {
            menu = 0;
            state = SubState.MENU;
        }
    }

    public int getMenu() {
        return menu;
    }

    public void setMenu(int menu) {
        this.menu = menu;
    }

    public enum SubState {
        MENU,
        LEVEL
    }
}
