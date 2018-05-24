package circle_fighter.functionaliy;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public interface UserInputListener {
    void keyPressed(int k);
    void keyReleased(int k);
    void mousePressed(MouseEvent e);
    void mouseReleased(MouseEvent e);
    void mouseScrolled(MouseWheelEvent e);
}
