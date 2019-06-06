package com.code.review.array.string.hard.print.number.shape;

public class PrintOutPyramid {

	/**
	 *  Figout an algorithm to print out Pyramid as following
	 *            1
	 *          1 2 1 
	 *        1 2 3 2 1
	 *      1 2 3 4 3 2 1
	 *    
	 *    layer number is passed as parameter
	 *    Algorithm: i from layer to 0: , print " " from j=0~2*i, print number +" " from k=1 ~ rowNumber and then m = rowNumber+1 to 1  
	 */
	public static void PrintPyramid(int layer) {
		 int rowNumber = 1;
		 
		 for (int i= layer; i>0; i--) {
			 
			 for (int j=0; j<2*i; j++) {
				 System.out.print(" ");
			 }
			 for (int j=1; j<rowNumber; j++) {
				 System.out.print(j+" ");
			 }
			 for (int j=rowNumber;j>0; j--) {
				 System.out.print(j+" ");
			 }
			 rowNumber++;
			 System.out.println();
				 
		 }
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrintPyramid(10); 
	}

}
