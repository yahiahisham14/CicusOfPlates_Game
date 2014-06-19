package controller;

import java.awt.EventQueue;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Random;

import model.Plate;
import model.PlatesPool;

import view.GuiClass;

public class Main implements Serializable {
	/**
	 * Launch the application.
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		Controller control=null;
		control=Controller.createController();
		control.begin();
	
				
		
		
		

	}

}
