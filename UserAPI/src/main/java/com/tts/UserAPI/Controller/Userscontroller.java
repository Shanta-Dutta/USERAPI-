package com.tts.UserAPI.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tts.UsersAPI.Model.Users;
import com.tts.UsersAPI.Repository.UsersRepository;

@RestController
public class Userscontroller {

	
	@Autowired
	 private UsersRepository usersRepository;
	
	
	@GetMapping("/users")
	public ResponseEntity<List<Users>> getUsers()
	{
		List<Users> nUsers =usersRepository.findAll();
	 
		return new ResponseEntity<>(nUsers,  HttpStatus.OK);
	}

	
	
	@GetMapping("/users/{id}")  
    public ResponseEntity <Optional<Users>> getUserById(@PathVariable(value="id") Long id)
	{
	Optional<Users> user = usersRepository.findById(id);
    	
    	if (!user.isPresent()){	
    		return  new ResponseEntity<>(user,  HttpStatus.NOT_FOUND);
       }
    	return new ResponseEntity<>(user,  HttpStatus.OK);
    		
	   }
    
       
     @PostMapping("/users")
      public ResponseEntity<Void> createUser(@RequestBody @Valid Users users,  BindingResult bindingResult){
      Optional<Users> user = usersRepository.findById(users.getId());
    	 	
    	if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
	    usersRepository.save(users);
	    return new ResponseEntity<>(HttpStatus.CREATED);
        }
    
      
    
      
       @PutMapping("/users/{id}")
       public ResponseEntity<Void> updateUser(@PathVariable(value="id") Long id,@RequestBody  @Valid Users users, BindingResult bindingResult){
    	 
    	Optional<Users> user = usersRepository.findById(id);
    	 	    	
    	if (!user.isPresent()){	
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    	 if (bindingResult.hasErrors()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
    	
	    usersRepository.save(users);
	    return new ResponseEntity<>(HttpStatus.OK);
    	
        }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value="id") Long id)
    {
    	 
    	Optional<Users> user = usersRepository.findById(id);
    	
    		if (!user.isPresent()){	
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usersRepository.deleteById(id);;
	    return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
  
    }
    
   

