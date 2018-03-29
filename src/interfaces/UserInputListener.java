package interfaces;

import java.awt.event.MouseEvent;

public interface UserInputListener {
    void keyPressed(int k);
    void keyReleased(int k);
    void mousePressed(MouseEvent e);
    void mouseReleased(MouseEvent e);
}
