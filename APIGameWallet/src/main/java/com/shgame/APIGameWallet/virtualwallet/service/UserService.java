package com.shgame.APIGameWallet.virtualwallet.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.shgame.APIGameWallet.virtualwallet.dto.RequestData;
import com.shgame.APIGameWallet.virtualwallet.dto.ResponseData;

public interface UserService {
	
	
	ResponseData addUser(RequestData request);
	
	ResponseData getUserDetailsByMobileNumber(RequestData request);
	
	ResponseData getUserDetailsByCorporateId(String corporateId);
	

}
