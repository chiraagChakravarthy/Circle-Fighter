package circle_fighter.user_lobby.shop.turret;

import circle_fighter.engine.Game;
import circle_fighter.gameState.UserLobbyState;
import circle_fighter.gfx.color.SolidColor;
import circle_fighter.menu.base.Menu;
import circle_fighter.menu.base.component.ListOption;
import circle_fighter.menu.base.component.TextBox;

public class TurretShop extends Menu {
    private UserLobbyState state;
    private TextBox upgradePointDisplay;

    public TurretShop(UserLobbyState state) {
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
                state.setMenu(14);
                break;
            case 1:
                state.setMenu(15);
                break;
            case 2:
                state.setMenu(1);
                break;
        }
    }

    @Override
    protected void constructMenu() {
        upgradePointDisplay = new TextBox("Upgrade Points: " + state.getUser().getUpgradePoints(), getLowestY()+Menu.COMPONENT_SPACING, Game.getInstance().getGameWidth()/3, new SolidColor(255, 255, 255));
        addComponent(upgradePointDisplay);
        addComponent(new ListOption("Reload Rate", getLowestY()+Menu.COMPONENT_SPACING, this));
        addComponent(new ListOption("Swivel Angle", getLowestY()+Menu.COMPONENT_SPACING, this));
        addComponent(new ListOption("Back", getLowestY()+Menu.COMPONENT_SPACING, this));
    }

    @Override
    protected String getTitle() {
        return "Turret";
    }
}
