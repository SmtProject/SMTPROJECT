package com.smt.web.excelImportTable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * SMT COPYRIGHT
 */
public class SmtTableHeadDeal {
	public SmtTableHeadDeal() {
	}

	public SmtTableHeadDeal(String dealString) {
		this.DealString(dealString);
		this.DealParam();
	}

	/** Rows Count */
	public int rows = 1;
	/** Cows Count */
	public int cows = 1;
	/** Header Parameters */
	public List<TableHeadParam> tableHeadParams = new ArrayList<TableHeadParam>();
	/** Header Bottom Parameters */
	public List<String> tableHeadBottomParams = new ArrayList<String>();

	/** 1.analytic */
	public void DealString(String head) {

		// TODO 1.1 Scanning header string, get all the character information,
		// table bottom line character information
		StringTokenizer stk = new StringTokenizer(head, ",{}", true);
		String stkTemp = null;

		int rowsTemp = 1;

		String stkLast = null;

		while (stk.hasMoreElements()) {
			stkTemp = stk.nextToken();
			if (stkTemp.equals("{")) {
				rowsTemp++;
				rows = (rows > rowsTemp) ? rows : rowsTemp;
			} else if (stkTemp.equals("}")) {
				rowsTemp--;
			} else if (stkTemp.equals(",")) {
				cows++;
			} else {
				tableHeadParams.add(new TableHeadParam(stkTemp));
			}
			if (stkTemp.equals("}") && !stkLast.equals("}")) {
				tableHeadBottomParams.add(stkLast);
			} else if (stkTemp.equals(",") && !stkLast.equals("}")) {
				tableHeadBottomParams.add(stkLast);
			}
			stkLast = new String(stkTemp);
		}
		if (!stkLast.equals("}")) {
			tableHeadBottomParams.add(stkLast);
		}

		// TODO 1.2 Scanning header string, make sure all the character
		// information - > long, wide, top left corner coordinates
		StringTokenizer stkForCoord = new StringTokenizer(head, ",{}", true);
		String stkTempForLength = null;

		int leftPointTemp = 0;
		int greadForLineNumber = 1;
		List<TableHeadParam> tableHeadParamsForLength = new ArrayList<TableHeadParam>();
		tableHeadParams.get(0).setLeftPoint(0);
		for (int i = 0; stkForCoord.hasMoreElements();) {
			stkTempForLength = stkForCoord.nextToken();
			if (stkTempForLength.equals("{")) {

				greadForLineNumber++;

				tableHeadParams.get(i).setLeftPoint(leftPointTemp);

				TableHeadParam thpForLength = tableHeadParams.get(i - 1);
				tableHeadParamsForLength.add(thpForLength);
			}
			if (stkTempForLength.equals("}")) {

				greadForLineNumber--;

				tableHeadParamsForLength.remove(tableHeadParamsForLength.size() - 1);
			}
			if (stkTempForLength.equals(",")) {

				leftPointTemp++;
				tableHeadParams.get(i).setLeftPoint(leftPointTemp);

				for (Iterator<TableHeadParam> iterator = tableHeadParamsForLength.iterator(); iterator.hasNext();) {
					TableHeadParam tableHeadParam = iterator.next();
					tableHeadParam.stack++;
					tableHeadParam.setLength(tableHeadParam.stack);
				}
			}
			if (!stkTempForLength.equals("{") && !stkTempForLength.equals("}") && !stkTempForLength.equals(",")) {
				tableHeadParams.get(i).setLineNumber(rows - greadForLineNumber);

				if ((i < tableHeadParams.size()) && (i - 1 >= 0)
						&& tableHeadParams.get(i).getLeftPoint() != tableHeadParams.get(i - 1).getLeftPoint()) {
					tableHeadParams.get(i - 1).setHeight(tableHeadParams.get(i - 1).getLineNumber());
					if (i == tableHeadParams.size() - 1) {
						tableHeadParams.get(i).setHeight(tableHeadParams.get(i).getLineNumber());
					}
				} else if ((i < tableHeadParams.size()) && (i - 1 >= 0)) {
					tableHeadParams.get(i - 1).setHeight(0);
					if (i == tableHeadParams.size() - 1) {
						tableHeadParams.get(i).setHeight(0);
					}
				}

				i++;

			}
		}
	}

	/** 2.Deal */
	public void DealParam() {
		for (TableHeadParam thp : tableHeadParams) {
			thp.setFirstCow(thp.getLeftPoint());
			thp.setFirstRow(rows - 1 - thp.getLineNumber());
			thp.setLastCow(thp.getLeftPoint() + thp.getLength() - 1);
			thp.setLastRow(rows - thp.getLineNumber() + thp.getHeight() - 1);
		}
	}

	/** 1+2 Analysis and processing. Header string */
	public void deal(String deal) {
		this.DealString(deal);
		this.DealParam();
	}

	public static String chooseDealString(String[] singleStrings, String multiString) {
		String singleString = new String();
		for (String ss : singleStrings) {
			singleString += ss + ",";
		}
		singleString = singleString.substring(0, singleString.length() - 1);
		if (multiString == null || multiString.equals("") || multiString.equals("OneLine")) {
			return singleString;
		} else {
			return multiString;
		}
	}

	public static String chooseDealString(String[] singleStrings) {
		String singleString = new String();
		for (String ss : singleStrings) {
			singleString += ss + ",";
		}
		singleString = singleString.substring(0, singleString.length() - 1);
		return singleString;
	}

}

class TableHeadParam {

	public TableHeadParam() {
		super();
	}

	public TableHeadParam(String value) {
		this.value = value;
		this.firstRow = 0;
		this.lastRow = 0;
		this.firstCow = 0;
		this.lastCow = 0;
	}

	public TableHeadParam(String value, int firstRow, int lastRow, int firstCow, int lastCow) {
		this.value = value;
		this.firstRow = firstRow;
		this.lastRow = lastRow;
		this.firstCow = firstCow;
		this.lastCow = lastCow;
	}

	private String value = "";
	private int firstRow = 0;
	private int lastRow = 0;
	private int firstCow = 0;
	private int lastCow = 0;
	public int stack = 1;

	/** 辅助属性 */
	/** Auxiliary attributes */
	private int length = 1;
	private int leftPoint = 0;
	private int height = 0;
	private int lineNumber = 0;

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getLeftPoint() {
		return leftPoint;
	}

	public void setLeftPoint(int leftPoint) {
		this.leftPoint = leftPoint;
	}

	@Override
	public String toString() {
		return this.value + " , fc: " + this.firstCow + " , fr: " + this.firstRow + " , lc: " + this.lastCow + " , lr: "
				+ this.lastRow + " , length: " + this.length + " , leftPoint: " + this.leftPoint + " , height: "
				+ this.height + " , lineNumber: " + this.lineNumber;
	}

	public int getFirstRow() {
		return firstRow;
	}

	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}

	public int getLastRow() {
		return lastRow;
	}

	public void setLastRow(int lastRow) {
		this.lastRow = lastRow;
	}

	public int getFirstCow() {
		return firstCow;
	}

	public void setFirstCow(int firstCow) {
		this.firstCow = firstCow;
	}

	public int getLastCow() {
		return lastCow;
	}

	public void setLastCow(int lastCow) {
		this.lastCow = lastCow;
	}
}
