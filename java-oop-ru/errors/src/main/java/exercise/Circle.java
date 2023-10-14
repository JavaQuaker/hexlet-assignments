package exercise;

// BEGIN
public class Circle {
    Point point;
    int radius;

    public Circle() {

    }
    public Circle(Point point, int radius) {
        this.point = point;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }
    public double getSquare() throws NegativeRadiusException {
       if (radius < 0) {
           throw new NegativeRadiusException("Не удалось посчитать площадь\nВычисление окончено");
       }
        return (Math.PI * (getRadius() * getRadius()));
    }


}
// END
