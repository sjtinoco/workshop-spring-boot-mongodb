package com.prospecti.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prospecti.workshopmongo.domain.User;
import com.prospecti.workshopmongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	@GetMapping //@RequestMapping(method=RequestMethod.GET) 
	public ResponseEntity<List<User>> findAll(){
		
		List<User> list = service.findAll(); 
//		List<User> list = new ArrayList<>();
//		User maria = new User("1001", "Maria Brown", "maria@gmail.com");
//		User alex = new User("1002", "Alex Green", "alex@gmail.com");
//		list.addAll(Arrays.asList(maria, alex));
		return ResponseEntity.ok().body(list);
	}
	
}
