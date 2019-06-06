package com.email.list.reader;

import java.util.ArrayList;
import java.util.List;

public class EmailDynamicVO {
	private List<String> colList = new ArrayList<String>();
	private int totalCols = 0;

	public void addCol(String colVal) {
		colList.add(colVal);
		totalCols++;
	}
	
	public List<String> getColList() {
		return colList;
	}
	

	public void setColList(List<String> colList) {
		this.colList = colList;
	}

	public int getTotalCols() {
		return totalCols;
	}

	public void setTotalCols(int totalCols) {
		this.totalCols = totalCols;
	}
	
}
