package com.shgame.APIGameWallet.virtualwallet.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shgame.APIGameWallet.virtualwallet.dto.RequestData;
import com.shgame.APIGameWallet.virtualwallet.dto.ResponseData;
import com.shgame.APIGameWallet.virtualwallet.exception.CorporateNotFoundException;
import com.shgame.APIGameWallet.virtualwallet.models.CorporateDetails;
import com.shgame.APIGameWallet.virtualwallet.models.CorporateTransactions;
import com.shgame.APIGameWallet.virtualwallet.models.CorporateWallet;
import com.shgame.APIGameWallet.virtualwallet.repository.CorporateRepository;
import com.shgame.APIGameWallet.virtualwallet.repository.CorporateWalletRepository;
import com.shgame.APIGameWallet.virtualwallet.service.CorporateService;

@Service

public class CorporateServiceImpl implements CorporateService {
	private static final Logger log = LoggerFactory.getLogger(CorporateService.class);

	@Autowired
	private CorporateRepository corpRepository;
	
	@Autowired
	private CorporateWalletRepository corpWaltRepository;
	ResponseData res = new ResponseData();

	private List<CorporateTransactions> transactions;

	
	@Override
    @Transactional
	public ResponseData addCorporate(RequestData request) {
		log.info("entry");
		
		
		Random rnd = new Random();
		int n = 10000 + rnd.nextInt(90000);

		String corporateId = "CORP"+n;
		
		CorporateDetails  corporate = new CorporateDetails();
		corporate.setAge(Integer.valueOf(request.getAge()));
		corporate.setFname(request.getFirstName());
		corporate.setLname(request.getLastName());
		corporate.setCity(request.getCity());
		corporate.setEmail(request.getEmail());
		corporate.setPhoneNo(request.getPhoneNo());
		corporate.setPinCode(request.getPinCode());
		corporate.setState(request.getState());
		corporate.setCorporateId(corporateId);
		//code for create wallet
		
		CorporateWallet wallet = new CorporateWallet();
	    wallet.setAmount(0);
		wallet.setCreateDateTime(LocalDateTime.now());
		wallet.setUpdateDateTime(LocalDateTime.now());
		wallet.setCorporate(corporate);
		
		corporate.setWallet(wallet);
		
		
		CorporateDetails savedCorp = corpRepository.save(corporate);
		
		res.setObject(savedCorp);
		
		log.info("exist");
		return res;
	}

	@Override
	public ResponseData getCorporateByMobileNumber(RequestData request) {
		log.info("entry");
		log.info("exist");
		return res;
	}

	@Override
	public ResponseData fetchAllTransaction(RequestData request) {
		log.info("entry");
		try {
			CorporateDetails corporateDetails = corpRepository.findAllCorporateDetailsByCorporateId(request.getCorporateId());
			transactions = corporateDetails.getTransactions();
			res.setObject(transactions);
			res.setMessage("success");
			res.setStatus("00");
		} catch (Exception e) {
			res.setMessage("failure");
			res.setStatus(e.getMessage());
			e.printStackTrace();
		}
		log.info("exist");
		return res;
	}
	
	@Override
	public ResponseData fetchCorporateBalance(RequestData request) {
		log.info("entry");
		try {
			CorporateDetails corporateDetails = corpRepository.findAllCorporateDetailsByCorporateId(request.getCorporateId());
			CorporateWallet wallet = corporateDetails.getWallet();
			res.setObject(wallet);
			res.setMessage("success");
			res.setStatus("00");
		} catch (Exception e) {
			res.setMessage("failure");
			res.setStatus(e.getMessage());
			e.printStackTrace();
		}
		log.info("exist");
		return res;
	}
	
	@Override
	public List<CorporateDetails> getAllCorporates(){
		return (List<CorporateDetails>) corpRepository.findAll();
	}
	
	@Override
	public CorporateDetails getCorporate(String corporateId) throws CorporateNotFoundException{
		CorporateDetails corporate = corpRepository.findAllCorporateDetailsByCorporateId(corporateId);
		if(corporate !=null) {
			return corporate;
		}else {
			throw new CorporateNotFoundException("Corporate Not found with id :"+corporateId);
		}
		
	}

}
