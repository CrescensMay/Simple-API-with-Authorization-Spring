package com.smartsoft.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)//dont allow someone to browse our queries
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
