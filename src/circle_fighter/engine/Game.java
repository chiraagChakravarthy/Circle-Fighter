package circle_fighter.engine;

import circle_fighter.engine.notification.NotificationManager;
import circle_fighter.engine.test.Test;
import circle_fighter.file.FileManager;
import circle_fighter.gameState.GameStateManager;
import circle_fighter.user.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Game extends Canvas implements Runnable, KeyListener, MouseListener, MouseWheelListener {
    private static Game instance;
    public static final boolean DEBUG = false, TEST = false;

    public final String TITLE = "Circle Fighter";
    private final Window window;
    private int runningTime = 0;
    private final ExecutorService service;
    private boolean running;
    private GameStateManager gsm;
    private KeyBindManager keybinds;

    private Test test;

    private Game() {
        service = Executors.newCachedThreadPool();
        window = new Window(TITLE, this);
        NotificationManager.init();
        addKeyListener(this);
        addMouseListener(this);
        addMouseWheelListener(this);
        requestFocus();
        running = true;
    }

    private void initialize(){
        if(TEST)
            test = new Test();
        else {
            keybinds = new KeyBindManager();
            gsm = new GameStateManager(keybinds);
        }
    }

    public static void main(String[] args) {
        instance = new Game();
        instance.initialize();
        instance.service.submit(instance);
    }

    public void run() {
        try {
            long lastTime = System.nanoTime();
            long timer = System.currentTimeMillis();
            int ticks = 60;
            double ns = 1000000000 / ticks;
            double delta = 0;
            int updates = 0, frames = 0;
            //Allows for the logging of the ticks and frames each second
            //Game Loop\\
            while (running) {
                //Boolean which controls the running of the circle_fighter.game loop. Were it to equal false, the circle_fighter.game would simply freeze.
                /////////////////////////////
                long now = System.nanoTime();
                delta += (now - lastTime) / ns;
                lastTime = now;
                while (delta > 1) {
                    tick();
                    updates++;
                    delta--;
                }
                frames++;
                render();
                /////////////////////////////
                //A tick is the circle_fighter.game's equivalent of an instant. The code above allows time to be constant in a loop that varies
                //in the length of each iteration
                if (System.currentTimeMillis() - timer >= 1000) {
                    System.out.println("FPS: " + frames + ", Ticks: " + updates);
                    updates = 0;
                    frames = 0;
                    timer += 1000;
                }
            }
            //Game Loop\\
            stop();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void stop() {
        running = false;
        FileManager.writeToFile("keybinds.txt", new ArrayList<>(Collections.singletonList(keybinds.save())));
        service.shutdown();
    }

    public void tick() {
        if(TEST)
            test.tick();
        else {
            gsm.tick();
            NotificationManager.tick();
        }
        runningTime++;
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        //Instead of drawing directly to the canvas, drawing to a buffer strategy allows for a concept called triple buffering
        if (bs == null) {
            createBufferStrategy(2);
            //triple buffering in the long term greatly increases performance. Instead of replacing every pixel, triple buffering
            //only changes the pixels that weren't present before. It also searches for and remembers patterns in a single runtime
            //iteration.
            return;
        }

        try {
            Graphics2D g = (Graphics2D) bs.getDrawGraphics();
            if (TEST)
                test.render(g);
            else {
                gsm.render(g);
                NotificationManager.render(g);
            }
            g.dispose();
            bs.show();
        }
        catch (IllegalStateException ignored){

        }
    }

    public void keyTyped(KeyEvent e) {
        //Irrelevant to program
    }

    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        if(TEST) {
            if (test != null)
                test.keyPressed(k);
        }
        else if(gsm != null)
                gsm.keyPressed(k);
    }

    public void keyReleased(KeyEvent e) {
        int k = e.getKeyCode();
        //k is an integer representing the key that was released
        if(TEST) {
            if (test != null)
                test.keyReleased(k);
        }
        else if(gsm != null)
            gsm.keyReleased(k);
    }

    public void mouseClicked(MouseEvent e) {
        //Irrelevant to program
    }

    public void mousePressed(MouseEvent e) {
        //The mouse event itself is passed because it contains information of the mouse button pressed and the location of the mouse
        if(TEST) {
            if (test != null)
                test.mousePressed(e);
        }
        else if(gsm != null)
                gsm.mousePressed(e);
    }

    public void mouseReleased(MouseEvent e) {
        //The mouse event itself is passed because it contains information of the mouse button released and the location of the mouse
        if(TEST) {
            if (test != null)
                test.mouseReleased(e);
        }
        else if(gsm != null)
            gsm.mouseReleased(e);
    }

    public void mouseEntered(MouseEvent e) {
        //Irrelevant to program
    }

    public void mouseExited(MouseEvent e) {
        //Irrelevant to program
    }

    public int getRunningTime() {
        return runningTime;
    }


    public int getGameWidth() {
        return window.getWidth();
    }

    public int getGameHeight() {
        return window.getHeight();
    }

    public Window getWindow() {
        return window;
    }

    public Point mouseLocation(){
        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(mouseLocation, window.getFrame());
        return new Point(mouseLocation.x, mouseLocation.y);
    }

    public ExecutorService getThreadService() {
        return service;
    }

    public static Game getInstance() {
        return instance;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(TEST){
            if(test != null){
                test.mouseScrolled(e);
            }
        }
        else if(gsm != null)
            gsm.mouseScrolled(e);
    }

    public static float msToTicks(float ms){
        return ms*3/50f;
    }

    public static float ticksToMs(float ticks){
        return ticks*50/3f;
    }
}