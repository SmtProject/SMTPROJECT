package com.smt.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import smt.model.tools.Followed;

@Entity
@Table(name = "YEAR")
public class Year  extends Followed{
	private static final long serialVersionUID = 2672388471179343489L;
	
	public enum YearStatus{OPENED,CLOSED}
	
	private String Name;
	private Date startDate;
	private Date endDate;
	private String Description;
	private YearStatus yearStatus;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	@Column(name = "NAME")
	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
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
		return Description;
	}


	public void setDescription(String description) {
		Description = description;
	}

	@Column(name = "YEAR_STATUS")
	@Enumerated(EnumType.STRING) 
	public YearStatus getYearStatus() {
		return yearStatus;
	}


	public void setYearStatus(YearStatus yearStatus) {
		this.yearStatus = yearStatus;
	}
	
	

}
