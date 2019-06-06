package com.design.pattern.decorator;

import com.design.pattern.base.Shape;
/**
 * 
 * Create abstract decorator class implementing the Shape interface.
   Reference to Shape as well, then decorator can be chosen different shapes 
   but caller format almost same but instantilization
 
 *
 */
public class ShapeDecorator implements Shape {
	protected Shape decoratedShape;

	public ShapeDecorator(Shape decoratedShape){
	   this.decoratedShape = decoratedShape;
	}
	public void draw() {
		// TODO Auto-generated method stub
		decoratedShape.draw();
	}

}
