package com.devcamp.shopbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.shopbook.model.CBanner;

public interface IBannerRepository extends JpaRepository<CBanner, Long> {

}
