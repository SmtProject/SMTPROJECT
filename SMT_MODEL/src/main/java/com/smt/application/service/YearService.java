package com.smt.application.service;

import java.util.List;

import javax.xml.bind.ValidationException;

import com.smt.data.entity.Year;

public interface YearService {

	public Year getCurrentYear();
	
	public List<Year> getYears();
	
	public Year saveYear(Year year) throws ValidationException;
}
