package exercise;

// BEGIN
public class Flat implements Home {
    double area;
    double balconyArea;
    int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    @Override
    public double getArea() {
        return area + balconyArea;
    }

    @Override
    public int compareTo(Home another) {
        if (this.area < area) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "Квартира площадью "
                + getArea() + " " + "метров " +
                "на" + " " + floor + " " + "этаже";
    }
}
// END
