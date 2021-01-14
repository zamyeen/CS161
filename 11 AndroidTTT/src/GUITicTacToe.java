/*
 * This project uses Graphic User Interface from Java to Create a Tic Tac Board outside of the console. 
 * Two users will press the buttons on the board to make value X or O, and the user can also change their number.
 * Each time a button is pressed the program will check for a Tie or a Win and will reset the board and update the score.
 * Author: Zareef Amyeen
 * Date : 10/24/17
 */

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUITicTacToe implements ActionListener{
	
			
	JFrame frame = new JFrame();
	JButton[][] button = new JButton [3][3];
	int [][] board = new int[3][3];
	final int BLANK = 0;
	final int X_MOVE = 1;
	final int O_MOVE = 2;
	final int X_TURN = 0;
	final int O_TURN = 1;
	int turn = X_TURN;
	Container center = new Container();
	JLabel xLabel = new JLabel("  Player X wins: 0   ");
	JLabel oLabel = new JLabel("  Player O wins: 0   ");
	JButton xChangeName = new JButton("Change X's Name.");
	JButton oChangeName = new JButton("Change O's Name.");
	JTextField xChangeField = new JTextField("");
	JTextField oChangeField = new JTextField("");
	Container south = new Container();
	String xPlayerName = "X ";
	String oPlayerName = "O ";
	int xwins = 0;
	int owins = 0;

	//Constructor for Tic Tac Toe, creates board
	public GUITicTacToe() {
		frame.setSize(500, 500);
		//Center grid container
		frame.setLayout(new BorderLayout());
		center.setLayout(new GridLayout (3,3));
		for (int i = 0; i < button.length; i++) {
			for (int j = 0; j < button[0].length; j++) {
				button[j][i] = new JButton(j+ "," + i);
				center.add(button[j][i]);
				button[j][i].addActionListener(this);
			}
		}
		
		
		frame.add(center, BorderLayout.CENTER);
		south.setLayout(new GridLayout(3,2));
		south.add(xLabel);
		south.add(oLabel);
		south.add(xChangeName);
		xChangeName.addActionListener(this);
		south.add(oChangeName);
		oChangeName.addActionListener(this);
		south.add(xChangeField);
		south.add(oChangeField);
		frame.add(south, BorderLayout.SOUTH);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		clearBoard();
	}
	
	//Main method runs constructor
	public static void main(String[] args) {
		new GUITicTacToe();
	}

	//checks for if Button has been pressed, and calls checkWin and checkTie method to check if someone has won
	public void actionPerformed(ActionEvent event) {
		JButton current;
		
		boolean gridButton = false;
		for (int i = 0; i < button.length; i++) {
			for (int j = 0; j < button[0].length; j++) {
				if (event.getSource().equals(button[j][i])) {
					gridButton = true;
					current = button[j][i];
					if (board[j][i] == BLANK) {
						if( turn == X_TURN){
							current.setText("X");
							board[j][i] = X_MOVE;
							turn = O_TURN;
						}
						else {
							current.setText("O");
							board[j][i] = O_MOVE;
							turn = X_TURN;
						}
						//check for wins and ties
						if(checkWin(X_MOVE) == true) {
							//
							xwins++;
							xLabel.setText( "  Player " + xPlayerName + "wins: " + xwins);
							clearBoard();
						}
						else if(checkWin(O_MOVE) == true){
							//
							owins++;
							oLabel.setText( "  Player " + oPlayerName + "wins: " + owins);
							clearBoard();
						}
						else if (checkTie() == true) {
							clearBoard();
						}
					}
				}
			}
		}
		if (gridButton == false) {
			if (event.getSource().equals(xChangeName) == true ) {
				//xChangeField.setText("X Change");
				System.out.println( "|"+ xChangeField.getText() + "|");
				if(false == xChangeField.getText().equals("")) {
					xPlayerName = xChangeField.getText();
					xLabel.setText(  "  Player " + xPlayerName + " wins: " + xwins);
				}
				
			}
			else if (event.getSource().equals(oChangeName) == true) {
				//oChangeField.setText("O Change");
				System.out.println("|"+oChangeField.getText()+"|");
				if(false == oChangeField.getText().equals("")) {
					oPlayerName = oChangeField.getText();
					oLabel.setText( "  Player " + oPlayerName + " wins: " + owins);
				}
			}
		}
	}
	
	//Checks for wins
	public boolean checkWin(int player) {
		if (board[0][0] == player && 
			board[0][1] == player && 
			board[0][2] == player) {
			return true;
		}
		else if (board[1][0] == player && 
				board[1][1] == player && 
				board[1][2] == player) {
			return true;
		}
		else if (board[2][0] == player && 
				board[2][1] == player && 
				board[2][2] == player) {
			return true;
		}
		else if (board[0][0] == player && 
				board[1][1] == player && 
				board[2][2] == player) {
			return true;
		}
		else if (board[2][0] == player && 
				board[1][1] == player && 
				board[0][2] == player) {
			return true;
		}
		else if (board[0][0] == player && 
				board[1][0] == player && 
				board[2][0] == player) {
			return true;
		}
		else if (board[0][1] == player && 
				board[1][1] == player && 
				board[2][1] == player) {
			return true;
		}
		else if (board[0][2] == player && 
				board[1][2] == player && 
				board[2][2] == player) {
			return true;
		}
			return false;
	}

	//Goes through board and clears
	public void clearBoard() {
		for (int row = 0; row<board.length;row++) {
			for (int column = 0;column<board[0].length;column++) {
				board[row][column] = BLANK;
				button[row][column].setText(row + "," + column);
			}
			
		}
		turn = X_TURN;
	}
	
	
	//Looks through board and checks for tie
	public boolean checkTie() {	
		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board[0].length; column++) {
				if(board[row][column] == BLANK) {
					return false;
				}
			}
		}
		return true;
	}
}