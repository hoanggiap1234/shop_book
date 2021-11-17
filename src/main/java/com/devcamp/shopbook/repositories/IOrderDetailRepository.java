package com.devcamp.shopbook.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devcamp.shopbook.model.COrderDetail;

public interface IOrderDetailRepository extends JpaRepository<COrderDetail, Long> {

	@Query(value = "SELECT c.id as 'cusId', c.first_name,c.last_name, o.id as 'orderId', o.order_date,"
			+ " o.status, od.quantity_order, od.price_each, p.id as 'productId' FROM customers "
			+ "INNER JOIN orders o ON c.id = o.customer_id "
			+ "INNER JOIN order_details od on od.order_id= o.id "
			+ "INNER JOIN products p ON p.id = od.product_id",nativeQuery = true)
	List<?> getCustomerSaleByOrderDetail();
}
