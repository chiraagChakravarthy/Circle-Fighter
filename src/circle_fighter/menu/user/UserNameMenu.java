package circle_fighter.menu.user;

import circle_fighter.gameState.MenuState;
import circle_fighter.menu.base.Menu;
import circle_fighter.menu.base.MenuProgression;
import circle_fighter.menu.base.StateMenu;
import circle_fighter.menu.base.component.ListOption;
import circle_fighter.menu.base.component.TextField;
import circle_fighter.user.UserManager;

public class UserNameMenu extends StateMenu {
    private TextField text;
    private UserManager users;
    public UserNameMenu(MenuState state) {
        super(state, false);
        users = state.getGsm().getUsers();
    }

    @Override
    public void keyPressed(int k) {
        super.keyPressed(k);
        if(progression.equals(MenuProgression.DEFAULT))
            text.keyPressed(k);
    }

    @Override
    public void keyReleased(int k) {
        super.keyReleased(k);
        if(progression.equals(MenuProgression.DEFAULT))
            text.keyReleased(k);
    }

    @Override
    protected void constructMenu() {
        text = new TextField(20, getLowestY()+Menu.COMPONENT_SPACING);
        addComponent(text);
        addComponent(new ListOption("Cancel", getLowestY()+Menu.COMPONENT_SPACING, this));
        addComponent(new ListOption("Done", getLowestY()+Menu.COMPONENT_SPACING, this));
    }

    @Override
    protected String getTitle() {
        return "New User";
    }

    @Override
    protected void onSelect(int selectedOption) {
        exit();
    }

    @Override
    protected void onExit(int selectedOption) {
        state.setMenu(3);
        switch (selectedOption){
            case 1:
                users.addUser(text.get());
                users.setCurrentUser(users.amount()-1);
                text.reset();
                break;
        }
    }

    @Override
    protected void onOpen(int selectedOption) {
        super.onOpen(selectedOption);
    }
}
