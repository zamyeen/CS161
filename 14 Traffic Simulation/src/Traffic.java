/**
 * The Traffic class is the Main Class
 * Date: April 12, 2018
 * @author Zareef Amyeen
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Traffic implements ActionListener,Runnable{

	JFrame frame = new JFrame("Traffic Simulation");
	Road road = new Road();
	//South Container
	Container south = new Container();
	JButton start = new JButton("Start");
	JButton stop = new JButton("Stop");
	JLabel throughput = new JLabel("Throughput:0");
	//West Container
	JButton semi = new JButton("Add Semi");
	JButton suv = new JButton ("Add SUV");
	JButton sports = new JButton ("Add Sports");
	Container west = new Container();
	int carCount = 0;
	long startTime = 0;
	boolean running;
	
	public Traffic() {
		frame.setSize(800, 600);
		frame.setLayout(new BorderLayout());
		frame.add(road,BorderLayout.CENTER);
		
		south.setLayout(new GridLayout(1,2));
		south.add(start);
		start.addActionListener(this);
		south.add(stop);
		stop.addActionListener(this);
		south.add(throughput);
		frame.add(south, BorderLayout.SOUTH);
		
		west.setLayout(new GridLayout(3,1));
		west.add(semi);
		semi.addActionListener(this);
		west.add(suv);
		suv.addActionListener(this);
		west.add(sports);
		sports.addActionListener(this);
		frame.add(west, BorderLayout.WEST);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		/**Semi testSemi = new Semi(10,20);
		SUV testSUV = new SUV(10,170);
		Sports testSports = new Sports (10,320);
		road.addCar(testSemi);
		road.addCar(testSUV);
		road.addCar(testSports);*/
		frame.repaint();
	}
	
	public static void main (String[] args){
		new Traffic();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		//If the button start has been pressed reset carcount and start a new throughput
		if (event.getSource().equals(start)) {
			if(!running) {
				running = true;
				road.resetCarCount();
				startTime = System.currentTimeMillis();
				Thread t = new Thread(this);
				t.start();
			}
		}
		if (event.getSource().equals(stop)) {
			running = false;
		}
		//If any of the buttons: Semi, SUV or Sports are pressed that means that if a space is available a car will be added
		if (event.getSource().equals(semi)){
			Semi semi = new Semi(0,30);
			//Initial spot is in the left (top) most lane
			road.addCar(semi);
			for (int x = 0; x < road.width; x = x + 20) {
				for (int y = 40; y < 480; y = y + 120) {
					semi.setX(x);
					semi.setY(y);
					if (road.collision(x, y, semi) == false) {
						//If their is no collision keep the road and repaint
						frame.repaint();
						return;
					}
				}
			}
		}
		if (event.getSource().equals(suv)){
			SUV suv = new SUV(0,30);
			road.addCar(suv);
			for (int x = 0; x < road.width; x = x + 20) {
				for (int y = 40; y < 480; y = y + 120) {
					suv.setX(x);
					suv.setY(y);
					if (road.collision(x, y, suv) == false) {
						frame.repaint();
						return;
					}
				}
			}
		}
		if (event.getSource().equals(sports)){
			Sports sports = new Sports(0,30);
			road.addCar(sports);
			for (int x = 0; x < road.width; x = x + 20) {
				for (int y = 40; y < 480; y = y + 120) {
					sports.setX(x);
					sports.setY(y);
					if (road.collision(x, y, sports) == false) {
						frame.repaint();
						return;
					}
				}
			}
		}
	}

	@Override
	//If the program is running (the button start has been pressed than make boolean variable running = true (which will keep the game running) and calculate for throughput value
	public void run() {
		while (running) {
			road.step();
			//Move the cars to the next method
			carCount = road.getCarCount();
			double throughputCalc = carCount / (1000*(double)(System.currentTimeMillis()-startTime));
			throughput.setText("Throughput: " + throughputCalc);
			//Repaint the frame and change throughput value
			frame.repaint();
			try {
				Thread.sleep(50);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
}