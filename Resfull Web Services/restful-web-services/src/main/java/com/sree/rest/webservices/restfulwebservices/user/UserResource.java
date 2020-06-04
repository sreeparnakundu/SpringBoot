package com.sree.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService daoService;
	
	//GET  /users
	//retriveAllUsers
	@GetMapping("/users")
	public List<User> retriveAllUsers(){
		return daoService.findAll();
	}
	
	//GET  /users/{id}
	//retriveUser(int id)
	@GetMapping("/users/{id}")
	public Resource<User> retriveUser(@PathVariable int id){
		User user = daoService.findOne(id);
		if(user == null)
			throw new UserNotFoundException("id : "+id);
		//return user;
		
		/**
		 * HATEOAS 
		 * 
		 *"all-users" , SERVER_PATH + "/users"
		 * retriveAllUsers()
		 */
		Resource<User> resource = new Resource<User>(user);
		//ControllerLinkBuilder.linkTo(controller) ==>  static import is done for ControllerLinkBuilder class, so no need ControllerLinkBuilder at first
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retriveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		ControllerLinkBuilder linkTo1 = linkTo(methodOn(this.getClass()).deleteByIdNew(id));
		resource.add(linkTo1.withRel("delete-user"));
		return resource;
	}
	
	/**
	 * old create user method
	 * 
	 * @param user
	 * @return
	 */
	//CREATED
	//input - details of user
	//output - CREATED & Return the created URI
	//@RequestBody will map with user, whatever we will pass as a body from request
	/*@PostMapping("/users")
	public ResponseEntity<Object>  createUser(@RequestBody User user) {
		User saveUser = daoService.save(user);
		//CREATED
		// /users/{id}    saveUser.getId()
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(saveUser.getId()).toUri();
		return ResponseEntity.created(location).build();  // created --> status : 204
	}*/
	/**
	 * New createAdvUser() method with validation
	 * 
	 * @Valid : use 2.1.3.RELEASE springboot parent version, else will not work
	 * If validation fails, handleMethodArgumentNotValid() of CustomisedResponseEntityExceptionhandler class will be triggered
	 * 
	 * <artifactId>spring-boot-starter-parent</artifactId>
		<version>2.1.3.RELEASE</version>
	 */
	@PostMapping("/users")
	public ResponseEntity<Object>  createAdvUser(@Valid @RequestBody User user) {
		User saveUser = daoService.save(user);
		//CREATED
		// /users/{id}    saveUser.getId()
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(saveUser.getId()).toUri();
		return ResponseEntity.created(location).build();  // created --> status : 204
	}
	
	
	
	/**
	 * old delete
	 * 
	 * @param id
	 * @return
	 */
	/*@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id){
		User user = daoService.deleteById(id);
		if(user == null)
			throw new UserNotFoundException("id : "+id);	//will invoke the UserNotFoundException class, which is throwing NOT_FOUND --> status : 404
	}*/
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Object> deleteByIdNew(@PathVariable int id){
		User user = daoService.deleteById(id);
		if(user == null)
			throw new UserNotFoundException("id : "+id);  //return ResponseEntity.notFound().build();  // not-found --> status : 404
		else
			return ResponseEntity.noContent().build();  // no-content --> status : 204
	}

}
