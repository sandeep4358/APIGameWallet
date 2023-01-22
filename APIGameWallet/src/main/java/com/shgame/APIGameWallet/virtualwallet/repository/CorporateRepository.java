package com.shgame.APIGameWallet.virtualwallet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shgame.APIGameWallet.virtualwallet.models.CorporateDetails;

@Repository
public interface CorporateRepository extends CrudRepository<CorporateDetails, Integer> {

    //@Query("SELECT c FROM CorporateDetails c WHERE c.corporateId=:corporateId")
	public CorporateDetails findAllCorporateDetailsByCorporateId(String corporateId);
}
