package mazegenerator;

/**
 * Generates a maze via random depth-first search.
 * @author Josh
 */
public class MazeGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Maze maze = new Maze(10,10);
        maze.printMaze();
        
    }
    
}
