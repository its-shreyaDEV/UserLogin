package com.tatapower.demo.ticket_management_system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tatapower.demo.ticket_management_system.entity.ActivityResp;
import com.tatapower.demo.ticket_management_system.entity.UserEntity;
import com.tatapower.demo.ticket_management_system.repository.IUserRepo;
import com.tatapower.demo.ticket_management_system.service.IUserService;

@Service
public class UserServiceImpl  implements IUserService{

	@Autowired
	private IUserRepo userRepo;
	
	
	@Override
	public UserEntity login(String username , String password) {
		
		UserEntity userResp = userRepo.findByUsernameAndPassword(username.trim(), password) .orElse(null); 
		
		return userResp;
	}
	
	
	@Override
	public List<UserEntity> getAllUser() {
		return userRepo.findAll();
		}
		
		
		@Override
		public void saveUser(UserEntity user) {
			user.setUsername(user.getUsername().trim());
			user.setStatus(1); 
			userRepo.save(user);
		}
		
		@Override
		public UserEntity getUserById(Long UserId) {
			return userRepo.findById(UserId).orElse(null);
			}
			
			
			@Override
			public ActivityResp updateUser(UserEntity user ) {
				ActivityResp respObj = new ActivityResp();
				UserEntity save = userRepo.save(user);
				if(save !=null) {
					respObj.setMessage("Updated Succesfully!!");
					respObj.setRespStatus(true);
					return respObj;
				}else {
					respObj.setMessage("Unable to update !!");
					respObj.setRespStatus(false);
					return respObj;
				} 
			}
	}
