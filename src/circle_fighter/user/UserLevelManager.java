package circle_fighter.user;

import circle_fighter.game.plane.PlayerPlane;

public class UserLevelManager {
    private UserManager users;
    private PlayerPlane plane;
    public UserLevelManager(UserManager users, PlayerPlane plane){
        this.users = users;
        this.plane = plane;
    }
}
