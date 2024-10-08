package com.smart.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.smart.Entities.Contact;
import com.smart.Entities.User;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer> {
	
	@Query(nativeQuery=true, value="SELECT DISTINCT c.cid, c.image, c.user_id, c.description, c.email, c.name,c.nickname,c.phone, c.work from SmartContact.contact as c join SmartContact.user as u where c.user_id=:user_id;")
	public List<Contact> findByUserId( @Param("user_id") int user_id);
}
