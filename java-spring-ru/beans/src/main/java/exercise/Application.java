package exercise;

import jakarta.annotation.PostConstruct;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.GregorianCalendar;

import exercise.daytime.Daytime;
import exercise.daytime.Day;
import exercise.daytime.Night;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

// BEGIN
import org.springframework.web.context.annotation.RequestScope;

import static java.util.Calendar.HOUR_OF_DAY;
// END

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @RequestScope
    @Bean
    public static Daytime getHour() {
        int x;
        Date date = new Date();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        x = gc.get(gc.HOUR_OF_DAY);
        if ((x > 6) && (x < 22)) {
            return new Day();
        } else {
            return new Night();
        }
    }
    // END
}
