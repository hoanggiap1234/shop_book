package com.devcamp.shopbook.controler;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
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

import com.devcamp.shopbook.model.CCatalog;
import com.devcamp.shopbook.model.CProduct;
import com.devcamp.shopbook.repositories.ICatalogRepository;
import com.devcamp.shopbook.repositories.IProductRepository;

@RestController
@RequestMapping("/")
public class CProductController {
	@Autowired
	private IProductRepository iProductRepository;
	
	@Autowired
	private ICatalogRepository iCatalogRepository;
	
	@CrossOrigin
	@GetMapping("/product/all")
	List<CProduct> getAllProduct(){
		return iProductRepository.findAll();
	}
	
	@CrossOrigin
	@GetMapping("/product")
	Page<CProduct> getAllProductWithPagable(@Param("page") int pageNumber, @Param("numberItem") int numberItem){
		Page<CProduct> page = iProductRepository.findAll(PageRequest.of(pageNumber, numberItem));		
		return page;
	}
	
	//getProduct by id
	@CrossOrigin
	@GetMapping("/product/detail/{id}")
	Optional<CProduct> getAllProductById(@PathVariable long id){
		if(iProductRepository.findById(id).isPresent()) {
			return iProductRepository.findById(id);
		}
		return null;
	}
	
	@CrossOrigin
	@GetMapping("/category/{id}/product")
	List<CProduct> getProductByCategoryId(@PathVariable("cat_id") long cat_id){
		Optional<CCatalog> vCatalog =  iCatalogRepository.findById(cat_id);
		if( vCatalog.isPresent()) {
			return iProductRepository.getProductByCatalogId(cat_id);
		}
		else {
			return null;
		}
	}
	
	@CrossOrigin
	@PutMapping("/product/{id}/update")
	ResponseEntity<Object> updateProductById(@PathVariable Long id,@RequestBody CProduct cProduct) {
		try {
			Optional<CProduct> dataProduct = iProductRepository.findById(id);
			if(dataProduct.isPresent()) {
				CProduct newProduct = dataProduct.get();
				newProduct.setProductCode(cProduct.getProductCode());
				newProduct.setProductName(cProduct.getProductName());
				newProduct.setBuyPrice(cProduct.getBuyPrice());
				newProduct.setUrlImg(cProduct.getUrlImg());
				newProduct.setQuatityInStock(cProduct.getQuatityInStock());
				newProduct.setSalePrice(cProduct.getSalePrice());
				newProduct.setVitualPrice(cProduct.getVitualPrice());
				CProduct saveProduct = iProductRepository.save(newProduct);
				return new ResponseEntity<>(saveProduct, HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.unprocessableEntity().body("failed to update Product " + e.getCause().getMessage());
		}
		return ResponseEntity.unprocessableEntity().body("id  is not exits" );
	}
	
	@CrossOrigin
	@PostMapping("/category/{id}/product/create")
	ResponseEntity<Object> createProductByCategoryId(@PathVariable Long id, @RequestBody CProduct cProduct) {
		try {
			Optional<CCatalog> dataCatalog = iCatalogRepository.findById(id);

			if (dataCatalog.isPresent()) {
				CProduct dataProduct = iProductRepository.findByProductCode(cProduct.getProductCode());
				if (dataProduct == null) {
					CProduct newProduct = new CProduct();
					newProduct.setProductCode(cProduct.getProductCode());
					newProduct.setProductName(cProduct.getProductName());
					newProduct.setUrlImg(cProduct.getUrlImg());
					newProduct.setQuatityInStock(cProduct.getQuatityInStock());
					newProduct.setSalePrice(cProduct.getSalePrice());
					newProduct.setVitualPrice(cProduct.getVitualPrice());
					newProduct.setBuyPrice(cProduct.getBuyPrice());
					newProduct.setCatalog(dataCatalog.get());
					CProduct saveProduct = iProductRepository.save(newProduct);
					return new ResponseEntity<>(saveProduct, HttpStatus.CREATED);
				} else {
					return ResponseEntity.unprocessableEntity()
							.body("productCode is dupplicate " + HttpStatus.BAD_REQUEST);
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.unprocessableEntity().body("failed to create Product " + e.getMessage());
		}
		return ResponseEntity.unprocessableEntity().body("id  is not exits or dupplicate productCode");
	}
	
	@CrossOrigin
	@DeleteMapping("/product/{id}/delete")
	public int deleteProductById(@PathVariable Long id) {
		Optional<CProduct> cProduct = iProductRepository.findById(id);
		if(cProduct.isPresent()) {
			iProductRepository.deleteById(id);
			return 1;
		}
		else {
			return 0;
		}
	}
	
}
