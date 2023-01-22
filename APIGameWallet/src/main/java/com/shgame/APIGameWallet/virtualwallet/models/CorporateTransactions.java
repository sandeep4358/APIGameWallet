package com.shgame.APIGameWallet.virtualwallet.models;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "corporate_transaction")
public class CorporateTransactions {
	 @Id //to set as primary key
	    @GeneratedValue(strategy = GenerationType.IDENTITY) // to set as autoincrement
	    private int id;
	    @Column(name = "transaction_id")
	    private String transactionId;
	    @Column(name = "transaction_type")
	    private String type;
	    @Column(name = "transaction_date_time")
	    private LocalDateTime timestamp;
	    @Column(name = "coins_balance")
	    private float balance;
	    @Column(name = "post_coins_balance")
	    private float postBalance;
	    @Column(name = "description")
	    private String description;
	    @ManyToOne	    
	    private CorporateDetails corporateTransaction;
}
