package com.shgame.APIGameWallet.virtualwallet.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shgame.APIGameWallet.virtualwallet.dto.RequestData;
import com.shgame.APIGameWallet.virtualwallet.dto.ResponseData;
import com.shgame.APIGameWallet.virtualwallet.models.CorporateDetails;
import com.shgame.APIGameWallet.virtualwallet.models.CorporateTransactions;
import com.shgame.APIGameWallet.virtualwallet.models.CorporateWallet;
import com.shgame.APIGameWallet.virtualwallet.models.UserDetails;
import com.shgame.APIGameWallet.virtualwallet.models.UserTransaction;
import com.shgame.APIGameWallet.virtualwallet.models.UserWallet;
import com.shgame.APIGameWallet.virtualwallet.repository.CorporateRepository;
import com.shgame.APIGameWallet.virtualwallet.repository.CorporateTransactionRepository;
import com.shgame.APIGameWallet.virtualwallet.repository.CorporateWalletRepository;
import com.shgame.APIGameWallet.virtualwallet.repository.UserRepository;
import com.shgame.APIGameWallet.virtualwallet.repository.UserTransactionRepository;
import com.shgame.APIGameWallet.virtualwallet.repository.UserWalletRepository;
import com.shgame.APIGameWallet.virtualwallet.service.WalletService;
import com.shgame.APIGameWallet.virtualwallet.utils.GameUtility;

@Service
public class WalletServiceImpl implements WalletService {

	private static final Logger log = LoggerFactory.getLogger(WalletServiceImpl.class);

	@Autowired
	private CorporateRepository corporateRepository;

	@Autowired
	private CorporateWalletRepository corporateWalletRepository;
	@Autowired
	private CorporateTransactionRepository corporateTransactionRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserWalletRepository userWalletRepository;

	@Autowired
	private UserTransactionRepository userTransactionRepository;

	private ResponseData res = new ResponseData();

	@Override
	@Transactional
	public ResponseData addFundToCorporateWallet(RequestData request) {
		log.info("enter");
		CorporateDetails corporateDetails = null;
		CorporateTransactions corporateTransactions;
		log.info("corporate id " + request.getCorporateId());
		int totalCoins = 0;
		try {
			corporateDetails = corporateRepository.findAllCorporateDetailsByCorporateId(request.getCorporateId());
			log.info("corporate : " + corporateDetails);
			CorporateWallet wallet = corporateDetails.getWallet();
			
			if(!request.getTransactionType().equalsIgnoreCase("credit")&&!request.getTransactionType().equalsIgnoreCase("debit")) {
				res.setMessage("Invalid transaction type.");
				res.setStatus("01");
				return res;
			}
			
			if (request.getTransactionType().equals("debit")) {
				totalCoins = wallet.getAmount() - Integer.parseInt(request.getCoins());

			} else {
				totalCoins = wallet.getAmount() + Integer.parseInt(request.getCoins());
			}

			int updateFlag = corporateWalletRepository.updateCorporateWalletAmount(totalCoins, wallet.getWalletId());

			if (updateFlag == 1) {
				CorporateTransactions transaction = new CorporateTransactions();
				transaction.setTransactionId(GameUtility.extractTransactionId(null));
				transaction.setBalance(totalCoins);
				transaction.setPostBalance(Integer.parseInt(request.getCoins()));
				transaction.setTimestamp(LocalDateTime.now());
				transaction.setPostBalance(totalCoins);
				transaction.setCorporateTransaction(corporateDetails);
				transaction.setType(request.getTransactionType());
				transaction.setDescription("Corporate wallet "+request.getTransactionType()+ " with coins "+request.getCoins());
				corporateTransactions = corporateTransactionRepository.save(transaction);

				res.setMessage("success");
				res.setStatus("00");
				res.setObject(corporateTransactions);
			} else {
				res.setMessage("failure");
				res.setStatus("01");
			}
			log.info("Corporate details : " + corporateDetails + "");

			res.setObject(corporateDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}

		log.info("exit");
		return res;
	}

	/**
	 * Load User Wallet.
	 */
	@Override
	@Transactional
	public ResponseData loadUserWallet(RequestData request) {
		log.info("enter");
		res.setMessage("failure");
		res.setStatus("00");
		CorporateWallet corporateWallet = null;
		String message = "failure";
		CorporateDetails corporate = null;
		CorporateTransactions corporateTransaction = new CorporateTransactions();
		UserTransaction userTransaction = new UserTransaction();
		UserWallet userWallet = null;
		String status = "00";
		try {

			UserDetails gameUser = userRepository.findAllByUserId(request.getUserId());
			if(gameUser==null) {
				message = "failure" + " User with "+ request.getUserId()+ " userid does not exist";
				status = "01";
				res.setMessage(message);
				res.setStatus(status);
				return res;
			}
			corporateWallet = gameUser.getCorporate().getWallet();

			if (corporateWallet.getAmount() <= 0) {
				message = "failure" + " Corporate Wallet Balance is low, load the corporate balance.";
				status = "01";
			} else {
				
				String transactionId = GameUtility.extractTransactionId(null);
				log.info("transactionId ::" + transactionId);

				userWallet = gameUser.getWallet();
				log.info("userWallet ::" + userWallet);
				int existingUserCoins = userWallet.getAmount();
				int existingCorporateCoins = corporateWallet.getAmount();
				log.info("userWallet existing Balance ::" + existingUserCoins);
				log.info("transacton Type :: " + request.getTransactionType());

				if (request.getTransactionType().equals("credit")) {
					existingUserCoins += Integer.parseInt(request.getCoins());
					existingCorporateCoins -= Integer.parseInt(request.getCoins());

				} else {
					if (existingUserCoins < Integer.parseInt(request.getCoins())) {
						message = "failure" + " User Wallet Balance is low, load the user balance.";
						status = "01";
					} else {
						existingUserCoins -= Integer.parseInt(request.getCoins());
						existingCorporateCoins += Integer.parseInt(request.getCoins());

					}
				}

				if (status != "01") {
					userWallet.setAmount(existingUserCoins); // it will not  automatically saved. Needed to check
					corporateWallet.setAmount(existingCorporateCoins);
					userWalletRepository.save(userWallet);  //user wallet entry is saved.
					
						userTransaction.setTransactionId(transactionId);
						userTransaction.setCoins(existingUserCoins);
						userTransaction.setPostBalanceCoins(existingUserCoins);
						userTransaction.setTaransactionDate(LocalDateTime.now());
						userTransaction.setTaransactionUpdateDate(LocalDateTime.now());
						userTransaction.setType(request.getTransactionType());
						userTransaction.setUser(gameUser);
						userTransaction.setDescription(request.getTransactionType().equalsIgnoreCase("credit")? "User wallet is credited with coins "+request.getCoins() : "User wallet is debited with coins "+request.getCoins() );
						//user transaction repository is saved.
						userTransactionRepository.save(userTransaction);
					
					//corporate wallet entry is saved. 
					corporateWalletRepository.save(corporateWallet);
					
						corporateTransaction.setTransactionId(transactionId);
						corporateTransaction.setBalance(existingCorporateCoins);
						corporateTransaction.setPostBalance(existingCorporateCoins);
						corporateTransaction.setTimestamp(LocalDateTime.now());
						corporateTransaction.setType(request.getTransactionType().equalsIgnoreCase("credit")? "debit" : "credit"); // corporate wallet is reverse of the user wallet
						corporateTransaction.setCorporateTransaction(corporate);
						corporateTransaction.setDescription(request.getTransactionType().equalsIgnoreCase("credit")? "User wallet is debited with coins "+request.getCoins() : "User wallet is credited with coins "+request.getCoins());
						//corporate transaction saved
						corporateTransactionRepository.save(corporateTransaction);
					
					
					
					
					log.info("userWallet updated Balance :: " + existingUserCoins);
					log.info("corporate update Balance   :: " + existingCorporateCoins);

					message = "success";
					status = "00";
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			message = "failure" + e.getMessage();
			status = "01";
		}

		res.setObject(userWallet);
		res.setMessage(message);
		res.setStatus(status);
		log.info("exit");
		return res;
	}

}
