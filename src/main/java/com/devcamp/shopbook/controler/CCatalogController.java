package com.devcamp.shopbook.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.shopbook.model.CCatalog;
import com.devcamp.shopbook.repositories.ICatalogRepository;

@RestController
@RequestMapping("/")
public class CCatalogController {
	
	@Autowired
	private ICatalogRepository iCatalogRepository;
	
	@CrossOrigin
	@GetMapping("/catalog/all")
	List<CCatalog> getAllCatalog(){
		return iCatalogRepository.findAll();
	}

}
