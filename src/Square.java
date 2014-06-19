import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.Random;
import model.Plate;
public class Square extends Plate implements Serializable{

	private Random rendomGenerator = new Random();

		public Square(int xCoordinate, int yCoordinate, Color col, int index) {
			this.x = xCoordinate;
			this.y = yCoordinate;
			this.PlateColor = col;
			this.index = index;
			this.dx = rendomGenerator.nextInt(3)-1;
			//this.dx=1;
			this.dy = 1;
			this.cargoDir = 0;
			this.caught = false;
			this.hight = 40;
			this.width = 40;
			this.PlayerId = 0;
			this.startingTime = System.currentTimeMillis();
		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub

		}

		@Override
		public void update() {
			long currentTime = System.currentTimeMillis();
			long diff = 2000;
			if (currentTime - this.startingTime >= diff) {
				this.startingTime=currentTime;
				if (dx == 1) {
					dx = -1;
				} else if (dx == -1) {
					dx = 1;
				}
			}

			
			this.x += dx;
			if (this.x>1900){
				this.x=1900;
				this.dx=-1;
			}
			if (this.x<0){
				this.x=0;
				this.dx=1;
			}
			this.y += dy;
		}

		@Override
		public int getX() {
			// TODO Auto-generated method stub
			return this.x;
		}

		@Override
		public int getY() {
			// TODO Auto-generated method stub
			return this.y;
		}

		@Override
		public void setY(int y) {
			// TODO Auto-generated method stub
			this.y = y;
		}

		@Override
		public int getWidth() {
			// TODO Auto-generated method stub
			return width;
		}

		@Override
		public int getHeight() {
			// TODO Auto-generated method stub
			return hight;
		}

		@Override
		public int getIndex() {
			// TODO Auto-generated method stub
			return index;
		}

		@Override
		public Color getColor() {
			// TODO Auto-generated method stub
			return this.PlateColor;
		}

		@Override
		public boolean isCaught() {
			// TODO Auto-generated method stub
			return this.caught;
		}

		@Override
		public long getStartTime() {
			// TODO Auto-generated method stub
			return this.startingTime;
		}

		@Override
		public int getCargoDirection(Plate p) {
			// TODO Auto-generated method stub
			return this.cargoDir;
		}

		@Override
		public int getPlayerId(Plate p) {
			// TODO Auto-generated method stub
			return this.PlayerId;
		}

		@Override
		public void realeaseGrab(Plate p) {
			// TODO Auto-generated method stub
			this.cargoDir = 0;
			this.caught = false;
			this.PlayerId = 0;
			int yCoordinate = rendomGenerator.nextInt(990) - 990;
			p.setY(yCoordinate);
		}

	}

	

