package com.shgame.APIGameWallet.virtualwallet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shgame.APIGameWallet.virtualwallet.dto.RequestData;
import com.shgame.APIGameWallet.virtualwallet.dto.ResponseData;
import com.shgame.APIGameWallet.virtualwallet.service.UserService;

@RestController
@RequestMapping(value = "user/")

public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService service;

	private ResponseData respose;

	@PostMapping(value = "/addUser")
	public ResponseData addUser(@RequestBody RequestData request) {
		log.info("enter");
		System.out.println(request);
		respose = service.addUser(request);
		log.info("exit");
		return respose;
	}
	
	@GetMapping(value = "/fetch/{corporateId}")
	public ResponseData getAllUserByCorporateId(@PathVariable String corporateId) {
		log.info("enter"+corporateId);
		respose = service.getUserDetailsByCorporateId(corporateId);
		log.info("exit");
		return respose;	 
	}
}
