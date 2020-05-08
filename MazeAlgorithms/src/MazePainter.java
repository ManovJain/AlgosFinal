import javax.swing.*;
import java.awt.*;


public class MazePainter extends JPanel {

    private Maze maze;
    int size;
    int x;
    int y;

    Maze[] history;

    MazePainter(){
        super();
        maze = new Maze();
        size = maze.size;
        x = 0;
        y = 0;

    }

    MazePainter(Maze mazeInput){
        maze = mazeInput;
        size = maze.size;
        x = 0;
        y = 0;
    }

    public void paint(Graphics g){
        super.paint(g);


        int x = 0;
        int y = 0;

        int bound = (size * 40);
        setSize(bound, bound);


        for(int i = 0; i < size; i ++){
            for(int k = 0; k < size; k ++){
                maze.getMaze()[i][k].paint(g, x, y);
                x += 40;
            }
            x = 0;
            y +=40;
        }

    }


}
