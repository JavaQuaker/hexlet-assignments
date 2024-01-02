package exercise;

import java.util.Arrays;

// BEGIN
public class MaxThread extends Thread {

    int max;
    public int [] number;
    public int getMax() {
        return max;
    }
    public int [] getNumber() {
        return number;
    }
    MaxThread(int [] number) {
        this.number = number;
    }

    @Override
    public void run() {
        int[] maxArr = Arrays.stream(getNumber()).sorted().toArray();
            max = maxArr[maxArr.length - 1];
            System.out.println("Thread" + " " + getName() + " " + "max" + " " + max);
            System.out.println("getMax" + " " + getMax());
    }
}
// END
