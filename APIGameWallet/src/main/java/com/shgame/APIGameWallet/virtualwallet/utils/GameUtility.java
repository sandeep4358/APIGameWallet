package com.shgame.APIGameWallet.virtualwallet.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class GameUtility {
	public static String extractTransactionId(String txnId) {
		String transactionId = txnId == null || txnId.length() < 1
				// ?
				// UUID.nameUUIDFromBytes(UtilityP.createMultipleTransactionID().getBytes()).toString()
				? createMultipleTransactionID()
				: txnId;

		return transactionId;
	}

	public static String createMultipleTransactionID() {
		String AgentTranID = "";
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSS");
			Date date = new Date();
			String tranID = sdf.format(date);
			int n = 4;
			Random randGen = new Random();
			int startNum = (int) Math.pow(10, n - 1);
			int range = (int) (Math.pow(10, n) - startNum);
			int randomNum = randGen.nextInt(range) + startNum;

			AgentTranID = tranID + randomNum;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return AgentTranID;
	}
}
