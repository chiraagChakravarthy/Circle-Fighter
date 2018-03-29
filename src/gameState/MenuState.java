package gameState;

import background.Background;
import menu.MainMenu;
import menu.OptionsMenu;
import menu.base.Menu;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MenuState extends GameState {
    private int menu;
    private List<Menu> menus;
    private Background background;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        menus = new ArrayList<>();
        menus.add(new MainMenu(this));
        menus.add(new OptionsMenu(this));
        background = new Background.PlainBackground();
    }

    @Override
    public void tick() {
        background.tick();
        menus.get(menu).tick();
    }

    @Override
    public void render(Graphics2D g) {
        background.render(g);
        menus.get(menu).render(g);
    }

    @Override
    public void keyPressed(int k) {
        background.keyPressed(k);
        menus.get(menu).keyPressed(k);
    }

    @Override
    public void keyReleased(int k) {
        background.keyReleased(k);
        menus.get(menu).keyReleased(k);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        background.mousePressed(e);
        menus.get(menu).mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        background.mouseReleased(e);
        menus.get(menu).mouseReleased(e);
    }

    public void setMenu(int menu) {
        if(menu>=0&&menu<menus.size())
            this.menu = menu;
        else
            System.err.println("Game Menu: " + menu + ", does not exist.");
    }

    public void setBackground(Background background) {
        this.background = background;
    }
}
