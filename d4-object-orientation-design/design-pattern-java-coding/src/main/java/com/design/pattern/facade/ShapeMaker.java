package com.design.pattern.facade;

import com.design.pattern.base.Circle;
import com.design.pattern.base.Rectangle;
import com.design.pattern.base.Shape;
import com.design.pattern.base.Square;

public class ShapeMaker {
	  private Shape circle;
	   private Shape rectangle;
	   private Shape square;

	   public ShapeMaker() {
	      circle = new Circle();
	      rectangle = new Rectangle();
	      square = new Square();
	   }

	   public void drawCircle(){
	      circle.draw();
	   }
	   public void drawRectangle(){
	      rectangle.draw();
	   }
	   public void drawSquare(){
	      square.draw();
	   }
}
