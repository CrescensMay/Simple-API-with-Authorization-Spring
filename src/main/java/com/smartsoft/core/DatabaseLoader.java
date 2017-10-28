package com.smartsoft.core;

import com.smartsoft.course.Course;
import com.smartsoft.course.CourseRepository;
import com.smartsoft.review.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DatabaseLoader implements ApplicationRunner{//we want to create courses
    private final CourseRepository courses;

    @Autowired //wiring whatever we have in the courseRepository
    public DatabaseLoader(CourseRepository courses) {
        this.courses = courses;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Course course = new Course("Java Basics","https://teamtreehouse.com/library/entities-and-repositories-review");
        course.addReview(new Review(3, "You are a dork!!!"));
        courses.save(course);

        String[] templates = {
                "Up and running with %s",
                "%s basics",
                "%s for beginners",
                "%s for Neckbeards",
                "Under the hood: %s",
        };

        String[] buzzwords = {
                "Spring REST Data",
                "Java 9",
                "Groovy",
                "Scala",
                "Hibernate"
        };

        List<Course> courseList = new ArrayList<>();
        IntStream.range(0, 100).forEach(i -> {
            String template = templates[i % templates.length];
            String buzzword = buzzwords[i % buzzwords.length];
            String title = String.format(template, buzzword);
            Course c = new Course(title, "https://projects.spring.io/spring-boot/");
            c.addReview(new Review(i % 5, String.format("More %s please!!!", buzzword)));
            courseList.add(c);
        });
        courses.save(courseList);

    }
}
