package com.tech.enthusiasts.merchandising.response;

import java.util.List;

import lombok.Data;

@Data
public class CatalogueResponse {
	private List<UserBrandRating> brandRatings;
}
