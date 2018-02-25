package com.smt.web.excelexportTable;

import java.util.List;

public class SmtExportExcelConfigurationBuilder implements Cloneable {
	protected SmtExportExcelConfigurationBuilder self;
	protected List<SmtExportExcelSheetConfiguration> value$sheetConfigs$java$util$ArrayList;
	protected boolean isSet$sheetConfigs$java$util$ArrayList = false;

	protected String value$exportExtension$java$lang$String;
	protected boolean isSet$exportExtension$java$lang$String = false;

	protected String value$exportFileName$java$lang$String;
	protected boolean isSet$exportFileName$java$lang$String = false;

	protected String value$generatedBy$java$lang$String;
	protected boolean isSet$generatedBy$java$lang$String = false;

	protected ExportType value$exportType$org$vaadin$addons$ExportType;
	protected boolean isSet$exportType$org$vaadin$addons$ExportType = false;

	public SmtExportExcelConfigurationBuilder() {
		self = this;
	}

	public SmtExportExcelConfigurationBuilder withSheetConfigs(List<SmtExportExcelSheetConfiguration> value) {
		value$sheetConfigs$java$util$ArrayList = value;
		isSet$sheetConfigs$java$util$ArrayList = true;
		return self;
	}

	public SmtExportExcelConfigurationBuilder withExportExtension(String value) {
		value$exportExtension$java$lang$String = value;
		isSet$exportExtension$java$lang$String = true;
		return self;
	}

	public SmtExportExcelConfigurationBuilder withExportFileName(String value) {
		value$exportFileName$java$lang$String = value;
		isSet$exportFileName$java$lang$String = true;
		return self;
	}

	public SmtExportExcelConfigurationBuilder withGeneratedBy(String value) {
		value$generatedBy$java$lang$String = value;
		isSet$generatedBy$java$lang$String = true;
		return self;
	}

	public SmtExportExcelConfigurationBuilder withExportType(ExportType value) {
		value$exportType$org$vaadin$addons$ExportType = value;
		isSet$exportType$org$vaadin$addons$ExportType = true;
		return self;
	}

	public Object clone() {
		try {
			SmtExportExcelConfigurationBuilder result = (SmtExportExcelConfigurationBuilder) super.clone();
			self = result;
			return result;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.getMessage());
		}
	}

	public SmtExportExcelConfigurationBuilder but() {
		return (SmtExportExcelConfigurationBuilder) clone();
	}

	public SmtExportExcelConfiguration build() {
		SmtExportExcelConfiguration result = new SmtExportExcelConfiguration();

		if (isSet$sheetConfigs$java$util$ArrayList) {
			result.setSheetConfigs(value$sheetConfigs$java$util$ArrayList);
		}
		if (isSet$exportExtension$java$lang$String) {
			result.setExportExtension(value$exportExtension$java$lang$String);
		}
		if (isSet$exportFileName$java$lang$String) {
			result.setExportFileName(value$exportFileName$java$lang$String);
		}
		if (isSet$generatedBy$java$lang$String) {
			result.setGeneratedBy(value$generatedBy$java$lang$String);
		}
		if (isSet$exportType$org$vaadin$addons$ExportType) {
			result.setExportType(value$exportType$org$vaadin$addons$ExportType);
		}

		return result;
	}
}
