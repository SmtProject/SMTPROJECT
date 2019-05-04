package com.model.common;

import java.time.ZoneId;

public enum AttributeDataType {
	STRING,DATE,INTEGER,DOUBLE,BOOLEAN;


	public String dataBaseName() {
		switch (this){
		case STRING:
			return "VARCHAR (255)";
		case DATE:
			return "DATE";
		case INTEGER:
			return "INT (10)";
		case DOUBLE:
			return "DOUBLE";
		case BOOLEAN:
			return "INT (1)";
		default:
			return "";
		}
	}

	public String getComponent() {
		switch (this){
		case STRING:
			return "TextField";
		case DATE:
			return "DateField";
		case INTEGER:
			return "TextField";
		case DOUBLE:
			return "TextField";
		case BOOLEAN:
			return "CheckBox";
		default:
			return "";
		}
	}
	public String getBinder(String className,String attributeName,String formatedEntityName) {
		switch (this){
		case INTEGER:
			return "	binder.forField("+attributeName+").withNullRepresentation(\"0\").withConverter(new StringToIntegerConverter(\"Please enter a number\")).bind("+className+"::get"+formatedEntityName+", "+className+"::set"+formatedEntityName+");\n";
		case DOUBLE:
			return "	binder.forField("+attributeName+").withNullRepresentation(\"0\").withConverter(new StringToDoubleConverter(\"Please enter a number\")).bind("+className+"::get"+formatedEntityName+", "+className+"::set"+formatedEntityName+");\n";
		case DATE:
			return "	binder.forField("+attributeName+").withConverter(new LocalDateToDateConverter(ZoneId.systemDefault())).bind("+className+"::get"+formatedEntityName+", "+className+"::set"+formatedEntityName+");\n";
		default:
			return null;
		}
	}

	@Override
	public String toString() {
		switch (this){
		case STRING:
			return "String";
		case DATE:
			return "Date";
		case INTEGER:
			return "Integer";
		case DOUBLE:
			return "Double";
		case BOOLEAN:
			return "Boolean";
		default:
			return "";
		}
	}

}
