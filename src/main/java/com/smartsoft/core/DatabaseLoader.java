package com.smartsoft.core;

import com.smartsoft.course.Course;
import com.smartsoft.course.CourseRepository;
import com.smartsoft.review.Review;
import com.smartsoft.user.User;
import com.smartsoft.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DatabaseLoader implements ApplicationRunner{//we want to create courses
    private final CourseRepository courses;
    private final UserRepository users;

    @Autowired //wiring whatever we have in the courseRepository
    public DatabaseLoader(CourseRepository courses, UserRepository users) {
        this.courses = courses;
        this.users = users;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Course course = new Course("Java Basics","https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods");
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
                "Hibernate",
                "Hello word"
        };

        List<User> students = Arrays.asList(
                new User("jacobproffer", "Jacob",  "Proffer", "password", new String[] {"ROLE_USER"}),
                new User("mlnorman", "Mike",  "Norman", "password", new String[] {"ROLE_USER"}),
                new User("k_freemansmith", "Karen",  "Freeman-Smith", "password", new String[] {"ROLE_USER"}),
                new User("seth_lk", "Seth",  "Kroger", "password", new String[] {"ROLE_USER"}),
                new User("mrstreetgrid", "Java",  "Vince", "password", new String[] {"ROLE_USER"}),
                new User("anthonymikhail", "Tony",  "Mikhail", "password", new String[] {"ROLE_USER"}),
                new User("boog690", "AJ",  "Teacher", "password", new String[] {"ROLE_USER"}),
                new User("faelor", "Erik",  "Faelor Shafer", "password", new String[] {"ROLE_USER"}),
                new User("christophernowack", "Christopher",  "Nowack", "password", new String[] {"ROLE_USER"}),
                new User("calebkleveter", "Caleb",  "Kleveter", "password", new String[] {"ROLE_USER"}),
                new User("richdonellan", "Rich",  "Donnellan", "password", new String[] {"ROLE_USER"}),
                new User("albertqerimi", "Albert",  "Qerimi", "password", new String[] {"ROLE_USER"})
        );
        users.save(students);
        users.save(new User("Crescens", "Kob", "Crescens Kob", "123456", new String[]{"ROLE_USER"}));

        List<Course> courseList = new ArrayList<>();
        IntStream.range(0, 100).forEach(i -> {
            String template = templates[i % templates.length];
            String buzzword = buzzwords[i % buzzwords.length];
            String title = String.format(template, buzzword);
            Course c = new Course(title, "https://projects.spring.io/spring-boot/");
            Review review = new Review((i % 5) + 1, String.format("More %s please!!!", buzzword));
            review.setReviewer(students.get(i % students.size()));
            c.addReview(review);
            courseList.add(c);
        });
        courses.save(courseList);

    }
}
