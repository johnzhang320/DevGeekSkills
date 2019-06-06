package com.interview.questions.builders;

public final class NutritionFacts {  // immumtable class must be final and   final memeber variable
	/**
	 *  From Java 1.8 Stream or Spring 4, we can see a lot of instances create flexible without
	 *  replying on multiple overlap constructors and immutable
	 *  This is another builder pattern
	 */
	private final int savingSize;    // private final int must be within final class
	private final int servings;
	private final int calories;
	private final int fat;
	private final int sodium;
	private final int carbonhydrate;
	
	public static class Builder {
		// required parameter;
		private int savingSize;
		private int servings;
		// Optional parameter-- initialized to default values;
		private int calories		=0;
		private int fat				=0;
		private int sodium			=0;	
		private int carbonhydrate	=0;
		public Builder(int savingSize,int servings) {
			this.savingSize = savingSize;
			this.servings = servings;
		}
 		// create optional builders
		public Builder calories(int val) {
			calories = val;
			return this;
		}
		public Builder fat(int val) {
			fat = val;
			return this;
		}
		public Builder sodium(int val) {
			sodium= val;
			return this;
		}
		public Builder carbonhydrate(int val) {
			carbonhydrate = val;
			return this;
		}
		public NutritionFacts build() {
			return new NutritionFacts(this);
		}
	}
	private NutritionFacts(Builder build) {
		savingSize=build.savingSize;
		servings=build.servings;
		calories=build.calories;
		fat=build.fat;
		sodium=build.sodium;
		carbonhydrate=build.carbonhydrate;
	}
	
	@Override
	public String toString() {
		String s= "NutritionFacts [savingSize=" + savingSize + ", servings=" + servings + ", calories=" + calories
				+ ", fat=" + fat + ", sodium=" + sodium + ", carbonhydrate=" + carbonhydrate + "]";
		System.out.println(s);
		return s;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NutritionFacts cocaCola1 = new NutritionFacts.Builder(240, 8).build();
		NutritionFacts cocaCola2 = new NutritionFacts.Builder(240, 8).calories(180).build();
		NutritionFacts cocaCola3 = new NutritionFacts.Builder(240, 8).calories(180).fat(250).build();
		NutritionFacts cocaCola4 = new NutritionFacts.Builder(240, 8).calories(180).fat(250).sodium(35).build();
		NutritionFacts cocaCola5 = new NutritionFacts.Builder(240, 8).calories(180).fat(250).sodium(35).carbonhydrate(27).build();
		
		cocaCola1.toString();
		cocaCola2.toString();
		cocaCola3.toString();
		cocaCola4.toString();
		cocaCola5.toString();
		
	}

}
