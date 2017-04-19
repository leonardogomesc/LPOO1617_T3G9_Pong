package gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import logic.Game;

public class GraphicsAndListeners extends JPanel implements KeyListener, MouseListener{
	
	private BufferedImage square;
	private BufferedImage ball;
	private Game game;
	GraphicInterface graphics;
	
	public GraphicsAndListeners(GraphicInterface g){
		addKeyListener(this);
		addMouseListener(this);
		
		try { ball = ImageIO.read(new File("src/gui/images/ball.png")); }
		catch (IOException e) {e.printStackTrace(); }
		
		try { square = ImageIO.read(new File("src/gui/images/square.png")); }
		catch (IOException e) {e.printStackTrace(); }
		
		game=new Game();
		this.graphics=g;
		
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
	game.update();
	super.paintComponent(g);
	//g.drawImage(ball, (int)game.getBall().getPosition()[0], (int)game.getBall().getPosition()[1], 25, 25, null);
	//graphics.panel.requestFocusInWindow();
	g.fillOval((int)game.getBall().getPosition()[0], (int)game.getBall().getPosition()[1], 25, 25);
	System.out.println("painted");
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		switch(arg0.getKeyCode()){ 
		case KeyEvent.VK_LEFT: 
			game.setPlaying(false); 
			break; 
		case KeyEvent.VK_RIGHT:
			game.setPlaying(true); 
			gameInProgress();
			//game.getBall().setPositionx(game.getBall().getPosition()[0]+10);
			//System.out.println("pressed");
			
		break;  
		case KeyEvent.VK_UP: break; 
		case KeyEvent.VK_DOWN: break; 
		}
		
		//repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void gameInProgress(){
		
		while(game.getPlaying()){
			//game.update();
			repaint();
			System.out.println("pressed");
			System.out.println((int)game.getBall().getPosition()[0] +" "+(int)game.getBall().getPosition()[1]);
			
			/*try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		graphics.panel.requestFocusInWindow();
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
