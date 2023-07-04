package exercise;

// BEGIN
public class App {
    public static void printSquare(Circle circle) throws NegativeRadiusException {

        try {
            System.out.print(Math.round(circle.getSquare()) + "\n");
            System.out.print("Вычисление окончено\n");

        } catch (NegativeRadiusException ex) {
            System.out.println("Не удалось посчитать площадь\nВычисление окончено");
        }
    }
}


// END
