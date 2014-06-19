package model;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class User implements Serializable {


	//used to add shape to the array list of the payer.
	public abstract void addShape(Plate p,int stackSelection);
	
	//used to remove a plate from the arrayList of the player.
	
	//return the index of the deleted objects to be freed from the pool;
	// returns -1 if no objects deleted
	public abstract int[] checkStatus(); 
	
	//used to get and set player Score.
	public abstract long getPlayerScore();
	public abstract void setPlayerScore(long score);

	public abstract void setX(int index);
	public abstract void setY(int y);
	public abstract void addPlate(Plate p,int index);
	public abstract void updateCargo(Plate p);
	public abstract void realeaseGrab(Plate p);
	public abstract ArrayList<Plate> getHand(int index);
	public abstract void updateCargo(int arr[]);
	public abstract void incrementScore();
	public abstract boolean checkEnd();
	//used to get Player Name.
	public abstract String getPlayerName();
	
	//used to get player id(first,Second)
	public abstract int getID();
	public abstract int getX();
	public abstract int getY();
	public abstract int getRightY();
	public abstract int getLeftY();
	public abstract void setY(int index,int y);
	public abstract int checkCollision(Plate P);
	protected int rightY;
	protected int leftY;
	
	//protected variables
	//variables
	
		//shapes on the right and left hands;
		protected ArrayList< Plate> cargo;
		
		//player name.
		protected String playerName;
		
		//player score.
		protected long playerScore;
		
		//player id;
		protected int id;
		
		protected int xCoordinate;
		protected int yCoordinate;
		protected int dx;
}
