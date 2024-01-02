package exercise;


import java.util.*;
import java.util.logging.Logger;
import java.util.logging.Level;
/*
* int[] numbers = {10, -4, 67, 100, -100, 8};

System.out.println(App.getMinMax(numbers)); // => {min=-100, max=100}*/
class App {
    static int[] numbers = {10, -4, 67, 100, -100, 8};
    private static final Logger LOGGER = Logger.getLogger("AppLogger");



    public static void main(String[] args) {
        System.out.println(getMinMax(numbers));
    }

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] numbers) {
        Map<String, Integer> result = new LinkedHashMap<>();

        MaxThread maxThread = new MaxThread(numbers);
        MinThread minThread = new MinThread(numbers);
        minThread.start();
        LOGGER.log(Level.INFO, "Thread" + " " + minThread + " " + "start");
        maxThread.start();
        LOGGER.log(Level.INFO, "Thread" + " " + maxThread + " " + "start");
        try {
            minThread.join();
            LOGGER.log(Level.INFO, "Thread" + " " + maxThread + " " + "stop");
        } catch (InterruptedException e) {
            System.out.println("Поток прерван");
        }
        System.out.println("MinThread" + minThread.getMin());
        System.out.println("MaxThread" + maxThread.getMax());

        result.put("min", minThread.getMin());
        result.put("max", maxThread.getMax());

        return result;

        // END
    }
}
