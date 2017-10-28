package com.smartsoft.review;

import org.springframework.data.repository.CrudRepository;

public interface  ReviewRepository extends CrudRepository<Review, Long> { // this will create CRUD automatically
}
