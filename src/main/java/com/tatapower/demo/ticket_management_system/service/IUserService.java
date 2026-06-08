package com.tatapower.demo.ticket_management_system.service;

import java.util.List;

import com.tatapower.demo.ticket_management_system.entity.ActivityResp;
import com.tatapower.demo.ticket_management_system.entity.UserEntity;

public interface IUserService {
	
	UserEntity login(String user, String password);
	
	List<UserEntity> getAllUser();
	
	void saveUser(UserEntity user);
	
	UserEntity getUserById(Long UserId);
	
	ActivityResp updateUser(UserEntity user);

}
