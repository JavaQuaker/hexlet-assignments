package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Data;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;

// BEGIN
@Setter
@Getter
@AllArgsConstructor
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;


    // BEGIN
    public static String serialize(Car car) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(car);
    }

    public static Car unserialize(String str) throws IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Car object = mapper.readValue(str, Car.class);
            return object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        // END
    }
}
