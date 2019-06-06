package com.code.review.aaa.google;

import java.util.HashSet;
import java.util.Set;

/**
 * 问题描述：

	Given a robot cleaner in a room modeled as a grid.
	
	Each cell in the grid can be empty or blocked.
	
	The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.
	
	When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.
	
	Design an algorithm to clean the entire room using only the 4 given APIs shown below.
	
	interface Robot {
	  // returns true if next cell is open and robot moves into the cell.
	  // returns false if next cell is obstacle and robot stays on the current cell.
	  boolean move();
	
	  // Robot will stay on the same cell after calling turnLeft/turnRight.
	  // Each turn will be 90 degrees.
	  void turnLeft();
	  void turnRight();
	
	  // Clean the current cell.
	  void clean();
	}
	Example:
	
	Input:
	room = {
	  {1,1,1,1,1,0,1,1},
	  {1,1,1,1,1,0,1,1},
	  {1,0,1,1,1,1,1,1},
	  {0,0,0,1,0,0,0,0},
	  {1,1,1,1,1,1,1,1}
	},
	Start point:
	row = 1,
	col = 3
	
	Explanation:
	All grids in the room are marked by either 0 or 1.
	0 means the cell is blocked, while 1 means the cell is accessible.
	The robot initially starts at the position of row=1, col=3.
	From the top left corner, its position is one row below and three columns right.
	Notes:
	
	The input is only given to initialize the room and the robot's position internally. You must solve this 
	problem "blindfolded". In other words, you must control the robot using only the mentioned 4 
	APIs, without knowing the room layout and the initial robot's position.
	The robot's initial position will always be in an accessible cell.
	The initial direction of the robot will be facing up.
	All accessible cells are connected, which means the all cells marked as 1 will be accessible by the robot.
	Assume all four edges of the grid are all surrounded by wall.
	
	给一个扫地机器人和一个用网格表示的屋子，每个格子是0或者1， 0表示是障碍物，1表示可以通过。机器人有move() ，turnLeft()，turnRight()，
	clean() 4个API，设计一个算法把整个屋子扫一遍。

	注意：房间的布局和机器人的初始位置都是不知道的。机器人的初始位置一定在可以通过的格子里。机器人的初始方向是向上。
	所以可以通过的格子是联通的。假设网格的四周都是墙。
	
	解法：DFS + Backtracking
	
	Different from regular dfs to visit all, the robot move() function need to be called, backtrack needs to
	- move() manually and backtracking path should not be blocked by visited positions
	- IMPORTANT: Mark on the way in using set, but `backtrack directly without re-check against set`
	- Backtrack: turn 2 times to revert, move 1 step, and turn 2 times to revert back.

	- Mark on the way in using set, but backtrack directly without re-check against set
	- Mark coordinate 'x@y'
	- Use degree to mark direction, rather than {0,0,1,-1}
	- Backtrack: turn 2 times to revert, move 1 step, and turn 2 times to revert back.
 */
public class LC489CleanRobot {
	 public void cleanRoom(Robot robot) {
	        // use 'x@y' mark visited nodes, where x,y are integers tracking the coordinates
	        dfs(robot, new HashSet<>(), 0, 0, 0); // 0: up, 90: right, 180: down, 270: left
	    }

	    public void dfs(Robot robot, Set<String> visited, int i, int j, int curDir) {
	    	String key = i + "@" + j;
	    	if (visited.contains(key)) return;
	        visited.add(key);
	    	robot.clean();

	    	for (int n = 0; n < 4; n++) { // 4 directions
	    		if(robot.move()) { // can go directly. Find the (x, y) for the next cell based on current direction
	    			int x = i, y = j;
	    			switch(curDir) {
	    				case 0: // go up, reduce i
	    					x = i-1;
	    					break;
	    				case 90: // go right
	    					y = j+1;
	    					break;
	    				case 180: // go down
	    					x = i+1;
	    					break;
	    				case 270: // go left
	    					y = j-1;
	    					break;
	    				default:
	    					break;
	    			}

	    			dfs(robot, visited, x, y, curDir);
	                backtrack(robot);
	    		} 

	            // turn to next direction
	    		robot.turnRight();
	    		curDir += 90;
	    		curDir %= 360;
	    	}
	    }

	    // go back to the starting position
	    private void backtrack(Robot robot) {
	        robot.turnLeft();
	        robot.turnLeft();
	        robot.move();
	        robot.turnRight();
	        robot.turnRight();
	    }
	    public static void main(String args[]) {
	    	int room[][] = {
	    	  	  {1,1,1,1,1,0,1,1},
	    	  	  {1,1,1,1,1,0,1,1},
	    	  	  {1,0,1,1,1,1,1,1},
	    	  	  {0,0,0,1,0,0,0,0},
	    	  	  {1,1,1,1,1,1,1,1}
	    	  	};
	    	
	    }
}
