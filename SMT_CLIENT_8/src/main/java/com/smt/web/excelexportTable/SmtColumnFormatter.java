package com.smt.web.excelexportTable;


import java.io.Serializable;

public abstract interface SmtColumnFormatter
  extends Serializable
{
  public abstract Object generateCell(Object paramObject1, Object paramObject2, Object paramObject3);
}