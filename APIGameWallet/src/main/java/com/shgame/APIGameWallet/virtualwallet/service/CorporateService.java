package com.shgame.APIGameWallet.virtualwallet.service;

import java.util.List;

import com.shgame.APIGameWallet.virtualwallet.dto.RequestData;
import com.shgame.APIGameWallet.virtualwallet.dto.ResponseData;
import com.shgame.APIGameWallet.virtualwallet.exception.CorporateNotFoundException;
import com.shgame.APIGameWallet.virtualwallet.models.CorporateDetails;

public interface CorporateService {

	ResponseData addCorporate(RequestData request);

	ResponseData getCorporateByMobileNumber(RequestData request);

	ResponseData fetchAllTransaction(RequestData request);

	public ResponseData fetchCorporateBalance(RequestData request);

	public List<CorporateDetails> getAllCorporates();

	public CorporateDetails getCorporate(String corporateId) throws CorporateNotFoundException;

}
