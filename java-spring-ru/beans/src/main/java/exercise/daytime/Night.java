package exercise.daytime;
import jakarta.annotation.PostConstruct;

public class Night implements Daytime {
    private String name = "night";

    public String getName() {
        return name;
    }

    // BEGIN
    public void controlCreateBean() {
        System.out.println("Bean create" + " " + getName());
    }
    // END
}
