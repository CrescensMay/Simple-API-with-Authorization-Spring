package com.smartsoft.review;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

public interface  ReviewRepository extends PagingAndSortingRepository<Review, Long> { // this will create CRUD automatically

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or @reviewRepository.findOne(#id)?.reviewer?.username == authentication.name")
    void delete(@Param("id") Long id);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or #review.reviewer?.username == authentication.name")
    void delete(@Param("review") Review entity);
}
