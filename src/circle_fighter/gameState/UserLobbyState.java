package circle_fighter.gameState;

import circle_fighter.engine.KeyBindManager;
import circle_fighter.gfx.background.Background;
import circle_fighter.gfx.color.Rainbow;
import circle_fighter.menu.base.Menu;
import circle_fighter.user.User;
import circle_fighter.user_lobby.DeleteUserMenu;
import circle_fighter.user_lobby.GameModeMenu;
import circle_fighter.user_lobby.SinglePlayerMenu;
import circle_fighter.user_lobby.shop.LobbyMenu;
import circle_fighter.user_lobby.shop.MainShop;
import circle_fighter.user_lobby.shop.health.HealthShop;
import circle_fighter.user_lobby.shop.health.InvulnerabilityShop;
import circle_fighter.user_lobby.shop.health.MaxHealthShop;
import circle_fighter.user_lobby.shop.health.RegenerationShop;
import circle_fighter.user_lobby.shop.movement.*;
import circle_fighter.user_lobby.shop.turret.ReloadRateShop;
import circle_fighter.user_lobby.shop.turret.SwivelAngleShop;
import circle_fighter.user_lobby.shop.turret.TurretShop;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class UserLobbyState extends GameState {
    private ArrayList<Menu> menus;
    private int currentMenu;
    private Background background;
    public UserLobbyState(GameStateManager gsm, KeyBindManager keyBinds) {
        super(gsm, keyBinds);
        menus = new ArrayList<>(Arrays.asList(
                new LobbyMenu(this),//0
                new MainShop(this),//1
                new MovementShop(this),//2
                new VelocityShop(this),//3
                new AccelerationShop(this),//4
                new AngularVelocityShop(this),//5
                new AngularAccelerationShop(this),//6
                new HealthShop(this),//7
                new MaxHealthShop(this),//8
                new RegenerationShop(this),//9
                new InvulnerabilityShop(this),//10
                new GameModeMenu(this),//11
                new SinglePlayerMenu(this),//12
                new TurretShop(this),//13
                new ReloadRateShop(this),//14
                new SwivelAngleShop(this),//15
                new DeleteUserMenu(this)//16
        ));
        currentMenu = 0;
        background = new Background.PlainBackground(new Rainbow(0.5f, 1));
    }

    @Override
    public void render(Graphics2D g) {
        background.render(g);
        menus.get(currentMenu).render(g);
    }

    @Override
    public void tick() {
        background.tick();
        menus.get(currentMenu).tick();
    }

    @Override
    public void keyPressed(int k) {
        background.keyPressed(k);
        menus.get(currentMenu).keyPressed(k);
    }

    @Override
    public void keyReleased(int k) {
        background.keyReleased(k);
        menus.get(currentMenu).keyReleased(k);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        background.mousePressed(e);
        menus.get(currentMenu).mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        background.mouseReleased(e);
        menus.get(currentMenu).mouseReleased(e);
    }

    @Override
    public void mouseScrolled(MouseWheelEvent e) {
        background.mouseScrolled(e);
        menus.get(currentMenu).mouseScrolled(e);
    }

    public User getUser(){
        return gsm.getUsers().getCurrentUser();
    }

    public void setMenu(int menu) {
        currentMenu = menu;
    }
}
