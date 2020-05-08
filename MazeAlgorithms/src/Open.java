import java.awt.*;

public class Open extends Location {



    Open(){
        super();
    }

    Open(int xIn, int yIn){
        super(xIn, yIn);
    }

    public void print(){
        if(isAccessible()){
            System.out.print(" ");
        }
        else{
            System.out.print("-");
        }
    }

    public void paint(Graphics g, int x, int y){
        g.setColor(Color.black);
        g.drawRect(x, y, x + 40, y + 40);

        if(isExit() || isEntrance()){
            g.setColor(Color.orange);
        }
        else if(visited) {
            if (deadend) {
                g.setColor(Color.green);
            }
            else
                {
                    g.setColor(Color.pink);
                }

        }
        else {

            if (isAccessible()) {
                g.setColor(Color.blue);
            } else {
                g.setColor(Color.red);
            }
        }
        g.fillRect(x, y, x + 40, y + 40);
    }


}
