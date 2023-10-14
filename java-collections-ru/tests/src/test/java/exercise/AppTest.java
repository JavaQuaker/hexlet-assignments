package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class AppTest {
    //List<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
    //System.out.println(App.take(numbers1, 2)); // => [1, 2]
    //
    //List<Integer> numbers2 = new ArrayList<>(Arrays.asList(7, 3, 10));
    //System.out.println(App.take(numbers2, 8)); // => [7, 3, 10]
    //  int actual1 = App.factorial(5);
    //        assertThat(actual1).isEqualTo(120);
    @Test
    void testTake() {
        // BEGIN
        List<Integer> numbers1 = new ArrayList<>(Arrays.asList(1,2,3,4));
        List<Integer> actual1 = App.take(numbers1, 2);
                    assertThat(actual1).containsOnly(1,2);
        // END
    }
}
