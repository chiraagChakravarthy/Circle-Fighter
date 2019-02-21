package circle_fighter.engine;

import javax.swing.*;
import java.awt.*;

public class Window {
    //Window was a custom made class that adds more functionality to a basic JFrame. Specifically the ability to easily create a fullscreen
    //window.
    private final int fullScreenMode;

    private int windowMode = 0;
    private JFrame frame;
    //Main window the rest of the functionality runs on
    private GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    public Window(String title, Game game) {
        String osName = System.getProperty("os.name").toLowerCase();
        if(osName.startsWith("windows")){
            fullScreenMode = 2;
        }
        else if(osName.startsWith("mac os x")){
            fullScreenMode = 3;
        }
        else {
            fullScreenMode = 1;
        }
        windowMode = fullScreenMode;
        frame = new JFrame(title);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(game);
        setFullScreen();
    }

    private void setFullScreen() {
        switch (windowMode) {
            case 1:
                device.setFullScreenWindow(null);
                frame.dispose();
                frame.setSize(new Dimension(device.getDisplayMode().getWidth(), device.getDisplayMode().getHeight()));
                frame.setResizable(true);
                frame.setUndecorated(false);
                frame.setVisible(true);
                break;
            //Creates a resizable non-fullScreenMode window the size of the display
            case 2:
                device.setFullScreenWindow(null);
                frame.dispose();
                frame.setMinimumSize(new Dimension(device.getDisplayMode().getWidth(), device.getDisplayMode().getHeight()));
                frame.setPreferredSize(new Dimension(device.getDisplayMode().getWidth(), device.getDisplayMode().getHeight()));
                frame.setMaximumSize(new Dimension(device.getDisplayMode().getWidth(), device.getDisplayMode().getHeight()));
                frame.setUndecorated(true);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                break;
            //Creates a non-resizable window which takes up the entire desktop
            case 3:
                frame.dispose();
                frame.setResizable(false);
                if (device.isFullScreenSupported())
                    device.setFullScreenWindow(frame);
                else
                    System.out.println("FullScreen windowMode is not supported.");
                break;

            default:
                System.out.println("FullScreen windowMode not supported.");
                setWindowMode(1);
                //Creates a window which covers the entire display. Cannot be swiped away from. Creates best gaming experience.
        }
    }

    public void setWindowMode(int windowMode) {
        this.windowMode = windowMode;
        setFullScreen();
    }

    public void setFullScreen(boolean fullScreen){
        System.out.println(getHeight());
        setWindowMode(fullScreen?fullScreenMode:1);
    }

    public boolean isFullScreen(){
        return windowMode==fullScreenMode;
    }


    public String getTitle() {
        return frame.getTitle();
    }

    public void setTitle(String title) {
        frame.setTitle(title);
    }

    public int getWidth() {
        return frame.getWidth();
    }

    public int getHeight() {
        return frame.getHeight();
    }

    public void dispose() {
        frame.dispose();
    }

    public JFrame getFrame() {
        return frame;
    }
}
