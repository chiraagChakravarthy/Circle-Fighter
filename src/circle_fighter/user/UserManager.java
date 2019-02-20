package circle_fighter.user;

import circle_fighter.file.FileManager;

import java.util.ArrayList;

public class UserManager {
    private ArrayList<User> users;
    private int currentUser;
    public UserManager(){
        users = new ArrayList<>();
        ArrayList<String> lines = FileManager.readFromFile("files/users.txt");
        for(int i = 0; i < lines.size(); i+= 2){
            users.add(new User(lines.get(i), lines.get(i+1)));
        }
        currentUser = -1;
    }

    public void saveUsers(){
        ArrayList<String> lines = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            lines.addAll(users.get(i).save());
        }
        FileManager.writeToFile("files/users.txt", lines);
    }

    public User getUser(int user){
        return users.get(user);
    }

    public void addUser(String name){
        users.add(new User(name));
    }

    public int amount(){
        return users.size();
    }

    public void setCurrentUser(int currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser(){
        return currentUser<0?null:users.get(currentUser);
    }

    public void clearCurrentUser(){
        currentUser = -1;
    }
}
