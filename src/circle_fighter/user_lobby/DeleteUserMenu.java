package circle_fighter.user_lobby;

import circle_fighter.engine.Game;
import circle_fighter.engine.KeyBindManager;
import circle_fighter.engine.notification.Notification;
import circle_fighter.engine.notification.NotificationManager;
import circle_fighter.gameState.UserLobbyState;
import circle_fighter.gfx.color.SolidColor;
import circle_fighter.menu.base.Menu;
import circle_fighter.menu.base.component.ListOption;
import circle_fighter.menu.base.component.TextBox;

public class DeleteUserMenu extends Menu {
    private UserLobbyState state;
    public DeleteUserMenu(UserLobbyState state) {
        super(state.getKeyBinds(), true);
        this.state = state;
    }

    @Override
    protected void onSelect(int selectedOption) {
        exit();
    }

    @Override
    protected void onExit(int selectedOption) {
        state.setMenu(0);
        if(selectedOption==1){
            state.getGsm().getUsers().removeCurrentUser();
            state.getGsm().setGameState(0);
            NotificationManager.addNotification("User Deleted");
        }
    }

    @Override
    protected void constructMenu() {
        addComponent(new TextBox("Are you sure you want to delete \"" + state.getUser().getName() + "\"?",
                getLowestY()+Menu.COMPONENT_SPACING, Game.getInstance().getGameWidth()*2/3, new SolidColor(255, 255, 255)));
        addComponent(new ListOption("No", getLowestY()+COMPONENT_SPACING, this));
        addComponent(new ListOption("Yes", getLowestY()+COMPONENT_SPACING, this));
    }

    @Override
    protected String getTitle() {
        return "Delete";
    }
}
