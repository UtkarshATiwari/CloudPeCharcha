package com.niit.cloudpecharcha.test;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.cloudpecharcha.DAO.UsersDAO;
import com.niit.cloudpecharcha.model.Users;

public class UsersTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext myContext= new AnnotationConfigApplicationContext();
		myContext.scan("com.niit.cloudpecharcha");
		myContext.refresh();
		UsersDAO userDAO = (UsersDAO) myContext.getBean("userDAO");
		Users user =(Users) myContext.getBean("Users");
		user.setUsername("Ankur");
		user.setPassword("123a");
		user.setF_name("Utkarsh");
		user.setL_name("Tiwari");
		user.setCity("Mumbai");
		user.setAddress("Tulinj Road Nallasopara");
		user.setDOB("29-aug-1992");
		user.setContact_no("7276295021");
		user.setLandline_no(02502434777);
		user.setGender("M");
		user.setMail("tiwari.utkarsh786@gmail.com");
		user.setUser_role("Admin");
		user.setU_no(1);
		
		userDAO.addNewUser(user);
		System.out.println(userDAO.getAllusers());
		
		List<Users>  user1 =  userDAO.getAllusers();
		
		for(Users user11 : user1)
	    	System.out.println( user11.getF_name()  + ":"+  user11.getUsername());
		
	}

}
