package exercise;

import java.util.Arrays;

// BEGIN
public class MinThread extends Thread {
    int min;
    public int [] number;
    public int getMin() {
        return min;
    }
    public int [] getNumber() {
        return number;
    }
    MinThread(int [] number) {
        this.number = number;
    }

    @Override
    public void run() {
        int[] manArr = Arrays.stream(getNumber()).sorted().toArray();
            min = manArr[0];
            System.out.println("Thread" + " " + getName() + " " + "min" + " " + min);
            System.out.println("GetMin" + " " + getMin());
    }
}
// END
