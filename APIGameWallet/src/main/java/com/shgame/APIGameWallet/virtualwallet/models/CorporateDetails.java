package com.shgame.APIGameWallet.virtualwallet.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name = "corporate_detail")
public class CorporateDetails implements Serializable {

	@Id // to set as primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // to set as auto-increment
	private int id;

	@Column(name = "corporate_id") private String corporateId;
	
	@Column(name="first_name")
	private String fname;
	@Column(name="last_name")
	private String lname;

	@Column(unique = true,name = "email_id")
	private String email;

	@Column(unique = true,name = "phone_no")
	private String phoneNo;  

	@Column(name = "age")
	private Integer age;
	@Column(name = "city")
	private String city;
	@Column(name = "state")
	private String state;
	@Column(name = "pincode")
	private String pinCode;
	/**
	 * Below is for the optimistic locking for cuncurrent transactions.
	 */
	@Version
    @Type(type = "dbtimestamp")
    private Date version;
 

	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<UserDetails> userList;

	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<CorporateTransactions> transactions;

	@OneToOne(mappedBy = "corporate",cascade=CascadeType.ALL)
	@JsonIgnore
	private CorporateWallet wallet;

	@CreationTimestamp
	private LocalDateTime createDateTime;

	@UpdateTimestamp
	private LocalDateTime updateDateTime;

	private static final long serialVersionUID = 1L;

}
