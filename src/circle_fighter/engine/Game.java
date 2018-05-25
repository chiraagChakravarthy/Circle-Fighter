package circle_fighter.engine;

import circle_fighter.gameState.GameStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Game extends Canvas implements Runnable, KeyListener, MouseListener, MouseWheelListener {
    private static Game instance;
    public final String TITLE = "Circle Fighter";
    private Window window;
    private int runningTime = 0;
    private final ExecutorService service;

    private boolean running;
    private GameStateManager gsm;

    private Game() {
        service = Executors.newCachedThreadPool();
        window = new Window(TITLE, 3, this);
        addKeyListener(this);
        addMouseListener(this);
        addMouseWheelListener(this);
        requestFocus();
        running = true;
    }

    private void initialize(){
        gsm = new GameStateManager();
    }

    public static void main(String[] args) {
        instance = new Game();
        instance.initialize();
        instance.service.submit(instance);
    }

    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        int ticks = 60;
        double ns = 1000000000 / ticks;
        double delta = 0;
        int updates = 0, frames = 0;
        //Allows for the logging of the ticks and frames each second
        //Game Loop\\
        while (running){
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
                //Logs the Frames and the ticks that have passed since the last logging. The minimum time between each
                //logging is a second. (The max being however long the tick and drawTo take to process), so the actual
                //message being logged is a tad misleading
            }
        }
        //Game Loop\\
        stop();
    }

    public void stop() {
        running = false;
        service.shutdown();
    }

    public void tick() {
        gsm.tick();
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
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        gsm.render(g);
        g.dispose();
        bs.show();
    }

    public void keyTyped(KeyEvent e) {
        //Irrelevant to program
    }

    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        gsm.keyPressed(k);
    }

    public void keyReleased(KeyEvent e) {
        int k = e.getKeyCode();
        //k is an integer representing the key that was released
        gsm.keyReleased(k);
    }

    public void mouseClicked(MouseEvent e) {
        //Irrelevant to program
    }

    public void mousePressed(MouseEvent e) {
        //The mouse event itself is passed because it contains information of the mouse button pressed and the location of the mouse
        gsm.mousePressed(e);
    }

    public void mouseReleased(MouseEvent e) {
        //The mouse event itself is passed because it contains information of the mouse button released and the location of the mouse
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

    public Point mouseLocation(){
        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(mouseLocation, window.getFrame());
        return mouseLocation;
    }

    public ExecutorService getThreadService() {
        return service;
    }

    public static Game getInstance() {
        return instance;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        gsm.mouseScrolled(e);
    }
}