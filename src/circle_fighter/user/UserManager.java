package circle_fighter.user;

import circle_fighter.file.FileManager;

import java.util.ArrayList;

public class UserManager {
    private ArrayList<User> users;
    public UserManager(){
        users = new ArrayList<>();
        ArrayList<String> lines = FileManager.readFromFile("files/users.txt");
        for(int i = 0; i < lines.size(); i+= 2){
            users.add(new User(lines.get(i), lines.get(i+1)));
        }
    }

    public void saveUsers(){
        ArrayList<String> lines = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            lines.addAll(users.get(i).save());
        }
        FileManager.writeToFile("files/users.txt", lines);
    }
}
