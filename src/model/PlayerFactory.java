package model;

import java.awt.Color;
import java.io.Serializable;

public class PlayerFactory implements Serializable {

private static PlayerFactory newPlayerFactory=null;
	
	private PlayerFactory(){
		
	}
	
	public static  PlayerFactory createFactory(){
		if (newPlayerFactory==null){
			newPlayerFactory=new PlayerFactory();
		}
		
		return newPlayerFactory;
	}
	public User makePlayer(int id,String name){
		
		switch (id) {
		case 1:
			
			return FirstPlayer.createUser(name);
			
		case 2:
			return SecondPlayer.createUser(name);
		default:
			return null;
			
		}
	}
	
	
}
