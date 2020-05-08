import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class MazeMaker {

    String[] maze;
    char[][] maze2D;
    String fileName;

    Location[][] mazeXRaw;
    Location[][] mazeX;
    int size;

    MazeMaker(){
        fileName = " ";
    }



    MazeMaker(String filename){
        makeMaze(filename);
    }

    //this constructor was created while referencing code from w3schools.com
    //https://www.w3schools.com/java/java_files_read.asp

   public void makeMaze(String filename) {
        fileName = filename;
        try {
            File myFile = new File(fileName);
            Scanner myReader = new Scanner(myFile);
            size = myReader.nextInt();
            maze = new String[size];
            maze2D = new char[size][size];
            mazeX = new Location[size][size];
            int count = 0;
            myReader.nextLine(); //skips to the next line!
            while (count < size) {
                String data = myReader.nextLine();

                maze[count] = data;
                count++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                maze2D[i][k] = maze[i].charAt(k);
            }
        }
        mazeXMaker();
    }

    char[][] getMaze2D() {
        return maze2D;
    }

    char getMaze2D(int x, int y) {
        return maze2D[x][y];
    }

    public void mazeXMaker() {
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                if (maze2D[i][k] == ' ') {
                    mazeX[i][k] = new Open(k, i);

                }
                else if (maze2D[i][k] == '#') {
                    mazeX[i][k] = new Wall(k, i);
                }
            }
        }

        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                if(mazeX[i][k].isAccessible()){
                    mazeX[i][k].setRoutes(mazeX);
                }
            }
            System.out.println();
        }

        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                maze2D[i][k] = maze[i].charAt(k);
                mazeX[i][k].print();
            }
            System.out.println();
        }

    }

}
