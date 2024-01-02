package exercise;

// BEGIN
public class ListThread extends Thread {
    SafetyList list;
    ListThread(SafetyList list) {
        this.list = list;
    }
    @Override
    public void run() {
        int count = 0;
        for (int i = 0; i < 1000; i++) {
            count = (int) (Math.random() * 10);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("10 millisecond has passed");
            list.add(count);
        }
    }

}
// END
