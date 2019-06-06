package com.code.review.matrix.mdidle.backtracking;

public class WordSearch {
	/**
	 *  LeetCode – Word Search (Java)
 

		Given a 2D board and a word, find if the word exists in the grid.
		
		The word can be constructed from letters of sequentially adjacent cell, where "adjacent"
		 cells are those horizontally or vertically neighboring. The same letter cell may not be 
		 used more than once.
		
		For example, given board =
		
		[
		  ["ABCE"],
		  ["SFCS"],
		  ["ADEE"]
		]
		
		word = "ABCCED", -> returns true,
		word = "SEE", -> returns true,
		word = "ABCB", -> returns false.
		This is typical backtracking from four directions
	 */
	 public static boolean exist(char[][] board, String word) {
	        if (null==board || board.length==0 ) return false;
	        int m = board.length;
	        int n = board[0].length;
	        boolean result = false;
	        for (int i=0; i<m; i++) {
	            for (int j=0; j <n; j++) {
	                if (dfs(board, word,i,j,0)) {
	                    result = true;
	                }
	            }
	        }
	        return result;
	    }
	    public static boolean dfs(char[][] board, String word, int i, int j, int k) {  // k =0 ~ word.length-1
	        int m = board.length;
	        int n = board[0].length;
	        // check boundary
	        if (i<0 || j<0 || i>=m || j>=n) {
	            return false;
	        }
	        if (board[i][j] == word.charAt(k)) { 
	            char [][] temp = board;
	            board[i][j] = '#';
	            if (k==word.length()-1) {
	                return true;
	            } else if (dfs(board,word,i-1,j,k+1) ||
	                       dfs(board,word,i+1,j,k+1) ||
	                       dfs(board,word,i,j-1,k+1) ||
	                       dfs(board,word,i,j+1,k+1)) {
	                return true;
	            }
	            board = temp;
	        }
	        return false;
	    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] board =
				{
						{'A','B','C','E'},
						{'S','F','C','S'},
						{'A','D','E','E'}
				};
		char[][] board2 =
			{
					{'A','B','C','E'},
					{'S','F','C','S'},
					{'A','D','E','E'}
			};
		char[][] board3 =
			{
					{'A','B','C','E'},
					{'S','F','C','S'},
					{'A','D','E','E'}
			};
		String word1 = "ABCCED";	//, -> returns true,
		String word2 = "SEE";	//, -> returns true,
		String word3 = "ABCB";	//, -> returns false.
	 
		System.out.println("word1:"+exist(board, word1));
		 
		System.out.println("word2:"+exist(board2, word2));
		 
		System.out.println("word3:"+exist(board3, word3));
	}

}
