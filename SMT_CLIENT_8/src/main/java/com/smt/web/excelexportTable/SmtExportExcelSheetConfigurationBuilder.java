package com.smt.web.excelexportTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;











public class SmtExportExcelSheetConfigurationBuilder
  implements Cloneable
{
  protected SmtExportExcelSheetConfigurationBuilder self;
  protected Integer value$defaultSheetRowNum$java$lang$Integer;
  protected boolean isSet$defaultSheetRowNum$java$lang$Integer = false;
  
  protected XSSFCellStyle value$additionalHeaderCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle;
  protected boolean isSet$additionalHeaderCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = false;
  
  protected Boolean value$isDefaultGeneratedByRequired$java$lang$Boolean;
  protected boolean isSet$isDefaultGeneratedByRequired$java$lang$Boolean = false;
  
  protected Boolean value$isHeaderSectionRequired$java$lang$Boolean;
  protected boolean isSet$isHeaderSectionRequired$java$lang$Boolean = false;
  
  protected Integer[] value$columnForTitleRegion$java$lang$Integer$;
  protected boolean isSet$columnForTitleRegion$java$lang$Integer$ = false;
  
  protected Boolean value$isHeaderSectionAdded$java$lang$Boolean;
  protected boolean isSet$isHeaderSectionAdded$java$lang$Boolean = false;
  
  protected XSSFCellStyle value$rAdditionalHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle;
  protected boolean isSet$rAdditionalHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = false;
  
  protected Integer value$noOfColumnsInAddHeader$java$lang$Integer;
  protected boolean isSet$noOfColumnsInAddHeader$java$lang$Integer = false;
  
  protected XSSFCellStyle value$generatedByStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle;
  protected boolean isSet$generatedByStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = false;
  
  protected Integer value$headerCaptionStartCol$java$lang$Integer;
  protected boolean isSet$headerCaptionStartCol$java$lang$Integer = false;
  
  protected Integer value$noOfColumnsInHeader$java$lang$Integer;
  protected boolean isSet$noOfColumnsInHeader$java$lang$Integer = false;
  
  protected Boolean value$isTotalRowRequired$java$lang$Boolean;
  protected boolean isSet$isTotalRowRequired$java$lang$Boolean = false;
  
  protected XSSFCellStyle value$headerCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle;
  protected boolean isSet$headerCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = false;
  
  protected String value$reportTitle$java$lang$String;
  protected boolean isSet$reportTitle$java$lang$String = false;
  
  protected String value$dateFormat$java$lang$String;
  protected boolean isSet$dateFormat$java$lang$String = false;
  
  protected Sheet value$sheet$org$apache$poi$ss$usermodel$Sheet;
  protected boolean isSet$sheet$org$apache$poi$ss$usermodel$Sheet = false;
  
  protected XSSFCellStyle value$rGeneratedByStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle;
  protected boolean isSet$rGeneratedByStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = false;
  
  protected Integer value$headerValueStartCol$java$lang$Integer;
  protected boolean isSet$headerValueStartCol$java$lang$Integer = false;
  
  protected XSSFCellStyle value$headerValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle;
  protected boolean isSet$headerValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = false;
  
  protected XSSFCellStyle value$rHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle;
  protected boolean isSet$rHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = false;
  
  protected Boolean value$isDefaultSheetTitleRequired$java$lang$Boolean;
  protected boolean isSet$isDefaultSheetTitleRequired$java$lang$Boolean = false;
  
  protected HashMap<String, String> value$additionalHeaderInfo$java$util$HashMap;
  protected boolean isSet$additionalHeaderInfo$java$util$HashMap = false;
  
  protected String value$loggerInfoRowContent$java$lang$String;
  protected boolean isSet$loggerInfoRowContent$java$lang$String = false;
  
  protected XSSFCellStyle value$rAdditionalHeaderCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle;
  protected boolean isSet$rAdditionalHeaderCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = false;
  
  protected Integer value$noOfColumnsToMergeInAddHeaderValue$java$lang$Integer;
  protected boolean isSet$noOfColumnsToMergeInAddHeaderValue$java$lang$Integer = false;
  
  protected List<SmtExportExcelComponentConfiguration> value$componentConfigs$java$util$ArrayList;
  protected boolean isSet$componentConfigs$java$util$ArrayList = false;
  
  protected XSSFCellStyle value$reportTitleStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle;
  protected boolean isSet$reportTitleStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = false;
  
  protected XSSFCellStyle value$rReportTitleStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle;
  protected boolean isSet$rReportTitleStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = false;
  
  protected Integer[] value$columnForGeneratedByRegion$java$lang$Integer$;
  protected boolean isSet$columnForGeneratedByRegion$java$lang$Integer$ = false;
  
  protected String value$reportTitleRowContent$java$lang$String;
  protected boolean isSet$reportTitleRowContent$java$lang$String = false;
  
  protected XSSFCellStyle value$additionalHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle;
  protected boolean isSet$additionalHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = false;
  
  protected XSSFCellStyle value$rHeaderCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle;
  protected boolean isSet$rHeaderCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = false;
  
  protected String value$sheetName$java$lang$String;
  protected boolean isSet$sheetName$java$lang$String = false;
  




  public SmtExportExcelSheetConfigurationBuilder()
  {
    self = this;
  }
  




  public SmtExportExcelSheetConfigurationBuilder withDefaultSheetRowNum(Integer value)
  {
    value$defaultSheetRowNum$java$lang$Integer = value;
    isSet$defaultSheetRowNum$java$lang$Integer = true;
    return self;
  }
  



  public SmtExportExcelSheetConfigurationBuilder withAdditionalHeaderCaptionStyle(XSSFCellStyle value)
  {
    value$additionalHeaderCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = value;
    isSet$additionalHeaderCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = true;
    return self;
  }
  



  public SmtExportExcelSheetConfigurationBuilder withIsDefaultGeneratedByRequired(Boolean value)
  {
    value$isDefaultGeneratedByRequired$java$lang$Boolean = value;
    isSet$isDefaultGeneratedByRequired$java$lang$Boolean = true;
    return self;
  }
  



  public SmtExportExcelSheetConfigurationBuilder withIsHeaderSectionRequired(Boolean value)
  {
    value$isHeaderSectionRequired$java$lang$Boolean = value;
    isSet$isHeaderSectionRequired$java$lang$Boolean = true;
    return self;
  }
  



  public SmtExportExcelSheetConfigurationBuilder withColumnForTitleRegion(Integer[] value)
  {
    value$columnForTitleRegion$java$lang$Integer$ = value;
    isSet$columnForTitleRegion$java$lang$Integer$ = true;
    return self;
  }
  



  public SmtExportExcelSheetConfigurationBuilder withIsHeaderSectionAdded(Boolean value)
  {
    value$isHeaderSectionAdded$java$lang$Boolean = value;
    isSet$isHeaderSectionAdded$java$lang$Boolean = true;
    return self;
  }
  



  public SmtExportExcelSheetConfigurationBuilder withRAdditionalHeaderValueStyle(XSSFCellStyle value)
  {
    value$rAdditionalHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = value;
    isSet$rAdditionalHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = true;
    return self;
  }
  



  public SmtExportExcelSheetConfigurationBuilder withNoOfColumnsInAddHeader(Integer value)
  {
    value$noOfColumnsInAddHeader$java$lang$Integer = value;
    isSet$noOfColumnsInAddHeader$java$lang$Integer = true;
    return self;
  }
  



  public SmtExportExcelSheetConfigurationBuilder withGeneratedByStyle(XSSFCellStyle value)
  {
    value$generatedByStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = value;
    isSet$generatedByStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = true;
    return self;
  }
  



  public SmtExportExcelSheetConfigurationBuilder withHeaderCaptionStartCol(Integer value)
  {
    value$headerCaptionStartCol$java$lang$Integer = value;
    isSet$headerCaptionStartCol$java$lang$Integer = true;
    return self;
  }
  



  public SmtExportExcelSheetConfigurationBuilder withNoOfColumnsInHeader(Integer value)
  {
    value$noOfColumnsInHeader$java$lang$Integer = value;
    isSet$noOfColumnsInHeader$java$lang$Integer = true;
    return self;
  }
  



  public SmtExportExcelSheetConfigurationBuilder withIsTotalRowRequired(Boolean value)
  {
    value$isTotalRowRequired$java$lang$Boolean = value;
    isSet$isTotalRowRequired$java$lang$Boolean = true;
    return self;
  }
  



  public SmtExportExcelSheetConfigurationBuilder withHeaderCaptionStyle(XSSFCellStyle value)
  {
    value$headerCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = value;
    isSet$headerCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = true;
    return self;
  }
  



  public SmtExportExcelSheetConfigurationBuilder withReportTitle(String value)
  {
    value$reportTitle$java$lang$String = value;
    isSet$reportTitle$java$lang$String = true;
    return self;
  }
  



  public SmtExportExcelSheetConfigurationBuilder withDateFormat(String value)
  {
    value$dateFormat$java$lang$String = value;
    isSet$dateFormat$java$lang$String = true;
    return self;
  }
  



  public SmtExportExcelSheetConfigurationBuilder withSheet(Sheet value)
  {
    value$sheet$org$apache$poi$ss$usermodel$Sheet = value;
    isSet$sheet$org$apache$poi$ss$usermodel$Sheet = true;
    return self;
  }
  



  public SmtExportExcelSheetConfigurationBuilder withRGeneratedByStyle(XSSFCellStyle value)
  {
    value$rGeneratedByStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = value;
    isSet$rGeneratedByStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = true;
    return self;
  }
  



  public SmtExportExcelSheetConfigurationBuilder withHeaderValueStartCol(Integer value)
  {
    value$headerValueStartCol$java$lang$Integer = value;
    isSet$headerValueStartCol$java$lang$Integer = true;
    return self;
  }
  



  public SmtExportExcelSheetConfigurationBuilder withHeaderValueStyle(XSSFCellStyle value)
  {
    value$headerValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = value;
    isSet$headerValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = true;
    return self;
  }
  



  public SmtExportExcelSheetConfigurationBuilder withRHeaderValueStyle(XSSFCellStyle value)
  {
    value$rHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = value;
    isSet$rHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = true;
    return self;
  }
  



  public SmtExportExcelSheetConfigurationBuilder withIsDefaultSheetTitleRequired(Boolean value)
  {
    value$isDefaultSheetTitleRequired$java$lang$Boolean = value;
    isSet$isDefaultSheetTitleRequired$java$lang$Boolean = true;
    return self;
  }
  



  public SmtExportExcelSheetConfigurationBuilder withAdditionalHeaderInfo(HashMap<String, String> value)
  {
    value$additionalHeaderInfo$java$util$HashMap = value;
    isSet$additionalHeaderInfo$java$util$HashMap = true;
    return self;
  }
  



  public SmtExportExcelSheetConfigurationBuilder withLoggerInfoRowContent(String value)
  {
    value$loggerInfoRowContent$java$lang$String = value;
    isSet$loggerInfoRowContent$java$lang$String = true;
    return self;
  }
  



  public SmtExportExcelSheetConfigurationBuilder withRAdditionalHeaderCaptionStyle(XSSFCellStyle value)
  {
    value$rAdditionalHeaderCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = value;
    isSet$rAdditionalHeaderCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = true;
    return self;
  }
  



  public SmtExportExcelSheetConfigurationBuilder withNoOfColumnsToMergeInAddHeaderValue(Integer value)
  {
    value$noOfColumnsToMergeInAddHeaderValue$java$lang$Integer = value;
    isSet$noOfColumnsToMergeInAddHeaderValue$java$lang$Integer = true;
    return self;
  }
  



  public SmtExportExcelSheetConfigurationBuilder withComponentConfigs(List<SmtExportExcelComponentConfiguration> value)
  {
    value$componentConfigs$java$util$ArrayList = value;
    isSet$componentConfigs$java$util$ArrayList = true;
    return self;
  }
  



  public SmtExportExcelSheetConfigurationBuilder withReportTitleStyle(XSSFCellStyle value)
  {
    value$reportTitleStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = value;
    isSet$reportTitleStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = true;
    return self;
  }
  



  public SmtExportExcelSheetConfigurationBuilder withRReportTitleStyle(XSSFCellStyle value)
  {
    value$rReportTitleStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = value;
    isSet$rReportTitleStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = true;
    return self;
  }
  



  public SmtExportExcelSheetConfigurationBuilder withColumnForGeneratedByRegion(Integer[] value)
  {
    value$columnForGeneratedByRegion$java$lang$Integer$ = value;
    isSet$columnForGeneratedByRegion$java$lang$Integer$ = true;
    return self;
  }
  



  public SmtExportExcelSheetConfigurationBuilder withReportTitleRowContent(String value)
  {
    value$reportTitleRowContent$java$lang$String = value;
    isSet$reportTitleRowContent$java$lang$String = true;
    return self;
  }
  



  public SmtExportExcelSheetConfigurationBuilder withAdditionalHeaderValueStyle(XSSFCellStyle value)
  {
    value$additionalHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = value;
    isSet$additionalHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = true;
    return self;
  }
  



  public SmtExportExcelSheetConfigurationBuilder withRHeaderCaptionStyle(XSSFCellStyle value)
  {
    value$rHeaderCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = value;
    isSet$rHeaderCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = true;
    return self;
  }
  



  public SmtExportExcelSheetConfigurationBuilder withSheetName(String value)
  {
    value$sheetName$java$lang$String = value;
    isSet$sheetName$java$lang$String = true;
    return self;
  }
  




  public Object clone()
  {
    try
    {
      SmtExportExcelSheetConfigurationBuilder result = (SmtExportExcelSheetConfigurationBuilder)super.clone();
      self = result;
      return result;
    } catch (CloneNotSupportedException e) {
      throw new InternalError(e.getMessage());
    }
  }
  



  public SmtExportExcelSheetConfigurationBuilder but()
  {
    return (SmtExportExcelSheetConfigurationBuilder)clone();
  }
  



  public SmtExportExcelSheetConfiguration build()
  {
	  SmtExportExcelSheetConfiguration result = new SmtExportExcelSheetConfiguration();
    
    if (isSet$additionalHeaderCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle) {
      result.setAdditionalHeaderCaptionStyle(value$additionalHeaderCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle);
    }
    if (isSet$isDefaultGeneratedByRequired$java$lang$Boolean) {
      result.setIsDefaultGeneratedByRequired(value$isDefaultGeneratedByRequired$java$lang$Boolean);
    }
    if (isSet$isHeaderSectionRequired$java$lang$Boolean) {
      result.setIsHeaderSectionRequired(value$isHeaderSectionRequired$java$lang$Boolean);
    }
    if (isSet$columnForTitleRegion$java$lang$Integer$) {
      result.setColumnForTitleRegion(value$columnForTitleRegion$java$lang$Integer$);
    }
    if (isSet$noOfColumnsInAddHeader$java$lang$Integer) {
      result.setNoOfColumnsInAddHeader(value$noOfColumnsInAddHeader$java$lang$Integer);
    }
    if (isSet$headerCaptionStartCol$java$lang$Integer) {
      result.setHeaderCaptionStartCol(value$headerCaptionStartCol$java$lang$Integer);
    }
    if (isSet$noOfColumnsInHeader$java$lang$Integer) {
      result.setNoOfColumnsInHeader(value$noOfColumnsInHeader$java$lang$Integer);
    }
    if (isSet$isTotalRowRequired$java$lang$Boolean) {
      result.setIsTotalRowRequired(value$isTotalRowRequired$java$lang$Boolean);
    }
    if (isSet$headerCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle) {
    }
    if (isSet$reportTitle$java$lang$String) {
      result.setReportTitle(value$reportTitle$java$lang$String);
    }
    if (isSet$dateFormat$java$lang$String) {
      result.setDateFormat(value$dateFormat$java$lang$String);
    }
    if (isSet$sheet$org$apache$poi$ss$usermodel$Sheet) {
    }
    if (isSet$rGeneratedByStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle) {
    }
    if (isSet$headerValueStartCol$java$lang$Integer) {
      result.setHeaderValueStartCol(value$headerValueStartCol$java$lang$Integer);
    }
    if (isSet$headerValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle) {
      result.setHeaderValueStyle(value$headerValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle);
    }
    if (isSet$rHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle) {
      result.setHeaderValueStyle(value$rHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle);
    }
    if (isSet$isDefaultSheetTitleRequired$java$lang$Boolean) {
      result.setIsDefaultSheetTitleRequired(value$isDefaultSheetTitleRequired$java$lang$Boolean);
    }
    if (isSet$additionalHeaderInfo$java$util$HashMap) {
      result.setAdditionalHeaderInfo(value$additionalHeaderInfo$java$util$HashMap);
    }
    if (isSet$loggerInfoRowContent$java$lang$String) {
      result.setLoggerInfoRowContent(value$loggerInfoRowContent$java$lang$String);
    }
    if (isSet$noOfColumnsToMergeInAddHeaderValue$java$lang$Integer) {
      result.setNoOfColumnsToMergeInAddHeaderValue(value$noOfColumnsToMergeInAddHeaderValue$java$lang$Integer);
    }
    if (isSet$componentConfigs$java$util$ArrayList) {
      result.setComponentConfigs(value$componentConfigs$java$util$ArrayList);
    }
    if (isSet$columnForGeneratedByRegion$java$lang$Integer$) {
      result.setColumnForGeneratedByRegion(value$columnForGeneratedByRegion$java$lang$Integer$);
    }
    if (isSet$reportTitleRowContent$java$lang$String) {
      result.setReportTitleRowContent(value$reportTitleRowContent$java$lang$String);
    }
    if (isSet$additionalHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle) {
      result.setAdditionalHeaderValueStyle(value$additionalHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle);
    }
    if (isSet$sheetName$java$lang$String) {
      result.setSheetName(value$sheetName$java$lang$String);
    }
    
    return result;
  }
}
