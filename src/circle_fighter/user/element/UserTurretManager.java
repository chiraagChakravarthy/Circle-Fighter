package circle_fighter.user.element;

import circle_fighter.file.DataStorage;
import circle_fighter.user.element.turret.BasicUserTurret;
import circle_fighter.user.element.turret.UserTurret;
import circle_fighter.user.element.turret.UserTurretRegistry;

import java.util.ArrayList;

public class UserTurretManager extends UserElement {
    private ArrayList<UserTurret> turrets;
    private int[] turretSlots;
    public UserTurretManager() {
        super();
        turrets = new ArrayList<>();
        turrets.add(new BasicUserTurret());
        turretSlots = new int[]{0};
    }

    public UserTurretManager(DataStorage storage){
        super();
        turrets = new ArrayList<>();
        DataStorage turretStorage = storage.getSubStorage(0), slotStorage = storage.getSubStorage(1);
        for (int i = 0; i < turretStorage.size(); i++) {
            turrets.add(UserTurretRegistry.fromID(storage.get(i), storage.getSubStorage(i)));
        }
        turretSlots = new int[slotStorage.size()];
        for (int i = 0; i < slotStorage.size(); i++) {
            turretSlots[i] = slotStorage.get(i);
        }
    }

    @Override
    public void save(DataStorage storage) {
        DataStorage turretStorage = storage.getSubStorage(0), slotStorage = storage.getSubStorage(1);
        for (int i = 0; i < turrets.size(); i++) {
            turretStorage.set(i, UserTurretRegistry.toID(turrets.get(i).getClass()));
            turrets.get(i).save(turretStorage.getSubStorage(i));
        }
        for (int i = 0; i < turretSlots.length; i++) {
            slotStorage.set(i, turretSlots[i]);
        }
    }

    public int getSlotAmount(){
        return turretSlots.length;
    }

    public UserTurret getTurretAt(int slot){
        return turrets.get(turretSlots[slot]);
    }
}