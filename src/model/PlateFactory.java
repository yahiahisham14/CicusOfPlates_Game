package model;

import java.awt.Color;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

public class PlateFactory implements Serializable {
//dynamic Loading
	private static PlateFactory myFactory;
	private Class firstClass = null;
	private Class secondClass = null;
	private Class [] type = {int.class,int.class, Color.class ,int.class};
	
	
	
	private static PlateFactory newPlateFactory=null;
	
	private PlateFactory(){
		
	}
	
	public static  PlateFactory createFactory(){
		if (newPlateFactory==null){
			newPlateFactory=new PlateFactory();
		}
		
		return newPlateFactory;
	}
	public Plate makePlate(int id,int xCoordinate,int yCoordinate,Color col,int index) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		Plate p=null;
		switch (id) {
		case 1:
			
			Object obj = firstClass.getConstructor(type).newInstance( xCoordinate ,yCoordinate,col,index );
			p = (Plate) obj;
			break;
		case 2:
			Object ob = secondClass.getConstructor(type).newInstance( xCoordinate ,yCoordinate,col,index );
			p = (Plate) ob;
			break;
		default:
			return null;
			
		}
		
		return p;
	}
	
	public void setFirstClass( Class cl ){
		firstClass = cl;
		
	}
	
	public void setSecondClass( Class cl ){
		secondClass = cl;
		
	}
	
}
