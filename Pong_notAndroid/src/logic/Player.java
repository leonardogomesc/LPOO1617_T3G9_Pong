package logic;

public class Player {
private double pos[];

private boolean up;
private boolean down;

public Player (double x, double y){
	pos=new double[2];
	pos[0]=x;
	pos[1]=y;
	up=false;
	down=false;
}


public double[] getPos() {
	return pos;
}

public void setPos_x(double pos) {
	this.pos[0] = pos;
}

public void setPos_y(double pos) {
	this.pos[1] = pos;
}

public boolean getUp() {
	return up;
}

public void setUp(boolean up) {
	this.up = up;
}

public boolean getDown() {
	return down;
}

public void setDown(boolean down) {
	this.down = down;
}
}
