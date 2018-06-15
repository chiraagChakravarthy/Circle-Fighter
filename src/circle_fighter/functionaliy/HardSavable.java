package circle_fighter.functionaliy;

import circle_fighter.file.DataStorage;

public interface HardSavable {
    void hardLoad(DataStorage storage);
    void hardSave(DataStorage storage);
}
