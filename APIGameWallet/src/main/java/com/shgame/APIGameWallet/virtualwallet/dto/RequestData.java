package com.shgame.APIGameWallet.virtualwallet.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class RequestData {
	private String userType;
	@NotNull(message = "First Name shouldn't be null.")
	private String firstName;
	private String middleName;
	@NotNull(message = "Last Name shouldn't be null.")
	private String lastName;
	@Email(message = "invalid email address")
	private String email;
	@Pattern(regexp = "^\\d{10}$", message = "invalid mobile number entered ")
	private String phoneNo;
	private String state;
	private String pinCode;
	private String panNo;
	
	private String gstin;
	private String transactionId;
	private String coins;
	@Min(18)
	@Max(60)
	private String age;
	private String city;
	private String corporateId;
	private String transactionType;
	private String userId;

}
