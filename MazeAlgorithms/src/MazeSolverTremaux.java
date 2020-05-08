import java.util.Objects;
import java.util.Vector;

public class MazeSolverTremaux {

    int moves = 0;
    protected Location[][] maze;
    Vector<Coordinate> visited;

    MazeSolverTremaux(Maze m){
        maze = m.getMaze();
        visited = new Vector<>();

    }



    public void solve(boolean animate){
        maze[0][1].setEntrance(true);
        maze[maze.length - 1][maze.length -2].setExit(true);
        int row = 0;
        int col = 1;
        new Coordinate(0, 1);
        Coordinate previous = new Coordinate(0, 1);
        Coordinate next;
        int count = 0;
        while(!maze[row][col].isExit() && count < 10 ){ //checking to see if isExit is true
            Coordinate current = new Coordinate(row, col);
            visited.add(current); //adding current spot to visited list
            maze[row][col].visited = true;
            maze[row][col].visitedx ++;
            print(row, col);
            maze[row][col].setRoutes(maze);

            next = makeMove(current, previous);
            System.out.println("Moving to " + next.getCol() + "," + next.getRow());

            previous = current;
            current = next;

            row = current.getRow();
            col = current.getCol();

            System.out.println();
            moves ++;
            if(animate){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        print(row, col);
        System.out.println("Moves: " + moves);
    }

    public Coordinate makeMove(Coordinate current, Coordinate previous) {
        int row = current.getRow();
        int col = current.getCol();

        int prevRow = previous.getRow();
        int prevCol = previous.getCol();
        String direction = "1 option";

        Coordinate moveTo = new Coordinate(0, 0);
        if (maze[row][col].getOptions() == 1) { //1 option
            //up
            if (maze[row][col].routes[0]) {
                moveTo = new Coordinate(row - 1, col);

                if (moveTo.getCol() == prevCol && moveTo.getRow() == prevRow) {
                    maze[row][col].setAccessible(false);
                    maze[row][col].deadend = true;
                    maze[prevRow][prevCol].deadend = true;


                }
            }
            //right
            else if (maze[row][col].routes[1]) {
                moveTo = new Coordinate(current.getRow(), current.getCol() + 1);
                if (moveTo.getCol() == prevCol && moveTo.getRow() == prevRow) {
                    maze[row][col].setAccessible(false);
                    maze[row][col].deadend = true;
                    maze[prevRow][prevCol].deadend = true;
                }
            }
            //down
            else if (maze[row][col].routes[2]) {
                moveTo = new Coordinate(current.getRow() + 1, current.getCol());
                if (moveTo.getCol() == prevCol && moveTo.getRow() == prevRow) {
                    maze[row][col].setAccessible(false);
                    maze[row][col].deadend = true;
                    maze[prevRow][prevCol].deadend = true;
                }
            }
            //left
            else if (maze[row][col].routes[3]) {
                moveTo = new Coordinate(current.getRow(), current.getCol() - 1);
                if (moveTo.getCol() == prevCol && moveTo.getRow() == prevRow) {
                    maze[row][col].setAccessible(false);
                    maze[row][col].deadend = true;
                    maze[prevRow][prevCol].deadend = true;
                }
            }
            return moveTo;
        }
        else { //options greater than 1
//            System.out.println("> 1 option");

            //up not visited
            if (maze[row][col].routes[0]) {
                moveTo = new Coordinate(row - 1, col);
                direction = "up";
                if (isIn(visited, moveTo) == false) {
//                    System.out.println("Up not visited");
                    return moveTo;
                }
            }
            //right not visited
            if (maze[row][col].routes[1]) {
                moveTo = new Coordinate(row, col + 1);
                direction = "right";
                if (isIn(visited, moveTo) == false) {
//                    System.out.println("Right not visited");
                    return moveTo;
                }
            }
            //down not visited
            if (maze[row][col].routes[2]) {
                moveTo = new Coordinate(row + 1, col);
                direction = "down";
                if (isIn(visited, moveTo) == false) {
//                    System.out.println("Down not visited");
                    return moveTo;
                }
            }
            //left not visited
            if (maze[row][col].routes[3]) {
                moveTo = new Coordinate(row, col - 1);
                direction = "left";
                if (isIn(visited, moveTo) == false) {
//                    System.out.println("Left not visited");
                    return moveTo;
                }
            }
        }
//        System.out.println(direction);
        return moveTo;
    }

    public void print () {
        for (int i = 0; i < maze.length; i++) {
            for (int k = 0; k < maze.length; k++) {
                maze[i][k].print();
            }
            System.out.println();
        }
    }

    public void print (int row, int col) {
        for (int i = 0; i < maze.length; i++) {
            for (int k = 0; k < maze.length; k++) {
                if(i == row && k == col){
                    System.out.print("M");
                }
                else {
                    maze[i][k].print();
                }
            }
            System.out.println();
        }
    }

    boolean isIn(Vector<Coordinate> visited, Coordinate destination){
        for (Coordinate coordinate : visited) {
            if (coordinate.getRow() == destination.getRow() && coordinate.getCol() == destination.getCol()) {
                return true;
            }
        }
        return false;
    }

    public Location[][] getMaze(){
        return maze;
    }

    public static class Coordinate{
        int row;
        int col;

        Coordinate(int r, int c){
            row = r;
            col = c;
        }

        public int getCol() {
            return col;
        }

        public int getRow() {
            return row;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return getRow() == that.getRow() &&
                    getCol() == that.getCol();
        }

        @Override
        public int hashCode() {
            return Objects.hash(getRow(), getCol());
        }

        public String toString(){
            return row + "," + col;
        }
    }


}
