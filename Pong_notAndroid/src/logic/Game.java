package logic;

import java.util.Random;

public class Game {
	private Ball ball;
	private Ball ballSim;
	private Player p1;
	private Player p2;
	private Obstacle o[];
	private double ballVelocity;
	private double paddleVelocity;
	public static final int ballSize=24;
	public static final int paddleSize[]={10,90};
	public static final int screen_x=984;
	public static final int screen_y=562;
	private int numberOfPlayers; 
	private int finalPosition;
	private double computerPaddleVelocity;
	private int computerPaddleZone;
	
	
	private boolean playing;
	
	private double startingPos[];
	
	public Game(){
		ball=new Ball(screen_x/2,screen_y/2,Math.cos(Math.toRadians(0)),Math.sin(Math.toRadians(0)));
		ballSim=null;
		
		p1=new Player(screen_x-10,screen_y/2);
		p2=new Player(10,screen_y/2);
		
		numberOfPlayers=1;
		
		playing=false;
		finalPosition=-1;
		
		ballVelocity=10;
		paddleVelocity=4;
		computerPaddleVelocity=7;
		computerPaddleZone=0;
		
		o=new Obstacle[8];
		
		/*o[0]=new Obstacle(210,80,50,50);
		o[1]=new Obstacle(510,80,50,50);
		o[2]=new Obstacle(210,412-80,50,50);
		o[3]=new Obstacle(510,412-80,50,50);
		o[4]=new Obstacle(360,200,50,50);*/
		
		o[0]=new Obstacle(screen_x/2-50,screen_y/2-125,5,150);
		o[1]=new Obstacle(screen_x/2+50,screen_y/2-125,5,150);
		o[2]=new Obstacle(screen_x/2-50,screen_y/2+125,5,150);
		o[3]=new Obstacle(screen_x/2+50,screen_y/2+125,5,150);
		o[4]=new Obstacle(screen_x/2-125,screen_y/2-50,150,5);
		o[5]=new Obstacle(screen_x/2-125,screen_y/2+50,150,5);
		o[6]=new Obstacle(screen_x/2+125,screen_y/2-50,150,5);
		o[7]=new Obstacle(screen_x/2+125,screen_y/2+50,150,5);
		
		
		startingPos=new double[]{p1.getPos()[0],p1.getPos()[1],p2.getPos()[0],p2.getPos()[1],ball.getPosition()[0],ball.getPosition()[1]};
		
	}
	
	public void update(){
		if(numberOfPlayers==2){
		collisionHandler();
		
		if(playing){
			
		if(p1.getUp()==true && p1.getPos()[1]>=0){
			p1.setPos_y(p1.getPos()[1]-paddleVelocity);
		}
		else if(p1.getDown()==true && p1.getPos()[1]<=screen_y){
		    p1.setPos_y(p1.getPos()[1]+paddleVelocity);
		}
		
		if(p2.getUp()==true && p2.getPos()[1]>=0){
			p2.setPos_y(p2.getPos()[1]-paddleVelocity);
		}
		else if(p2.getDown()==true && p2.getPos()[1]<=screen_y){
		    p2.setPos_y(p2.getPos()[1]+paddleVelocity);
		}
		
		ball.setPositionx(ball.getPosition()[0]+ballVelocity*ball.getVector()[0]);
		ball.setPositiony(ball.getPosition()[1]+ballVelocity*ball.getVector()[1]);
		}}
		else if(numberOfPlayers==1){
			collisionHandler();
			
			if(finalPosition==-1){
				simulation();
			}
			
			if(playing){
				
			/*if(p1.getUp()==true && p1.getPos()[1]>=0){
				p1.setPos_y(p1.getPos()[1]-paddleVelocity);
			}
			else if(p1.getDown()==true && p1.getPos()[1]<=screen_y){
			    p1.setPos_y(p1.getPos()[1]+paddleVelocity);
			}*/
			
			
			if(finalPosition==2){
				if(p2.getPos()[1]+computerPaddleZone*paddleSize[1]/7< ballSim.getPosition()[1]){
					if(ballSim.getPosition()[1]-(p2.getPos()[1]+computerPaddleZone*paddleSize[1]/7)>6){
					p2.setPos_y(p2.getPos()[1]+computerPaddleVelocity);
					}
				}
				else if(p2.getPos()[1]+computerPaddleZone*paddleSize[1]/7>ballSim.getPosition()[1]){
					if((p2.getPos()[1]+computerPaddleZone*paddleSize[1]/7)-ballSim.getPosition()[1]>6){
					p2.setPos_y(p2.getPos()[1]-computerPaddleVelocity);
					}
				}
			}

		   if(finalPosition==1){

				if(p1.getPos()[1]+computerPaddleZone*paddleSize[1]/7< ballSim.getPosition()[1]){
					if(ballSim.getPosition()[1]-(p1.getPos()[1]+computerPaddleZone*paddleSize[1]/7)>6){
						p1.setPos_y(p1.getPos()[1]+computerPaddleVelocity);
					}
				}
				else if(p1.getPos()[1]+computerPaddleZone*paddleSize[1]/7>ballSim.getPosition()[1]){
					if((p1.getPos()[1]+computerPaddleZone*paddleSize[1]/7)-ballSim.getPosition()[1]>6){
						p1.setPos_y(p1.getPos()[1]-computerPaddleVelocity);
					}
				}
			}

			ball.setPositionx(ball.getPosition()[0]+ballVelocity*ball.getVector()[0]);
			ball.setPositiony(ball.getPosition()[1]+ballVelocity*ball.getVector()[1]);
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

	public void collisionHandler(){

		boolean collidedWithPaddle=false;

		/*if(ball.getPosition()[0]<=ballSize/2+player2pos[0]+paddleSize[0]/2 && ball.getPosition()[1]<= player2pos[1]+paddleSize[1]/2 && ball.getPosition()[1]>= player2pos[1]-paddleSize[1]/2 && ball.getVector()[0]<0){

			collidedWithPaddle=true;
			ball.setVector_x(-ball.getVector()[0]);
		}

        if(ball.getPosition()[0]>=player1pos[0]-paddleSize[0]/2-ballSize/2 && ball.getPosition()[1]<= player1pos[1]+paddleSize[1]/2 && ball.getPosition()[1]>= player1pos[1]-paddleSize[1]/2 && ball.getVector()[0]>0){
        	collidedWithPaddle=true;
        	ball.setVector_x(-ball.getVector()[0]);

		}*/

		//Player 2 Paddle

		if(ball.getPosition()[0]<=ballSize/2+p2.getPos()[0]+paddleSize[0]/2 &&
				ball.getPosition()[1]<= p2.getPos()[1]+paddleSize[1]/2 && 
				ball.getPosition()[1]> p2.getPos()[1]+paddleSize[1]/2-paddleSize[1]/7 &&
				ball.getVector()[0]<0 &&
				ball.getPosition()[0]>=p2.getPos()[0]-paddleSize[0]/2){

			collidedWithPaddle=true;
			ball.setVector_x(Math.cos(Math.toRadians(60)));
			ball.setVector_y(Math.sin(Math.toRadians(60)));
			finalPosition=-1;
		}else if(ball.getPosition()[0]<=ballSize/2+p2.getPos()[0]+paddleSize[0]/2 &&
				ball.getPosition()[1]<=p2.getPos()[1]+paddleSize[1]/2-paddleSize[1]/7 && 
				ball.getPosition()[1]> p2.getPos()[1]+paddleSize[1]/2-2*paddleSize[1]/7 &&
				ball.getVector()[0]<0 &&
				ball.getPosition()[0]>=p2.getPos()[0]-paddleSize[0]/2){

			collidedWithPaddle=true;
			ball.setVector_x(Math.cos(Math.toRadians(40)));
			ball.setVector_y(Math.sin(Math.toRadians(40)));
			finalPosition=-1;
		}else if(ball.getPosition()[0]<=ballSize/2+p2.getPos()[0]+paddleSize[0]/2 &&
				ball.getPosition()[1]<= p2.getPos()[1]+paddleSize[1]/2-2*paddleSize[1]/7 && 
				ball.getPosition()[1]>  p2.getPos()[1]+paddleSize[1]/2-3*paddleSize[1]/7 && 
				ball.getVector()[0]<0 &&
				ball.getPosition()[0]>=p2.getPos()[0]-paddleSize[0]/2){

			collidedWithPaddle=true;
			ball.setVector_x(Math.cos(Math.toRadians(20)));
			ball.setVector_y(Math.sin(Math.toRadians(20)));
			finalPosition=-1;
		}else if(ball.getPosition()[0]<=ballSize/2+p2.getPos()[0]+paddleSize[0]/2 &&
				ball.getPosition()[1]<= p2.getPos()[1]+paddleSize[1]/2-3*paddleSize[1]/7 &&
				ball.getPosition()[1]>  p2.getPos()[1]+paddleSize[1]/2-4*paddleSize[1]/7 && 
				ball.getVector()[0]<0 &&
				ball.getPosition()[0]>=p2.getPos()[0]-paddleSize[0]/2){

			collidedWithPaddle=true;
			ball.setVector_x(Math.cos(Math.toRadians(0)));
			ball.setVector_y(Math.sin(Math.toRadians(0)));
			finalPosition=-1;
		}else if(ball.getPosition()[0]<=ballSize/2+p2.getPos()[0]+paddleSize[0]/2 &&
				ball.getPosition()[1]<= p2.getPos()[1]+paddleSize[1]/2-4*paddleSize[1]/7 && 
				ball.getPosition()[1]>  p2.getPos()[1]+paddleSize[1]/2-5*paddleSize[1]/7 &&
				ball.getVector()[0]<0 &&
				ball.getPosition()[0]>=p2.getPos()[0]-paddleSize[0]/2){

			collidedWithPaddle=true;
			ball.setVector_x(Math.cos(Math.toRadians(20)));
			ball.setVector_y(-Math.sin(Math.toRadians(20)));
			finalPosition=-1;
		}else if(ball.getPosition()[0]<=ballSize/2+p2.getPos()[0]+paddleSize[0]/2 && 
				ball.getPosition()[1]<= p2.getPos()[1]+paddleSize[1]/2-5*paddleSize[1]/7 &&
				ball.getPosition()[1]>  p2.getPos()[1]+paddleSize[1]/2-6*paddleSize[1]/7 && 
				ball.getVector()[0]<0 &&
				ball.getPosition()[0]>=p2.getPos()[0]-paddleSize[0]/2){

			collidedWithPaddle=true;
			ball.setVector_x(Math.cos(Math.toRadians(40)));
			ball.setVector_y(-Math.sin(Math.toRadians(40)));
			finalPosition=-1;
		}else if(ball.getPosition()[0]<=ballSize/2+p2.getPos()[0]+paddleSize[0]/2 && 
				ball.getPosition()[1]<= p2.getPos()[1]+paddleSize[1]/2-6*paddleSize[1]/7 && 
				ball.getPosition()[1]>=  p2.getPos()[1]+paddleSize[1]/2-7*paddleSize[1]/7 &&
				ball.getVector()[0]<0 &&
				ball.getPosition()[0]>=p2.getPos()[0]-paddleSize[0]/2){

			collidedWithPaddle=true;
			ball.setVector_x(Math.cos(Math.toRadians(60)));
			ball.setVector_y(-Math.sin(Math.toRadians(60)));
			finalPosition=-1;
		}

		//Player 1 Paddle

		if(ball.getPosition()[0]>=p1.getPos()[0]-paddleSize[0]/2-ballSize/2 &&
				ball.getPosition()[1]<= p1.getPos()[1]+paddleSize[1]/2 &&
				ball.getPosition()[1]> p1.getPos()[1]+paddleSize[1]/2-paddleSize[1]/7 &&
				ball.getVector()[0]>0 && 
				ball.getPosition()[0]<=p1.getPos()[0]+paddleSize[0]/2){

			collidedWithPaddle=true;
			ball.setVector_x(-Math.cos(Math.toRadians(60)));
			ball.setVector_y(Math.sin(Math.toRadians(60)));
			finalPosition=-1;
		}else if(ball.getPosition()[0]>=p1.getPos()[0]-paddleSize[0]/2-ballSize/2 && 
				ball.getPosition()[1]<=p1.getPos()[1]+paddleSize[1]/2-paddleSize[1]/7 && 
				ball.getPosition()[1]> p1.getPos()[1]+paddleSize[1]/2-2*paddleSize[1]/7 && 
				ball.getVector()[0]>0 &&
				ball.getPosition()[0]<=p1.getPos()[0]+paddleSize[0]/2){

			collidedWithPaddle=true;
			ball.setVector_x(-Math.cos(Math.toRadians(40)));
			ball.setVector_y(Math.sin(Math.toRadians(40)));
			finalPosition=-1;
		}else if(ball.getPosition()[0]>=p1.getPos()[0]-paddleSize[0]/2-ballSize/2 && 
				ball.getPosition()[1]<= p1.getPos()[1]+paddleSize[1]/2-2*paddleSize[1]/7 &&
				ball.getPosition()[1]> p1.getPos()[1]+paddleSize[1]/2-3*paddleSize[1]/7 &&
				ball.getVector()[0]>0 && 
				ball.getPosition()[0]<=p1.getPos()[0]+paddleSize[0]/2){

			collidedWithPaddle=true;
			ball.setVector_x(-Math.cos(Math.toRadians(20)));
			ball.setVector_y(Math.sin(Math.toRadians(20)));
			finalPosition=-1;
		}else if(ball.getPosition()[0]>=p1.getPos()[0]-paddleSize[0]/2-ballSize/2 && 
				ball.getPosition()[1]<= p1.getPos()[1]+paddleSize[1]/2-3*paddleSize[1]/7 && 
				ball.getPosition()[1]> p1.getPos()[1]+paddleSize[1]/2-4*paddleSize[1]/7 &&
				ball.getVector()[0]>0 && 
				ball.getPosition()[0]<=p1.getPos()[0]+paddleSize[0]/2){

			collidedWithPaddle=true;
			ball.setVector_x(-Math.cos(Math.toRadians(0)));
			ball.setVector_y(Math.sin(Math.toRadians(0)));
			finalPosition=-1;
		}else if(ball.getPosition()[0]>=p1.getPos()[0]-paddleSize[0]/2-ballSize/2 &&
				ball.getPosition()[1]<= p1.getPos()[1]+paddleSize[1]/2-4*paddleSize[1]/7 && 
				ball.getPosition()[1]> p1.getPos()[1]+paddleSize[1]/2-5*paddleSize[1]/7 &&
				ball.getVector()[0]>0 &&
				ball.getPosition()[0]<=p1.getPos()[0]+paddleSize[0]/2){

			collidedWithPaddle=true;
			ball.setVector_x(-Math.cos(Math.toRadians(20)));
			ball.setVector_y(-Math.sin(Math.toRadians(20)));
			finalPosition=-1;
		}else if(ball.getPosition()[0]>=p1.getPos()[0]-paddleSize[0]/2-ballSize/2 &&
				ball.getPosition()[1]<= p1.getPos()[1]+paddleSize[1]/2-5*paddleSize[1]/7 && 
				ball.getPosition()[1]> p1.getPos()[1]+paddleSize[1]/2-6*paddleSize[1]/7 &&
				ball.getVector()[0]>0 && 
				ball.getPosition()[0]<=p1.getPos()[0]+paddleSize[0]/2){

			collidedWithPaddle=true;
			ball.setVector_x(-Math.cos(Math.toRadians(40)));
			ball.setVector_y(-Math.sin(Math.toRadians(40)));
			finalPosition=-1;
		}else if(ball.getPosition()[0]>=p1.getPos()[0]-paddleSize[0]/2-ballSize/2 && 
				ball.getPosition()[1]<= p1.getPos()[1]+paddleSize[1]/2-6*paddleSize[1]/7 &&
				ball.getPosition()[1]>= p1.getPos()[1]+paddleSize[1]/2-7*paddleSize[1]/7 && 
				ball.getVector()[0]>0 && 
				ball.getPosition()[0]<=p1.getPos()[0]+paddleSize[0]/2){

			collidedWithPaddle=true;
			ball.setVector_x(-Math.cos(Math.toRadians(60)));
			ball.setVector_y(-Math.sin(Math.toRadians(60)));
			finalPosition=-1;
		}


		if(!collidedWithPaddle){

			if(ball.getPosition()[0]<=ballSize/2 && ball.getVector()[0]<0){
				playing=false;
			}
			else if(ball.getPosition()[1]<=ballSize/2 && ball.getVector()[1]<0){
				ball.setVector_y(-ball.getVector()[1]);
			}
			else if(ball.getPosition()[0]>=screen_x-ballSize/2 && ball.getVector()[0]>0){
				playing=false;
			}
			else if(ball.getPosition()[1]>=screen_y-ballSize/2 && ball.getVector()[1]>0){
				ball.setVector_y(-ball.getVector()[1]);

			}
			
			if(o!=null){

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
		}
	}

	public void reset(){
		p1=new Player(startingPos[0],startingPos[1]);
		p2=new Player(startingPos[2],startingPos[3]);
		ball.setPositionx(startingPos[4]);
		ball.setPositiony(startingPos[5]);
		ball.setVector_x(1);
		ball.setVector_y(0);
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
				ball.setVector_x(Math.cos(Math.toRadians((Math.toDegrees(Math.acos(ball.getVector()[0]))+1))));
				ball.setVector_y(Math.cos(Math.toRadians((Math.toDegrees(Math.acos(ball.getVector()[1]))+1))));
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
			ballSim.setPositionx(ballSim.getPosition()[0]+ballVelocity*ballSim.getVector()[0]);
			ballSim.setPositiony(ballSim.getPosition()[1]+ballVelocity*ballSim.getVector()[1]);
			}
	}

	private void simCollisionHandler() {
		
		if(ballSim.getPosition()[0]<=p2.getPos()[0]+paddleSize[0]/2+ballSize/2 && ballSim.getVector()[0]<0){
			finalPosition=2;
		}
		else if(ballSim.getPosition()[1]<=ballSize/2 && ballSim.getVector()[1]<0){
			ballSim.setVector_y(-ballSim.getVector()[1]);
		}
		else if(ballSim.getPosition()[0]>=p1.getPos()[0]-paddleSize[0]/2-ballSize/2 && ballSim.getVector()[0]>0){
			finalPosition=1;
		}
		else if(ballSim.getPosition()[1]>=screen_y-ballSize/2 && ballSim.getVector()[1]>0){
			ballSim.setVector_y(-ballSim.getVector()[1]);

		}
		
		if(o!=null){

		for(int i=0;i< o.length;i++){

			if(ballSim.getVector()[1]>0 &&
					ballSim.getPosition()[1]<o[i].getK()[0]*ballSim.getPosition()[0]+o[i].getB()[0] &&
					ballSim.getPosition()[1]<o[i].getK()[1]*ballSim.getPosition()[0]+o[i].getB()[1] &&
					ballSim.getPosition()[1]>o[i].getPos()[1]-o[i].getSize()[1]/2-ballSize/2 &&
					ballSim.getPosition()[0]<o[i].getPos()[0]+o[i].getSize()[0]/2 && 
					ballSim.getPosition()[0]>o[i].getPos()[0]-o[i].getSize()[0]/2){
				ballSim.setVector_y(-ballSim.getVector()[1]);
			}
			else if(ballSim.getVector()[0]<0 && 
					ballSim.getPosition()[1]<o[i].getK()[0]*ballSim.getPosition()[0]+o[i].getB()[0] &&
					ballSim.getPosition()[1]>o[i].getK()[1]*ballSim.getPosition()[0]+o[i].getB()[1] && 
					ballSim.getPosition()[0]<o[i].getPos()[0]+o[i].getSize()[0]/2+ballSize/2 && 
					ballSim.getPosition()[1]<o[i].getPos()[1]+o[i].getSize()[1]/2 && 
					ballSim.getPosition()[1]>o[i].getPos()[1]-o[i].getSize()[1]/2){
				ballSim.setVector_x(-ballSim.getVector()[0]);
			}
			else if(ballSim.getVector()[1]<0 && 
					ballSim.getPosition()[1]>o[i].getK()[0]*ballSim.getPosition()[0]+o[i].getB()[0] && 
					ballSim.getPosition()[1]>o[i].getK()[1]*ballSim.getPosition()[0]+o[i].getB()[1] && 
					ballSim.getPosition()[1]<o[i].getPos()[1]+o[i].getSize()[1]/2+ballSize/2 &&
					ballSim.getPosition()[0]<o[i].getPos()[0]+o[i].getSize()[0]/2 && 
					ballSim.getPosition()[0]>o[i].getPos()[0]-o[i].getSize()[0]/2){
				ballSim.setVector_y(-ballSim.getVector()[1]);
			}
			else if(ballSim.getVector()[0]>0 &&
					ballSim.getPosition()[1]>o[i].getK()[0]*ballSim.getPosition()[0]+o[i].getB()[0] && 
					ballSim.getPosition()[1]<o[i].getK()[1]*ballSim.getPosition()[0]+o[i].getB()[1] && 
					ballSim.getPosition()[0]>o[i].getPos()[0]-o[i].getSize()[0]/2-ballSize/2 && 
					ballSim.getPosition()[1]<o[i].getPos()[1]+o[i].getSize()[1]/2 &&
					ballSim.getPosition()[1]>o[i].getPos()[1]-o[i].getSize()[1]/2){
				ballSim.setVector_x(-ballSim.getVector()[0]);
			}
			else if(Math.sqrt((Math.pow(((o[i].getPos()[0]-o[i].getSize()[0]/2)-ballSim.getPosition()[0]),2)+Math.pow(((o[i].getPos()[1]-o[i].getSize()[1]/2)-ballSim.getPosition()[1]),2)))<= ballSize/2 && !(ballSim.getVector()[0]<0 && ballSim.getVector()[1]<0)){
				ballSim.setVector_x(-Math.cos(Math.toRadians(45)));
				ballSim.setVector_y(-Math.sin(Math.toRadians(45)));

			}
			else if(Math.sqrt((Math.pow(((o[i].getPos()[0]+o[i].getSize()[0]/2)-ballSim.getPosition()[0]),2)+Math.pow(((o[i].getPos()[1]-o[i].getSize()[1]/2)-ballSim.getPosition()[1]),2)))<= ballSize/2 && !(ballSim.getVector()[0]>0 && ballSim.getVector()[1]<0)){
				ballSim.setVector_x(Math.cos(Math.toRadians(45)));
				ballSim.setVector_y(-Math.sin(Math.toRadians(45)));
			}
			else if(Math.sqrt((Math.pow(((o[i].getPos()[0]+o[i].getSize()[0]/2)-ballSim.getPosition()[0]),2)+Math.pow(((o[i].getPos()[1]+o[i].getSize()[1]/2)-ballSim.getPosition()[1]),2)))<= ballSize/2 && !(ballSim.getVector()[0]>0 && ballSim.getVector()[1]>0)){
				ballSim.setVector_x(Math.cos(Math.toRadians(45)));
				ballSim.setVector_y(Math.sin(Math.toRadians(45)));
			}
			else if(Math.sqrt((Math.pow(((o[i].getPos()[0]-o[i].getSize()[0]/2)-ballSim.getPosition()[0]),2)+Math.pow(((o[i].getPos()[1]+o[i].getSize()[1]/2)-ballSim.getPosition()[1]),2)))<= ballSize/2 && !(ballSim.getVector()[0]<0 && ballSim.getVector()[1]>0)){
				ballSim.setVector_x(-Math.cos(Math.toRadians(45)));
				ballSim.setVector_y(Math.sin(Math.toRadians(45)));
			}

		}
		}
	}	
}
