package com.smart.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.smart.Entities.Contact;
import com.smart.Entities.User;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer> {
	
	//this query will return the contact object based on userid of the logged-in user.
	//Pageable interface is responsible for providing the limited number of contact per page.
	//The following method will return the limited number of contacts per page based on the request made
	//Implementation is provided by the spring boot and argument is passed by the users or developers
	
	@Query(nativeQuery=true, value="SELECT * from SmartContact.contact  WHERE user_id=:user_id;")
	public Page<Contact> findByUserId( @Param("user_id") int user_id, Pageable pageable);
}
