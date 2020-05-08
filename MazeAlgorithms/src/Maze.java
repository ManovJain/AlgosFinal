public class Maze {

    private Location[][] maze;
    int size;

    int x;
    int y;

    Maze(){
        maze = new Location[5][5];
        size = maze.length;
        x = 0;
        y = 0;
    }

    Maze(Location[][] mazeInput){
        maze = mazeInput;
        size = maze.length;
        x = 0;
        y = 0;
    }

    Location[][] getMaze(){
        return maze;
    }

    public void print(){
        for(int i = 0; i < size; i ++){
            for(int k = 0; k < size; k ++){
                maze[i][k].print();
            }
            System.out.println();
        }
    }

}
