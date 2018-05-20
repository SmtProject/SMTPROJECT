package com.smt.data.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import smt.model.tools.Followed;

@Entity
@Table(name = "PAYMENT")
public class Payment extends Followed {
	private static final long serialVersionUID = 1L;
	private Integer Id;
	private Student student;
	private String name;
	private Integer paymentAmount;
	private Integer paymentDiscount;
	private Integer splitNumber;
	private Integer payedAmount;
	private Date startDate;
	private Date endDate;
	private String description;
	private String paymentStatus;
	private List<PaymentDetail>paymentDetails;
	
	public Payment() {
	}
	public Payment(Student student, String name, Integer paymentAmount, Integer paymentDiscount, Integer splitNumber,
			Integer payedAmount, Date startDate, Date endDate, String description, String paymentStatus) {
		this.student = student;
		this.name = name;
		this.paymentAmount = paymentAmount;
		this.paymentDiscount = paymentDiscount;
		this.splitNumber = splitNumber;
		this.payedAmount = payedAmount;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.paymentStatus = paymentStatus;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="STUDENT_ID")
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PAYMENT_AMOUNT")
	public Integer getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Integer paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	@Column(name = "PAYMENT_DISCOUNT")
	public Integer getPaymentDiscount() {
		return paymentDiscount;
	}

	public void setPaymentDiscount(Integer paymentDiscount) {
		this.paymentDiscount = paymentDiscount;
	}

	@Column(name = "SPLIT_NUMBER")
	public Integer getSplitNumber() {
		return splitNumber;
	}

	public void setSplitNumber(Integer splitNumber) {
		this.splitNumber = splitNumber;
	}

	@Column(name = "PAYED_AMOUNT")
	public Integer getPayedAmount() {
		return payedAmount;
	}

	public void setPayedAmount(Integer payedAmount) {
		this.payedAmount = payedAmount;
	}

	@Column(name = "START_DATE")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "END_DATE")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "PAYMENT_STATUS")
	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	@OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="PAYMENT_ID")
	public List<PaymentDetail> getPaymentDetails() {
		return paymentDetails;
	}
	public void setPaymentDetails(List<PaymentDetail> paymentDetails) {
		this.paymentDetails = paymentDetails;
	}
	
	

}
