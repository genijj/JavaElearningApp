package com.myclass.dto;

public class UserDto {
	
	private int id;
	private String fullname;
	private String email;
	private String password;
	private String avatar;
	private String phone;
	private String address;
	private int roleId;
	private String roleDesc;
	
	public UserDto() {}
	
	public UserDto(int id, String fullname, String email, String roleDesc) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.roleDesc = roleDesc;
	}
	
	public UserDto(int id, String fullname, String email, String password, String avatar, String phone, String address, int roleId) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.password = password;
		this.avatar = avatar;
		this.phone = phone;
		this.address = address;
		this.roleId = roleId;
	}

	public UserDto(int id, String fullname, String email, String password, String avatar, String phone, String address,
			int roleId, String roleDesc) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.password = password;
		this.avatar = avatar;
		this.phone = phone;
		this.address = address;
		this.roleId = roleId;
		this.roleDesc = roleDesc;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}
