package com.tech.enthusiasts.merchandising.service;

import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.tech.enthusiasts.merchandising.response.CatalogueResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CachingService {
	
	private ConcurrentHashMap<Long, CatalogueResponse> cachedData;
	
	@PostConstruct
	public void init() {
		cachedData = new ConcurrentHashMap<>();
	}
	
	public void putData(final Long userId, final CatalogueResponse response) {
		cachedData.compute(userId, (k,v) -> response);
		log.info("Updated cache against userID :: {}", userId);
	}
	
	public CatalogueResponse getData(final Long userId) {
		return cachedData.get(userId);
	}
}
