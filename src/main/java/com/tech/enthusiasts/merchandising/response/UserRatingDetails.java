package com.tech.enthusiasts.merchandising.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRatingDetails {
	
	private Long userId;
	private List<UserBrandRating> userBrandRating;
}
