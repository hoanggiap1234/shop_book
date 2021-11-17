package com.devcamp.shopbook.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
@Table(name = "products")
public class CProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "input product code")
	@Column(name = "product_code", unique = true)
	private String productCode;
	
	@NotBlank(message = "input product name")
	@Column(name = "product_name")
	private String productName;
	
	@NotNull
	@NotBlank(message = "input url image of product")
	@Column(name = "url_image")
	private String urlImg;
	
	@NotNull
//	@NotEmpty(message = "input buy price")
	@Column(name = "buy_price")
	private double buyPrice;	
	
	@NotNull
//	@NotEmpty(message = "input sale price")
	@Column(name = "sale_price")
	private double salePrice;
	
	@Column(name = "vitual_price")
	private double vitualPrice;
		
	@Column(name = "quatity_in_stock")
	private double quatityInStock;

	@ManyToOne
	@JoinColumn(name = "cat_id")
	private CCatalog catalog; 
	
	@OneToMany(mappedBy = "products")
	private List<COrderDetail> orderDetails;
	
	public double getQuatityInStock() {
		return quatityInStock;
	}

	public void setQuatityInStock(double quatityInStock) {
		this.quatityInStock = quatityInStock;
	}
	
	public List<COrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<COrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	@JsonIgnore
	public CCatalog getCatalog() {
		return catalog;
	}

	public void setCatalog(CCatalog catalog) {
		this.catalog = catalog;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public String getUrlImg() {
		return urlImg;
	}

	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public double getVitualPrice() {
		return vitualPrice;
	}

	public void setVitualPrice(double vitualPrice) {
		this.vitualPrice = vitualPrice;
	}
	
}
