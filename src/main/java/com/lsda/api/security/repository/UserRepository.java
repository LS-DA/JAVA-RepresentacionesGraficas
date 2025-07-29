package com.lsda.api.security.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lsda.api.security.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    
    @Query("select e from User e where e.username=:username")
	 User getUser(@Param("username") String username);
    
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update User u set  u.password = :newpassword where u.id = :id")    
    int updatePassword(@Param("newpassword") String newpassword,@Param("id") Long id);
    
    @Query("select e from User e where e.username=:username AND e.password=:password")
	User getVigenciaPassword(@Param("username")String username, @Param("password")String password);

	
}
