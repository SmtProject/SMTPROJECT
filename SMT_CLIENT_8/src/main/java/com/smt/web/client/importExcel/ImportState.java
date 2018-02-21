package com.smt.web.client.importExcel;

import com.smt.web.excelImportTable.SmtExcelContext;

public interface ImportState {

	public void doAction(SmtExcelContext context);
	
	public String [] getImportTemplateColumns();
}
