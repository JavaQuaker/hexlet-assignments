package exercise;

// BEGIN
public class App {
    public static void printSquare(Circle circle) throws NegativeRadiusException {
        System.out.print(Math.round(circle.getSquare())+ "\n");
        System.out.print("Вычисление окончено");
    }

}
// END
