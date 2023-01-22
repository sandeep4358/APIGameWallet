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
import com.shgame.APIGameWallet.virtualwallet.models.CorporateDetails;
import com.shgame.APIGameWallet.virtualwallet.models.UserDetails;
import com.shgame.APIGameWallet.virtualwallet.models.UserWallet;
import com.shgame.APIGameWallet.virtualwallet.repository.CorporateRepository;
import com.shgame.APIGameWallet.virtualwallet.repository.UserRepository;
import com.shgame.APIGameWallet.virtualwallet.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CorporateRepository corpRepository;
	
	ResponseData res = new ResponseData();

	
	@Override
    @Transactional
	public ResponseData addUser(RequestData request) {
		log.info("enter");
		try {
			CorporateDetails corporate = corpRepository.findAllCorporateDetailsByCorporateId(request.getCorporateId());
			log.info("Corporate fetch successfully :: "+corporate);
			if (corporate == null) {
				res.setMessage("Please provide valide Corporate Details.");
				res.setStatus("01");
				log.error("Corporate does not exist corporate id ::"+request.getCorporateId());
			} else {
				Random rnd = new Random();
				int n = 10000 + rnd.nextInt(90000);

				String userId = "USER"+n;
				UserDetails userDetails = new UserDetails();
				userDetails.setAge(Integer.valueOf(request.getAge()));
				userDetails.setFname(request.getFirstName());
				userDetails.setLname(request.getLastName());
				userDetails.setEmail(request.getEmail());
				userDetails.setPhoneNo(request.getPhoneNo());
				userDetails.setCity(request.getCity());
				userDetails.setCreateDateTime(LocalDateTime.now());
				userDetails.setPinCode(request.getPinCode());
				userDetails.setState(request.getState());
				userDetails.setUpdateDateTime(LocalDateTime.now());
				userDetails.setUserId(userId);
				UserWallet wallet = new UserWallet();
				wallet.setAmount(0);
				wallet.setCreateDateTime(null);
				wallet.setUpdateDateTime(null);
				wallet.setUser(userDetails);

				userDetails.setCorporate(corporate);
				userDetails.setWallet(wallet);

				UserDetails saveUser = userRepository.save(userDetails);

				res.setMessage("success :: user and wallet created with zero balance");
				res.setStatus("00");
				res.setObject(saveUser);
				log.error("User Saved successfully..");

			}

		} catch (Exception e) {
			res.setMessage(e.getMessage());
			e.printStackTrace();
			res.setStatus("01");
		}
		log.info("exit");
		return res;
	}

	@Override
	public ResponseData getUserDetailsByMobileNumber(RequestData request) {
		log.info("enter");

		log.info("exit");
		return null;
	}

	@Override
	public ResponseData getUserDetailsByCorporateId(String corporateid) {
	try {
			CorporateDetails corporate = corpRepository.findAllCorporateDetailsByCorporateId(corporateid);
			log.info(corporate+"");
			List<UserDetails> userDetails = userRepository.findByCorporateId(corporate.getId());
			res.setObject(userDetails);
			 
			
		} catch (Exception e) {
			res.setMessage(e.getMessage());
			e.printStackTrace();
		}
		log.info("exit");
		return res;
	}

}
