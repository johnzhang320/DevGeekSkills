package com.code.review.aaa.google;

import java.util.PriorityQueue;

public class LC505RollingMazeII {
/**
 * 505. The Maze II 
  
	Average Rating: 5 (11 votes)
	
	May 23, 2017  |  15.1K views
	There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by 
	rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball
	 stops, it could choose the next direction.
	
	Given the ball's start position, the destination and the maze, find the shortest distance for
	 the ball to stop at the destination. The distance is defined by the number of empty spaces 
	 traveled by the ball from the start position (excluded) to the destination (included). If 
	 the ball cannot stop at the destination, return -1.
	
	The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. 
	You may assume that the borders of the maze are all walls. The start and destination coordinates
	 are represented by row and column indexes.
	
	 
	
	Example 1:
	
	Input 1: a maze represented by a 2D array
	
	0 0 1 0 0
	0 0 0 0 0
	0 0 0 1 0
	1 1 0 1 1
	0 0 0 0 0
	
	Input 2: start coordinate (rowStart, colStart) = (0, 4)
	Input 3: destination coordinate (rowDest, colDest) = (4, 4)
	
	Output: 12
	
	Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
	             The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.
	Note:

	There is only one ball and one destination in the maze.
	Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
	The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
	The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.             
 * 
 */
	
/**
 *   这道题是之前那道The Maze的拓展，那道题只让我们判断能不能在终点位置停下，而这道题让我们求出到达终点的最少步数。其实本质都是一样的，
 *   难点还是在于对于一滚到底的实现方法，唯一不同的是，这里我们用一个二位数组dists，其中dists[i][j]表示到达(i,j)这个位置时需要的
 *   最小步数，我们都初始化为整型最大值，在后在遍历的过程中不断用较小值来更新每个位置的步数值，最后我们来看终点位置的步数值，
 *   如果还是整型最大值的话，说明没法在终点处停下来，返回-1，否则就返回步数值。注意在压入栈的时候，我们对x和y进行了判断，
 *   只有当其不是终点的时候才压入栈，这样是做了优化，因为如果小球已经滚到终点了，我们就不要让它再滚了，就不把终点位置压入栈，
 *   免得它还滚，参见代码如下：
 * 
 */
/**
 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.
The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.
We need to use PriorityQueue instead of standard queue, and record the minimal length of each point.
Why using PriorityQueue?
We can consider this question as a shortest-route graph problem, that is, each stoppable point is a vertical, where every possible path between two nodes is an edge.
In this way, we can using Dijkstra algorithm to solve this problem, and that's why we use PriorityQueue.
 * 
 */
	
	public class Position implements Comparable<Position>
    {
        int distance;
        int x;
        int y;
        public Position(int x, int y, int d)
        {
            this.x=x;
            this.y=y;
            this.distance=d;
        }
        
        public int compareTo(Position o)
        {
            return this.distance-o.distance;
        }
    }
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        for(int i=0;i<maze.length;i++)
            for(int j=0;j<maze[0].length;j++)
              maze[i][j]=maze[i][j]==0 ? Integer.MAX_VALUE :-1;
        return bfs(maze,start,destination);
    }

    public int bfs(int[][] maze, int [] start, int [] destination)
    {
        int[][] dirs = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        PriorityQueue<Position> queue = new PriorityQueue<>();
        Position startPos = new Position(start[0],start[1],0);
        maze[start[0]][start[1]]=0;
        queue.offer(startPos);
        while(!queue.isEmpty())
        {
            Position cur = queue.poll();
            for(int[] dir : dirs)
            {
                int distance=0;
                int x = cur.x+dir[0];
                int y=cur.y+dir[1];
                while(x>=0 && x<maze.length && y>=0 && y<maze[0].length && maze[x][y]!=-1)
                {
                    x+=dir[0];
                    y+=dir[1];
                    distance++;
                }
                x-=dir[0];
                y-=dir[1];
                
                if(maze[x][y]<=cur.distance+distance) continue;
                maze[x][y]=cur.distance+distance;
                if(x==destination[0] && y==destination[1]) return maze[x][y];
                queue.offer(new Position(x,y,cur.distance+distance));
            }
        }
        return -1;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
