package circle_fighter.menu.base;

import circle_fighter.gameState.MenuState;

public abstract class StateMenu extends Menu {
    protected MenuState state;
    public StateMenu(MenuState state, boolean reconstructOnOpen){
        super(state.getKeyBinds(), reconstructOnOpen);
        this.state = state;
    }
}