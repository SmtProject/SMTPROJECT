package com.model.common;

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
