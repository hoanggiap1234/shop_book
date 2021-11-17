package com.devcamp.shopbook.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "catalog")
public class CCatalog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "input catalog code")
	@Column(name = "catalog_code", unique = true)
	private String catalogCode;
	
	@NotBlank(message = "input catalog name")
	@Column(name = "catalog_name")
	private String catalogName;

	@OneToMany(mappedBy = "catalog", cascade = CascadeType.ALL)
	List<CProduct> listProducts;
	
	public List<CProduct> getListProducts() {
		return listProducts;
	}

	public void setListProducts(List<CProduct> listProducts) {
		this.listProducts = listProducts;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCatalogCode() {
		return catalogCode;
	}

	public void setCatalogCode(String catalogCode) {
		this.catalogCode = catalogCode;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	
	
}
