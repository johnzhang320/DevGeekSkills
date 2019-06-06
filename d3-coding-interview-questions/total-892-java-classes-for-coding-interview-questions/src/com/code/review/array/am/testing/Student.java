package com.code.review.array.am.testing;

import java.util.Observable;
import java.util.Observer;

class Student implements Observer {
	  public void update(Observable o, Object arg) {
		  if ( o instanceof ObservableDemo ) {
			  ObservableDemo demo = (ObservableDemo) o;
			  System.out.println("Weather Report Live for student, it's " + demo.getWeather());
		  } else if (o instanceof MessageBoard) {
			  System.out.println("Message board changed: " + arg);
		  }
	  }
}
