package com.code.review.array.am.testing;
import java.util.Observable;
public class ObservableDemo extends Observable {
	 
	/**
	 * 

	1. Observable

	The java.util.Observable class is used along with Observer instance to implement the Observer pattern. 
	A class whose changes are to be tracked by the observers, extends the java.util.Observable class.
	 This class has implemented methods for updating/notifying the Observers about the changes made to 
	 the Observable. It also provides method to the Observer instances to hook on with itself, or unhook. 
	 Few methods in the java.util.Observable class are:
	•public void addObserver(Observer o) Add an Observer.
	•public void deleteObserver(Observer o) Delete an Observer.
	•public void notifyObservers() notify observers of changes.

	2. Observer Pattern

	The Observer and Observable are used to implement the Observer Pattern in Java. This pattern is 
	used when a multiple number of instances called Observers are listening to changes to a particular 
	class called Observable. For example, if the underlying data-source changes, all the views using that
	 data-source should reflect the changes.

	Here is a simple weather reporting system, which tries to implement the Observer Pattern using the 
	Observer interface and Observable class,

	 */
	 
		private String weather;

		public ObservableDemo(String weather)
	    {
				this.weather = weather;
	    }

		public String getWeather()
	    {
		    return weather;
	    }

		public void setWeather(String weather)
	    {
		    this.weather = weather;
		    setChanged();
		    notifyObservers();
	    }

 

}
