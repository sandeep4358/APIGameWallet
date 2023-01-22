package com.shgame.APIGameWallet.virtualwallet.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Sandeep Arya
 * @date_of_creation 30 October 202
 */

@Entity
@Table(name = "user_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@DynamicInsert
@DynamicUpdate
public class UserDetails implements Serializable {

	@Id // to set as primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // to set as auto-increment
	private int id;

	@Column(name = "user_id")
	private String userId;

	@Column
	private String fname;
	@Column
	private String lname;
	@Column(unique = true)
	private String email;

	@Column(name = "phone_no", unique = true)
	private String phoneNo;
	@Column(name = "age")
	private Integer age;
	@Column(name="city")
	private String city;
	@Column(name = "state")
	private String state;
	@Column(name = "pincode")
	private String pinCode;

	@ManyToOne
	@JsonIgnore
	private CorporateDetails corporate;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private UserWallet wallet;

	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<UserTransaction> transaction;

	@CreationTimestamp
	private LocalDateTime createDateTime;

	@UpdateTimestamp
	private LocalDateTime updateDateTime;

	private static final long serialVersionUID = 1L;

}
