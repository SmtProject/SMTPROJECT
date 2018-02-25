/*
 * 
 */
package com.smt.web.excelexportTable;

import java.util.List;

/**
 * The Class ComponentHeaderConfiguration is used to configure footers for the
 * Excel component
 *
 * @author Kartik Suba
 */
public abstract class SmtAbstractComponentHeaderFooterConfiguration<ROWTYPE> {

	/** The row. */
	private ROWTYPE row;

	/** The column keys. */
	private String[] columnKeys;

	/** The merged cells. */
	private List<SmtMergedCell> mergedCells;

	/** The is default. */
	private Boolean defaultConfig;

	public ROWTYPE getRow() {
		return this.row;
	}

	public void setRow(ROWTYPE row) {
		this.row = row;
	}

	public String[] getColumnKeys() {
		return this.columnKeys;
	}

	public void setColumnKeys(String[] columnKeys) {
		this.columnKeys = columnKeys;
	}

	public List<SmtMergedCell> getMergedCells() {
		return this.mergedCells;
	}

	public void setMergedCells(List<SmtMergedCell> mergedCells) {
		this.mergedCells = mergedCells;
	}

	public Boolean getDefaultConfig() {
		return this.defaultConfig;
	}

	public void setDefaultConfig(Boolean defaultConfig) {
		this.defaultConfig = defaultConfig;
	}

}