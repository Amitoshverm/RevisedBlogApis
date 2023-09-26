package com.amitosh.blogapis.Repositories;

import com.amitosh.blogapis.Enitities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
