package com.tatapower.demo.ticket_management_system.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tatapower.demo.ticket_management_system.entity.UserEntity;


@Repository
public interface IUserRepo extends JpaRepository<UserEntity, Long> {
	
	@Query(
		    value = "SELECT * FROM tms.user_master WHERE username = :username AND password = :password",
		    nativeQuery = true
		)
		Optional<UserEntity> findByUsernameAndPassword(
		        @Param("username") String username,
		        @Param("password") String password
		);
}
