package com.shgame.APIGameWallet.virtualwallet.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "corporate_wallet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CorporateWallet {

	@Id // to set as primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // to set as autoincrement
	private int walletId;
	
	@Column(name = "coins")
	int amount;
	
	@CreationTimestamp
	@Column(name = "creationDateTime")
	private LocalDateTime createDateTime;

	@UpdateTimestamp
	private LocalDateTime updateDateTime;

	@OneToOne
	private CorporateDetails corporate;

	private static final long serialVersionUID = 1L;

}
