/*
 * This program is an implementation of Conway's Life
 * simulation
 * Date: November 13th, 2017
 * Author: Zareef Amyeen
 */


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Life implements MouseListener,ActionListener, Runnable{
	
	//Variables and Objects
	boolean [][] cells = new boolean[25][25];
	JFrame frame = new JFrame("Life simulation");
	LifePanel panel = new LifePanel(cells);
	Container south = new Container();
	JButton step = new JButton("Step");
	JButton start = new JButton("Start");
	JButton stop = new JButton("Stop");
	boolean running = false;
	
	//Constructor
	public Life() {
		frame.setSize(600,600);
		frame.setLayout(new BorderLayout());
		frame.add(panel,BorderLayout.CENTER);
		panel.addMouseListener(this);
		//south container
		south.setLayout(new GridLayout(1,3));
		south.add(step);
		step.addActionListener(this);
		south.add(start);
		start.addActionListener(this);
		south.add(stop);
		stop.addActionListener(this);
		frame.add(south, BorderLayout.SOUTH);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	//main method that calls constructor
	public static void main(String[] args) {
		new Life();
	}
	
	public void mouseClicked(MouseEvent event) {
	}
	
	public void mouseEntered(MouseEvent event) {
	}
	
	public void mouseExited(MouseEvent event) {
	}
	
	public void mousePressed(MouseEvent event) {	
	}
	//checks for where mouse was released and makes boolean value for that cell false (which means it is painted in) and then repainted
	public void mouseReleased(MouseEvent event) {
		double width = (double)panel.getWidth()/cells[0].length;
		double height = (double)panel.getHeight()/cells.length;
		int column = Math.min(cells[0].length-1,(int)(event.getX()/width));
		int row = Math.min(cells.length-1,(int)(event.getY()/height));
		System.out.println(column + "," + row);
		cells[row][column] = !cells[row][column];
		frame.repaint();
	}
	//checks for which of the Jbuttons has been pressed and then steps(one step) or starts the continual loop which doesnít end (unless stop is pressed) 
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(step) == true) {
			System.out.println("Step");
			step();
		}
		else if(event.getSource().equals(start) == true) {
			System.out.println("Start");
			if(running == false){
				running = true;
				Thread t = new Thread(this);
				t.start();
			}
		}
		else if(event.getSource().equals(stop) == true){
			System.out.println("Stop");
			running = false;
		}
	}

	//this method keeps start loop running and puts a slight 250 millisecond º second delay in between each step
	public void run() {
		while(running == true){
			step();
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/*
	 * row-1,column-1		row-1,column		row-1,column+1
	 * row,column-1			row,column			row,column+1
	 * row+1,column-1		row+1,column		row+1,column+1
	 */

//this method checks for how many neighbors each cell has by going through the entire array and then deciding if the cell will die or not at the end
	public void step(){
		boolean[][] nextCells = new boolean[cells.length][cells[0].length];
		for (int row = 0; row < cells.length; row++) {
			for (int column = 0; column < cells[0].length; column++) {
				int neighborCount = 0;
//how many neighbors does each cell have
				if(row > 0 && column > 0 && cells[row-1][column-1] == true){
					neighborCount++;
				}
				if(row > 0 && cells[row-1][column] == true){
					neighborCount++;
				}
				if(row > 0 && column < cells.length-1 && cells[row-1][column+1] == true){//Shouldn't this be column + 1
					neighborCount++;
				}
				if(column > 0 && cells[row][column-1] == true){
					neighborCount++;
				}
				if(column < cells[0].length-1 && cells[row][column + 1] == true){
					neighborCount++;
				}
				if(row < cells.length-1 && column > 0 && cells[row+1][column-1] == true){
					neighborCount++;
				}
				if(row < cells.length-1 && cells[row+1][column] == true){
					neighborCount++;
				}
				if(row < cells.length -1 && column < cells[0].length-1 && cells[row + 1][column + 1] == true){
					neighborCount++;
				}
				//If  the next generation of cells are going to die or not
				if(cells[row][column] == true){
					if(neighborCount == 2 || neighborCount == 3){
						nextCells[row][column] = true;
					}
					else{
						nextCells[row][column] = false;
					}
				}
				else{
					if(neighborCount == 3){
						nextCells[row][column] = true;
					}
					else{
						nextCells[row][column]= false;
					}
				}
			}
		}
	
	cells = nextCells;
	panel.setCells(nextCells);
	frame.repaint();
	}
}
	
