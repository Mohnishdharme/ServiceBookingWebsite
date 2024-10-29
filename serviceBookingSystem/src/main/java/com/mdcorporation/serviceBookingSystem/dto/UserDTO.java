package com.mdcorporation.serviceBookingSystem.dto;

import com.mdcorporation.serviceBookingSystem.enums.UserRole;

import lombok.Data;

@Data
public class UserDTO {
	
private long id;
	
	private String email;
	
	private String password;
	
	private String name;
	
	private String lastname;
	
	private String phone;
	
	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", lastname="
				+ lastname + ", phone=" + phone + ", role=" + role + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	private UserRole role;
	
	
	

}
