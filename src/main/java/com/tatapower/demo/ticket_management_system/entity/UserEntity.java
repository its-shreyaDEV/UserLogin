package com.tatapower.demo.ticket_management_system.entity;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

import jakarta.persistence.Column;
import lombok.Data;

@Data
@Entity
//user_master name of DB
@Table(name = "user_master")
public class UserEntity  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sl_no")
    private Long slNo;

	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	
	@Column(name = "user_role")
	private String role;
	
	@Column(name = "valid_from")
	private String  validFrom;
	
	@Column(name = "valid_to")
	private String validTo;
	
	
    @Column(name = "status")
    private Integer status;
    
    
    
	
}
