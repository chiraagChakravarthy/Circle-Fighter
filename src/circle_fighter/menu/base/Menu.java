package circle_fighter.menu.base;

import circle_fighter.gfx.color.SolidColor;
import circle_fighter.engine.Game;
import circle_fighter.engine.KeyBindManager;
import circle_fighter.functionaliy.Renderable;
import circle_fighter.functionaliy.Updatable;
import circle_fighter.functionaliy.UserInputListener;
import circle_fighter.menu.base.component.MenuComponent;
import circle_fighter.menu.base.component.Option;
import circle_fighter.menu.base.component.TextBox;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
//TODO make all menus return to previous screen on esc by setting selected option to that which will return it to such on exit
public abstract class Menu implements Updatable, Renderable, UserInputListener{
    private ArrayList<MenuComponent> menuComponents;
    private ArrayList<Option> options;
    private KeyBindManager keyBinds;

    protected static final int COMPONENT_SPACING = 20;
    private static final float TRANSITION_RATE = 1f, SCROLLING_RATE = 20;
    private int selectedOption;
    private float activeOption;
    private float scrollingOffset;
    protected MenuProgression progression;

    private TextBox title;

    public Menu(String title, KeyBindManager keyBinds){
        this.title = new TextBox(title, 50, (int) (Game.getInstance().getGameWidth()*2.0/3), new SolidColor(255, 255, 255));
        this.keyBinds = keyBinds;
        menuComponents = new ArrayList<>();
        options = new ArrayList<>();
        addComponent(this.title);
        selectedOption = -1;
        activeOption = 0;
        progression = MenuProgression.EXITING;
    }

    public void tick(){
        if(menuComponents.get(menuComponents.size()-1).isFinishedExiting()&&progression.equals(MenuProgression.EXITING)){
            progression = MenuProgression.ENTERING;
            onOpen(selectedOption);
            selectedOption = -1;
        }
        for(MenuComponent component : menuComponents){
            component.setScrollingOffset((int) scrollingOffset);
            component.tick();
        }
        if(progression.equals(MenuProgression.ENTERING)){
            if(activeOption < menuComponents.size()){
                MenuComponent menuComponent = menuComponents.get((int)activeOption);
                if(menuComponent.getState().equals(MenuProgression.EXITING)) menuComponent.advanceState();
                activeOption+=TRANSITION_RATE;
            }
            else if(menuComponents.get(menuComponents.size()-1).isFinishedEntering()){
                progression = MenuProgression.DEFAULT;
                activeOption = 0;
            }
        }
        else if(progression.equals(MenuProgression.DEFAULT)){
            if (selectedOption >= 0 && selectedOption < options.size()) options.get(selectedOption).select();
            Point mouse = Game.getInstance().mouseLocation();
            for (int i = 0; i < options.size(); i++) {
                if(options.get(i).getArea(true).contains(mouse)){
                    selectedOption = i;
                    return;
                }
            }
            selectedOption = -1;
        }
        else if(progression.equals(MenuProgression.EXITING)){
            if(activeOption < menuComponents.size()){
                MenuComponent menuComponent = menuComponents.get((int)activeOption);
                if(menuComponent.getState().equals(MenuProgression.DEFAULT)) menuComponent.advanceState();
                activeOption+=TRANSITION_RATE;
            }
            else if(menuComponents.get(menuComponents.size()-1).isFinishedExiting()){
                activeOption = 0;
                onExit(selectedOption);
            }
        }
    }

    public void render(Graphics2D g){
        for(MenuComponent menuComponent : menuComponents){
            menuComponent.render(g);
        }
    }

    protected abstract void onSelect(int selectedOption);
    protected abstract void onExit(int selectedOption);
    protected abstract void onOpen(int selectedOption);

    public void keyPressed(int k){
        if(k==KeyEvent.VK_UP){
            if(progression.equals(MenuProgression.DEFAULT))selectedOption = selectedOption < 0 ? options.size()-1 : selectedOption == 0 ? options.size()-1 : selectedOption - 1;
        }
        else if(k==KeyEvent.VK_DOWN){
            if(progression.equals(MenuProgression.DEFAULT))selectedOption = selectedOption < 0 ? 0 : selectedOption==options.size()-1 ? 0 : selectedOption + 1;
        }
        else if(k==KeyEvent.VK_ENTER && selectedOption != -1&&options.get(selectedOption).isEnabled()) onSelect(selectedOption);
    }

    public void keyReleased(int k){

    }

    public void mousePressed(MouseEvent e) {
        if(progression.equals(MenuProgression.DEFAULT) && selectedOption != -1&&options.get(selectedOption).isEnabled()) onSelect(selectedOption);
    }

    public void mouseReleased(MouseEvent e){

    }

    //TODO fix osx glitch where this rapidly switches between 1 and -1
    @Override
    public void mouseScrolled(MouseWheelEvent e) {
        if(progression.equals(MenuProgression.DEFAULT))
            scrollingOffset = -Math.max(Math.min(Math.abs(scrollingOffset)+SCROLLING_RATE*e.getUnitsToScroll(), getLowestY()+COMPONENT_SPACING-Game.getInstance().getGameHeight()), 0);
    }

    protected void addComponent(MenuComponent menuComponent){
        menuComponents.add(menuComponent);
    }

    protected void exit(){
        progression = MenuProgression.EXITING;
    }

    public void addOption(Option option){
        options.add(option);
    }

    protected int getLowestY(){
        float maxY = 0;
        for(MenuComponent component : menuComponents){
            maxY = (float) Math.max(component.getArea(false).getMaxY(), maxY);
        }
        return (int)maxY;
    }

    private int getHighestY(){
        return menuComponents.get(0).getArea(false).y;
    }

    protected ArrayList<MenuComponent> getMenuComponents() {
        return menuComponents;
    }

    protected int options(){
        return options.size();
    }

    protected void setSelectedOption(int selectedOption) {
        this.selectedOption = selectedOption;
    }

    public int getSelectedOption() {
        return selectedOption;
    }

    protected ArrayList<Option> getOptions() {
        return options;
    }

    public TextBox getTitle() {
        return title;
    }
}