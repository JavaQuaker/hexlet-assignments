package exercise.controller;

import exercise.model.Course;
import exercise.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseRepository courseRepository;

    @GetMapping(path = "")
    public Iterable<Course> getCorses() {
        return courseRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Course getCourse(@PathVariable long id) {
        return courseRepository.findById(id);
    }

    // BEGIN
    @GetMapping(path = "/{id}/previous")
    public List<Course> getPreviousCourses(@PathVariable long id) {
        Course course = courseRepository.findById(id);
        String path = course.getPath();
        List<Course> result = new ArrayList<>();
        if (path != null) {
            List<Integer> pathNumber = parse(path);
           for (Integer idByCourse : pathNumber) {
               result.add(courseRepository.findById(idByCourse));
           }
        } else {
            return result;
        }
        System.out.println("resultTest" + result);
        return result;
    }
    public static List parse(String path) {
        String [] word = path.split("\\.");
        int array [] = new int[word.length];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < word.length; i++) {
            array[i] = Integer.parseInt(word[i]);
            list.add(array[i]);
        }
        return list;
    }

    // END
}
