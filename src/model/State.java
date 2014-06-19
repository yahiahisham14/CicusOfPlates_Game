package model;

import java.io.Serializable;



public class State implements Serializable {

	
	PlateState freeFall;
	PlateState dead;
	PlateState onPlayer;
	
	PlateState state;
	
	public State(){
		freeFall=new FreeFallState();
		dead=new DeadState();
		onPlayer= new OnPlayerState();
		state=freeFall;
	}
	
	public void setState(PlateState state){
		this.state = state;
	}
	

	
	public PlateState getFreeFallState(){
		return freeFall;
	}
	public PlateState getDeadState(){
		return dead;
	}
	public PlateState getOnPlayerState(){
		return onPlayer;
	}

	
}
