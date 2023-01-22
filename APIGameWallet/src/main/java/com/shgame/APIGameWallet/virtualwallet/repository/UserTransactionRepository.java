package com.shgame.APIGameWallet.virtualwallet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shgame.APIGameWallet.virtualwallet.models.UserTransaction;
@Repository
public interface UserTransactionRepository extends CrudRepository<UserTransaction, Integer> {	
}
