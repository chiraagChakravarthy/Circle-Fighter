package circle_fighter.level.level.tutorial.stages;

import circle_fighter.gfx.color.SolidColor;
import circle_fighter.level.level.tutorial.TextHint;
import circle_fighter.level.level.tutorial.Tutorial;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

public class Stage4 extends TutorialStage {
    private ArrayList<TextHint> hintList;
    private int hint;
    public Stage4(Tutorial tutorial) {
        super(tutorial);
        hintList = new ArrayList<>();
        hintList.add(new TextHint("Congratulations! You have completed the tutorial!", new Font("Arial", Font.PLAIN, 38), 5000, new SolidColor(255, 255, 255), 100));
        hintList.add(new TextHint("If you want more of a challenge, try out some of the other levelDisplay.", new Font("Arial", Font.PLAIN, 38), 5000, new SolidColor(255, 255, 255), 100));
        hintList.add(new TextHint("Keep in mind, they'll actually chase after you.", new Font("Arial", Font.PLAIN, 38), 5000, new SolidColor(255, 255, 255), 100));
        hintList.add(new TextHint("I won't hold you in place next time. Promise.", new Font("Arial", Font.PLAIN, 38), 5000, new SolidColor(255, 255, 255), 100));
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
