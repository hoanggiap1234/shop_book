package com.devcamp.shopbook.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devcamp.shopbook.model.COrder;

public interface IOrderRepository extends JpaRepository<COrder, Long>{
	@Query(value = "SELECT * FROM orders WHERE customer_id like :id and order_Code = :orderCode " , nativeQuery = true)
	COrder getOrderByCustomerIdAndOrderCode(@Param("id")Long id, @Param("orderCode") String orderCode);
	
	@Query(value = "SELECT * FROM orders WHERE customer_id like :id " , nativeQuery = true)
	List<COrder> getOrderByCustomerId(@Param("id")Long id);
}
