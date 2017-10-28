package com.smartsoft.course;

import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {// this will create CRUD automatically

}
