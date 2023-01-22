package com.shgame.APIGameWallet.virtualwallet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shgame.APIGameWallet.virtualwallet.models.UserDetails;
@Repository
public interface UserRepository extends CrudRepository<UserDetails, Integer> {

	@Query("SELECT c FROM UserDetails c WHERE c.email=:email")
	Iterable<UserDetails> findCustomerByEmail(@Param("email") String email);


	List<UserDetails> findByCorporateId(int id);


	UserDetails findAllByUserId(String userId);
}
