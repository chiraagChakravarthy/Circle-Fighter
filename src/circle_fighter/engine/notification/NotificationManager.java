package circle_fighter.engine.notification;

import java.awt.*;
import java.util.ArrayList;

public class NotificationManager {
    private static ArrayList<Notification> addedNotifications;
    private static Notification activeNotification;

    public static void tick(){
        if(activeNotification != null) activeNotification.tick();
    }

    public static void render(Graphics2D g){
        if(activeNotification != null) activeNotification.render(g);
    }

    public static void addNotification(String message){
        Notification notification = new Notification(message);
        if(activeNotification == null)activeNotification = notification;
        else addedNotifications.add(new Notification(message));
    }

    public static void next() {
        if(!addedNotifications.isEmpty()){
            activeNotification = addedNotifications.get(0);
            addedNotifications.remove(0);
        }
        else activeNotification = null;
    }

    public static void init() {
        addedNotifications = new ArrayList<>();
        activeNotification = null;
    }
}
