package com.devcamp.shopbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.shopbook.model.CCustomer;

public interface ICustomerRepository extends JpaRepository<CCustomer, Long> {
	CCustomer findByEmail(String email);
}
