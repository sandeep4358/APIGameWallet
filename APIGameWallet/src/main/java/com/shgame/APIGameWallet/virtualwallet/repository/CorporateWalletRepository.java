package com.shgame.APIGameWallet.virtualwallet.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shgame.APIGameWallet.virtualwallet.models.CorporateWallet;

@Repository
public interface CorporateWalletRepository extends CrudRepository<CorporateWallet, Integer> {

    CorporateWallet findByCorporateId(Integer corporateId);
	@Transactional
	@Modifying
	@Query("update CorporateWallet c set c.amount = ?1 where c.walletId = ?2")
	int updateCorporateWalletAmount(int amount, int walletId);
	
}
