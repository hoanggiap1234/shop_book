package com.devcamp.shopbook.controler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.shopbook.model.CCustomer;
import com.devcamp.shopbook.model.COrder;
import com.devcamp.shopbook.repositories.ICustomerRepository;
import com.devcamp.shopbook.repositories.IOrderRepository;

@RestController
@RequestMapping("/")
public class COrderController {
	@Autowired
	private IOrderRepository iOrderRepository;
	@Autowired
	private ICustomerRepository iCustomerRepository;

	// Get all order
	@CrossOrigin
	@GetMapping("/order/all")
	List<COrder> getAllOrder() {
		return iOrderRepository.findAll();
	}

	// get order by customer id
	@CrossOrigin
	@GetMapping("/customer/{id}/order")
	ResponseEntity<Object> getOrderByCustomerId(@PathVariable Long id, @RequestBody COrder cOrder) {
		try {
			Optional<CCustomer> dataCus = iCustomerRepository.findById(id);
			if (dataCus.isPresent()) {
				List<COrder> listOrder = iOrderRepository.getOrderByCustomerId(id);
				return new ResponseEntity<Object>(listOrder, HttpStatus.OK);
			} else {
				return ResponseEntity.unprocessableEntity().body("id is not exits");
			}

		} catch (Exception e) {
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// get order by id
	@CrossOrigin
	@GetMapping("/order/detail/{id}")
	Optional<COrder> getOrderById(@PathVariable Long id) {
		if (iOrderRepository.findById(id).isPresent()) {
			return iOrderRepository.findById(id);
		}
		return null;
	}

	private Date getCurrentDate() {
		Date date = new Date();
		return date;
	}
	private String setRandomOrderCode(Long id) {
		Optional<CCustomer>  dataCus = iCustomerRepository.findById(id);
		
		String result = "IV0000" + dataCus.get().getId() +(int) Math.random(); 
		return result;
	}

	// create Order
	@CrossOrigin
	@PostMapping("/customer/{id}/order/create")
	ResponseEntity<Object> createOrderByCustomerId(@PathVariable Long id) {
		try {
			Optional<CCustomer> dataCus = iCustomerRepository.findById(id);
			if (dataCus.isPresent()) {
				COrder newOrder = new COrder();
				newOrder.setOrder_date(getCurrentDate());
				newOrder.setOrderCode(setRandomOrderCode(id));
				newOrder.setCustomers(dataCus.get());
				COrder saveOrder = iOrderRepository.save(newOrder);
				return new ResponseEntity<Object>(saveOrder, HttpStatus.CREATED);
			} else {
				return ResponseEntity.unprocessableEntity().body("id is not exits");
			}
		} catch (Exception e) { // TODO: handle exception
			return ResponseEntity.unprocessableEntity()
					.body("failed to create Order" + e.getCause().getCause().getMessage());
		}
	}

	// update order
	@CrossOrigin
	@PutMapping("/customer/{id}/order/{orderCode}/update")
	ResponseEntity<Object> updateOrderByCustomerId(@PathVariable Long id,@PathVariable String orderCode,  @RequestBody COrder cOrder) {
		try {
			Optional<CCustomer> dataCus = iCustomerRepository.findById(id);
			if (dataCus.isPresent()) {
				COrder newOrder = iOrderRepository.getOrderByCustomerIdAndOrderCode(id,orderCode);				
					newOrder.setShippedDate(cOrder.getShippedDate());
					newOrder.setStatus(cOrder.getStatus());
					newOrder.setComments(cOrder.getComments());
					COrder saveOrder = iOrderRepository.save(newOrder);			
			
				return new ResponseEntity<Object>(saveOrder, HttpStatus.CREATED);
			} else {
				return ResponseEntity.unprocessableEntity().body("id is not exits");
			}
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.unprocessableEntity()
					.body("failed to update Order" + e.getCause());
		}
	}

}
