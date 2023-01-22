package com.shgame.APIGameWallet.virtualwallet.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = Visibility.DEFAULT, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseData extends RuntimeException {
	private static final long serialVersionUID = 1L;

	@JsonProperty("message")
	private String message;

	@JsonProperty("status")
	private String status;

	@JsonProperty("reqRefId")
	private Object object;

}
