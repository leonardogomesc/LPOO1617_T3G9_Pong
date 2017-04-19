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
		ball.setPositionx(ball.getPosition()[0]+0.00005*ball.getVector()[0]);
		ball.setPositiony(ball.getPosition()[1]+0.00005*ball.getVector()[1]);
		
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

}
