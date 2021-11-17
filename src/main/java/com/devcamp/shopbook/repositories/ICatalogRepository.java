package com.devcamp.shopbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.shopbook.model.CCatalog;

public interface ICatalogRepository extends JpaRepository<CCatalog, Long> {
	
}
