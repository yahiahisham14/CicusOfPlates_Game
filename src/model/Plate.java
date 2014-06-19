package model;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.io.Serializable;



public abstract class Plate implements Serializable {

	//1.abstract methods
	
	//Paint method for painting inside each class for dynamic loading.
	public abstract void paint(Graphics g);
	public abstract void update();
	
	public abstract int getX();
	public abstract int getY();
	public abstract void setY(int y);
	public abstract int getWidth();
	public abstract int getHeight();
	public abstract int getIndex();
	public abstract Color getColor();
	public abstract boolean isCaught();
	public abstract long getStartTime();
	public abstract int getCargoDirection(Plate p);
	public abstract int getPlayerId(Plate p);
	public abstract void realeaseGrab(Plate p); 
	//2.protected variables
	//to define color of the shape
	protected Color PlateColor;
	
	//to define begining of the shape 
	protected int x;
	protected int y;
	protected int width;
	protected int hight;
	protected int dx;
	protected int dy;
	protected boolean caught;
	protected long startingTime;
	protected int cargoDir;
	protected int PlayerId;
	//every shape has its own index.
	protected int index;

	

	
}
