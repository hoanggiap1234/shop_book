package com.devcamp.shopbook.repositories;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devcamp.shopbook.model.CProduct;

public interface IProductRepository extends JpaRepository<CProduct, Long> {
		@Query(value = "SELECT * FROM products", nativeQuery = true)
		List<CProduct> getProductsWithPagable(Pageable pageable);
		
		@Query(value = "SELECT * FROM products WHERE cat_id = :id ", nativeQuery =  true)
		List<CProduct> getProductByCatalogId(@Param("id") long id);
		
		CProduct findByProductCode(String productCode);
}
