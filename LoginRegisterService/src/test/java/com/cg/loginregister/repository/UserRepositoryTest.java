package com.cg.loginregister.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.loginregister.model.User;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserRepositoryTest {
	//Object of PaymentRepositoryTest to communicate with Repository
		@Autowired
		private UserRepository repository;
		
		
		private User user1;
	    private User user2;
	    List<User> UserList;
	    LocalDate myDate1;
	    
	    @BeforeEach
	    public void setUp() {
	    UserList=new ArrayList<>(); 
	    myDate1 =LocalDate.parse("2022-05-14");
	    user1= new User("1","Tejas","user456@gmail.com","12345678"); //user input
	    user2= new User("2","Yash","user789@gmail.com","123456");  //user input 
	    UserList.add(user1);
	    UserList.add(user2);
	    }
	    
	    @AfterEach
	    public void tearDown() {
	    	user2 = null;
	    	user1=null;
	    	UserList = null;
	    }
		 
	    //Test CAse for adding User NAme
		@Test
		public void givenUserShouldReturnUserObject() {
			repository.save(user1);  // Data is saved in Database
			User p2= repository.findById(user1.getId()).get(); //Fetching the data from DataBase
			assertNotNull(p2);  //To check if it returning the Passenger Object
			assertEquals(user1.getEmail(),p2.getEmail());		
		}

		
		
		//Test Case for getByUserDetail Implementation
		@Test
		public void givenIdThenShouldReturnUserOfThatId() {
			repository.save(user2);
		     Optional<User> optional =  repository.findById(user2.getId());
		     assertEquals(user2.getId(), optional.get().getId());
		     assertEquals(user2.getEmail(), optional.get().getEmail());
		}
}
