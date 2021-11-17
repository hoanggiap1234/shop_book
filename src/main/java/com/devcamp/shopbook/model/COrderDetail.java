package com.devcamp.shopbook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "order_details")
public class COrderDetail {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
	private long id;
	
	@ManyToOne()
	@JoinColumn(name = "product_id")
	private CProduct products;
	
	@NotNull
	@Column(name = "quantity_order")
	private long quantityOrder;
	
	@NotNull
	@Column(name = "price_each")
	private double  priceEach;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private COrder orders;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	@JsonIgnore
	public CProduct getProducts() {
		return products;
	}

	public void setProducts(CProduct products) {
		this.products = products;
	}

	public long getQuantityOrder() {
		return quantityOrder;
	}

	public void setQuantityOrder(long quantityOrder) {
		this.quantityOrder = quantityOrder;
	}

	public double getPriceEach() {
		return priceEach;
	}

	public void setPriceEach(double priceEach) {
		this.priceEach = priceEach;
	}
    @JsonIgnore
	public COrder getOrders() {
		return orders;
	}

	public void setOrders(COrder orders) {
		this.orders = orders;
	}
	
	
}


