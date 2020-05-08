import java.awt.*;

public class Wall extends Location{

    Wall(){
        super();
        accessible = false; //cannot access a wall!
        wall = true;
    }

    Wall(int xIn, int yIn){
        super(xIn, yIn);
        accessible = false;
        wall = true;
    }


    public void paint(Graphics g, int x, int y){
        g.setColor(Color.black);
        g.drawRect(x, y, x + 40, y + 40);
        g.setColor(Color.gray);
        g.fillRect(x, y, x + 40, y + 40);
    }


}
