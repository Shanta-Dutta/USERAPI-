package com.tts.UsersAPI.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;


import com.tts.UsersAPI.Model.Users;

	
	@Repository
	public interface UsersRepository extends CrudRepository<Users,Long> {
	    Users findById(long id);
		List<Users> findAll();
	    List<Users> findByState(String state);
	
	    

}
