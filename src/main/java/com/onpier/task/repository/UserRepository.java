package com.onpier.task.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onpier.task.entity.Users;

/**
 * @author Udayshree
 * 
 *         Repository for the UserDetails table
 *
 */
@Repository
public interface UserRepository extends JpaRepository<Users, String> {
	// find users by Name and FirstName
	Optional<Users> findByNameAndFirstName(String name, String firstName);

	// get non terminated userList
	List<Users> findByMemberTillIsNull();
	
	
}
