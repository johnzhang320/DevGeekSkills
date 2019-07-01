package com.boot.security.auth.model;

public class MatrixForm {
	private String message;
	private String [][] matrix;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String[][] getMatrix() {
		return matrix;
	}
	public void setMatrix(String[][] matrix) {
		this.matrix = matrix;
	}
	
}
