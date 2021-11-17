package com.devcamp.shopbook.model;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "orders")
public class COrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private long id;
	
	@NotNull(message = "Order code not empty and duplicate")
	@Column(name = "order_code" , unique = true)
	private String orderCode;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "order_date")
	@CreatedDate
	@JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
	private Date order_date;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
	@Column(name = "shipped_date",  nullable = true)
	private Date shippedDate;

	@Column(name = "status")
	private String status;
	

	@Column(name = "comments", nullable = true)
	private String comments;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = true)
	private CCustomer customers;

	@OneToMany(mappedBy = "orders")
	private List<COrderDetail> orderDetails;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}


	public Date getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	@JsonIgnore
	public CCustomer getCustomers() {
		return customers;
	}

	public void setCustomers(CCustomer customers) {
		this.customers = customers;
	}

	public List<COrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<COrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
