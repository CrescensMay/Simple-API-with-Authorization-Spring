package com.smartsoft.review;

import com.smartsoft.user.User;
import com.smartsoft.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Review.class) //trying to do something in the described class
public class ReviewEventHandler {
    private final UserRepository users;

    @Autowired
    public ReviewEventHandler(UserRepository users) {
        this.users = users;
    }

    @HandleBeforeCreate
    public void addReviewBaseOnLoggedInUser(Review review){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();//get current long in user
        User user = users.findByUsername(username);
        review.setReviewer(user);
    }
}
