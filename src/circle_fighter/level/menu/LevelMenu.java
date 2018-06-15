package circle_fighter.level.menu;

import circle_fighter.gfx.color.SolidColor;
import circle_fighter.gameState.LevelState;
import circle_fighter.menu.base.Menu;
import circle_fighter.menu.base.component.Option;

import java.util.ArrayList;

public class LevelMenu extends Menu {
    private LevelState state;
    private int levels;
    public LevelMenu(LevelState state, int levels) {
        super("Level Select", state.getKeyBinds());
        this.state = state;
        this.levels = levels;
        addComponent(new Option("Tutorial", getLowestY()+Menu.COMPONENT_SPACING, this));
        for (int i = 0; i < levels; i++) {
            addComponent(new Option("Level " + (i+1), getLowestY()+Menu.COMPONENT_SPACING, this));
        }
        addComponent(new Option("Back", getLowestY()+COMPONENT_SPACING, this));
    }

    @Override
    protected void onSelect(int selectedOption) {
        exit();
    }

    @Override
    protected void onExit(int selectedOption) {
        if(selectedOption==levels+1){
            state.getGsm().setGameState(0);
        }
        else {
            state.setState(LevelState.SubState.LEVEL);
            state.setLevel(selectedOption);
        }
    }

    @Override
    protected void onOpen(int selectedOption) {
        ArrayList<Option> options = getOptions();
        for (int i = 0; i < options.size(); i++) {
            if(i<state.getHighestLevel()) {
                options.get(i).setEnabled(true);
                options.get(i).setColor(new SolidColor(255, 255, 255));
            }
            else if(i==state.getHighestLevel()){
                options.get(i).setEnabled(true);
                options.get(i).setColor(new SolidColor(255, 0, 0));
            }
            else {
                options.get(i).setColor(new SolidColor(255, 255, 255));
                options.get(i).setEnabled(i==options.size()-1);
            }
        }
        options.get(options.size()-1).setColor(new SolidColor(255, 255, 255));
    }
}