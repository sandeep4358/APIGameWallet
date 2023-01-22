package com.shgame.APIGameWallet.virtualwallet.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shgame.APIGameWallet.virtualwallet.dto.RequestData;
import com.shgame.APIGameWallet.virtualwallet.dto.ResponseData;
import com.shgame.APIGameWallet.virtualwallet.exception.CorporateNotFoundException;
import com.shgame.APIGameWallet.virtualwallet.models.CorporateDetails;
import com.shgame.APIGameWallet.virtualwallet.service.CorporateService;

@RestController
@RequestMapping(value = "corporate/")
public class CorporateController {
	private static final Logger log = LoggerFactory.getLogger(CorporateController.class);

	@Autowired
	private CorporateService service;

	private ResponseData response;
	
	@PostMapping(value = "/addCorporate")
	public ResponseData addCorporate(@RequestBody @Valid RequestData request) {
		log.info("enter");		
		System.out.println(request);		
		response = service.addCorporate(request);
		log.info("exit");
		return response;
	}
	
	@PostMapping(value = "/fetchCorpTransactions")
	public ResponseData fetchAllTransaction(@RequestBody  RequestData request) {
		log.info("enter");
		
		System.out.println(request);
		
		response = service.fetchAllTransaction(request);
		log.info("exit");
		return response;
	}
	
	@PostMapping(value = "/fetchCorpBalance")
	public ResponseData fetcheCorpBalance(@RequestBody RequestData request) {
		log.info("enter");
		
		System.out.println(request);
		
		response = service.fetchCorporateBalance(request);
		log.info("exit");
		return response;
	}
	
	 @GetMapping("/fetchAll")
	    public ResponseData getAllCorporates(){
			log.info("enter");
			 List<CorporateDetails> allCorporates = service.getAllCorporates();
			log.info("exit");
	        return new ResponseData("success", "00", allCorporates);
	    }

	 @GetMapping("fetch/{corporateId}")
	    public ResponseData getCorporate(@PathVariable String corporateId) throws CorporateNotFoundException {
		 log.info("enter");
			CorporateDetails corporate = service.getCorporate(corporateId);
			log.info("exit");
	        return new ResponseData("success", "00", corporate);
	    }
}
