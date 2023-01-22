package com.shgame.APIGameWallet.virtualwallet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shgame.APIGameWallet.virtualwallet.models.CorporateTransactions;
@Repository
public interface CorporateTransactionRepository extends CrudRepository<CorporateTransactions, Integer> {
	
}
