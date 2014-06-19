package model;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class SecondPlayer extends User implements Serializable {

	private static SecondPlayer playerTwo;

	private SecondPlayer(String name) {
		playerName = name;
		id = 2;
		cargo = new ArrayList<Plate>();
		this.xCoordinate = 1000;
		this.yCoordinate = 700;
		this.playerScore=0;
		this.dx = 10;
		this.rightY = yCoordinate;
		this.leftY = yCoordinate;
	}

	public static User createUser(String name) {
		if (playerTwo == null) {
			playerTwo = new SecondPlayer(name);
		}
		return playerTwo;
	}

	@Override
	public void addShape(Plate p, int stackSelection) {
		cargo.add(p);
	}

	@Override
	public String getPlayerName() {
		return this.playerName;

	}

	@Override
	public int getID() {

		return this.id;
	}

	@Override
	public void setPlayerScore(long score) {
		this.playerScore = score;

	}

	@Override
	public long getPlayerScore() {

		return this.playerScore;
	}

	@Override
	public int[] checkStatus() {
		int arr[] = new int[3];
		Arrays.fill(arr, -1);
		if (cargo.size()>=3){
			Color lastColor = this.cargo.get(cargo.size() - 1).PlateColor;
			int direction = this.cargo.get(cargo.size() - 1).cargoDir;
			ArrayList<Plate> right =new ArrayList<Plate>();
			ArrayList<Plate> left =new ArrayList<Plate>();
			for (int i=0;i<cargo.size();i++){
				if (cargo.get(i).cargoDir==1){
					right.add(cargo.get(i));
				}else {
					left.add(cargo.get(i));
				}
			}
			
			//Right Pile
			if (cargo.get(cargo.size()-1).cargoDir==1){
				if (right.size()>=3){
					if (right.get(right.size()-2).PlateColor.equals(lastColor) && right.get(right.size()-3).PlateColor.equals(lastColor)){
						arr[0]=right.get(right.size()-1).index;
						arr[1]=right.get(right.size()-2).index;
						arr[2]=right.get(right.size()-3).index;
						this.updateCargo(arr);
						return arr;
					}
				}
			}else if (cargo.get(cargo.size()-1).cargoDir==2){
				if (left.size()>=3){
					if (left.get(left.size()-2).PlateColor.equals(lastColor) && left.get(left.size()-3).PlateColor.equals(lastColor)){
						arr[0]=left.get(left.size()-1).index;
						arr[1]=left.get(left.size()-2).index;
						arr[2]=left.get(left.size()-3).index;
						this.updateCargo(arr);
						return arr;
					}
				}
			}
		}
		return arr;
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return this.xCoordinate;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return this.yCoordinate;
	}

	@Override
	public void setX(int index) {
		if (index == 1) {
			this.xCoordinate += dx;
		} else {
			this.xCoordinate -= dx;
		}
		if (this.xCoordinate + 30 > 1750) {
			this.xCoordinate = 1720;
		} else if (this.xCoordinate < 0) {
			this.xCoordinate = 0;
		}
	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub
		this.yCoordinate = y;
	}

	@Override
	public void addPlate(Plate p, int index) {
		cargo.add(p);

	}

	@Override
	public ArrayList<Plate> getHand(int index) {
		return cargo;
	}

	@Override
	public int getRightY() {

		return this.rightY;
	}

	@Override
	public int getLeftY() {
		// TODO Auto-generated method stub
		return this.leftY;
	}

	@Override
	public void setY(int index, int y) {
		if (index == 1) {
			this.rightY = y;
		} else if (index == 2) {
			this.leftY = y;
		}

	}

	@Override
	public int checkCollision(Plate p) {

		boolean found = false;
		for (int i = 0; i < cargo.size(); i++) {
			Plate s = cargo.get(i);
			if (p.index == s.index)
				found = true;
		}
		// Right pile
		if (!found) {
			if (Math.abs(this.xCoordinate + 230 - p.getX()) < 30
					&& Math.abs(p.getY() + p.getHeight() - this.rightY) < 5) {
				p.caught = true;
				p.cargoDir = 1;
				p.y = this.rightY - p.getHeight();
				p.x = this.xCoordinate + 230;
				p.PlayerId = this.id;
				this.rightY -= p.hight;
				this.cargo.add(p);
				return p.index;

			} else if (Math.abs(p.getX() - this.xCoordinate - 10) < 40
					&& Math.abs((p.getHeight() + p.y) - (this.getLeftY())) < 5) {
				// left pile
				// check left y
				p.caught = true;
				p.cargoDir = 2;
				p.PlayerId = this.id;
				p.x = this.xCoordinate + 20;
				p.y = this.leftY - p.getHeight();
				this.leftY -= p.hight;
				this.cargo.add(p);
				return p.index;
			}
		}
		return -1;

	}

	@Override
	public void updateCargo(Plate p) {
		// right plate
		if (p.cargoDir == 2) {
			p.x = this.getX() + 20;
		} else if (p.cargoDir == 1) {
			p.x = this.getX() + 235;
		}

	}

	@Override
	public void realeaseGrab(Plate p) {
		if (p.cargoDir == 1) {
			this.rightY += p.getHeight();
		} else if (p.cargoDir == 2) {
			this.leftY += p.getHeight();
		}

	}

	@Override
	public void updateCargo(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < cargo.size(); j++) {
				if (arr[i]==cargo.get(j).index){
					cargo.remove(j);
				}
			}
			
		}
		
	}
	@Override
	public void incrementScore() {
		// TODO Auto-generated method stub
		this.playerScore+=100;
	}
	@Override
	public boolean checkEnd() {
		
		if (this.leftY<=20 || this.rightY<=20)return true;
		return false;
	}
}
