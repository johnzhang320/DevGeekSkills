package com.code.review.algorithm.depth.first.search;

public class WordSearch {
/**
 * Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell,
 where "adjacent" cells are those horizontally or vertically neighboring. 
 The same letter cell may not be used more than once.
 
 For example, given board =

[
  ["ABCE"],
  ["SFCS"],
  ["ADEE"]
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.

 * @param args
 */
	public boolean exist(char[][] board, String word) {
	    int m = board.length;
	    int n = board[0].length;
	 
	    boolean result = false;
	    for(int i=0; i<m; i++){
	        for(int j=0; j<n; j++){
	           if(dfs(board,word,i,j,0)){
	               result = true;
	           }
	        }
	    }
	 
	    return result;
	}
	 
	public boolean dfs(char[][] board, String word, int i, int j, int k){
	    int m = board.length;
	    int n = board[0].length;
	 
	    if(i<0 || j<0 || i>=m || j>=n){
	        return false;
	    }
	 
	    if(board[i][j] == word.charAt(k)){
	        char temp = board[i][j];
	        board[i][j]='#';
	        if(k==word.length()-1){
	            return true;
	        }else if(dfs(board, word, i-1, j, k+1)
	        ||dfs(board, word, i+1, j, k+1)
	        ||dfs(board, word, i, j-1, k+1)
	        ||dfs(board, word, i, j+1, k+1)){
	            return true;
	        }
	        board[i][j]=temp;
	    }
	 
	    return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char borad[][]={
		                {'A','B','C','E'},
		                {'S','F','C','S'},
		                {'A','D','E','E'}
		              };
		WordSearch ref = new WordSearch();
		System.out.println(ref.exist(borad,"ABCCED"));	
		char borad2[][]={
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
              };
		System.out.println(ref.exist(borad2,"SEE"));	
		char borad3[][]={
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
              };
		System.out.println(ref.exist(borad3,"ABCB"));	
		
	}
	
}