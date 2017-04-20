package logic;

public class Game {
	private Ball ball;
	private double player1pos[];
	private double player2pos[];
	public boolean p1Up,p1Down;
	public boolean p2Up,p2Down;
	private double ballVelocity;
	private double paddleVelocity;
	public static final int ballSize=24;
	public static final int paddleSize[]={10,90};
	private boolean playing;
	
	private double startingPos[];
	
	public Game(){
		ball=new Ball();
		
		player1pos=new double[2];
		player1pos[0]=724;
		player1pos[1]=150;
		
		player2pos=new double[2];
		player2pos[0]=10;
		player2pos[1]=150;
	
		playing=false;
		p1Up=false;
		p1Down=false;
		p2Up=false;
		p2Down=false;
		
		ballVelocity=10;
		paddleVelocity=5;
		
		startingPos=new double[]{player1pos[0],player1pos[1],player2pos[0],player2pos[1],ball.getPosition()[0],ball.getPosition()[1]};
		
	}
	
	public void update(){
		collisionHandler();
		
		if(playing){
			
		if(p1Up==true && player1pos[1]>=0+paddleSize[1]/2){
			player1pos[1]=player1pos[1]-paddleVelocity;
		}
		else if(p1Down==true && player1pos[1]<=412-paddleSize[1]/2){
			player1pos[1]=player1pos[1]+paddleVelocity;
		}
		
		if(p2Up==true && player2pos[1]>=0+paddleSize[1]/2){
			player2pos[1]=player2pos[1]-paddleVelocity;
		}
		else if(p2Down==true && player2pos[1]<=412-paddleSize[1]/2){
			player2pos[1]=player2pos[1]+paddleVelocity;
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
		
		if(ball.getPosition()[0]<=ballSize/2+player2pos[0]+paddleSize[0]/2 && ball.getPosition()[1]<= player2pos[1]+paddleSize[1]/2 && ball.getPosition()[1]>= player2pos[1]-paddleSize[1]/2 && ball.getVector()[0]<0){
			
			collidedWithPaddle=true;
			ball.setVector_x(-ball.getVector()[0]);
		}
		
        if(ball.getPosition()[0]>=player1pos[0]-paddleSize[0]/2-ballSize/2 && ball.getPosition()[1]<= player1pos[1]+paddleSize[1]/2 && ball.getPosition()[1]>= player1pos[1]-paddleSize[1]/2 && ball.getVector()[0]>0){
        	collidedWithPaddle=true;
        	ball.setVector_x(-ball.getVector()[0]);
			
		}
		
		
		if(!collidedWithPaddle){
			
		if(ball.getPosition()[0]<=12 && ball.getVector()[0]<0){
			playing=false;
		}
		else if(ball.getPosition()[1]<=12 && ball.getVector()[1]<0){
			ball.setVector_y(-ball.getVector()[1]);
		}
		else if(ball.getPosition()[0]>=734-12 && ball.getVector()[0]>0){
			playing=false;
		}
		else if(ball.getPosition()[1]>=412-12 && ball.getVector()[1]>0){
			ball.setVector_y(-ball.getVector()[1]);
			
		}
		
		}
			
		
	}

	public double[] getPlayer1pos() {
		return player1pos;
	}

	public double[] getPlayer2pos() {
		return player2pos;
	}
	
	public void reset(){
		player1pos[0]=startingPos[0];
		player1pos[1]=startingPos[1];
		player2pos[0]=startingPos[2];
		player2pos[1]=startingPos[3];
		ball.setPositionx(startingPos[4]);
		ball.setPositiony(startingPos[5]);
	}
}
