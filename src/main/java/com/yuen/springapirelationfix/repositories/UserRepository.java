package com.yuen.springapirelationfix.repositories;

import com.yuen.springapirelationfix.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    //search by name
    List<User> findAllByNameFirstNameIgnoreCaseOrNameMiddleNameIgnoreCaseOrNameLastNameIgnoreCaseOrNameSuffixIgnoreCase(String firstName, String middleName, String lastName,  String suffix);
}
