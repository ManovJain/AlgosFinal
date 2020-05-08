import java.io.*;
import java.util.*;

public class MazeSolver {

    protected int mazeSize;
    protected Location[][] maze;
    private Vector<Location> deadEnds;
    boolean animate = false;

    int moves = 0;

    MazeSolver(Location[][] mazeInput){
        maze = mazeInput;
        mazeSize = maze.length;
        deadEnds = new Vector<>();
    }

    public void solve(boolean a){
        animate = a;
        setDeadEnds();
        fillDeadEnds();
        print();
        System.out.println("Moves: " + moves);
    }

    public void setDeadEnds(){
        maze[0][1].setEntrance(true);
        maze[mazeSize - 1][mazeSize -2].setExit(true);

        for(int i = 0; i < mazeSize; i ++){
            for(int k = 0; k < mazeSize; k ++){
                moves ++;
                if(maze[i][k].isAccessible()){ //checking to see if it is an open spot
                    if(maze[i][k].options == 1){ //checking to see that it is a deadend
                        if(!maze[i][k].isEntrance() && !maze[i][k].isExit()){ //filtering out the entrance and exit
                              deadEnds.add(maze[i][k]); //filling deadEnds
                        }
                    }
                }
            }
        }
    }

    public void fillDeadEnds (){
        int row = 0;
        int col = 0;
        boolean filled = false;


        for(int i = 0; i < deadEnds.size(); i ++) {
            System.out.println("DeadEnds being filled.");
            row = deadEnds.get(i).getY();
            col = deadEnds.get(i).getX();

            Maze m = new Maze(maze);
            smallFill(row, col);
        }
    }

    public void smallFill(int row, int col){
        if(animate)try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
    }
        maze[row][col].setRoutes(maze);//resets routes an options to account for previous change
        print();
        System.out.println();
        moves ++;
        if(maze[row][col].getOptions() < 2) {
            maze[row][col].setAccessible(false); //no longer accessible

            //up
            if (maze[row][col].routes[0]) {
                smallFill(row - 1, col);
            }
            //right
            else  if (maze[row][col].routes[1]) {
                smallFill(row, col + 1);
            }
            //down
            else  if (maze[row][col].routes[2]) {
                smallFill(row + 1, col);
            }
            //left
            else  if (maze[row][col].routes[3]) {
                smallFill(row, col - 1);
            }
        }



    }

    public void print(){
        for(int i = 0; i < mazeSize; i ++){
            for(int k = 0; k < mazeSize; k ++){
                maze[i][k].print();
            }
            System.out.println();
        }
    }

    public Location[][] getMaze() {
        return maze;
    }


}
