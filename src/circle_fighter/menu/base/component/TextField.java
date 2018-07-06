package circle_fighter.menu.base.component;

import circle_fighter.engine.Game;
import circle_fighter.functionaliy.UserInputListener;
import circle_fighter.gfx.text.Text;
import circle_fighter.gfx.text.TextBuilder;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
//TODO add pointer by super imposing rectangle over desired character, and allow for it to be controlled using vertical and horizontal arrow keys.
public class TextField extends MenuComponent implements UserInputListener{
    private Text text;
    private int x, y, max;
    private boolean shift;

    public TextField(int max, int y){
        this.y = y;
        reset();
        this.x = text.getX();
        this.max = max;
    }

    @Override
    public void tick() {
        super.tick();
        text.tick();
        text.setX(x+transitionOffset);
        text.setY(y+scrollingOffset);
    }

    @Override
    public void render(Graphics2D g) {
        text.render(g);
    }

    @Override
    public Rectangle getArea(boolean onScreen) {
        return new Rectangle(text.getX(), text.getY()-(onScreen?scrollingOffset:0), text.getWidth(), text.getHeight());
    }

    @Override
    public void keyPressed(int k) {
        if(k==KeyEvent.VK_SHIFT)
            shift = true;
        else if(k>=KeyEvent.VK_A&&k<=KeyEvent.VK_Z||k>= KeyEvent.VK_0&&k<=KeyEvent.VK_9)
            addText(shift ? KeyEvent.getKeyText(k) : KeyEvent.getKeyText(k).toLowerCase());
        else if(k==KeyEvent.VK_SPACE)
            addText(" ");
        else if(k==KeyEvent.VK_BACK_SPACE){
            String text = this.text.getText();
            if(text.length()>0){
                this.text.setText(text.substring(0, text.length()-1));
            }
        }
    }

    private void addText(String text){
        String newText = this.text.getText()+text;
        if(newText.length()<=max){
            this.text.setText(newText);
        }
    }

    @Override
    public void keyReleased(int k) {
        if(k==KeyEvent.VK_SHIFT)
            shift=false;
    }

    public String get(){
        return text.getText();
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

    public void reset(){
        text = new TextBuilder()
                .setText("")
                .setTextAlign(Text.Alignment.CENTER)
                .setShowBorder(true)
                .setY(y)
                .setCenter(true)
                .setFont(new Font("Arial", Font.PLAIN, 30))
                .setWidth(Game.getInstance().getGameWidth()*2/3)
                .setMinimizeWidth(false)
                .get();
        shift = false;
    }
}
