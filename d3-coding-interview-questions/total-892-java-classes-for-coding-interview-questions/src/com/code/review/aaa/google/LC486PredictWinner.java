package com.code.review.aaa.google;
/**
 * Given an array of scores that are non-negative integers. Player 1 picks one of the numbers from either end of the array followed by the player 2 and then player 1 and so on. Each time a player picks a number, that number will not be available for the next player. This continues until all the scores have been chosen. The player with the maximum score wins.

Given an array of scores, predict whether player 1 is the winner. You can assume each player plays to maximize his score.

Example 1:

Input: [1, 5, 2]
Output: False
Explanation: Initially, player 1 can choose between 1 and 2. 
If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will 
be left with 1 (or 2). 
So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. 
Hence, player 1 will never be the winner and you need to return False. 
Example 2:

Input: [1, 5, 233, 7]
Output: True
Explanation: Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.
Note:

1 <= length of the array <= 20.
Any scores in the given array are non-negative integers and will not exceed 10,000,000.
If the scores of both players are equal, then player 1 is still the winner. 
Subscribe to see which companies asked this question.

【题目分析】

给定一个正整数数组，选手1从数组的头部或者尾部选择一个数，选手2从剩下部分的头部或尾部选择一个数，循环往复，直到该数组中的数都被取完。
判断选手1取的数的和值是否大于选手2.

【思路】

两人依次拿，如果Player1赢，则Player1拿的>Player2拿的。我们把Player1拿的视为"+"，把Player2拿的视为"-"，如果最后结果大于等于0
则Player1赢。

因此对于递归来说，beg ~ end的结果为max(nums[beg] - partition(beg + 1, end), nums[end] - partition(beg, end + 1))；
对于非递归来说DP[beg][end]表示即为beg ~ end所取的值的大小（最终与零比较）。

总结：

1. 该问题没有直接比较一个选手所拿元素的和值，而是把问题转换为两个选手所拿元素的差值。这一点很巧妙，是关键的一步。

2. 找出递推表达式：max(nums[beg] - partition(beg + 1, end), nums[end] - partition(beg, end + 1))

3. 通过递推表达式构造递归算法是比较简单的。但是要构造一个非递归的算法难度较大。对于非递归算法，首先在dp中赋初始值，
这是我们解题的第一步。在这个问题中，我们使用一个二位的数组dp来表示nums数组中任意开始和结束位置两人结果的差值。

初始的时候，我们仅仅知道对角线上的值。dp[i][i] = nums[i]. 这一点很好理解。

接下来既然是求任意的开始和结束，对于二维数组，那肯定是一个双层的循环。通过dp中已知的元素和动态规划的递推表达式，
我们就可以构造出我们的需要的结果。非递归的方式是从小问题到大问题的过程。
First assign an initial value to dp,
This is the first step in our problem solving. In this problem, we use a two-dim array dp to represent the difference between the two results at any start and end position in the nums array.

At the beginning, we only know the values on the diagonal. Dp[i][i] = nums[i]. This is a good understanding.

The next step is to ask for arbitrary start and end. For a two-dimensional array, it must be a double-layered loop. Through the known elements of dp and the recursive expression of dynamic programming,
We can construct the results of our needs. The non-recursive approach is the process from small to big.
 */
public class LC486PredictWinner {
	 public boolean PredictTheWinner(int[] nums) {
         if(nums == null || nums.length == 0) return false;
      int n = nums.length;
      int[][] dp = new int[n][n];
      
      for(int i = 0; i < n; i++) {
          dp[i][i] = nums[i];
     }
     
    for(int i = n-2; i >= 0; i--) {
        for(int j = i+1; j < n; j++) {
             dp[i][j] = Math.max(nums[i]-dp[i+1][j], nums[j]-dp[i][j-1]);
         }
     }
     
     return dp[0][n-1] >= 0;
 }
}
