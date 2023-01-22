package com.shgame.APIGameWallet.virtualwallet.service;

import com.shgame.APIGameWallet.virtualwallet.dto.RequestData;
import com.shgame.APIGameWallet.virtualwallet.dto.ResponseData;

public interface WalletService {

	ResponseData addFundToCorporateWallet(RequestData request);
	
	ResponseData loadUserWallet(RequestData request);
}
