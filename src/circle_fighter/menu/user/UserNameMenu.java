package circle_fighter.menu.user;

import circle_fighter.gameState.MenuState;
import circle_fighter.menu.base.Menu;
import circle_fighter.menu.base.MenuProgression;
import circle_fighter.menu.base.StateMenu;
import circle_fighter.menu.base.component.Option;
import circle_fighter.menu.base.component.TextField;

public class UserNameMenu extends StateMenu {
    private TextField text;
    public UserNameMenu(MenuState state) {
        super(state, "New User");
        text = new TextField(20, getLowestY()+Menu.COMPONENT_SPACING);
        addComponent(text);
        addComponent(new Option("Back", getLowestY()+Menu.COMPONENT_SPACING, this));
        addComponent(new Option("Done", getLowestY()+Menu.COMPONENT_SPACING, this));
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
    protected void onSelect(int selectedOption) {
        exit();
    }

    @Override
    protected void onExit(int selectedOption) {
        switch (selectedOption){
            case 0:
                state.setMenu(3);
                //next user preference menu
                break;
            case 1:
                text.reset();
                break;
        }
    }

    @Override
    protected void onOpen(int selectedOption) {

    }
}
