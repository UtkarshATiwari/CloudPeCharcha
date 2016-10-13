package com.niit.cloudpecharcha.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.cloudpecharcha.DAO.UsersDAO;
import com.niit.cloudpecharcha.model.Users;

@RestController
public class UsersRestController {
	
	@Autowired
	private UsersDAO usersDAO;
	
	@RequestMapping(value="user/create", method=RequestMethod.POST, consumes="application/json",produces="application/json")
	public ResponseEntity<?> createUser(@RequestBody Users user)
	{
		usersDAO.addNewUser(user);
		System.out.println("user added: "+user);
		return new ResponseEntity("hello",HttpStatus.OK);
	}
	@GetMapping("user/edit/{id}")
	public ResponseEntity<?> editUser()
	{
		System.out.println("editing User");
		return new ResponseEntity("EDIT",HttpStatus.OK);
	}
	
	@DeleteMapping(value="user/delete/{id}",  consumes="application/json",produces="application/json")
	
	public ResponseEntity<?> deleteUser(@PathVariable("id") int id,@RequestBody Users user)
	{
		user=usersDAO.getUserById(id);
		System.out.println("Delete Mapping With Id: "+id);
		usersDAO.deleteUser(user);
		System.out.println("user Deleted:"+user);
		return new ResponseEntity("DELETE",HttpStatus.OK);
}
	@PutMapping (value="user/edit/{id}",  consumes="application/json",produces="application/json")
	public ResponseEntity<?> UpdateUser(@PathVariable("id") int id,@RequestBody Users user)
	{
		if(user==null)
		{
			return new ResponseEntity("No User Found For This Id:" +id,HttpStatus.NOT_FOUND);
		}
		user=usersDAO.UpdateUser(user, id);
		return new ResponseEntity(user,HttpStatus.OK);
	}
}
