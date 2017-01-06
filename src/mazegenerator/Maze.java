package mazegenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A maze class. This maze is represented by a 2-dimensional array. Maze is
 * generated through a random depth-first recursion algorithm.
 * @author Josh
 */
public class Maze {
	private MazeCell[][] maze;
	/**
         * Generates a default maze of size (height x width).
         * Default maze is a grid, where each cell has all four walls.
         * @param height
         * @param width 
         */
	public Maze(int height, int width) {
		maze = new MazeCell[height][width];
                for(int x = 0; x < height; ++x) {
                    for(int y = 0; y < width; ++y) {
                        maze[x][y] = new MazeCell(x,y);
                    }
                }
                randDFS(maze, 0, 0);
	}
	/**
	 * The recursive depth-first search algorithm we are going
	 * to use in our maze generation algorithm. Randomly selects
	 * a neighbor to visit & opens the walls between them.
	 **/
	private static void randDFS(MazeCell[][] myMaze, int row, int col) {
                myMaze[row][col].visited = true;
                List<Neighbor> neighbors = getNeighbors(myMaze, row, col);
		if (neighbors.size() == 0 ) { // base case: no more neighbors .
                    return;
		}
                Collections.shuffle(neighbors);
                for(Neighbor n : neighbors) {
                    if(!n.cell.visited) {
                        openWall(myMaze[row][col], n);
                        randDFS(myMaze, n.row,n.col);
                    }
                }
	}
        /**
         * Removes a wall between a MazeCell & its neighbor. This method
         * must remove 2 walls: the wall in the direction of the neighbor in the
         * current cell & the wall in the opposite direction in the adjacent cell. 
         * @param randNeighbor 
         */
        private static void openWall(MazeCell cell, Neighbor randNeighbor) {
            	if (randNeighbor.dir == 0) { //north neighbor
			cell.northWall = false;
			randNeighbor.cell.southWall = false;
		} else if((randNeighbor.dir == 1)) {
			cell.southWall = false;
			randNeighbor.cell.northWall = false;		
		} else if((randNeighbor.dir == 2)) {
			cell.eastWall = false;
			randNeighbor.cell.westWall = false;		
		} else if((randNeighbor.dir == 3)) {
			cell.westWall = false;
			randNeighbor.cell.eastWall = false;		
		}
        }
        /**
         * Gets the neighbors of a cell denoted by (row, col)
         * @param myMaze
         * @param row
         * @param col
         * @return 
         */
	private static List<Neighbor> getNeighbors(MazeCell[][] myMaze, int row, int col) {
		List<Neighbor> neighbs = new ArrayList<Neighbor>();
		if (row + 1 < myMaze.length ) {
                        Neighbor neighbor = new Neighbor ();
			neighbor.row  = row + 1;
			neighbor.col  = col;
			neighbor.dir  = 1;
			neighbor.cell = myMaze [row + 1] [col];
			neighbs.add(neighbor);
		} 
		if (row - 1 >= 0) {
                    	Neighbor neighbor = new Neighbor ();
			neighbor.row  = row - 1;
			neighbor.col  = col;
			neighbor.dir  = 0;
			neighbor.cell = myMaze[row - 1][col];
			neighbs.add(neighbor);
		}
		if (col + 1 < myMaze[row].length) {
                    	Neighbor neighbor = new Neighbor ();
			neighbor.row  = row;
			neighbor.col  = col + 1;
			neighbor.dir  = 2;
			neighbor.cell = myMaze[row][col + 1];
			neighbs.add(neighbor);
		}
		if (col - 1 >= 0) {
                    	Neighbor neighbor = new Neighbor ();
			neighbor.row  = row;
			neighbor.col  = col - 1;
			neighbor.dir  = 3;
			neighbor.cell = myMaze[row][col - 1];
			neighbs.add(neighbor);
		}
		return neighbs;
	}
        /**
         * Helper class to give us details of a neighbor, along with it's
         * relative position.
         */
	private static class Neighbor {
		public MazeCell cell;
		public int dir; // 0 = N, 1 = S, 2 = E, 3 = W
		public int row;
		public int col;
	}
	private static class MazeCell
	{
            public int row = -1; // the row of the cell in the maze
            public int col = -1; // the col of the cell in the maze
            public boolean northWall = true;
            public boolean southWall = true;
            public boolean eastWall  = true;
            public boolean westWall  = true;
            public boolean visited   = false;
            /**
             * Creates a MazeCell object at the given position.
             * @param row
             * @param col 
             */
            MazeCell(int row, int col) {
                this.row = row;
                this.col = col;
            }
	}
        /**
         * Prints our maze to the console.s
         */
        public void printMaze() {
            System.out.print(" ");
            for(int col = 0; col < 2*maze[0].length - 1; ++col)
                System.out.print("_");
            System.out.println();

            for(int row = 0; row < maze.length; ++row) {
                System.out.print("|");
                for(int col = 0; col < maze[row].length; ++col) {
                    if(maze[row][col].southWall) {
                        System.out.print("_");
                    } else {
                        System.out.print(" ");
                    }
                    if(maze[row][col].eastWall) {
                        System.out.print("|");
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }
}
