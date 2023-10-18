package exercise.daytime;
import jakarta.annotation.PostConstruct;

public class Day implements Daytime {
    private String name = "day";

    public String getName() {
        return name;
    }

    // BEGIN
    public void controlCreateBean() {
        System.out.println("Bean create" + " " + getName());
    }
    // END
}
