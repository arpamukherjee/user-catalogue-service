package com.tech.enthusiasts.merchandising.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_EMPTY)
public class BrandInfoResponse {
	private String brandDescription;
	private String countryOfOrigin;
}
