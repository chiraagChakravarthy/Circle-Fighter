package circle_fighter.functionaliy;

import circle_fighter.file.DataStorage;
//TODO make all implementations of this save instance level variables rather than universally applicable stuff
public interface Savable {
    void save(DataStorage storage);
}