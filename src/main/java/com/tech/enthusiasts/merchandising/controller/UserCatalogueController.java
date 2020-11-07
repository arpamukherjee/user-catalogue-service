package com.tech.enthusiasts.merchandising.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.enthusiasts.merchandising.response.CatalogueResponse;
import com.tech.enthusiasts.merchandising.service.UserCatalogueService;

@RestController
@RequestMapping("/catalogue/user")
public class UserCatalogueController {
	
	@Autowired
	private UserCatalogueService userCatalogueService;
	
	@GetMapping("/{userId}")
	public ResponseEntity<CatalogueResponse> findBrandCatalogueByUserId(@PathVariable final Long userId){
		final CatalogueResponse catalogueResponse = userCatalogueService.findBrandCatalogueByUserId(userId);
		return ResponseEntity.ok(catalogueResponse);
	}
}
