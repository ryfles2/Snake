import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

    private ImageIcon imageTitle;

    private int[] snakeXlenght = new int[782];
    private int[] snakeYlenght = new int[782];

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    private boolean isPlaying=true;

    private ImageIcon sUp;
    private ImageIcon sDown;
    private ImageIcon sRight;
    private ImageIcon sLeft;
    private ImageIcon s;
    private ImageIcon sEnemy;

    private int lenghtOfSnake=1;
    private int moves =0;

    private Timer timer;
    private int delay=100;

    private int scaleX=30;
    private int scaleY=30;

    private Random random = new Random();

    private int verticalDivision=34;
    private int horizontalDivision=23;

    private int xpos = random.nextInt(verticalDivision);
    private int ypos = random.nextInt(horizontalDivision);



    public Gameplay()
    {

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay,this);
        timer.start();
    }
    public void paint(Graphics graphics)
    {
        if (moves == 0)
        {
            snakeXlenght[0]=100;
            snakeYlenght[0]=100;
            sRight = new ImageIcon("sRight.png");
            sRight=getScaledImage(sRight, scaleX,scaleY);
            sLeft = new ImageIcon("sLeft.png");
            sLeft=getScaledImage(sLeft, scaleX,scaleY);
            sUp = new ImageIcon("sUp.png");
            sUp=getScaledImage(sUp, scaleX,scaleY);
            sDown = new ImageIcon("sDown.png");
            sDown=getScaledImage(sDown, scaleX,scaleY);
            s = new ImageIcon("s.png");
            s=getScaledImage(s, scaleX,scaleY);
            sEnemy = new ImageIcon("sEnemy.png");
            sEnemy = getScaledImage(sEnemy,scaleX,scaleY);
        }
        //draw image border
        graphics.setColor(Color.WHITE);
        graphics.drawRect(24,10,851,55);

        //draw title image
        imageTitle=new ImageIcon("sTitle.png");
        imageTitle.paintIcon(this,graphics,25,11);

        //draw gameplay border
        graphics.setColor(Color.WHITE);
        graphics.drawRect(24,74,851,577);

        //draw background gameplay
        graphics.setColor(Color.black);
        graphics.fillRect(25,75,850,575);

        //draw score
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("arial", Font.PLAIN,20));
        graphics.drawString("Scores:" +(lenghtOfSnake-1), 780,30);


        sRight = new ImageIcon("sRight.png");
        sRight=getScaledImage(sRight, scaleX,scaleY);
        sRight.paintIcon(this,graphics,snakeXlenght[0],snakeYlenght[0]);

        for(int i=0; i<lenghtOfSnake;i++)
        {
            if(i==0 && right)
            {
                sRight.paintIcon(this,graphics,snakeXlenght[0],snakeYlenght[0]);
            }
            if(i==0 && left)
            {
                sLeft.paintIcon(this,graphics,snakeXlenght[0],snakeYlenght[0]);
            }
            if(i==0 && up)
            {
                sUp.paintIcon(this,graphics,snakeXlenght[0],snakeYlenght[0]);
            }
            if(i==0 && down)
            {
                sDown.paintIcon(this,graphics,snakeXlenght[0],snakeYlenght[0]);
            }
            if(i!=0)
            {
                s.paintIcon(this,graphics,snakeXlenght[i],snakeYlenght[i]);
            }
        }


        int tmpXpos= 25+(25*xpos);
        int tmpYpos= 75+(25*ypos);

        if(((tmpXpos)==snakeXlenght[0]) && (tmpYpos==snakeYlenght[0]))
        {
            lenghtOfSnake++;
            xpos=random.nextInt(verticalDivision);
            ypos=random.nextInt(horizontalDivision);
            if(delay>50) timer.setDelay(--delay);
        }

        sEnemy.paintIcon(this,graphics,tmpXpos,tmpYpos);

        for(int i=1;i<lenghtOfSnake;i++)
        {
            //Game over
            if(snakeXlenght[i]==snakeXlenght[0] && snakeYlenght[i] == snakeYlenght[0])
            {
                right = false;
                left = false;
                up = false;
                down = false;
                isPlaying=false;

                graphics.setColor(Color.WHITE);
                graphics.setFont(new Font("arial",Font.BOLD,50));
                graphics.drawString("Game Over!!!",300,300);


                graphics.setFont(new Font("arial",Font.BOLD,20));
                graphics.drawString("Space to restart",350,340);
            }
        }
        graphics.dispose();
    }
    private ImageIcon getScaledImage(ImageIcon imageIcon,int x, int y)
    {
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back
        return imageIcon;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        int x=0;
        int y=0;
        if(isPlaying)
        {
            if(right) x=25;

            else if(left) x=-25;

            else if(down) y=25;

            else if(up) y=-25;

            for (int i=lenghtOfSnake;i>=0;i--)
            {
                if(i==0)
                {
                    snakeXlenght[i]+=x;
                    snakeYlenght[i]+=y;
                }
                else
                {
                    snakeXlenght[i]=snakeXlenght[i-1];
                    snakeYlenght[i]=snakeYlenght[i-1];
                }
                if(snakeXlenght[i]>850)
                {
                    snakeXlenght[i]=25;
                }
                else  if(snakeXlenght[i]<25)
                {
                    snakeXlenght[i]=850;
                }
                else if(snakeYlenght[i]<75)
                {
                    snakeYlenght[i]=625;
                }
                else if(snakeYlenght[i]>625)
                {
                    snakeYlenght[i]=75;
                }
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode()==KeyEvent.VK_SPACE && !isPlaying)
        {
            moves=0;
            lenghtOfSnake=1;
            isPlaying=true;
            repaint();
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT)
        {
            moves++;
            if(!left)
            {
                right = true;
            }

            up = false;
            down=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT)
        {
            moves++;
            if(!right)
            {
                left = true;
            }

            up = false;
            down=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_UP)
        {
            moves++;
            if(!down)
            {
                up = true;
            }

            left = false;
            right=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN)
        {
            moves++;
            if(!up)
            {
                down = true;
            }

            left = false;
            right=false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}