package model;
import java.awt.Color;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.*;



public class PlatesPool implements Serializable{

	private static  PlatesPool newPlatePool=null;
	private PlateFactory newFactory;
	private Plate newPlate;
//	private HashMap<Integer,Plate> locked;
//	//unlocked map only will be offered to the controller class.	
//	private HashMap<Integer,Plate> unlocked;
	private ArrayList< Plate> locked ;
	private ArrayList< Plate> pool ;
	//random generator for pool;
	Random randomGen=new Random();
	//pool max size.
	private int poolSize;
	//colors array
	private Color [] colArray={Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW};
	
	//static pointer on the most recent object
	private int currentPlateKey;
	
	
	
	
	
	
	private PlatesPool() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		pool=new ArrayList<Plate>();
		locked=new ArrayList<Plate>();
		poolSize=100;
		currentPlateKey=0;
		newFactory=PlateFactory.createFactory();
	
		int Objectrange=2;
		int xRange=2000-40;
		int yrange=990;
		int colorRange=4;
		for (int i=0;i< poolSize;i++){
			//for id (1,2)
			int id =randomGen.nextInt(Objectrange)+1;
			//for x coordinate
			int xCoordinate=randomGen.nextInt(xRange)-10;
			if (xCoordinate>1700)xCoordinate=1700;
			
			int ycoordinate=randomGen.nextInt(yrange)-990;
			//for color(0-3)
			int col=randomGen.nextInt(colorRange)+0;
			
			newPlate=newFactory.makePlate(id, xCoordinate, ycoordinate	, colArray[col], i);
			pool.add(newPlate);
	
		}
		
	}
	
	//used to get an object from the pool.
	public boolean checkIn(){
		if (locked.size()>0){
			Plate p= locked.get(0);
			pool.add(p);
			locked.remove(0);
			return true;
		}else{
			return false;
		}
	}
	
	
	
	//used to remove an object from the pool
	public void checkOut(Plate p){
		locked.add(p);
		for(Plate s : pool){
			if (s.index==p.index){
				pool.remove(s);
				break;
			}
		}
	}
	
	
	
	
	
	//singleton
	public static PlatesPool createPlatePool() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		if (newPlatePool==null){
			newPlatePool=new PlatesPool();
		}
		
		return newPlatePool;
	}
	
	//iterator
	public Iterator getUnlockedIterator(){
		return pool.iterator();
	}
	
	//lazem a3dlha (unlocked)
	public ArrayList< Plate> getShapesPool(){
		return pool;
	}
	
	
	
}
