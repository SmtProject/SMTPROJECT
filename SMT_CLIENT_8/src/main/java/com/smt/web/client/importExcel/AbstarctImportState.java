package com.smt.web.client.importExcel;

import java.util.List;

import com.smt.web.client.toolBox.ProgressWindow;
import com.smt.web.excelImportTable.SmtExcelContext;

public abstract class AbstarctImportState implements ImportState  {

	protected boolean matchHeader(List<String> excelHeader, String[] importTemplateColumns) {
		if( excelHeader ==null || importTemplateColumns ==null ||  excelHeader.size()!=importTemplateColumns.length)
			return false;
		for(int i=0;i<excelHeader.size();i++) {
			if(!excelHeader.get(i).replaceAll("\\s+","").toUpperCase().equals(importTemplateColumns[i].toUpperCase()))
				return false;
		}
		return true;
	}

}
