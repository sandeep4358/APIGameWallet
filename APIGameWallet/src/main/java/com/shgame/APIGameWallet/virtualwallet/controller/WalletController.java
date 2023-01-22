package com.shgame.APIGameWallet.virtualwallet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shgame.APIGameWallet.virtualwallet.dto.RequestData;
import com.shgame.APIGameWallet.virtualwallet.dto.ResponseData;
import com.shgame.APIGameWallet.virtualwallet.service.WalletService;

@RestController
@RequestMapping(value = "wallet/")
public class WalletController {
	private static final Logger log = LoggerFactory.getLogger(WalletController.class);

	@Autowired
	private WalletService service;

	ResponseData response ;
	
	@PostMapping(value = "/loadUserWallet")
	public ResponseData loadFundsToUserWallet(@RequestBody RequestData data) {
		log.info("enter");
		
		response = service.loadUserWallet(data);

		log.info("exit");
		return response;
	}

	@PostMapping(value = "/addFundToCorporate")
	public ResponseData addFundToCorporateWallet(@RequestBody RequestData data) {
		log.info("enter");
		response = service.addFundToCorporateWallet(data);
		log.info("exit");
		return response;
	}

	@PostMapping(value = "/withdrawUserWallet")
	public ResponseData withdrawFundFromUserWallet(RequestData data) {
		log.info("enter");

		log.info("exit");
		return response;
	}

	@PostMapping(value = "/withdrawCorpWallet")
	public ResponseData withdrawFundFromCorporateWallet(RequestData data) {
		log.info("enter");

		log.info("exit");
		return response;
	}

}
