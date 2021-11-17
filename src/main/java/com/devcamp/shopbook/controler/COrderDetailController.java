package com.devcamp.shopbook.controler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.shopbook.model.COrder;
import com.devcamp.shopbook.model.COrderDetail;
import com.devcamp.shopbook.model.CProduct;
import com.devcamp.shopbook.repositories.ICustomerRepository;
import com.devcamp.shopbook.repositories.IOrderDetailRepository;
import com.devcamp.shopbook.repositories.IOrderRepository;
import com.devcamp.shopbook.repositories.IProductRepository;

@RestController
@RequestMapping("/")
public class COrderDetailController {
 @Autowired
 private IOrderDetailRepository iOrderDetailRepository;
 @Autowired
 private IOrderRepository iOrderRepository;
 @Autowired
 private IProductRepository iProductRepository;
 @Autowired
 private ICustomerRepository iCustomerRepository;
 
 @CrossOrigin
 @PostMapping("/order/{id}/orderDetail/product/{productId}/create")
 ResponseEntity<Object> createOrderDetailByOrderIdAndProductId(@PathVariable Long id,  @RequestBody COrderDetail cOrderDetail,@PathVariable Long productId) {
		try {
			Optional<COrder> dataOrder = iOrderRepository.findById(id);
			Optional<CProduct> dataProduct = iProductRepository.findById(productId);
			if (dataOrder.isPresent()) {
				COrderDetail newOrderDetail = new COrderDetail();
				newOrderDetail.setQuantityOrder(cOrderDetail.getQuantityOrder());
				newOrderDetail.setPriceEach(cOrderDetail.getPriceEach());
				newOrderDetail.setOrders(dataOrder.get());	
				newOrderDetail.setProducts(dataProduct.get());
				
				COrderDetail saveOrderDetail = iOrderDetailRepository.save(newOrderDetail);
				
				return new ResponseEntity<Object>(saveOrderDetail, HttpStatus.CREATED);
			} else {
				return ResponseEntity.unprocessableEntity().body("id is not exits");
			}
		} catch (Exception e) { 		
			// TODO: handle exception
			return ResponseEntity.unprocessableEntity()
					.body("failed to create OrderDetail" + e.getCause().getCause().getMessage());
		}
	}
 
	@CrossOrigin
	@GetMapping("/orderDetail/all")
	List<COrderDetail> getAllOrderDetail() {
		return iOrderDetailRepository.findAll();
	}
	
	@CrossOrigin
	@GetMapping("/customer/orderDetail")
	List<?> getAllCustomerWithOrderDetail() {
		return iOrderDetailRepository.getCustomerSaleByOrderDetail();
	}
	
	
	
 
}
