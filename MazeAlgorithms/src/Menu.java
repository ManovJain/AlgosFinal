import javax.swing.*;
import java.util.Scanner;

public class Menu {

    MazeMaker mm;
    MazeSolver ms;
    MazeSolverTremaux mst;
    MazePainter mp;
    Maze maze;
    Maze solvedMaze;
    JFrame jf = new JFrame();
    boolean show;

    boolean exit = false;
    String[] mazeFiles = new String[]{"mazeText", "mazeText2", "mazeText3", "mazeText4", "mazeText5"};

    public void runMenu(){
        printWelcome();
        while(!exit){
            printMazeSelection();
            int choice = getInput();
            selectMaze(choice);
            printMazeTraversal();
            int choice2 = getInput();
            printMazeAnimate();
            int choice3 = getInput();
            selectAnimate(choice3);
            selectTraversal(choice2);
            exit = true;
        }
    }

    private void printWelcome(){
        System.out.println("------------------------------------------------");
        System.out.println("              Manov's Maze Solver!              ");
        System.out.println();
        System.out.println("------------------------------------------------");
    }




    private void printMazeSelection(){
        System.out.println("Welcome to the Maze Solver!");
        System.out.println("Choose using the numbers!");
        System.out.println("Choose the difficulty of the maze you want to solve.");
        System.out.println("1. Rookie");
        System.out.println("2. Novice");
        System.out.println("3. Intermediate");
        System.out.println("4. Challenging");
        System.out.println("5. Impossible");
        System.out.println("0. Quit");
    }

    private void printMazeTraversal(){
        System.out.println();
        System.out.println("How would you like to solve the maze?");
        System.out.println("1. Trivial");
        System.out.println("2. Trivial with visual");
        System.out.println("3. Complex");
        System.out.println("4. Complex with visual");
        System.out.println("0. Quit");
    }

    private void printMazeAnimate(){
        System.out.println();
        System.out.println("Would you like to see the maze traversal live?");
        System.out.println("1. Yes");
        System.out.println("2. No");
    }

    private int getInput() {
        int choice = -1;
        Scanner kb = new Scanner(System.in);
        while (choice < 0 || choice > 5) {
            try {
                System.out.print("Make your selection");
                choice = Integer.parseInt(kb.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection, please try again.");
            }

        }
        return choice;
    }

    private void selectMaze(int choice){
        mm = new MazeMaker();
        System.out.println();
        switch(choice){
            case 1:
                mm.makeMaze(mazeFiles[0]);
                System.out.println("You chose the rookie maze!");
                maze = new Maze(mm.mazeX);
                break;
            case 2:
                mm.makeMaze(mazeFiles[1]);
                System.out.println("You chose the novice maze!");
                maze = new Maze(mm.mazeX);
                break;
            case 3:
                mm.makeMaze(mazeFiles[2]);
                System.out.println("You chose the intermediate maze!");
                maze = new Maze(mm.mazeX);
                break;
            case 4:
                mm.makeMaze(mazeFiles[3]);
                System.out.println("You chose the challenging maze!");
                maze = new Maze(mm.mazeX);
                break;
            case 5:
                mm.makeMaze(mazeFiles[4]);
                System.out.println("You chose the impossible maze!");
                maze = new Maze(mm.mazeX);
                break;
            case 0:
                exit = true;
                System.out.println("Thank you!");
                break;

        }

        System.out.println();
    }

    private void selectTraversal(int choice){
        System.out.println();
        switch(choice){
            case 1:
                System.out.println("You chose the trivial solution!");
                ms = new MazeSolver(mm.mazeX);
                ms.solve(show);
                exit = true;
                break;
            case 2:
                System.out.println("You chose the trivial solution with a visual!");
                ms = new MazeSolver(mm.mazeX);
                ms.solve(show);
                solvedMaze = new Maze(ms.getMaze());
                jf.setBounds(0, 0, 800, 800);
                jf.setTitle("Manov's maze solver!");
                jf.setResizable(false);
                mp = new MazePainter(solvedMaze);
                jf.add(mp);
                System.out.println("gray = wall, blue = path, red = dead end, yellow = start/end");
                jf.setVisible(true);


                jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                exit = true;
                break;
            case 3:
                System.out.println("You chose the complex solution!");
                mst = new MazeSolverTremaux(maze);
                mst.solve(show);
                exit = true;
                break;
            case 4:
                System.out.println("You chose the complex solution with a visual!");
                mst = new MazeSolverTremaux(maze);
                mst.solve(show);
                solvedMaze = new Maze(mst.getMaze());
                jf.setBounds(0, 0, 800, 800);
                jf.setTitle("Manov's maze solver!");
                jf.setResizable(false);
                mp = new MazePainter(solvedMaze);
                jf.add(mp);
                System.out.println("gray = wall, pink = path, blue = unvisited, green = dead end, yellow = start/end");
                jf.setVisible(true);
                exit = true;
                break;
            case 0:
                exit = true;
                System.out.println("Thank you!");
                break;

        }
        System.out.println();
    }

    private void selectAnimate(int choice){
        switch(choice) {
            case 1:
                show = true;
                break;
            case 2:
                show = false;
                break;
        }
        System.out.println();
    }
}
