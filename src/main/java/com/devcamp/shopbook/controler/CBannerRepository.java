package com.devcamp.shopbook.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.shopbook.model.CBanner;
import com.devcamp.shopbook.repositories.IBannerRepository;

@RequestMapping("/")
@RestController
public class CBannerRepository {
	@Autowired
	IBannerRepository iBannerRepository;
	
	@GetMapping("/banner/all")
	List<CBanner> getBanner(){
		return iBannerRepository.findAll();
	}
}
