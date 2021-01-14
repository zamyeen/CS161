/**
 * @author Zareef
 * Date: April 12, 2018
 *The Road method does all the drawing of the lanes and checks for collisions as well as checks where the position of the car is after the next move
 */
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Road extends JPanel{

	private static final long serialVersionUID = 1;
	
	//Variables and Constants
	int NUMBER_OF_LANES = 5;
	int width = 800;
	int height;
	int lane_width;
	ArrayList<Vehicle> cars = new ArrayList<Vehicle>();
	int carCount = 0;
	
	//This Constructor calls on this entire class
	public Road() {
		super();
	}
	
	//Add car adds a car into the ArrayList
	public void addCar (Vehicle v) {
		cars.add(v);
	}
	
	//The PaintComponent method draws the road with specifications such as width, height and lane_width
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		width = this.getHeight();
		height = this.getWidth();
		lane_width = 120;
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.WHITE);
		for (int i = lane_width; i < lane_width * (NUMBER_OF_LANES - 1); i = i + lane_width) {
			//Changing variable i changes column height's (width of the lane)
			for (int j = 0; j < width; j = j + 40/**By changing the interval that j changes by you create space between each line that you draw (creating dashed lines)*/) {
				g.fillRect(j, i, 30/**how much space between lines*/, 5/**how large the lines are*/);
				/**g.fillRect(x, y, width, height);*/
			}
		}
		//For all the cars that are inside the arraylist cars draw it
		for (int i = 0; i < cars.size(); i++) {
			cars.get(i).paintVehicle(g);		
		}
	}
	
	//The step method goes through all the cars in the arraylist and checks if there is a collision
	public void step() {
		for (int i = 0; i < cars.size(); i++) {
			Vehicle v = cars.get(i);
			if(collision(v.getX()+v.getSpeed(),v.getY(),v) == false) {//if car moves forward by speed and doesn't hit car move forward
				v.setX(v.getX()+v.getSpeed());
				if(v.getX() > width) {
					if(collision(0,v.getY(),v) == false) {//if wrapping back to 0 there is no collision move
						v.setX(0);
						carCount += 1;
					}
				}
			}
			else {//If the vehicles lane is not the left most and there is not a car to the left move to the next left lane
				if (v.getY() > 40 && (collision(v.getX(),v.getY() - lane_width, v) == false)) {
					v.setY(v.getY()-lane_width);
				}
				//Else if the vehicles lane is not the right most lane and there is not a car to the right move to the next right lane
				else if((v.getY() < 40 + 3 * lane_width) && (collision(v.getX(),v.getY() + lane_width,v) == false)){
					v.setY(v.getY() + lane_width);
				}
			}
		}
	}
	 
	
	public boolean collision(int x, int y, Vehicle v) {
		for (int a = 0; a < cars.size(); a++) {
			Vehicle u = cars.get(a);
			if (y == u.getY()) {//If the car is in a specific lane (Y coordinate)
				if(u.equals(v) == false) {//Doesn't count hitting if the vehicle hits itself
					if(x < u.getX() + u.getWidth() //If the x value is less than the vehicles position after a step
					&& x + v.getWidth() > u.getX()) {//And if the x value is hitting another car right in front of it
						return true;
					}
				}
			}
		}
		return false;
	}

	public int getCarCount() {
		return carCount;
	}
	
	public void resetCarCount() {
		carCount = 0;
	}
	
}