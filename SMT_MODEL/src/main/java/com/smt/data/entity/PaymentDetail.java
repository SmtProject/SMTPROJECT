package com.smt.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import smt.model.tools.Followed;


@Entity
@Table(name = "PAYMENT_DETAILS")
public class PaymentDetail extends Followed{

	private static final long serialVersionUID = -3124188959638817517L;
	private Integer paymentId;
	private Integer number;
	private Integer amount;
	private Boolean payed;
	
	public PaymentDetail() {
	
	}
	
	public PaymentDetail(Integer paymentId, Integer number, Integer amount, Boolean payed) {
		super();
		this.paymentId = paymentId;
		this.number = number;
		this.amount = amount;
		this.payed = payed;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	@Column(name = "PAYMENT_ID")
	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	@Column(name = "NUMBER")
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Column(name = "AMOUNT")
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Column(name = "PAYED")
	public Boolean getPayed() {
		return payed;
	}

	public void setPayed(Boolean payed) {
		this.payed = payed;
	}

}
