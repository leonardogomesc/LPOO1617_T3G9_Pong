package gui;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import logic.Game;

public class GraphicsAndListeners extends JPanel implements KeyListener, MouseListener, ActionListener{
	
	private BufferedImage square;
	private BufferedImage ball;
	private Game game;
	GraphicInterface graphics;
	Timer t;
	
	public GraphicsAndListeners(GraphicInterface g){
		addKeyListener(this);
		addMouseListener(this);
		
		try { ball = ImageIO.read(new File("src/gui/images/ball.png")); }
		catch (IOException e) {e.printStackTrace(); }
		
		try { square = ImageIO.read(new File("src/gui/images/square.png")); }
		catch (IOException e) {e.printStackTrace(); }
		
		game=new Game();
		this.graphics=g;
		
		t=new Timer(10,this);
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
	 super.paintComponent(g);
	g.drawImage(ball, (int)game.getBall().getPosition()[0]-game.ballSize/2, (int)game.getBall().getPosition()[1]-game.ballSize/2, game.ballSize, game.ballSize, null);
	g.drawImage(square,(int)game.getP1().getPos()[0]-game.paddleSize[0]/2,(int)game.getP1().getPos()[1]-game.paddleSize[1]/2, game.paddleSize[0],game.paddleSize[1],null);
	g.drawImage(square,(int)game.getP2().getPos()[0]-game.paddleSize[0]/2,(int)game.getP2().getPos()[1]-game.paddleSize[1]/2, game.paddleSize[0],game.paddleSize[1],null);
	
	if(game.getObstacles()!=null){
	for(int i=0;i<game.getObstacles().length;i++){
		g.drawImage(square, (int)(game.getObstacles()[i].getPos()[0]-game.getObstacles()[i].getSize()[0]/2), (int)(game.getObstacles()[i].getPos()[1]-game.getObstacles()[i].getSize()[1]/2), (int)game.getObstacles()[i].getSize()[0], (int)game.getObstacles()[i].getSize()[1], null);
	}
	}
	
	/*for(int x=0;x<700;x=x+5){
		g.drawRect(x, (int)(game.getObstacles()[0].getK()[0]*x+game.getObstacles()[0].getB()[0]), 2,2);
		g.drawRect(x, (int)(game.getObstacles()[0].getK()[1]*x+game.getObstacles()[0].getB()[1]), 2,2);
	}
	
	for(int x=0;x<700;x=x+5){
		g.drawRect(x, (int)(game.getObstacles()[0].getPos()[1]-game.getObstacles()[0].getSize()[1]/2-game.ballSize/2), 2,2);
		g.drawRect(x, (int)(game.getObstacles()[0].getPos()[1]+game.getObstacles()[0].getSize()[1]/2+game.ballSize/2), 2,2);
	}
	
	for(int y=0;y<400;y=y+5){
		g.drawRect((int)(game.getObstacles()[0].getPos()[0]-game.getObstacles()[0].getSize()[0]/2-game.ballSize/2),y , 2,2);
		g.drawRect((int)(game.getObstacles()[0].getPos()[0]+game.getObstacles()[0].getSize()[0]/2+game.ballSize/2),y, 2,2);
	}
	*/
	
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		switch(arg0.getKeyCode()){ 
		case KeyEvent.VK_LEFT: 
			game.setPlaying(false); 
			break; 
		case KeyEvent.VK_RIGHT:
			game.setPlaying(true); 
			t.start();
		break;  
		case KeyEvent.VK_UP:
			game.getP1().setUp(true);
			//System.out.println("UP, pressed");
			
		break; 
		case KeyEvent.VK_DOWN:
			game.getP1().setDown(true);
			//System.out.println("Down, pressed");
		break; 
		case KeyEvent.VK_W:
			game.getP2().setUp(true);
			break;
        case KeyEvent.VK_S:
        	game.getP2().setDown(true);
			break;	
        case KeyEvent.VK_R:
        	game.reset();
        	repaint();
			break;	
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		switch(arg0.getKeyCode()){
		case KeyEvent.VK_UP:
			game.getP1().setUp(false);
			break; 
		case KeyEvent.VK_DOWN:
			game.getP1().setDown(false);
			break; 
		case KeyEvent.VK_W:
			game.getP2().setUp(false);
			break;
		case KeyEvent.VK_S:
			game.getP2().setDown(false);
			break;	
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
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


	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(game.getPlaying()){
		game.update();
		repaint();
		//System.out.println((int)game.getBall().getPosition()[0] +" "+(int)game.getBall().getPosition()[1]);
	}
	}


}
