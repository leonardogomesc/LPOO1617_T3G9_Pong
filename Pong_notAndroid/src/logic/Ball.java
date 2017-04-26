package logic;

public class Ball {

	private double position[];
	private double vector[];
	
	
	public Ball(double x,double y, double vx,double vy){
		position=new double[2];
		position[0]=x;
		position[1]=y;
		vector=new double[2];
		vector[0]=vx;
		vector[1]=vy;
      }
	
	

	public double[] getPosition() {
		return position;
	}


	public void setPositionx(double position) {
		this.position[0] = position;
	}
	
	public void setPositiony(double position) {
		this.position[1] = position;
	}


	public double[] getVector() {
		return vector;
	}
	
	public void setVector_x(double vector) {
		this.vector[0] = vector;
	}
	
	public void setVector_y(double vector) {
		this.vector[1] = vector;
	}


	public void setVector(double[] vector) {
		this.vector = vector;
	}
}
