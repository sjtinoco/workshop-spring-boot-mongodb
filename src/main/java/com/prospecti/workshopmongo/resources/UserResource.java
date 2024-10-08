package com.prospecti.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.prospecti.workshopmongo.domain.User;
import com.prospecti.workshopmongo.dto.UserDTO;
import com.prospecti.workshopmongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	@GetMapping //@RequestMapping(method=RequestMethod.GET) 
	public ResponseEntity<List<UserDTO>> findAll(){
		
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
//		List<User> list = new ArrayList<>();
//		User maria = new User("1001", "Maria Brown", "maria@gmail.com");
//		User alex = new User("1002", "Alex Green", "alex@gmail.com");
//		list.addAll(Arrays.asList(maria, alex));
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET) //@GetMapping(value = "/{id}") 
	public ResponseEntity<UserDTO> findById(@PathVariable String id){ //@PathVariable para "/{id}" ser reconhecido por String id
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@RequestMapping(method=RequestMethod.POST) 
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDTO){
		User obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE) 
	public ResponseEntity<Void> delete(@PathVariable String id){ //@PathVariable para "/{id}" ser reconhecido por String id
		service.delete(id);
		return ResponseEntity.noContent().build(); //noContent() : 204
	}
}
