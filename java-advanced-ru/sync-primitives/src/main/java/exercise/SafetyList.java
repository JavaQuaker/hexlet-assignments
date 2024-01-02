package exercise;

import java.util.ArrayList;
import java.util.List;

class SafetyList {
    // BEGIN
    private List<Integer> list = new ArrayList<>();
    public synchronized void add(int value) {
        list.add(value);
    }
    public int getValue(int index) {
        return list.get(index);
    }
    public int getSize() {
        return list.size();
    }
    // END
}
