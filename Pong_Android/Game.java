package com.example.up201503708.pong;

import java.util.Random;

public class Game {
	private Ball ball;
	private Ball ballSim;
	private Player p1;
	private Player p2;
	private Obstacle o[];
	private double ballVelocity;
	private double paddleVelocity;
	public static final int ballSize=50;
	public static final int paddleSize[]={40,250};
	public static int min_screen_x;
	public static int min_screen_y;
	public static int max_screen_x;
	public static int max_screen_y;
	public int targetPos;
	private int numberOfPlayers; 
	private int finalPosition;
	private double computerPaddleVelocity;
	private int computerPaddleZone;
	
	
	private boolean playing;
	
	private double startingPos[];
	
	public Game(int min_x,int min_y, int max_x,int max_y){

		min_screen_x=min_x;
		min_screen_y=min_y;
		max_screen_x=max_x;
		max_screen_y=max_y;

		ball=new Ball((max_screen_x+min_screen_x)/2,(max_screen_y+min_screen_y)/2,Math.cos(Math.toRadians(0)),Math.sin(Math.toRadians(0)));
		ballSim=null;
		
		p1=new Player(min_screen_x+40,(max_screen_y+min_screen_y)/2);
		p2=new Player(max_screen_x-40,(max_screen_y+min_screen_y)/2);
		
		numberOfPlayers=1;
		
		playing=false;
		finalPosition=-1;


		ballVelocity=25;
		paddleVelocity=15;
		computerPaddleVelocity=20;
		computerPaddleZone=0;
		
		o=new Obstacle[4];
		
		/*o[0]=new Obstacle(210,80,50,50);
		o[1]=new Obstacle(510,80,50,50);
		o[2]=new Obstacle(210,412-80,50,50);
		o[3]=new Obstacle(510,412-80,50,50);
		o[4]=new Obstacle(360,200,50,50);*/
		
		/*o[0]=new Obstacle((max_screen_x+min_screen_x)/2-50,(max_screen_y+min_screen_y)/2-125,5,150);
		o[1]=new Obstacle((max_screen_x+min_screen_x)/2+50,(max_screen_y+min_screen_y)/2-125,5,150);
		o[2]=new Obstacle((max_screen_x+min_screen_x)/2-50,(max_screen_y+min_screen_y)/2+125,5,150);
		o[3]=new Obstacle((max_screen_x+min_screen_x)/2+50,(max_screen_y+min_screen_y)/2+125,5,150);
		o[4]=new Obstacle((max_screen_x+min_screen_x)/2-125,(max_screen_y+min_screen_y)/2-50,150,5);
		o[5]=new Obstacle((max_screen_x+min_screen_x)/2-125,(max_screen_y+min_screen_y)/2+50,150,5);
		o[6]=new Obstacle((max_screen_x+min_screen_x)/2+125,(max_screen_y+min_screen_y)/2-50,150,5);
		o[7]=new Obstacle((max_screen_x+min_screen_x)/2+125,(max_screen_y+min_screen_y)/2+50,150,5);*/

		o[0]=new Obstacle((max_screen_x+min_screen_x)/2,min_screen_y+1*(max_screen_y-min_screen_y)/5, max_screen_x/2,50);
		o[1]=new Obstacle((max_screen_x+min_screen_x)/2,min_screen_y+2*(max_screen_y-min_screen_y)/5, max_screen_x/2,50);
		o[2]=new Obstacle((max_screen_x+min_screen_x)/2,min_screen_y+3*(max_screen_y-min_screen_y)/5, max_screen_x/2,50);
		o[3]=new Obstacle((max_screen_x+min_screen_x)/2,min_screen_y+4*(max_screen_y-min_screen_y)/5, max_screen_x/2,50);


		
		startingPos=new double[]{p1.getPos()[0],p1.getPos()[1],p2.getPos()[0],p2.getPos()[1],ball.getPosition()[0],ball.getPosition()[1],ball.getVector()[0],ball.getVector()[1]};
		
	}
	
	public void update(){
	
		if(playing){
			collisionHandler();
			
			if(finalPosition==-1 && numberOfPlayers!=2){
				simulation();
			}

			if(numberOfPlayers!=2 && finalPosition==2){
				aiPaddleMovement(p2);
			}

			paddleMovement(p1);

			updateBallPos(ball);
			
		}
		
	}
	
	public void paddleMovement(Player p){
		if(p.getPos()[1]>targetPos){
			if((p.getPos()[1]-targetPos)>10) {
				p.setPos_y(p.getPos()[1] - paddleVelocity);
			}
		}
		else if(p.getPos()[1]<targetPos){
			if((targetPos-p.getPos()[1])>10) {
				p.setPos_y(p.getPos()[1] + paddleVelocity);
			}
		}
	}
	
	public void aiPaddleMovement(Player p){
		if(p.getPos()[1]+computerPaddleZone*paddleSize[1]/7< ballSim.getPosition()[1]){
			if(ballSim.getPosition()[1]-(p.getPos()[1]+computerPaddleZone*paddleSize[1]/7)>10){
				p.setPos_y(p.getPos()[1]+computerPaddleVelocity);
			}
		}
		else if(p.getPos()[1]+computerPaddleZone*paddleSize[1]/7>ballSim.getPosition()[1]){
			if((p.getPos()[1]+computerPaddleZone*paddleSize[1]/7)-ballSim.getPosition()[1]>10){
				p.setPos_y(p.getPos()[1]-computerPaddleVelocity);
			}
		}
	}
	
	public Ball getBall(){
		return ball;
	}
	
	public boolean getPlaying(){
		return playing;
	}
	
	public void setPlaying(boolean a){
		playing=a;
		
	}
	
	public boolean player1CollisionHandler(){
		boolean collidedWithPaddle=false;

		if(ball.getPosition()[0]<=ballSize/2+p1.getPos()[0]+paddleSize[0]/2 &&
				ball.getPosition()[1]<= p1.getPos()[1]+paddleSize[1]/2 && 
				ball.getPosition()[1]> p1.getPos()[1]+paddleSize[1]/2-paddleSize[1]/7 &&
				ball.getVector()[0]<0 &&
				ball.getPosition()[0]>=p1.getPos()[0]-paddleSize[0]/2){

			collidedWithPaddle=true;
			ball.setVector_x(Math.cos(Math.toRadians(60)));
			ball.setVector_y(Math.sin(Math.toRadians(60)));
			finalPosition=-1;
		}else if(ball.getPosition()[0]<=ballSize/2+p1.getPos()[0]+paddleSize[0]/2 &&
				ball.getPosition()[1]<=p1.getPos()[1]+paddleSize[1]/2-paddleSize[1]/7 && 
				ball.getPosition()[1]> p1.getPos()[1]+paddleSize[1]/2-2*paddleSize[1]/7 &&
				ball.getVector()[0]<0 &&
				ball.getPosition()[0]>=p1.getPos()[0]-paddleSize[0]/2){

			collidedWithPaddle=true;
			ball.setVector_x(Math.cos(Math.toRadians(40)));
			ball.setVector_y(Math.sin(Math.toRadians(40)));
			finalPosition=-1;
		}else if(ball.getPosition()[0]<=ballSize/2+p1.getPos()[0]+paddleSize[0]/2 &&
				ball.getPosition()[1]<= p1.getPos()[1]+paddleSize[1]/2-2*paddleSize[1]/7 && 
				ball.getPosition()[1]>  p1.getPos()[1]+paddleSize[1]/2-3*paddleSize[1]/7 && 
				ball.getVector()[0]<0 &&
				ball.getPosition()[0]>=p1.getPos()[0]-paddleSize[0]/2){

			collidedWithPaddle=true;
			ball.setVector_x(Math.cos(Math.toRadians(20)));
			ball.setVector_y(Math.sin(Math.toRadians(20)));
			finalPosition=-1;
		}else if(ball.getPosition()[0]<=ballSize/2+p1.getPos()[0]+paddleSize[0]/2 &&
				ball.getPosition()[1]<= p1.getPos()[1]+paddleSize[1]/2-3*paddleSize[1]/7 &&
				ball.getPosition()[1]>  p1.getPos()[1]+paddleSize[1]/2-4*paddleSize[1]/7 && 
				ball.getVector()[0]<0 &&
				ball.getPosition()[0]>=p1.getPos()[0]-paddleSize[0]/2){

			collidedWithPaddle=true;
			ball.setVector_x(Math.cos(Math.toRadians(0)));
			ball.setVector_y(Math.sin(Math.toRadians(0)));
			finalPosition=-1;
		}else if(ball.getPosition()[0]<=ballSize/2+p1.getPos()[0]+paddleSize[0]/2 &&
				ball.getPosition()[1]<= p1.getPos()[1]+paddleSize[1]/2-4*paddleSize[1]/7 && 
				ball.getPosition()[1]>  p1.getPos()[1]+paddleSize[1]/2-5*paddleSize[1]/7 &&
				ball.getVector()[0]<0 &&
				ball.getPosition()[0]>=p1.getPos()[0]-paddleSize[0]/2){

			collidedWithPaddle=true;
			ball.setVector_x(Math.cos(Math.toRadians(20)));
			ball.setVector_y(-Math.sin(Math.toRadians(20)));
			finalPosition=-1;
		}else if(ball.getPosition()[0]<=ballSize/2+p1.getPos()[0]+paddleSize[0]/2 && 
				ball.getPosition()[1]<= p1.getPos()[1]+paddleSize[1]/2-5*paddleSize[1]/7 &&
				ball.getPosition()[1]>  p1.getPos()[1]+paddleSize[1]/2-6*paddleSize[1]/7 && 
				ball.getVector()[0]<0 &&
				ball.getPosition()[0]>=p1.getPos()[0]-paddleSize[0]/2){

			collidedWithPaddle=true;
			ball.setVector_x(Math.cos(Math.toRadians(40)));
			ball.setVector_y(-Math.sin(Math.toRadians(40)));
			finalPosition=-1;
		}else if(ball.getPosition()[0]<=ballSize/2+p1.getPos()[0]+paddleSize[0]/2 && 
				ball.getPosition()[1]<= p1.getPos()[1]+paddleSize[1]/2-6*paddleSize[1]/7 && 
				ball.getPosition()[1]>=  p1.getPos()[1]+paddleSize[1]/2-7*paddleSize[1]/7 &&
				ball.getVector()[0]<0 &&
				ball.getPosition()[0]>=p1.getPos()[0]-paddleSize[0]/2){

			collidedWithPaddle=true;
			ball.setVector_x(Math.cos(Math.toRadians(60)));
			ball.setVector_y(-Math.sin(Math.toRadians(60)));
			finalPosition=-1;
		}
		
		return collidedWithPaddle;
	}
	
	public boolean player2CollisionHandler(){
		
		boolean collidedWithPaddle=false;

		if(ball.getPosition()[0]>=p2.getPos()[0]-paddleSize[0]/2-ballSize/2 &&
				ball.getPosition()[1]<= p2.getPos()[1]+paddleSize[1]/2 &&
				ball.getPosition()[1]> p2.getPos()[1]+paddleSize[1]/2-paddleSize[1]/7 &&
				ball.getVector()[0]>0 && 
				ball.getPosition()[0]<=p2.getPos()[0]+paddleSize[0]/2){

			collidedWithPaddle=true;
			ball.setVector_x(-Math.cos(Math.toRadians(60)));
			ball.setVector_y(Math.sin(Math.toRadians(60)));
			finalPosition=-1;
		}else if(ball.getPosition()[0]>=p2.getPos()[0]-paddleSize[0]/2-ballSize/2 && 
				ball.getPosition()[1]<=p2.getPos()[1]+paddleSize[1]/2-paddleSize[1]/7 && 
				ball.getPosition()[1]> p2.getPos()[1]+paddleSize[1]/2-2*paddleSize[1]/7 && 
				ball.getVector()[0]>0 &&
				ball.getPosition()[0]<=p2.getPos()[0]+paddleSize[0]/2){

			collidedWithPaddle=true;
			ball.setVector_x(-Math.cos(Math.toRadians(40)));
			ball.setVector_y(Math.sin(Math.toRadians(40)));
			finalPosition=-1;
		}else if(ball.getPosition()[0]>=p2.getPos()[0]-paddleSize[0]/2-ballSize/2 && 
				ball.getPosition()[1]<= p2.getPos()[1]+paddleSize[1]/2-2*paddleSize[1]/7 &&
				ball.getPosition()[1]> p2.getPos()[1]+paddleSize[1]/2-3*paddleSize[1]/7 &&
				ball.getVector()[0]>0 && 
				ball.getPosition()[0]<=p2.getPos()[0]+paddleSize[0]/2){

			collidedWithPaddle=true;
			ball.setVector_x(-Math.cos(Math.toRadians(20)));
			ball.setVector_y(Math.sin(Math.toRadians(20)));
			finalPosition=-1;
		}else if(ball.getPosition()[0]>=p2.getPos()[0]-paddleSize[0]/2-ballSize/2 && 
				ball.getPosition()[1]<= p2.getPos()[1]+paddleSize[1]/2-3*paddleSize[1]/7 && 
				ball.getPosition()[1]> p2.getPos()[1]+paddleSize[1]/2-4*paddleSize[1]/7 &&
				ball.getVector()[0]>0 && 
				ball.getPosition()[0]<=p2.getPos()[0]+paddleSize[0]/2){

			collidedWithPaddle=true;
			ball.setVector_x(-Math.cos(Math.toRadians(0)));
			ball.setVector_y(Math.sin(Math.toRadians(0)));
			finalPosition=-1;
		}else if(ball.getPosition()[0]>=p2.getPos()[0]-paddleSize[0]/2-ballSize/2 &&
				ball.getPosition()[1]<= p2.getPos()[1]+paddleSize[1]/2-4*paddleSize[1]/7 && 
				ball.getPosition()[1]> p2.getPos()[1]+paddleSize[1]/2-5*paddleSize[1]/7 &&
				ball.getVector()[0]>0 &&
				ball.getPosition()[0]<=p2.getPos()[0]+paddleSize[0]/2){

			collidedWithPaddle=true;
			ball.setVector_x(-Math.cos(Math.toRadians(20)));
			ball.setVector_y(-Math.sin(Math.toRadians(20)));
			finalPosition=-1;
		}else if(ball.getPosition()[0]>=p2.getPos()[0]-paddleSize[0]/2-ballSize/2 &&
				ball.getPosition()[1]<= p2.getPos()[1]+paddleSize[1]/2-5*paddleSize[1]/7 && 
				ball.getPosition()[1]> p2.getPos()[1]+paddleSize[1]/2-6*paddleSize[1]/7 &&
				ball.getVector()[0]>0 && 
				ball.getPosition()[0]<=p2.getPos()[0]+paddleSize[0]/2){

			collidedWithPaddle=true;
			ball.setVector_x(-Math.cos(Math.toRadians(40)));
			ball.setVector_y(-Math.sin(Math.toRadians(40)));
			finalPosition=-1;
		}else if(ball.getPosition()[0]>=p2.getPos()[0]-paddleSize[0]/2-ballSize/2 && 
				ball.getPosition()[1]<= p2.getPos()[1]+paddleSize[1]/2-6*paddleSize[1]/7 &&
				ball.getPosition()[1]>= p2.getPos()[1]+paddleSize[1]/2-7*paddleSize[1]/7 && 
				ball.getVector()[0]>0 && 
				ball.getPosition()[0]<=p2.getPos()[0]+paddleSize[0]/2){

			collidedWithPaddle=true;
			ball.setVector_x(-Math.cos(Math.toRadians(60)));
			ball.setVector_y(-Math.sin(Math.toRadians(60)));
			finalPosition=-1;
		}

			return collidedWithPaddle;		
	}
	
	public void wallCollisionHandler(){
		if(ball.getPosition()[0]<=min_screen_x+ballSize/2 && ball.getVector()[0]<0){
			playing=false;
		}
		else if(ball.getPosition()[1]<=min_screen_y+ballSize/2 && ball.getVector()[1]<0){
			ball.setVector_y(-ball.getVector()[1]);
		}
		else if(ball.getPosition()[0]>=max_screen_x-ballSize/2 && ball.getVector()[0]>0){
			playing=false;
		}
		else if(ball.getPosition()[1]>=max_screen_y-ballSize/2 && ball.getVector()[1]>0){
			ball.setVector_y(-ball.getVector()[1]);

		}
	}
	
	public void obstacleCollisionHandler(Ball ball){
		
			for(int i=0;i< o.length;i++){

				if(ball.getVector()[1]>0 &&
						ball.getPosition()[1]<o[i].getK()[0]*ball.getPosition()[0]+o[i].getB()[0] &&
						ball.getPosition()[1]<o[i].getK()[1]*ball.getPosition()[0]+o[i].getB()[1] &&
						ball.getPosition()[1]>o[i].getPos()[1]-o[i].getSize()[1]/2-ballSize/2 &&
						ball.getPosition()[0]<o[i].getPos()[0]+o[i].getSize()[0]/2 && 
						ball.getPosition()[0]>o[i].getPos()[0]-o[i].getSize()[0]/2){
					ball.setVector_y(-ball.getVector()[1]);
				}
				else if(ball.getVector()[0]<0 && 
						ball.getPosition()[1]<o[i].getK()[0]*ball.getPosition()[0]+o[i].getB()[0] &&
						ball.getPosition()[1]>o[i].getK()[1]*ball.getPosition()[0]+o[i].getB()[1] && 
						ball.getPosition()[0]<o[i].getPos()[0]+o[i].getSize()[0]/2+ballSize/2 && 
						ball.getPosition()[1]<o[i].getPos()[1]+o[i].getSize()[1]/2 && 
						ball.getPosition()[1]>o[i].getPos()[1]-o[i].getSize()[1]/2){
					ball.setVector_x(-ball.getVector()[0]);
				}
				else if(ball.getVector()[1]<0 && 
						ball.getPosition()[1]>o[i].getK()[0]*ball.getPosition()[0]+o[i].getB()[0] && 
						ball.getPosition()[1]>o[i].getK()[1]*ball.getPosition()[0]+o[i].getB()[1] && 
						ball.getPosition()[1]<o[i].getPos()[1]+o[i].getSize()[1]/2+ballSize/2 &&
						ball.getPosition()[0]<o[i].getPos()[0]+o[i].getSize()[0]/2 && 
						ball.getPosition()[0]>o[i].getPos()[0]-o[i].getSize()[0]/2){
					ball.setVector_y(-ball.getVector()[1]);
				}
				else if(ball.getVector()[0]>0 &&
						ball.getPosition()[1]>o[i].getK()[0]*ball.getPosition()[0]+o[i].getB()[0] && 
						ball.getPosition()[1]<o[i].getK()[1]*ball.getPosition()[0]+o[i].getB()[1] && 
						ball.getPosition()[0]>o[i].getPos()[0]-o[i].getSize()[0]/2-ballSize/2 && 
						ball.getPosition()[1]<o[i].getPos()[1]+o[i].getSize()[1]/2 &&
						ball.getPosition()[1]>o[i].getPos()[1]-o[i].getSize()[1]/2){
					ball.setVector_x(-ball.getVector()[0]);
				}
				else if(Math.sqrt((Math.pow(((o[i].getPos()[0]-o[i].getSize()[0]/2)-ball.getPosition()[0]),2)+Math.pow(((o[i].getPos()[1]-o[i].getSize()[1]/2)-ball.getPosition()[1]),2)))<= ballSize/2 && !(ball.getVector()[0]<0 && ball.getVector()[1]<0)){
					ball.setVector_x(-Math.cos(Math.toRadians(45)));
					ball.setVector_y(-Math.sin(Math.toRadians(45)));

				}
				else if(Math.sqrt((Math.pow(((o[i].getPos()[0]+o[i].getSize()[0]/2)-ball.getPosition()[0]),2)+Math.pow(((o[i].getPos()[1]-o[i].getSize()[1]/2)-ball.getPosition()[1]),2)))<= ballSize/2 && !(ball.getVector()[0]>0 && ball.getVector()[1]<0)){
					ball.setVector_x(Math.cos(Math.toRadians(45)));
					ball.setVector_y(-Math.sin(Math.toRadians(45)));
				}
				else if(Math.sqrt((Math.pow(((o[i].getPos()[0]+o[i].getSize()[0]/2)-ball.getPosition()[0]),2)+Math.pow(((o[i].getPos()[1]+o[i].getSize()[1]/2)-ball.getPosition()[1]),2)))<= ballSize/2 && !(ball.getVector()[0]>0 && ball.getVector()[1]>0)){
					ball.setVector_x(Math.cos(Math.toRadians(45)));
					ball.setVector_y(Math.sin(Math.toRadians(45)));
				}
				else if(Math.sqrt((Math.pow(((o[i].getPos()[0]-o[i].getSize()[0]/2)-ball.getPosition()[0]),2)+Math.pow(((o[i].getPos()[1]+o[i].getSize()[1]/2)-ball.getPosition()[1]),2)))<= ballSize/2 && !(ball.getVector()[0]<0 && ball.getVector()[1]>0)){
					ball.setVector_x(-Math.cos(Math.toRadians(45)));
					ball.setVector_y(Math.sin(Math.toRadians(45)));
				}

			}
	}

	public void collisionHandler(){

	boolean collidedWithPaddle=false;
	
	collidedWithPaddle=player1CollisionHandler();
	
	collidedWithPaddle=player2CollisionHandler();
	
		if(!collidedWithPaddle){

			wallCollisionHandler();
			
			if(o!=null){
				obstacleCollisionHandler(ball);
		 }
	   }
	}

	public void reset(){
		p1=new Player(startingPos[0],startingPos[1]);
		p2=new Player(startingPos[2],startingPos[3]);
		ball.setPositionx(startingPos[4]);
		ball.setPositiony(startingPos[5]);
		ball.setVector_x(startingPos[6]);
		ball.setVector_y(startingPos[7]);
		finalPosition=-1;
	}
	
	public Player getP1(){
		return p1;
	}
	
	public Player getP2(){
		return p2;
	}
	
	public Obstacle[] getObstacles(){
		return o;
	}
	
	public void simulation(){
		ballSim=new Ball(ball.getPosition()[0],ball.getPosition()[1],ball.getVector()[0],ball.getVector()[1]);
		Random r=new Random();
		
		int counter=0;
	
		while(finalPosition==-1){
			if(counter<30000){
			updateSimulation();
			}
			else{
				ball.setVector_x(Math.cos(Math.acos(ball.getVector()[0])+(2*Math.PI/360)));
				ball.setVector_y(Math.sin(Math.asin(ball.getVector()[1])+(2*Math.PI/360)));
				
				ballSim=new Ball(ball.getPosition()[0],ball.getPosition()[1],ball.getVector()[0],ball.getVector()[1]);
				counter=0;
			}
			counter=counter+1;
		}
		
	/// computerPaddleVelocity=r.nextInt(3)+3;
	 computerPaddleZone=r.nextInt(7)-3;
	 	
	}

	private void updateSimulation() {
		simCollisionHandler();
		if(finalPosition==-1){
			updateBallPos(ballSim);
			}
	}
	
	private void simWallCollisionHandler(){
		if(ballSim.getPosition()[0]<=p1.getPos()[0]+paddleSize[0]/2+ballSize/2 && ballSim.getVector()[0]<0){
			finalPosition=1;
		}
		else if(ballSim.getPosition()[1]<=min_screen_y+ballSize/2 && ballSim.getVector()[1]<0){
			ballSim.setVector_y(-ballSim.getVector()[1]);
		}
		else if(ballSim.getPosition()[0]>=p2.getPos()[0]-paddleSize[0]/2-ballSize/2 && ballSim.getVector()[0]>0){
			finalPosition=2;
		}
		else if(ballSim.getPosition()[1]>=max_screen_y-ballSize/2 && ballSim.getVector()[1]>0){
			ballSim.setVector_y(-ballSim.getVector()[1]);

		}
		
	}
	
	private void updateBallPos(Ball ball){
		
		ball.setPositionx(ball.getPosition()[0]+ballVelocity*ball.getVector()[0]);
		ball.setPositiony(ball.getPosition()[1]+ballVelocity*ball.getVector()[1]);
	}

	private void simCollisionHandler() {
		
		simWallCollisionHandler();
		
		if(o!=null){
			obstacleCollisionHandler(ballSim);
		}
	}	
}
