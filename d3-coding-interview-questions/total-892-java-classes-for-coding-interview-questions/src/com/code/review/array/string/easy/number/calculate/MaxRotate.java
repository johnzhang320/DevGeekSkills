package com.code.review.array.string.easy.number.calculate;
/**
 * 
 * Given an array of n integers, return the maximum PMEAN of all possible array rotations, 
	where PMEAN = the sum of each integer multiplied by its current location (index + 1).
	
	For example:
	
	The PMEANs for every rotation of the array {20, 30, 10} are:
	
	PMEAN1 = (1 * 20) + (2 * 30) + (3 * 10) = 110
	PMEAN2 = (1 * 30) + (2 * 10) + (3 * 20) = 110
	PMEAN3 = (1 * 10) + (2 * 20) + (3 * 30) = 140
	
	The max PMEAN of array {20, 30, 10} is 140.
	
	The question is simple enough. I was able to get a working algorithm quick enough, but I failed to 
	optimize my answer.
	Hint from the interviewer:
	If you have PMEANn, how can you use the result to get PMEANn+1?
 *
 */
public class MaxRotate {

}
