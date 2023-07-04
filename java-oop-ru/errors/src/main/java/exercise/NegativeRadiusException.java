package exercise;

// BEGIN
public class NegativeRadiusException extends Exception {
    public NegativeRadiusException(String str) {
        super(str);
    }
    public void go () {
        Circle circle = new Circle();
        try {
          circle.getSquare();
        } catch (NegativeRadiusException ex) {
                ex.printStackTrace();
            }
        }
    }

// END
