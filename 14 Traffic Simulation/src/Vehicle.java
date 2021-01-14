/**
 * @author Zareef
 * Date: April 12, 2018
 *The Get methods all are methods that you can call from other classes
 *The Set methods are all methods that you can set values from other classes
 *All the different vehicle seperate classes have the dimension of each vehicle
 */

import java.awt.*;

public class Vehicle {

	int x;
	int y;
	int width = 0;
	int height = 0;
	int speed = 0;
	
	public Vehicle (int newx, int newy) {
		x = newx;
		y = newy;
	}
	
	public void paintVehicle(Graphics g) {
		
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int newx) {
		x = newx;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int newy) {
		y = newy;
	}
	
	public int getWidth() {
		return width;
	}
	
}