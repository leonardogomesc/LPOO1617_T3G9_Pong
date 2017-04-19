package logic;

public class Game {
	private Ball ball;
	private int player1pos[];
	private int player2pos[];
	boolean playing;
	
	public Game(){
		ball=new Ball();
		playing=false;
	}
	
	public void update(){
		collisionHandler();
		
		ball.setPositionx(ball.getPosition()[0]+10*ball.getVector()[0]);
		ball.setPositiony(ball.getPosition()[1]+10*ball.getVector()[1]);
		
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
		
		if(ball.getPosition()[0]<=12 && ball.getVector()[0]<0){
			ball.setVector_x(-ball.getVector()[0]);
		}
		else if(ball.getPosition()[1]<=12 && ball.getVector()[1]<0){
			ball.setVector_y(-ball.getVector()[1]);
		}
		else if(ball.getPosition()[0]>=734-12 && ball.getVector()[0]>0){
			ball.setVector_x(-ball.getVector()[0]);
		}
		else if(ball.getPosition()[1]>=412-12 && ball.getVector()[1]>0){
			ball.setVector_y(-ball.getVector()[1]);
			
		}
		
	}

}
