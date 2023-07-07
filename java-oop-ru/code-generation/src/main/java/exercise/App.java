package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.LinkedHashMap;
import java.util.Map;

// BEGIN
public class App {
    public static void main(String[] args) {
        Map<String, Object> owner = new LinkedHashMap<>();
      User user = new User(1, "Ivan", "Ivanov", 50 );
      Car car = new Car(1, "bmv", "x6", "white", user);
        App.save(Path.of("java-oop-ru/code-generation/src/test/resources/testCarJson.json"), car);
    }
    public static void save(Path path, Car car) {

        ObjectMapper objectMapper = new ObjectMapper();
        String filePath = String.valueOf(path);
        File file = new File(filePath);

        try {
           String result = objectMapper.writeValueAsString(car);
           FileWriter fileWriter = new FileWriter(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public static Car extract(Path path) {
        Car car;
        String str = String.valueOf(path);
        File file = new File(str);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            car = objectMapper.readValue(file, Car.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return car;
    }
}
// END
