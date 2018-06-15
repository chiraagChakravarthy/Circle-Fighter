package circle_fighter.functionaliy;

import circle_fighter.file.DataStorage;
//TODO for all class implimenting this, include a constructor which contains a data storage and all shared parameters
public interface Savable {
    void save(DataStorage storage);
}
