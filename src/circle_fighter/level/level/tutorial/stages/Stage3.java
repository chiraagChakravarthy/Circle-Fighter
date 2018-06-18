package circle_fighter.level.level.tutorial.stages;

import circle_fighter.game.object.position.UpdatingPosition;
import circle_fighter.gfx.color.SolidColor;
import circle_fighter.engine.Game;
import circle_fighter.game.object.implementations.CharacterObject;
import circle_fighter.game.object.objects.bots.TutorialBot;
import circle_fighter.game.object.position.Position;
import circle_fighter.level.level.tutorial.TextHint;
import circle_fighter.level.level.tutorial.Tutorial;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

public class Stage3 extends TutorialStage {
    private Position playerPosition;
    private ArrayList<TextHint> hints;
    private int hint;

    public Stage3(Tutorial tutorial) {
        super(tutorial);
        hints = new ArrayList<>();
        hints.add(new TextHint("This is a Bot. It wants to kill you, and can do so by touching you.", new Font("Arial", Font.PLAIN, 38), 5000, new SolidColor(255, 255, 255), 100));
        hints.add(new TextHint("You don't want that to happen. You know what to do.", new Font("Arial", Font.PLAIN, 38), 5000, new SolidColor(255, 255, 255), 100));
    }

    @Override
    public void reset() {
        new TutorialBot(new UpdatingPosition(0, 0), tutorial, 1);
        playerPosition = new Position(Game.getInstance().getGameWidth()/4, -Game.getInstance().getGameHeight()/4);
        for(TextHint hint : hints){
            hint.reset();
        }
        hint = 0;
    }

    @Override
    public void render(Graphics2D g) {
        if(hint < hints.size())
            hints.get(hint).render(g);
    }

    @Override
    public void tick() {
        if(tutorial.getObjectManager().getBy(1, CharacterObject.class).size()==0) {
            tutorial.advance();
        }
        else {
            if(hint < hints.size()) {
                hints.get(hint).tick();
                if(hints.get(hint).isFinished()) {
                    hint++;
                }
            }
            tutorial.getPlayer().getPosition().setX(playerPosition.getX()).setY(playerPosition.getY());
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
