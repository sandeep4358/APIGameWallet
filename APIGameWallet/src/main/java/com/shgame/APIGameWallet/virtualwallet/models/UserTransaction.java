package com.shgame.APIGameWallet.virtualwallet.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Sandeep Arya
 * @date_of_creation 30 October 202
 */

@Entity
@Table(name = "user_transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserTransaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id // to set as primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // to set as auto-increment
	private @Column int id;
	private @Column(name = "transaction_id") String transactionId;
	private @Column(name = "transaction_type") String type; // Deposit or Withdraws
	private @Column(name = "coins_balance") float coins;
	private @Column(name = "post_coins_balance") float postBalanceCoins;
	private @Column(name = "description") String description;

	@ManyToOne
	private UserDetails user;

	@CreationTimestamp
	private LocalDateTime taransactionDate;

	@CreationTimestamp
	private LocalDateTime taransactionUpdateDate;

}
