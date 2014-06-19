package controller;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import view.GuiClass;
import view.OpeningWindow;

import model.Plate;
import model.PlateFactory;
import model.PlatesPool;
import model.PlayerFactory;
import model.SaveGame;
import model.State;
import model.User;

public class Controller implements Serializable{

	// for dynamic loading
	private Class[] type = { Color.class };
	private Class firstClass = null;
	private Class secondClass = null;
	private String path = "C:\\Users\\techno city\\Desktop";
	private PlateFactory factory;
	private State cuurentState;
	private static Controller con = null;
	private PlatesPool newPool = null;
	private PlayerFactory playerFactory;
	private User firstPlayer;
	private User secondPlayer;
	private ArrayList<Plate> plates;
	private Random randomGen;

	private Controller() {
		randomGen = new Random();
	}

	public static Controller createController() {
		if (con == null) {
			con = new Controller();
		}

		return con;
	}

	public PlatesPool getPool() {
		return newPool;
	}

	public void begin() throws MalformedURLException, ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {

		dynamicLoading();
		factory = PlateFactory.createFactory();
		factory.setFirstClass(firstClass);
		factory.setSecondClass(secondClass);
		newPool = PlatesPool.createPlatePool();
		plates = newPool.getShapesPool();
		playerFactory = PlayerFactory.createFactory();
		firstPlayer = playerFactory.makePlayer(1, "First player");
		secondPlayer = playerFactory.makePlayer(2, "Second player");

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpeningWindow frame = new OpeningWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public void update(Plate p) {
		p.update();
		// dead plate
		if (p.getY() > 1000) {
			int yCoordinate = randomGen.nextInt(990) - 990;
			p.setY(yCoordinate);

		} else {
			firstPlayer.checkCollision(p);
			secondPlayer.checkCollision(p);
			int arr1[] = firstPlayer.checkStatus();

			int arr2[] = secondPlayer.checkStatus();
			deleteThree(arr1);
			deleteThree(arr2);
		}

	}

	public Iterator getIterator() {
		Iterator iterator = newPool.getUnlockedIterator();

		return iterator;
	}

	public int getFirstPlayerX() {
		return firstPlayer.getX();
	}

	public int getFirstPlayery() {
		return firstPlayer.getY();
	}

	public int getSecondPlayerX() {
		return secondPlayer.getX();
	}

	public int getSecondPlayerY() {
		return secondPlayer.getY();
	}

	public long getFirstPlayerScore() {
		return firstPlayer.getPlayerScore();
	}

	public String getFirstPlayerName() {
		return firstPlayer.getPlayerName();
	}

	public String getSecondPlayerName() {
		return secondPlayer.getPlayerName();
	}

	public long getSecondPlayerScore() {
		return secondPlayer.getPlayerScore();
	}

	public void setFirstPlayerX(int x) {
		firstPlayer.setX(x);
	}

	public void setSecondPlayerX(int x) {
		secondPlayer.setX(x);
	}

	public void addPlateFirst(Plate p, int index) {
		firstPlayer.addPlate(p, index);
	}

	public void addPlateSecond(Plate p, int index) {
		secondPlayer.addPlate(p, index);
	}

	public Iterator getSecondPlayerHand(int index) {
		return secondPlayer.getHand(index).iterator();
	}

	public Iterator getFirstPlayerHand(int index) {
		return firstPlayer.getHand(index).iterator();
	}

	public void updateCargo(Plate p) {
		int id = p.getPlayerId(p);
		if (id == 1) {
			firstPlayer.updateCargo(p);
		} else if (id == 2) {
			secondPlayer.updateCargo(p);
		}
	}

	private void deleteThree(int arr[]) {
		if (arr[0] != -1) {
			Iterator it = getIterator();
			while (it.hasNext()) {
				Object ob = it.next();
				Plate p = (Plate) ob;
				if (p.getIndex() == arr[0] || p.getIndex() == arr[1]
						|| p.getIndex() == arr[2]) {
					if (p.getPlayerId(p) == 1) {
						firstPlayer.realeaseGrab(p);
						firstPlayer.incrementScore();
					} else if (p.getPlayerId(p) == 2) {
						secondPlayer.realeaseGrab(p);
						secondPlayer.incrementScore();
					}
					p.realeaseGrab(p);
				}
			}
		}
	}

	public void dynamicLoading() throws MalformedURLException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {

		File file = new File(path);
		URL url = file.toURL();
		URL[] urls = new URL[] { url };
		ClassLoader cl = new URLClassLoader(urls);
		firstClass = cl.loadClass("Square");
		secondClass = cl.loadClass("Rectangle");

	}// end method
	
	public void saveFile(String input) throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		SaveGame sa = new SaveGame();
		sa.saveFile(input);
	}
	
	public void loadFile() throws FileNotFoundException, ClassNotFoundException, IOException{
		SaveGame s = new SaveGame();
		con = s.load(s.getFileName());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiClass frame = new GuiClass();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			});
	}
	
	public void runGame(){
		
		EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				GuiClass frame = new GuiClass();
				frame.setPool(newPool.getShapesPool());

				frame.setVisible(true);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});

	}
	
	public boolean checkEnd(){
		boolean one=firstPlayer.checkEnd();
		boolean two=secondPlayer.checkEnd();
		if (one || two)return true;
		return false;
	}

}
