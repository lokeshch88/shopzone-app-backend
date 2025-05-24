package com.booking.app.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.app.entity.User;
@Repository
public interface UserRepo extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String usernameOrEmail);


	boolean existsByEmail(String email);
	boolean existsByUsername(String username);

}
