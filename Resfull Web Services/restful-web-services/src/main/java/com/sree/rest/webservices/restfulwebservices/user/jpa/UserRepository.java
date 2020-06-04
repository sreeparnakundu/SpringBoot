package com.sree.rest.webservices.restfulwebservices.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserJPA, Integer>{

}
