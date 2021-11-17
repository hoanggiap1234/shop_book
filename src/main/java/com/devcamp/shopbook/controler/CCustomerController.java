package com.devcamp.shopbook.controler;

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
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.shopbook.model.CCustomer;
import com.devcamp.shopbook.repositories.ICustomerRepository;

@RestController
@RequestMapping("/")

public class CCustomerController {
	@Autowired
	private ICustomerRepository iCustomerRepository;
	// create Customer
	
	@CrossOrigin
	@GetMapping("/customer/all")
	List<CCustomer> getAllCustomer(){
		return iCustomerRepository.findAll();
	}
	
	@CrossOrigin
	@PostMapping("/customer/create")
	ResponseEntity<Object> createCustomer(@RequestBody CCustomer cCustomer){		
		try {
			String email = cCustomer.getEmail();
			CCustomer dataCustomer = iCustomerRepository.findByEmail(email);
			if(dataCustomer == null) {
				CCustomer newCus = new CCustomer();
				newCus.setFirstName(cCustomer.getFirstName());
				newCus.setLastName(cCustomer.getLastName());
				newCus.setAddress(cCustomer.getAddress());
				newCus.setEmail (cCustomer.getEmail());
				newCus.setPhoneNumber(cCustomer.getPhoneNumber());
			CCustomer saveCus = iCustomerRepository.save(newCus);
			return new ResponseEntity<Object>(saveCus, HttpStatus.CREATED);
			}
			
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().body("failed to creat customer" + e.getCause().getCause().getMessage());
		}
		return ResponseEntity.unprocessableEntity().body("email is dupplicate");
	}
	// update customer by id
	@CrossOrigin
	@PutMapping("/customer/update/{id}")
	ResponseEntity<Object> updateCustomerById(@PathVariable Long id,  @RequestBody CCustomer cCustomer){		
		try {
			
			Optional<CCustomer> dataCustomer = iCustomerRepository.findById(id);
			if(dataCustomer.isPresent()) {
				CCustomer newCus =dataCustomer.get();
				newCus.setFirstName(cCustomer.getFirstName());
				newCus.setLastName(cCustomer.getLastName());
				newCus.setAddress(cCustomer.getAddress());
				newCus.setEmail (cCustomer.getEmail());
				newCus.setPhoneNumber(cCustomer.getPhoneNumber());
			CCustomer saveCus = iCustomerRepository.save(newCus);
			return new ResponseEntity<Object>(saveCus, HttpStatus.CREATED);
			}
			
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().body("failed to update customer" + e.getCause().getCause().getMessage());
		}
		return ResponseEntity.unprocessableEntity().body("id is not exit");
	}
	
//	// update Customer By Email
//	@CrossOrigin
//	@PutMapping("/customer/update/{email}")
//	ResponseEntity<Object> updateCustomerByEmail(@PathVariable String email, @RequestBody CCustomer cCustomer){		
//		try {
//			CCustomer dataCustomer = iCustomerRepository.findByEmail(email);
//			if(dataCustomer != null) {
//				CCustomer newCus = dataCustomer;
//				newCus.setFirstName(cCustomer.getFirstName());
//				newCus.setLastName(cCustomer.getLastName());
//				newCus.setAddress(cCustomer.getAddress());
//				newCus.setEmail (cCustomer.getEmail());
//				newCus.setPhoneNumber(cCustomer.getPhoneNumber());
//			CCustomer saveCus = iCustomerRepository.save(newCus);
//			return new ResponseEntity<Object>(saveCus, HttpStatus.CREATED);
//			}
//			
//		} catch (Exception e) {
//			return ResponseEntity.unprocessableEntity().body("failed to creat customer" + e.getCause().getCause().getMessage());
//		}
//		return  new ResponseEntity<Object>(null);
//	}
//	
//	// update Customer By id
//		@CrossOrigin
//		@PutMapping("/customer/update/{id}")
//		ResponseEntity<Object> updateCustomerByEmail(@PathVariable Long id, @RequestBody CCustomer cCustomer){		
//			try {
//				Optional<CCustomer> dataCustomer = iCustomerRepository.findById(id);
//				if(dataCustomer.isPresent()) {
//					CCustomer newCus = dataCustomer.get();
//					newCus.setFirstName(cCustomer.getFirstName());
//					newCus.setLastName(cCustomer.getLastName());
//					newCus.setAddress(cCustomer.getAddress());
//					newCus.setEmail (cCustomer.getEmail());
//					newCus.setPhoneNumber(cCustomer.getPhoneNumber());
//				CCustomer saveCus = iCustomerRepository.save(newCus);
//				return new ResponseEntity<Object>(saveCus, HttpStatus.CREATED);
//				}
//				
//			} catch (Exception e) {
//				return ResponseEntity.unprocessableEntity().body("failed to creat customer" + e.getCause().getCause().getMessage());
//			}
//			return  ResponseEntity.unprocessableEntity().body("id is not exits");
//		}
//		
		// Delete Customer by Id
				@CrossOrigin
				@DeleteMapping("/customer/delete/{id}")
				ResponseEntity<Object> deleteCustomerById(@PathVariable long id){
					try {
						iCustomerRepository.deleteById(id);
						return new ResponseEntity<Object>(HttpStatus.OK);
					} catch (Exception e) {
						System.out.println("++++++++ Custom Error: " + e.getCause().getCause().getMessage());
						return ResponseEntity.unprocessableEntity()
								.body("Failed to delete Customer: " + e.getCause().getCause().getMessage());
					}
					
				}
}
