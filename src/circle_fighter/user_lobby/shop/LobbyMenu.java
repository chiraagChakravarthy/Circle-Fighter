package circle_fighter.user_lobby.shop;

import circle_fighter.engine.Game;
import circle_fighter.gameState.UserLobbyState;
import circle_fighter.gfx.color.SolidColor;
import circle_fighter.menu.base.Menu;
import circle_fighter.menu.base.component.ListOption;
import circle_fighter.menu.base.component.TextBox;
import circle_fighter.user.element.UserLevel;

public class LobbyMenu extends Menu {
    private TextBox levelDisplay;
    private UserLobbyState state;
    public LobbyMenu(UserLobbyState state) {
        super(state.getKeyBinds(), true);
        this.state = state;
    }

    @Override
    protected void onSelect(int selectedOption) {
        exit();
    }

    @Override
    protected void onExit(int selectedOption) {
        switch (selectedOption){
            case 0:
                state.setMenu(11);
                break;
            case 1:
                state.setMenu(1);
                break;
            case 2:
                state.getGsm().getUsers().clearCurrentUser();
                state.getGsm().setGameState(0);
                break;
        }
    }

    @Override
    protected void constructMenu() {
        UserLevel level = state.getUser().getLevel();
        levelDisplay = new TextBox("Level: " + level.getLevel(), getLowestY()+Menu.COMPONENT_SPACING, Game.getInstance().getGameWidth()/3, new SolidColor(255, 255, 255));
        addComponent(levelDisplay);
        addComponent(new ListOption("Play", getLowestY()+Menu.COMPONENT_SPACING, this));
        addComponent(new ListOption("Shop", getLowestY()+Menu.COMPONENT_SPACING, this));
        addComponent(new ListOption("Exit", getLowestY()+Menu.COMPONENT_SPACING, this));
    }

    @Override
    protected String getTitle() {
        return state.getUser().getName();
    }
}
