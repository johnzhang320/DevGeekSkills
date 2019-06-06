package com.design.pattern.facade;

public class FacadePatternDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  ShapeMaker shapeMaker = new ShapeMaker();

	      shapeMaker.drawCircle();
	      shapeMaker.drawRectangle();
	      shapeMaker.drawSquare();	
	}

}
