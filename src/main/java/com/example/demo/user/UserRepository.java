package com.example.demo.user;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
    List<User> findUsersByMajor(String major);

    @Query(value = "select * from users u where u.name like %?1%", nativeQuery = true)
    List<User> findUsersByNameMatching(String name);
}
