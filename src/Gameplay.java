import javax.swing.*;
import java.awt.*;

public class Gameplay extends JPanel {

    private ImageIcon imageTitle;

    private int[] snakeXlenght = new int[750];
    private int[] snakeYlenght = new int[750];

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    private ImageIcon sUp;
    private ImageIcon sDown;
    private ImageIcon sRight;
    private ImageIcon sLeft;
    public Gameplay()
    {

    }
    public void paint(Graphics graphics)
    {
        //draw image border
        graphics.setColor(Color.WHITE);
        graphics.drawRect(24,10,851,50);

        //draw title image
        imageTitle=new ImageIcon("sTitle.png");
        imageTitle.paintIcon(this,graphics,25,11);

        //draw gameplay border
        graphics.setColor(Color.WHITE);
        graphics.drawRect(24,74,851,577);

        //draw background gameplay
        graphics.setColor(Color.black);
        graphics.fillRect(25,75,850,575);


    }
}
