/*
 * Panel for Conway's life simulation. This draws the grid
 * and the cells
 * Date: 11/13/17
 * Author: Zareef Amyeen
 */
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class LifePanel extends JPanel{
	//Variables
	boolean[][] cells;
	double width;
	double height;
	
//Method that makes cells from Life.java the same as the cells in the Panel
	public LifePanel(boolean[][] in) {
		cells = in;
	}
	//This method makes all nextCells from the step function so that they can be painted in
	public void setCells(boolean [][] nextCells){
		cells = nextCells;
	}
	//This method  finds the width and height of each cells dimensions
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		width = (double)this.getWidth() / cells[0].length;
		height = (double)this.getHeight() / cells.length;
		
		//Draw the Cells
		g.setColor(Color.CYAN);
		for (int row = 0; row < cells.length; row++) {
			for (int column = 0; column < cells[0].length; column++) {
				if(cells[row][column] == true) {
					g.fillRect((int)Math.round(column*width),
							(int)Math.round(row*height),
							(int)width+1,(int)height+1);
				}
			}
		}
		
		//Draw the grid
		g.setColor(Color.BLACK);
		for (int x = 0; x < cells[0].length + 1; x++) {
			g.drawLine((int)Math.round(x*width), 0, (int)Math.round(x*width),this.getHeight());
		}
		for (int y = 0; y < cells.length; y++) {
			g.drawLine(0, (int)Math.round(y*height),this.getWidth(),(int) Math.round(y*height));
		}
	}
	
	
	
	private static final long serialVersionUID = 1L;
}