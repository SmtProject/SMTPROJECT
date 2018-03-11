package com.smt.application.service.impl;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.smt.application.service.YearService;
import com.smt.data.entity.QYear;
import com.smt.data.entity.Year;
import com.smt.data.entity.Year.YearStatus;
import com.smt.data.repository.YearRepository;

import smt.model.tools.Followed.FollowedStatus;

public class YearServiceImpl  implements YearService {


	@Autowired
	private YearRepository yearRepository;

	@Override
	public Year getCurrentYear() {
		List<Year>years= Lists.newArrayList(yearRepository.findAll(QYear.year.status.eq(FollowedStatus.ACTIVE).and(QYear.year.yearStatus.eq(YearStatus.OPENED))));
		if(years==null || years.size()==0)
			return null;
		return years.get(years.size()-1);
	}

	@Override
	public List<Year> getYears() {
		return Lists.newArrayList(yearRepository.findAll(QYear.year.status.eq(FollowedStatus.ACTIVE)));
	}

	@Override
	public Year saveYear(Year year) throws ValidationException {
		if(year!=null) {
			_yearValidation(year);
			_validateYearUniqueness(year);
			return yearRepository.save(year);
		}else {
			throw new ValidationException("Empty year Info");
		}
	}

	private void _validateYearUniqueness(Year yearToSave) throws ValidationException{
		if(yearToSave!=null) {
			Year serverYear = yearRepository.findOne(QYear.year.name.equalsIgnoreCase(yearToSave.getName()));
			if(serverYear!=null && !serverYear.getId().equals(yearToSave.getId())) {
				throw new ValidationException("Year Name  Already Exicts");
			}
		}else {
			throw new ValidationException("Empty Year");
		}
	}

	private void _yearValidation(Year yearToSave)throws ValidationException{
		if(yearToSave.getName()==null || yearToSave.getName().isEmpty()) {
			throw new ValidationException("Empty Year Name");
		}
		if(yearToSave.getStartDate()==null ) {
			throw new ValidationException("Empty Start Date");
		}
		if(yearToSave.getEndDate()==null ) {
			throw new ValidationException("Empty End Date");
		}
	}


}
