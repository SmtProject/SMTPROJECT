package com.script.generator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.model.entity.Attribute;
import com.model.entity.ProjectEntity;

public class EntityScriptGenerator {
	public static final String NEW_LINE = "\n";

	
	public static String generateScript(ProjectEntity projectEntity){
		if(projectEntity==null || projectEntity.getEntityName()==null || projectEntity.getEntityName().isEmpty())
			return  null;
		StringBuffer buffer=new StringBuffer();
		buffer.append("CREATE TABLE "+projectEntity.getEntityName()+NEW_LINE);
		buffer.append("("+NEW_LINE);
		buffer.append("ID BIGINT AUTO_INCREMENT PRIMARY KEY,"+NEW_LINE);
		List<String> generatedDataRows=new ArrayList<>();
		for (Attribute attribute : projectEntity.getAttributes()) {		
			String generateScriptDataRow = generateScriptDataRow( attribute);
			if(generateScriptDataRow!=null)
				generatedDataRows.add(generateScriptDataRow);
		}
		if(generatedDataRows.isEmpty())
			return null;
		buffer.append(StringUtils.join(generatedDataRows,","+NEW_LINE));
		
		String constraits= addConstraits(projectEntity);
		if(constraits!=null)
			buffer.append(","+NEW_LINE+constraits);
		
		buffer.append(NEW_LINE+");");
		return buffer.toString();
	}

	private static String addConstraits(ProjectEntity projectEntity) {
		List<String>constraits=new ArrayList<>();
		for (Attribute dataRow : projectEntity.getAttributes()) {
			if(dataRow!=null && dataRow.getIsUnique())
				constraits.add("CONSTRAINT UK_"+projectEntity.getEntityName()+"_"+dataRow.getEntityName()+" UNIQUE ("+dataRow.getEntityName().toUpperCase()+")");
		}
		if(!constraits.isEmpty())
			return StringUtils.join(constraits,","+NEW_LINE);
		return null;
	}

	private static String generateScriptDataRow( Attribute attribute) {
		if(attribute==null || attribute.getEntityName()==null || attribute.getEntityType()==null || attribute.getIsMandatory()==null || attribute.getIsUnique()==null)
			return null;
		StringBuffer buffer=new StringBuffer();
		buffer.append(attribute.getEntityName().toUpperCase()+" "+attribute.getEntityType().dataBaseName());
		if(attribute.getIsMandatory())
			buffer.append(" NOT NULL");
		return buffer.toString();

	}

}
