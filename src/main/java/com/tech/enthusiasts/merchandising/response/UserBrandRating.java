package com.tech.enthusiasts.merchandising.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserBrandRating {
	private Long brandId;
	private Long rating;
	@JsonProperty("brandInformation")
	private BrandInfoResponse brandInfoResponse;
}
