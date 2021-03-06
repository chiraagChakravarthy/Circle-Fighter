package circle_fighter.level.level.tutorial.stages;

import circle_fighter.gfx.color.SolidColor;
import circle_fighter.level.level.tutorial.TextHint;
import circle_fighter.level.level.tutorial.Tutorial;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

public class Stage1 extends TutorialStage {
    private ArrayList<TextHint> hintList;
    private int hint;
    public Stage1(Tutorial tutorial) {
        super(tutorial);
        hintList = new ArrayList<>();
        hintList.add(new TextHint("Welcome to Circle Fighter!", new Font("Arial", Font.PLAIN, 38), 5000, new SolidColor(255, 255, 255), 100));
        hintList.add(new TextHint("You may hit [Esc] to enter pause menu", new Font("Arial", Font.PLAIN, 38), 5000, new SolidColor(255, 255, 255), 100));
        hintList.add(new TextHint("Hold [W] to move forward, and [S] to move back.", new Font("Arial", Font.PLAIN, 38), 5000, new SolidColor(255, 255, 255), 100));
        hintList.add(new TextHint("Hold [A] or [D] to Rotate.", new Font("Arial", Font.PLAIN, 38), 5000, new SolidColor(255, 255, 255), 100));
        hintList.add(new TextHint("Hold [Space] to Shoot.", new Font("Arial", Font.PLAIN, 38), 5000, new SolidColor(255, 255, 255), 100));
        hintList.add(new TextHint("Take a second to get a handle on the controls", new Font("Arial", Font.PLAIN, 38), 5000, new SolidColor(255, 255, 255), 100));
        reset();
    }

    @Override
    public void reset() {
        hint = 0;
        for(TextHint textHint : hintList){
            textHint.reset();
        }
    }

    @Override
    public void render(Graphics2D g) {
        hintList.get(hint).render(g);
    }

    @Override
    public void tick() {
        hintList.get(hint).tick();
        if(hintList.get(hint).isFinished()){
            hint++;
            if(hint>=hintList.size()){
                tutorial.advance();
            }
        }
    }

    @Override
    public void keyPressed(int k) {

    }

    @Override
    public void keyReleased(int k) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseScrolled(MouseWheelEvent e) {

    }
}
