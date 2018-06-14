package circle_fighter.functionaliy;

import circle_fighter.file.DataStorage;

public interface Savable {
    void from(DataStorage storage);
    void to(DataStorage storage);
}
