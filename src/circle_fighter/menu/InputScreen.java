package circle_fighter.menu;

import circle_fighter.color.SolidColor;
import circle_fighter.engine.Game;
import circle_fighter.engine.KeyBindManager;
import circle_fighter.menu.base.Menu;
import circle_fighter.menu.base.component.Option;
import circle_fighter.menu.base.component.TextBox;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class InputScreen extends Menu {
    private static final String BLINKER_TEXT = "|";

    private String input;
    private TextBox inputBox;

    private boolean upperCase;
    private int blinkerTime, maximumCharacters;

    public InputScreen(String title, int maximumCharacters, KeyBindManager manager){
        super(title, manager);
        this.maximumCharacters = maximumCharacters;
        input = "";
        inputBox = new TextBox(input, getLowestY() + COMPONENT_SPACING, Game.getInstance().getGameWidth()*2/3, new SolidColor(255, 255, 255));
        blinkerTime = 0;
        addComponent(inputBox);
        addComponent(new Option("Submit", getLowestY() + COMPONENT_SPACING, this));
        addComponent(new Option("Clear", getLowestY() + COMPONENT_SPACING, this));
        addComponent(new Option("Cancel", getLowestY() + COMPONENT_SPACING, this));
        setSelectedOption(0);
    }

    public String getInput() {
        return input;
    }

    public void tick(){
        super.tick();
        blinkerTime = blinkerTime >= 60 ? 0 : blinkerTime+1;
        inputBox.setMessage(input + (blinkerTime/2==1?BLINKER_TEXT:" "));
    }

    @Override
    protected void onSelect(int selectedOption) {
        if(selectedOption != 1) exit();
        else input = "";
    }

    @Override
    protected void onExit(int selectedOption) {
        input = "";
    }

    public void render(Graphics2D g){
        super.render(g);
    }

    @Override
    public void keyPressed(int k){
        if(k==KeyEvent.VK_SHIFT) upperCase = true;
        else if(k==KeyEvent.VK_BACK_SPACE && input.length()>0)input = input.substring(0, input.length()-1);
        else if (k >= KeyEvent.VK_A && k <= KeyEvent.VK_Z)
            addToInput(upperCase ? KeyEvent.getKeyText(k).toUpperCase() : KeyEvent.getKeyText(k).toLowerCase());
        else if((k >= KeyEvent.VK_COMMA && k <= KeyEvent.VK_EQUALS)
                || (k >= KeyEvent.VK_OPEN_BRACKET && k <= KeyEvent.VK_CLOSE_BRACKET)
                || (k >= KeyEvent.VK_SUBTRACT && k <= KeyEvent.VK_DECIMAL)) addToInput(KeyEvent.getKeyText(k));
        else if(k==KeyEvent.VK_SPACE) addToInput(" ");
        else super.keyPressed(k);
    }

    private void addToInput(String add){
        if(input.length()<maximumCharacters) input += add;
    }

    @Override
    public void keyReleased(int k) {
        if(k==KeyEvent.VK_SHIFT) upperCase = false;
        else super.keyReleased(k);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if((input.length() >= input.replace(" ", "").length() && input.length() > 0) || getSelectedOption()==2) super.mousePressed(e);
    }
}