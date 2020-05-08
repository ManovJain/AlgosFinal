import javax.swing.*;
import java.awt.*;

public class Location {
    private int x; //x-coordinate
    private int y; //y-coordinate
    protected boolean[] routes; //0 is up, 1 is right, 2 is down, 3 is left
    protected int options;
    boolean exit = false;
    boolean entrance = false;

    boolean visited = false;
    int visitedx;
    boolean deadend = false;

    boolean wall = false;
    boolean accessible = true;

    Location(){
        x = 0;
        y = 0;
        options = 0;
        routes = new boolean[4];
    }

    Location(int xIn, int yIn){
        x = xIn;
        y = yIn;
        options = 0;
        routes = new boolean[4];
    }

    public void print(){
        if(isAccessible()){
            System.out.print("O");
        }
        else {
            System.out.print("#");
        }
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public void setRoutes(Location[][] map){
        options = 0;
        int xCheck = getX();
        int yCheck = getY();

//        System.out.println(xCheck + "," + yCheck);
        //check above
        if(yCheck != 0) { //in case this one is already at the top row
            if (!map[yCheck - 1][xCheck].isAccessible()) { //directly above
                routes[0] = false;
//                System.out.println(xCheck + "," + (yCheck -1) + " is closed!    ");
            }
            else{
                routes[0] = true;
                options ++;
//                System.out.println(xCheck + "," + (yCheck -1) + " is open!");
            }
        }
        else {
            routes[0] = false;
//            System.out.println(xCheck + "," + (yCheck -1) + " is closed!");
        }
        //check right
        if(xCheck != map.length - 1) { //in case this one is already on the right edge
            if (!map[yCheck][xCheck + 1].isAccessible()) { // directly to the right of
                routes[1] = false;
  //              System.out.println((xCheck + 1) + "," + (yCheck) + " is closed!");
            }
            else{
                routes[1] = true;
                options ++;
 //               System.out.println((xCheck + 1) + "," + (yCheck) + " is open!");
            }

        }
        else {
            routes[1] = false;
//            System.out.println((xCheck + 1) + "," + (yCheck) + " is closed!");
        }
        //check below
        if(yCheck != map.length - 1) { //in case this one is already on the bottom row
            if (!map[yCheck + 1][xCheck].isAccessible()) { // directly below
                routes[2] = false;
 //               System.out.println(xCheck + "," + (yCheck +1) + " is closed!");
            }
            else{
                routes[2] = true;
                options ++;
 //               System.out.println(xCheck + "," + (yCheck +1) + " is open!");
            }
        }
        else {
            routes[2] = false;
//            System.out.println(xCheck + "," + (yCheck +1) + " is closed!");
        }
        //check left
        if(xCheck != 0) { //in case this one is already at the left edge
            if (!map[yCheck][xCheck - 1].isAccessible()) { // directly to the left of
                routes[3] = false;
 //               System.out.println((xCheck - 1) + "," + (yCheck ) + " is closed!");
            }
            else{
                routes[3] = true;
                options ++;
//                System.out.println((xCheck - 1) + "," + (yCheck ) + " is open!");
            }
        }
        else {
            routes[3] = false;
//            System.out.println((xCheck - 1) + "," + (yCheck ) + " is closed!");
        }
    }

    public int getOptions() {
        return options;
    }

    public boolean[] getRoutes() {
        return routes;
    }

    public boolean isExit(){
        return exit;
    }

    public void setExit(boolean b){
        exit = b;
    }

    public boolean isEntrance(){
        return entrance;
    }

    public void setEntrance(boolean b){
        entrance = b;
    }

    public void setAccessible(boolean b){
        accessible = b;
    }

    public void setOptions(int x){
        options = x;
    }

    boolean isWall(){
        return wall;
    }

    public void paint(Graphics g, int x, int y){
        g.setColor(Color.white);
        g.fillRect(x, y, x + 40, y + 40);
    }


}
