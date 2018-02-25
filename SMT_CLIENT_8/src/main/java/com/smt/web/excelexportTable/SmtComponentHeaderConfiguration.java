package com.smt.web.excelexportTable;

import com.vaadin.ui.components.grid.HeaderRow;

/*
 * 
 */

import net.karneim.pojobuilder.GeneratePojoBuilder;

/**
 * The Class ComponentHeaderConfiguration is used to configure footers for the
 * Excel component
 *
 * @author Kartik Suba
 */
@GeneratePojoBuilder(intoPackage = "*.builder")
public class SmtComponentHeaderConfiguration extends SmtAbstractComponentHeaderFooterConfiguration<HeaderRow> {

	private boolean autoFilter = false;

	public boolean isAutoFilter() {
		return this.autoFilter;
	}

	public void setAutoFilter(boolean autoFilter) {
		this.autoFilter = autoFilter;
	}

}