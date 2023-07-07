package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.Path;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.LinkedHashMap;
import java.util.Map;

// BEGIN
public class App {
    public static void main(String[] args) throws JsonProcessingException {
      Map<String, Object> owner = new LinkedHashMap<>();
      User user = new User(1, "Ivan", "Ivanov", 50 );
      Car car = new Car(1, "bmv", "x6", "white", user);
      App.save(Path.of("src/test/resources/testCarJson.json"), car);
    }
    public static void save(Path path, Car car) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String filePath = String.valueOf(path);
        File file = new File(filePath);

        try {
            String result = objectMapper.writeValueAsString(car);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(result);
            fileWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
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
