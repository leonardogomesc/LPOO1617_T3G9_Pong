package com.example.up201503708.pong;

public class Obstacle {
private double k[];
private double b[];
private double pos[];
private int size[];

public Obstacle(double x,double y,int size_x,int size_y){
	pos=new double[2];
	pos[0]=x;
	pos[1]=y;
	size=new int [2];
	size[0]=size_x;
	size[1]=size_y;
	k=new double [2];
	b=new double [2];
	calculator();
}

public double[] getPos() {
	return pos;
}

public int[] getSize() {
	return size;
}

public double[] getK() {
	return k;
}

public double[] getB() {
	return b;
}

private void calculator(){
	int x1,x2,x3,x4,y1,y2,y3,y4; 
	
	x1=(int)(pos[0]-size[0]/2);
	y1=(int)(pos[1]-size[1]/2);
	
	x2=(int)(pos[0]+size[0]/2);
	y2=(int)(pos[1]-size[1]/2);
	
	x3=(int)(pos[0]-size[0]/2);
	y3=(int)(pos[1]+size[1]/2);
	
	x4=(int)(pos[0]+size[0]/2);
	y4=(int)(pos[1]+size[1]/2);
	
	k[0]=(double)((y4-y1)/(x4-x1));
	k[1]=(double)((y3-y2)/(x3-x2));
	
	b[0]=(double)(y1-k[0]*x1);
	b[1]=(double)(y2-k[1]*x2);
}
}
