package com.design.pattern.decorator;

import com.design.pattern.base.Circle;
import com.design.pattern.base.Rectangle;
import com.design.pattern.base.Shape;

public class DecoratorPatternDemo {
	public static void main(String[] args) {

	      Shape circle = new Circle();

	      Shape redCircle = new ShapeDecorator(new Circle());

	      Shape redRectangle = new ShapeDecorator(new Rectangle());
	      System.out.println("Circle with normal border");
	      circle.draw();

	      System.out.println("\nCircle of red border");
	      redCircle.draw();

	      System.out.println("\nRectangle of red border");
	      redRectangle.draw();
	   }
}
