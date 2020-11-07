package com.tech.enthusiasts.merchandising.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tech.enthusiasts.merchandising.response.BrandInfoResponse;
import com.tech.enthusiasts.merchandising.response.CatalogueResponse;
import com.tech.enthusiasts.merchandising.response.UserBrandRating;
import com.tech.enthusiasts.merchandising.response.UserRatingDetails;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserCatalogueService {

	@Value("${brandInfoApi.brandDetailsUri}")
	private String brandInfoEndpoint;

	@Value("${userRatingApi.brandRatingUri}")
	private String ratingInfoEndPoint;

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CachingService cachingService;
	
	@HystrixCommand(fallbackMethod = "getCachedResponse")
	public CatalogueResponse findBrandCatalogueByUserId(final Long userId) {
		log.info("Calling External API's to fetch response for userID :: {}", userId);
		final CatalogueResponse catalogueResponse = new CatalogueResponse();
		final UserRatingDetails userRatingDetail = restTemplate.getForObject(ratingInfoEndPoint + "/" + userId,
				UserRatingDetails.class);
		final ServiceInstance info = discoveryClient.getInstances("brand-info-service").get(0);
		log.info(info.toString());
		final List<UserBrandRating> userRatingDetails = userRatingDetail.getUserBrandRating();
		userRatingDetails.stream().forEach(ratingDetail -> {
			final Long brandId = ratingDetail.getBrandId();
			final BrandInfoResponse brandInfoResponse = restTemplate.getForObject(brandInfoEndpoint + "/" + brandId,
					BrandInfoResponse.class);
			ratingDetail.setBrandInfoResponse(brandInfoResponse);
		});
		catalogueResponse.setBrandRatings(userRatingDetails);
		cachingService.putData(userId, catalogueResponse);//Write through
		return catalogueResponse;
	}
	
	public CatalogueResponse getCachedResponse(final Long userId) {
		log.info("Returning cached response for userId :: {}", userId);
		return cachingService.getData(userId);
	}
	

}
