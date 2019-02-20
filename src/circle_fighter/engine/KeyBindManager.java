package circle_fighter.engine;

import circle_fighter.file.DataStorage;
import circle_fighter.file.FileManager;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KeyBindManager {
    public static final int PLAYER_FORWARD = 0,
    PLAYER_BACKWARD = PLAYER_FORWARD+1,
    ROTATE_CLOCKWISE = PLAYER_BACKWARD+1,
    ROTATE_COUNTER_CLOCKWISE = ROTATE_CLOCKWISE+1,
    SHOOT = ROTATE_COUNTER_CLOCKWISE+1,
    PAUSE = SHOOT+1;

    private int[] keybinds;

    public KeyBindManager(){
        keybinds = new int[6];
        ArrayList<String> lines = FileManager.readFromFile("keybinds.txt");
        if(lines.isEmpty()){
            lines = FileManager.readFromFile("keybind_default.txt");
        }
        if(lines.isEmpty()){
            generateDefaults();
        }
        else {
            DataStorage storage = new DataStorage().fromString(lines.get(0));
            for (int i = 0; i < keybinds.length; i++) {
                keybinds[i] = storage.get(i);
            }
        }
    }

    private void generateDefaults(){
        keybinds = new int[]{
                KeyEvent.VK_W,
                KeyEvent.VK_S,
                KeyEvent.VK_D,
                KeyEvent.VK_A,
                KeyEvent.VK_SPACE,
                KeyEvent.VK_ESCAPE
        };
    }

    public int get(int bind){
        return keybinds[bind];
    }

    public void set(int bind, int key){
        keybinds[bind] = key;
    }

    public int[] getKeybinds() {
        return keybinds;
    }

    public String save(){
        DataStorage storage = new DataStorage();
        for(int key : keybinds){
            storage.add(key);
        }
        return storage.toString();
    }
}
