package com.niit.cloudpecharcha.DAO;

import java.util.List;

import com.niit.cloudpecharcha.model.Users;

public interface UsersDAO {
	
	public void addNewUser(Users user);
	public List<Users> getAllusers();
	public boolean getUser(Users user);
	public Users getUserById(int id);
	public void deleteUser(Users user);
	public Users UpdateUser(Users user, int id);
	
	

}
