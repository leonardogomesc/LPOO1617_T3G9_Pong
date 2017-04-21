package logic;

public class Game {
	private Ball ball;
	private Player p1;
	private Player p2;
	private Obstacle o[];
	private double ballVelocity;
	private double paddleVelocity;
	public static final int ballSize=24;
	public static final int paddleSize[]={10,90};
	private boolean playing;
	
	private double startingPos[];
	
	public Game(){
		ball=new Ball(500,150,0);
		
		p1=new Player(724,150);
		p2=new Player(10,150);
	
		playing=false;
		
		ballVelocity=7;
		paddleVelocity=4;
		
		o=new Obstacle[5];
		
		o[0]=new Obstacle(210,80,50,50);
		o[1]=new Obstacle(510,80,50,50);
		o[2]=new Obstacle(210,412-80,50,50);
		o[3]=new Obstacle(510,412-80,50,50);
		o[4]=new Obstacle(360,200,50,50);
		
		startingPos=new double[]{p1.getPos()[0],p1.getPos()[1],p2.getPos()[0],p2.getPos()[1],ball.getPosition()[0],ball.getPosition()[1]};
		
	}
	
	public void update(){
		collisionHandler();
		
		if(playing){
			
		if(p1.getUp()==true && p1.getPos()[1]>=0){
			p1.setPos_y(p1.getPos()[1]-paddleVelocity);
		}
		else if(p1.getDown()==true && p1.getPos()[1]<=412){
		    p1.setPos_y(p1.getPos()[1]+paddleVelocity);
		}
		
		if(p2.getUp()==true && p2.getPos()[1]>=0){
			p2.setPos_y(p2.getPos()[1]-paddleVelocity);
		}
		else if(p2.getDown()==true && p2.getPos()[1]<=412){
		    p2.setPos_y(p2.getPos()[1]+paddleVelocity);
		}
		
		ball.setPositionx(ball.getPosition()[0]+ballVelocity*ball.getVector()[0]);
		ball.setPositiony(ball.getPosition()[1]+ballVelocity*ball.getVector()[1]);
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

        if(ball.getPosition()[0]<=ballSize/2+p2.getPos()[0]+paddleSize[0]/2 && ball.getPosition()[1]<= p2.getPos()[1]+paddleSize[1]/2 && ball.getPosition()[1]> p2.getPos()[1]+paddleSize[1]/2-paddleSize[1]/7 && ball.getVector()[0]<0){

        	collidedWithPaddle=true;
        	ball.setVector_x(Math.cos(Math.toRadians(60)));
        	ball.setVector_y(Math.sin(Math.toRadians(60)));
        }else if(ball.getPosition()[0]<=ballSize/2+p2.getPos()[0]+paddleSize[0]/2 && ball.getPosition()[1]<=p2.getPos()[1]+paddleSize[1]/2-paddleSize[1]/7 && ball.getPosition()[1]> p2.getPos()[1]+paddleSize[1]/2-2*paddleSize[1]/7 && ball.getVector()[0]<0){

        	collidedWithPaddle=true;
        	ball.setVector_x(Math.cos(Math.toRadians(40)));
        	ball.setVector_y(Math.sin(Math.toRadians(40)));
        }else if(ball.getPosition()[0]<=ballSize/2+p2.getPos()[0]+paddleSize[0]/2 && ball.getPosition()[1]<= p2.getPos()[1]+paddleSize[1]/2-2*paddleSize[1]/7 && ball.getPosition()[1]>  p2.getPos()[1]+paddleSize[1]/2-3*paddleSize[1]/7 && ball.getVector()[0]<0){

        	collidedWithPaddle=true;
        	ball.setVector_x(Math.cos(Math.toRadians(20)));
        	ball.setVector_y(Math.sin(Math.toRadians(20)));
        }else if(ball.getPosition()[0]<=ballSize/2+p2.getPos()[0]+paddleSize[0]/2 && ball.getPosition()[1]<= p2.getPos()[1]+paddleSize[1]/2-3*paddleSize[1]/7 && ball.getPosition()[1]>  p2.getPos()[1]+paddleSize[1]/2-4*paddleSize[1]/7 && ball.getVector()[0]<0){

        	collidedWithPaddle=true;
        	ball.setVector_x(Math.cos(Math.toRadians(0)));
        	ball.setVector_y(Math.sin(Math.toRadians(0)));
        }else if(ball.getPosition()[0]<=ballSize/2+p2.getPos()[0]+paddleSize[0]/2 && ball.getPosition()[1]<= p2.getPos()[1]+paddleSize[1]/2-4*paddleSize[1]/7 && ball.getPosition()[1]>  p2.getPos()[1]+paddleSize[1]/2-5*paddleSize[1]/7 && ball.getVector()[0]<0){

        	collidedWithPaddle=true;
        	ball.setVector_x(Math.cos(Math.toRadians(20)));
        	ball.setVector_y(-Math.sin(Math.toRadians(20)));
        }else if(ball.getPosition()[0]<=ballSize/2+p2.getPos()[0]+paddleSize[0]/2 && ball.getPosition()[1]<= p2.getPos()[1]+paddleSize[1]/2-5*paddleSize[1]/7 && ball.getPosition()[1]>  p2.getPos()[1]+paddleSize[1]/2-6*paddleSize[1]/7 && ball.getVector()[0]<0){

        	collidedWithPaddle=true;
        	ball.setVector_x(Math.cos(Math.toRadians(40)));
        	ball.setVector_y(-Math.sin(Math.toRadians(40)));
        }else if(ball.getPosition()[0]<=ballSize/2+p2.getPos()[0]+paddleSize[0]/2 && ball.getPosition()[1]<= p2.getPos()[1]+paddleSize[1]/2-6*paddleSize[1]/7 && ball.getPosition()[1]>=  p2.getPos()[1]+paddleSize[1]/2-7*paddleSize[1]/7 && ball.getVector()[0]<0){

        	collidedWithPaddle=true;
        	ball.setVector_x(Math.cos(Math.toRadians(60)));
        	ball.setVector_y(-Math.sin(Math.toRadians(60)));
        }
        
        //Player 1 Paddle
        
        if(ball.getPosition()[0]>=p1.getPos()[0]-paddleSize[0]/2-ballSize/2 && ball.getPosition()[1]<= p1.getPos()[1]+paddleSize[1]/2 && ball.getPosition()[1]> p1.getPos()[1]+paddleSize[1]/2-paddleSize[1]/7 && ball.getVector()[0]>0){

        	collidedWithPaddle=true;
        	ball.setVector_x(-Math.cos(Math.toRadians(60)));
        	ball.setVector_y(Math.sin(Math.toRadians(60)));
        }else if(ball.getPosition()[0]>=p1.getPos()[0]-paddleSize[0]/2-ballSize/2 && ball.getPosition()[1]<=p1.getPos()[1]+paddleSize[1]/2-paddleSize[1]/7 && ball.getPosition()[1]> p1.getPos()[1]+paddleSize[1]/2-2*paddleSize[1]/7 && ball.getVector()[0]>0){

        	collidedWithPaddle=true;
        	ball.setVector_x(-Math.cos(Math.toRadians(40)));
        	ball.setVector_y(Math.sin(Math.toRadians(40)));
        }else if(ball.getPosition()[0]>=p1.getPos()[0]-paddleSize[0]/2-ballSize/2 && ball.getPosition()[1]<= p1.getPos()[1]+paddleSize[1]/2-2*paddleSize[1]/7 && ball.getPosition()[1]> p1.getPos()[1]+paddleSize[1]/2-3*paddleSize[1]/7 && ball.getVector()[0]>0){

        	collidedWithPaddle=true;
        	ball.setVector_x(-Math.cos(Math.toRadians(20)));
        	ball.setVector_y(Math.sin(Math.toRadians(20)));
        }else if(ball.getPosition()[0]>=p1.getPos()[0]-paddleSize[0]/2-ballSize/2 && ball.getPosition()[1]<= p1.getPos()[1]+paddleSize[1]/2-3*paddleSize[1]/7 && ball.getPosition()[1]> p1.getPos()[1]+paddleSize[1]/2-4*paddleSize[1]/7 && ball.getVector()[0]>0){

        	collidedWithPaddle=true;
        	ball.setVector_x(-Math.cos(Math.toRadians(0)));
        	ball.setVector_y(Math.sin(Math.toRadians(0)));
        }else if(ball.getPosition()[0]>=p1.getPos()[0]-paddleSize[0]/2-ballSize/2 && ball.getPosition()[1]<= p1.getPos()[1]+paddleSize[1]/2-4*paddleSize[1]/7 && ball.getPosition()[1]> p1.getPos()[1]+paddleSize[1]/2-5*paddleSize[1]/7 && ball.getVector()[0]>0){

        	collidedWithPaddle=true;
        	ball.setVector_x(-Math.cos(Math.toRadians(20)));
        	ball.setVector_y(-Math.sin(Math.toRadians(20)));
        }else if(ball.getPosition()[0]>=p1.getPos()[0]-paddleSize[0]/2-ballSize/2 && ball.getPosition()[1]<= p1.getPos()[1]+paddleSize[1]/2-5*paddleSize[1]/7 && ball.getPosition()[1]> p1.getPos()[1]+paddleSize[1]/2-6*paddleSize[1]/7 && ball.getVector()[0]>0){

        	collidedWithPaddle=true;
        	ball.setVector_x(-Math.cos(Math.toRadians(40)));
        	ball.setVector_y(-Math.sin(Math.toRadians(40)));
        }else if(ball.getPosition()[0]>=p1.getPos()[0]-paddleSize[0]/2-ballSize/2 && ball.getPosition()[1]<= p1.getPos()[1]+paddleSize[1]/2-6*paddleSize[1]/7 && ball.getPosition()[1]>= p1.getPos()[1]+paddleSize[1]/2-7*paddleSize[1]/7 && ball.getVector()[0]>0){

        	collidedWithPaddle=true;
        	ball.setVector_x(-Math.cos(Math.toRadians(60)));
        	ball.setVector_y(-Math.sin(Math.toRadians(60)));
        }
        
        
		
		
		if(!collidedWithPaddle){
			
		if(ball.getPosition()[0]<=ballSize/2 && ball.getVector()[0]<0){
			playing=false;
		}
		else if(ball.getPosition()[1]<=ballSize/2 && ball.getVector()[1]<0){
			ball.setVector_y(-ball.getVector()[1]);
		}
		else if(ball.getPosition()[0]>=734-ballSize/2 && ball.getVector()[0]>0){
			playing=false;
		}
		else if(ball.getPosition()[1]>=412-ballSize/2 && ball.getVector()[1]>0){
			ball.setVector_y(-ball.getVector()[1]);
			
		}
		
		for(int i=0;i< o.length;i++){

			if(ball.getVector()[1]>0 && ball.getPosition()[1]<o[i].getK()[0]*ball.getPosition()[0]+o[i].getB()[0] && ball.getPosition()[1]<o[i].getK()[1]*ball.getPosition()[0]+o[i].getB()[1] && ball.getPosition()[1]>o[i].getPos()[1]-o[i].getSize()[1]/2-ballSize/2 && ball.getPosition()[0]<o[i].getPos()[0]+o[i].getSize()[0]/2+ballSize/4 && ball.getPosition()[0]>o[i].getPos()[0]-o[i].getSize()[0]/2-ballSize/4){
				ball.setVector_y(-ball.getVector()[1]);
			}
			else if(ball.getVector()[0]<0 && ball.getPosition()[1]<o[i].getK()[0]*ball.getPosition()[0]+o[i].getB()[0] && ball.getPosition()[1]>o[i].getK()[1]*ball.getPosition()[0]+o[i].getB()[1] && ball.getPosition()[0]<o[i].getPos()[0]+o[i].getSize()[0]/2+ballSize/2 && ball.getPosition()[1]<o[i].getPos()[1]+o[i].getSize()[1]/2+ballSize/4 && ball.getPosition()[1]>o[i].getPos()[1]-o[i].getSize()[1]/2-ballSize/4){
				ball.setVector_x(-ball.getVector()[0]);
			}
			else if(ball.getVector()[1]<0 && ball.getPosition()[1]>o[i].getK()[0]*ball.getPosition()[0]+o[i].getB()[0] && ball.getPosition()[1]>o[i].getK()[1]*ball.getPosition()[0]+o[i].getB()[1] && ball.getPosition()[1]<o[i].getPos()[1]+o[i].getSize()[1]/2+ballSize/2 && ball.getPosition()[0]<o[i].getPos()[0]+o[i].getSize()[0]/2+ballSize/4 && ball.getPosition()[0]>o[i].getPos()[0]-o[i].getSize()[0]/2-ballSize/4){
				ball.setVector_y(-ball.getVector()[1]);
			}
			else if(ball.getVector()[0]>0 && ball.getPosition()[1]>o[i].getK()[0]*ball.getPosition()[0]+o[i].getB()[0] && ball.getPosition()[1]<o[i].getK()[1]*ball.getPosition()[0]+o[i].getB()[1] && ball.getPosition()[0]>o[i].getPos()[0]-o[i].getSize()[0]/2-ballSize/2 && ball.getPosition()[1]<o[i].getPos()[1]+o[i].getSize()[1]/2+ballSize/4 && ball.getPosition()[1]>o[i].getPos()[1]-o[i].getSize()[1]/2-ballSize/4){
				ball.setVector_x(-ball.getVector()[0]);
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
		
}
