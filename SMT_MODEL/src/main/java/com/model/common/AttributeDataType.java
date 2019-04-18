package com.model.common;

public enum AttributeDataType {
STRING,DATE,INTEGER,DOUBLE,BOOLEAN,ONE_TO_ONE,ONE_TO_MANY;
	
	
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
	
	public boolean isObject() {
		return this.equals(ONE_TO_ONE) || this.equals(ONE_TO_MANY) ;
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
		case ONE_TO_ONE:
			return "Has One";
		case ONE_TO_MANY:
			return "Has Many";
		default:
			return "";
		}
	}

}
