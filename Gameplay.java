package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.Timer;
import java.awt.event.*;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Rectangle;




public class Gameplay extends JPanel implements KeyListener,ActionListener {
	
	private boolean play=false;
	private int score=0;
	private int totalBricks=21;
	
	private Timer timer;
	private int delay=3;
	
	private int playerX=380;
	
	private int ballposX=120;
	private int ballposY=350;
	
	private int ballXdir=-1;
	private int ballYdir=-2;
	
	private MapGenerator map;
	
	public Gameplay()//constructor
	
	{
		map=new MapGenerator(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
	    timer = new Timer(delay,this);
		timer.start();		
	}
	
	public void paint(Graphics g)
	{
		//background
		g.setColor(Color.pink);
		g.fillRect(1,1,692,592);
		
		//drawing map
		map.draw((Graphics2D)g);
		
		//borders
		g.setColor(Color.black);
		g.fillRect(0,0,3,592);
		g.fillRect(0,0,692,3);
		g.fillRect(691,0,3,592);
		
		
		//scores
		g.setColor(Color.black);
		g.setFont(new Font("serif",Font.BOLD,25));
		g.drawString(""+score,590,30);
		
		
		
		//paddles
		g.setColor(Color.black);
		g.fillRect(playerX,550,100,8);
		
		//balls
		g.setColor(Color.white);
		g.fillOval(ballposX,ballposY,20,20);
		
		if(totalBricks<=0)
		{
			play=false;
			ballXdir=0;
			ballYdir=0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("YOU WON!! ",190,300);
			
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("Press Enter to Restart the game ",230,350);
			
		}
		
		if(ballposY>570)
		{
			play=false;
			ballXdir=0;
			ballYdir=0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("GAME OVER!!\n",190,300);
			// Read();
			
	
			
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("Press Enter to Restart the game ",230,350);
			
			
			
		}
		
		g.dispose();
				
		
		
	}
	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		timer.start();
		repaint();
		if(play)
		{
			if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8))) {
				ballYdir=-ballYdir;
			}
		
			
		B:	for(int i=0;i<map.map.length;i++)
			{
				for(int j=0;j<map.map[0].length;j++)
				{
					if(map.map[i][j]>0){
						
						int brickX=j*map.brickwidth+80;
						int brickY=i*map.brickheight+50;
						int brickwidth=map.brickwidth;
						int brickHeight=map.brickheight;
						
						Rectangle rect=new Rectangle(brickX,brickY,brickwidth,brickHeight);
						Rectangle ballRect=new Rectangle(ballposX,ballposY,20,20);
						Rectangle brickRect=rect;
						
						if(ballRect.intersects(brickRect)) {
							map.setBrickValue(0,i,j);
							totalBricks--;
							score+=10; bw.write(score);
							//file writing
							
							if(ballposX+19<=brickRect.x || ballposX+1>=brickRect.x + brickRect.width) {
								ballXdir=-ballXdir;
								}
							
							else
							{
								ballYdir=-ballYdir;
							}
							
							break B;
						}
						
					}
			
				}
			}
			
			ballposX+=ballXdir;
			ballposY+=ballYdir;
		
		
		if(ballposX<0)
		{
			ballXdir=-ballXdir;
			
		}
		
		if(ballposY<0)
		{
			ballYdir=-ballYdir;
			
		}
		
		if(ballposX>670)
		{
			ballXdir=-ballXdir;
			
		}
	
		}
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			if(playerX>=600)
			{
				playerX=600;
			}
			else
			{
				moveRight();
			}
		}
			
			
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			if(playerX<10)
			{
				playerX=10;
			}
			else
			{
				moveLeft();
			}
			
		}
		
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			if(!play)
			{
				play=true;
				ballposX=120;
				ballposY=350;
				ballXdir=-1;
				ballYdir=-2;
				playerX=310;
				score=0;
				totalBricks=21;
				map=new MapGenerator(3,7);
				repaint();
				
			}
		}
		
	}
	
	public void moveRight(){
		play=true;
		playerX+=20;
		
	}
	
	public void moveLeft(){
		play=true;
		playerX-=20;
		
	}


	

}

