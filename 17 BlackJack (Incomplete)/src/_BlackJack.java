
/* 2018-6-13 23;38;25
// welcome to Chengfeng Tang and Zareef's Blackjack Game
// Players get 1000 $ at start, once u lose all the money u lose the game, and u have to bet a certain amount of money
to bein the game
once is starts
/*Dealers and users get two cards at the beginning
// Players have options to 
// hit: which is to draw the card
// stand: do nothing and end ur turn
// double: double the bet and draw one card then end ur turn
 * split: when you get two cards within the first two draws that have the same value, you can split them
 * into two different hands and play the game with half of the value twice, but if you get a card with the same value
 * you cant' split again
 * 
 * if you lose with one hand and win with another hand you don't lose money.
 *
 * insure: guess if the dealer has a blackjack which is totalvalue of 21 with his first 2 cards, if u guess
 * it right u win
 * else u lose
 * 
 * the goal is to make the total value of ur cards <= 21 but bigger than dealers
 * anything abbove 10 counts as 10, ace could be 11 or 1 you can't choose what value the ace
 * has cuz I am smart and i calculate for u
 * 
 * 
 * the dealer always moves after u
 * 
 *Enjoy the game with lame cards, I really tried ;-; T-T -- Chengfeng Tang
 *
 * I have 17 mins left til this project is due, let's pray that I can comment all 1000 lines of messy 
 * + brainlessnaming variables 
 * 
 * 
 * trash part about the game:
 * the suit of the card is pointless :))))))))))))))))) i wonder whose idea it was
 * you can only have 6 cards maximum, you can have more but I won't show heeehee
 * cards look so bad ;-;
 * let's hope I don't lose 20 points for this because I spent a lot of time on it ;-;
 * plzplzplzplzplzplzplz
 * my commenting may be confusing, I can explain it better if you want me to in person
 * I mean I kinda asked you for help for the complicated part and you probably know what I am doing
 * 
 * please don't make me lose too many points for maknig the interface look bad
 * 
 */

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class _BlackJack implements ActionListener {

	boolean game = false; // whether the game goes on or no, pretty useless
	boolean splittable = false; // basically if the user hits split
	boolean cansplit = false; // if the user can split at the beginning
	boolean secondhand1 = false; // this is if you are on ur second hand, basically after u split and ur first
									// hand
	// already ended
	ArrayList<Integer> user = new ArrayList<Integer>(); // users cards
	ArrayList<String> usersuit = new ArrayList<String>();
	ArrayList<Integer> secondhand = new ArrayList<Integer>(); // users cards for split
	ArrayList<String> secondhandsuit = new ArrayList<String>();

	ArrayList<Integer> dealer = new ArrayList<Integer>(); // dealers cards
	ArrayList<String> dealersuit = new ArrayList<String>();

	ArrayList<Integer> cardstack = new ArrayList<Integer>(); // the card stack with all the cards
	ArrayList<String> cardsuit = new ArrayList<String>();

	ArrayList<Integer> temporary = new ArrayList<Integer>(); // temporary store value
	ArrayList<String> temporary2 = new ArrayList<String>();
	
	int secondhandvalue = 0;
	int usertotalvalue = 0;
	int dealertotalvalue = 0;
	int totalcards = 52;
	boolean userhasace = false;
	boolean dealerhasace = false;
	int money = 1000;
	int bet = 0;
	Scanner ask = new Scanner(System.in);
	String input = "";

	JFrame frame = new JFrame();
	_BlackJackPanel panel = new _BlackJackPanel();
	JButton Play = new JButton("Play");

	JLabel moneyRemaining = new JLabel("You have: $" + Integer.toString((money)));
	JLabel valueofcards = new JLabel("Value of your cards: " + Integer.toString((usertotalvalue)));
	JLabel betvalue = new JLabel("You are betting: $" + Integer.toString(bet));

	JButton Hit = new JButton("Hit");
	JButton Stand = new JButton("Stand");
	JButton Double = new JButton("Double");
	JButton Insure = new JButton("Insure");
	JButton split = new JButton("Split");

	JButton d1 = new JButton("$1");
	JButton d5 = new JButton("$5");
	JButton d10 = new JButton("$10");
	JButton d25 = new JButton("$25");
	JButton d100 = new JButton("$100");
	JButton d50 = new JButton("$50");
	JButton clearbet = new JButton("Reset");

	JLabel dealercard1 = new JLabel("Dealer's"); // all the dumb labels I made,
	JLabel card1 = new JLabel("X");
	JLabel dealercard2 = new JLabel("Dealer's");
	JLabel card2 = new JLabel("X");
	JLabel dealercard3 = new JLabel("Dealer's");
	JLabel card3 = new JLabel("X");
	JLabel dealercard4 = new JLabel("Dealer's");
	JLabel card4 = new JLabel("X");
	JLabel dealercard5 = new JLabel("Dealer's");
	JLabel card5 = new JLabel("X");
	JLabel dealercard6 = new JLabel("Dealer's");
	JLabel card6 = new JLabel("X");

	JLabel usercard1 = new JLabel("User's");
	JLabel usercard111 = new JLabel("X");
	JLabel usercard2 = new JLabel("User's");
	JLabel usercard22 = new JLabel("X");
	JLabel usercard3 = new JLabel("User's");
	JLabel usercard33 = new JLabel("X");
	JLabel usercard4 = new JLabel("User's");
	JLabel usercard44 = new JLabel("X");
	JLabel usercard5 = new JLabel("User's");
	JLabel usercard55 = new JLabel("X");
	JLabel usercard6 = new JLabel("User's");
	JLabel usercard66 = new JLabel("X");
	JLabel usercard7 = new JLabel("SecondHand's");
	JLabel usercard77 = new JLabel("X");
	JLabel usercard8 = new JLabel("SecondHand's");
	JLabel usercard88 = new JLabel("X");
	JLabel usercard9 = new JLabel("SecondHand's");
	JLabel usercard99 = new JLabel("X");
	JLabel usercard10 = new JLabel("SecondHand's");
	JLabel usercard1010 = new JLabel("X");
	JLabel usercard11 = new JLabel("SecondHand's");
	JLabel usercard1111 = new JLabel("X");
	JLabel usercard12 = new JLabel("SecondHand's");
	JLabel usercard1212 = new JLabel("X");

	@SuppressWarnings("deprecation")

	public _BlackJack() {

		frame.setSize(1000, 750);
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);

		Color DarkGreen = new Color(20, 128, 42);

		panel.setBackground(DarkGreen);
		panel.setLayout(null);

		Play.setBounds(740, 25, 200, 50);
		panel.add(Play);
		Play.addActionListener(this);

		Hit.addActionListener(this);
		Stand.addActionListener(this);
		Double.addActionListener(this);
		split.addActionListener(this);
		Insure.addActionListener(this);
		clearbet.addActionListener(this);

		d1.addActionListener(this);
		d5.addActionListener(this);
		d10.addActionListener(this);
		d50.addActionListener(this);
		d25.addActionListener(this);
		d100.addActionListener(this);

		d1.setBounds(740, 600, 70, 25);
		d5.setBounds(810, 600, 70, 25);
		d10.setBounds(880, 600, 70, 25);
		d25.setBounds(740, 650, 70, 25);
		d50.setBounds(810, 650, 70, 25);
		d100.setBounds(880, 650, 70, 25);
		betvalue.setBounds(740, 540, 200, 25);
		clearbet.setBounds(740, 570, 210, 25);

		moneyRemaining.setBounds(740, 520, 200, 25);
		valueofcards.setBounds(740, 500, 200, 25);
		Hit.setBounds(740, 150, 200, 50);
		Stand.setBounds(740, 225, 200, 50);
		Double.setBounds(740, 300, 200, 50);
		split.setBounds(740, 375, 200, 50);
		Insure.setBounds(740, 450, 200, 50);

		dealercard1.setBounds(20, 50, 100, 100);
		dealercard2.setBounds(130, 50, 100, 100);
		dealercard3.setBounds(240, 50, 100, 100);
		dealercard4.setBounds(350, 50, 100, 100);
		dealercard5.setBounds(460, 50, 100, 100);
		dealercard6.setBounds(570, 50, 100, 100);

		card1.setBounds(25, 50, 200, 200);
		card2.setBounds(135, 50, 200, 200);
		card3.setBounds(245, 50, 200, 200);
		card4.setBounds(355, 50, 200, 200);
		card5.setBounds(465, 50, 200, 200);
		card6.setBounds(575, 50, 200, 200);
		card1.setFont(new Font("Serif", Font.PLAIN, 12));
		card2.setFont(new Font("Serif", Font.PLAIN, 12));
		card3.setFont(new Font("Serif", Font.PLAIN, 12));
		card4.setFont(new Font("Serif", Font.PLAIN, 12));
		card5.setFont(new Font("Serif", Font.PLAIN, 12));
		card6.setFont(new Font("Serif", Font.PLAIN, 12));

		usercard1.setBounds(20, 280, 100, 100);
		usercard2.setBounds(130, 280, 100, 100);
		usercard3.setBounds(240, 280, 100, 100);
		usercard4.setBounds(350, 280, 100, 100);
		usercard5.setBounds(460, 280, 100, 100);
		usercard6.setBounds(570, 280, 100, 100);

		usercard7.setBounds(20, 500, 100, 100);
		usercard8.setBounds(130, 500, 100, 100);
		usercard9.setBounds(240, 500, 100, 100);
		usercard10.setBounds(350, 500, 100, 100);
		usercard11.setBounds(460, 500, 100, 100);
		usercard12.setBounds(570, 500, 100, 100);

		usercard111.setBounds(25, 280, 200, 200);
		usercard22.setBounds(135, 280, 200, 200);
		usercard33.setBounds(245, 280, 200, 200);
		usercard44.setBounds(355, 280, 200, 200);
		usercard55.setBounds(465, 280, 200, 200);
		usercard66.setBounds(575, 280, 200, 200);

		usercard77.setBounds(25, 500, 200, 200);
		usercard88.setBounds(135, 500, 200, 200);
		usercard99.setBounds(245, 500, 200, 200);
		usercard1010.setBounds(355, 500, 200, 200);
		usercard1111.setBounds(465, 500, 200, 200);
		usercard1212.setBounds(575, 500, 200, 200);

		usercard111.setFont(new Font("Serif", Font.PLAIN, 12));
		usercard22.setFont(new Font("Serif", Font.PLAIN, 12));
		usercard33.setFont(new Font("Serif", Font.PLAIN, 12));
		usercard44.setFont(new Font("Serif", Font.PLAIN, 12));
		usercard55.setFont(new Font("Serif", Font.PLAIN, 12));
		usercard66.setFont(new Font("Serif", Font.PLAIN, 12));
		usercard77.setFont(new Font("Serif", Font.PLAIN, 12));
		usercard88.setFont(new Font("Serif", Font.PLAIN, 12));
		usercard99.setFont(new Font("Serif", Font.PLAIN, 12));
		usercard1010.setFont(new Font("Serif", Font.PLAIN, 12));
		usercard1111.setFont(new Font("Serif", Font.PLAIN, 12));
		usercard1212.setFont(new Font("Serif", Font.PLAIN, 12));

		// this part is painful, I want to cry

		panel.add(Hit);
		panel.add(Stand);
		panel.add(Double);
		panel.add(split);
		panel.add(Insure);
		panel.add(moneyRemaining);
		panel.add(valueofcards);
		panel.add(d1);
		panel.add(d5);
		panel.add(d10);
		panel.add(d25);
		panel.add(d100);
		panel.add(d50);
		panel.add(betvalue);
		panel.add(clearbet);

		panel.add(dealercard1);
		panel.add(dealercard2);
		panel.add(dealercard3);
		panel.add(dealercard4);
		panel.add(dealercard5);
		panel.add(dealercard6);
		panel.add(card1);
		panel.add(card2);
		panel.add(card3);
		panel.add(card4);
		panel.add(card5);
		panel.add(card6);

		panel.add(usercard111);
		panel.add(usercard22);
		panel.add(usercard33);
		panel.add(usercard44);
		panel.add(usercard55);
		panel.add(usercard66);
		panel.add(usercard77);
		panel.add(usercard88);
		panel.add(usercard99);
		panel.add(usercard1010);
		panel.add(usercard1111);
		panel.add(usercard1212);

		panel.add(usercard1);
		panel.add(usercard2);
		panel.add(usercard3);
		panel.add(usercard4);
		panel.add(usercard5);
		panel.add(usercard6);
		panel.add(usercard7);
		panel.add(usercard8);
		panel.add(usercard9);
		panel.add(usercard10);
		panel.add(usercard11);
		panel.add(usercard12);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		JOptionPane.showMessageDialog(null, "Start the game by betting then click play.");
		Hit.setEnabled(false);
		Stand.setEnabled(false);
		Double.setEnabled(false);
		split.setEnabled(false);
		Insure.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(Play)) {
			if (bet == 0) { // if u havn't bet , bet, if u don't have enough money, well u can't play
				JOptionPane.showMessageDialog(null, "Please bet first!");
			} else if (bet > money) {
				JOptionPane.showMessageDialog(null, "You don't have enough money!");
			} else {
				game = true; // no idea why game = true, game is always true so idk
				if (game == true) {
					d1.setEnabled(false); // disable play and betting section and start the game
					Play.setEnabled(false);
					d5.setEnabled(false);
					d10.setEnabled(false);
					d25.setEnabled(false);
					d50.setEnabled(false);
					d100.setEnabled(false);
					clearbet.setEnabled(false);

					Hit.setEnabled(true);
					Stand.setEnabled(true);
					if (bet * 2 <= money) { // if u don't have enough money to double , well u can't double
						Double.setEnabled(true);
					}

					Insure.setEnabled(true);

					clearboard();
					fillcardstack(); // fill the deck
					fillcardsuit();
					userdrawtwocards(); // user draw 2 cards

					dealerdrawtwocards();
					// System.out.println("Your cards: ");
					//printuser();
					// System.out.println("Dealers Cards: ");
					// System.out.println("dealer: ");
					// System.out.println("[" + dealer.get(0) + ", X]");
					if (cansplit == false) {
						split.setEnabled(false);
					} else {
						split.setEnabled(true);

					}

				}
			}
		}
		if (e.getSource().equals(d1)) {
			bet += 1;

			betvalue.setText(("You are betting: $" + Integer.toString(bet)));
			panel.repaint();
		}
		if (e.getSource().equals(d5)) {
			bet += 5;
			betvalue.setText(("You are betting: $" + Integer.toString(bet)));
			panel.repaint();
		}
		if (e.getSource().equals(d10)) {
			bet += 10;

			betvalue.setText(("You are betting: $" + Integer.toString(bet)));
			frame.repaint();
		}
		if (e.getSource().equals(d25)) {
			bet += 25;
			betvalue.setText(("You are betting: $" + Integer.toString(bet)));
			panel.repaint();
		}
		if (e.getSource().equals(d50)) {
			bet += 50;
			betvalue.setText(("You are betting: $" + Integer.toString(bet)));
			panel.repaint();
		}
		if (e.getSource().equals(d100)) {
			bet += 100;
			betvalue.setText(("You are betting: $" + Integer.toString(bet)));
			panel.repaint();
		}
		if (e.getSource().equals(clearbet)) {
			bet = 0;
			betvalue.setText(("You are betting: $" + Integer.toString(bet)));
			panel.repaint();
		}
		if (e.getSource().equals(Hit)) { // when u hit

			hit(); // draw a card
			//printuser();
			if (usertotalvalue <= 21) // and if the user is not yet busted, keep asking him to hit or stand
			{
				Insure.setEnabled(false);
				Double.setEnabled(false);
				split.setEnabled(false);

				if (usertotalvalue > 21) {

					if (splittable == false) {
						end(); // keep allowing user to hit until he is busted
					}
					if (splittable == true) // if the user is on his first hand after split
					{

						splittable = false; // basically change his first hand to second hand,
						// get the temporary card that we removed from splitting, and play with that
						// deck
						Insure.setEnabled(false);
						Double.setEnabled(false);
						split.setEnabled(false);

						secondhandvalue = usertotalvalue; // restore the first hand
						for (int i = 0; i < user.size(); i++) {
							secondhand.add(user.get(i));
						}

						user.clear();
						usertotalvalue = 0;

						for (int i = 0; i < usersuit.size(); i++) {
							secondhandsuit.add(usersuit.get(i));
						}
						usersuit.clear();

						usertotalvalue = temporary.get(0);
						user.add(temporary.get(0));

						temporary.clear();

						JOptionPane.showMessageDialog(null, "This is your second deck!");
						secondhand1 = true;
						hit();
						//printuser();

						valueofcards.setText("Value of your cards: " + Integer.toString((usertotalvalue)));
						frame.repaint();

						// thisiswhereyoushowyourcard, rightnowIamjustprintingthecardsuit and user'ssuit
						// System.out.println(usersuit);
						// System.out.println(cardsuit);
						// System.out.println(secondhandsuit);
					}

				}
			}

			else // if he is busted right after he hits, end the game
			{
				if (splittable == false) {
					end();
					// game = false;
					// game = true;
				}
				if (splittable == true) {

					splittable = false;
					Insure.setEnabled(false);
					Double.setEnabled(false);
					split.setEnabled(false);

					secondhandvalue = usertotalvalue; // restore the first hand
					for (int i = 0; i < user.size(); i++) {
						secondhand.add(user.get(i));
					}

					user.clear();
					usertotalvalue = 0;

					for (int i = 0; i < usersuit.size(); i++) {
						secondhandsuit.add(usersuit.get(i));
					}
					usersuit.clear();

					usertotalvalue = temporary.get(0);
					user.add(temporary.get(0));
					usersuit.add(temporary2.get(0));
					temporary2.clear();
					temporary.clear();

					JOptionPane.showMessageDialog(null, "This is your second deck!");
					secondhand1 = true;
					hit();

					//printuser();

					valueofcards.setText("Value of your cards: " + Integer.toString((usertotalvalue)));
					frame.repaint();
					splittable = false;

					// thisiswhereyoushowyourcard, rightnowIamjustprintingthecardsuit and user'ssuit
					// System.out.println(usersuit);
					// System.out.println(cardsuit);
					// System.out.println(secondhandsuit);
				}

			}
		} else if (e.getSource().equals(Stand)) { // same idea as hit with the splittable == true/false

			if (splittable == true) {
				stand();

				Insure.setEnabled(false);
				Double.setEnabled(false);
				split.setEnabled(false);

				secondhandvalue = usertotalvalue; // restore the first hand
				for (int i = 0; i < user.size(); i++) {
					secondhand.add(user.get(i));
				}

				user.clear();
				usertotalvalue = 0;

				for (int i = 0; i < usersuit.size(); i++) {
					secondhandsuit.add(usersuit.get(i));
				}
				usersuit.clear();

				usertotalvalue = temporary.get(0);
				user.add(temporary.get(0));
				usersuit.add(temporary2.get(0));
				temporary.clear();
				temporary2.clear();

				JOptionPane.showMessageDialog(null, "This is your second deck!");

				secondhand1 = true;
				hit();
				//printuser();

				valueofcards.setText("Value of your cards: " + Integer.toString((usertotalvalue)));
				frame.repaint();

				splittable = false;

				// thisiswhereyoushowyourcard, rightnowIamjustprintingthecardsuit and user'ssuit
				// System.out.println(usersuit);
				// System.out.println(cardsuit);
				// System.out.println(secondhandsuit);

			} else {
				stand();

				computermoves();
				end();
			}
		} else if (e.getSource().equals(Double)) {

			hit(); // you only get to hit once

			bet = bet * 2; // double the bet
			computermoves();
			end(); // end game

			// game = false;
			// game = true;

		} else if (e.getSource().equals(split)) {
			// after u split, one of ur card goes to temporary annd u play with the
			// firstcard
			splittable = true;
			split.setEnabled(false);
			Insure.setEnabled(false);
			Double.setEnabled(false);

			temporary.add(user.get(1)); // transfer the card over

			usertotalvalue -= user.get(1);
			valueofcards.setText("Value of your cards: " + Integer.toString((usertotalvalue)));
			frame.repaint();
			user.remove(1);

			temporary2.add(usersuit.get(1));
			usersuit.remove(1);
			// thisiswhereyoushowyourcard, rightnowIamjustprintingthecardsuit and user'ssuit
			// System.out.println(usersuit);
			// System.out.println(cardsuit);
			// System.out.println(secondhandsuit);

			hit(); // the first deck draws another card

			//printuser();

			usercard77.setText(printuser(temporary2.get(0)));
			frame.repaint();
			// finish the current game then end the other one

		} else if (e.getSource().equals(Insure)) {
			insure();

		}
		/*
		 * else if (temporary.isEmpty()) { end(); }
		 */

	}

	private void insure() {
		boolean insure = false;

		if (dealertotalvalue == 21) {
			insure = true;
		}

		if (insure == true) { // restart the game and if u get it u win mooney
			money += bet;
			moneyRemaining.setText("You have: $" + Integer.toString((money)));
			JOptionPane.showMessageDialog(null, "You got it right! You have $" + money + " left! ");

			clearboard();
			game = false;
			d1.setEnabled(true);
			Play.setEnabled(true);
			d5.setEnabled(true);
			d10.setEnabled(true);
			d25.setEnabled(true);
			d50.setEnabled(true);
			d100.setEnabled(true);
			clearbet.setEnabled(true);
			Hit.setEnabled(false);
			Stand.setEnabled(false);
			Double.setEnabled(false);
			split.setEnabled(false);
			Insure.setEnabled(false);
			panel.repaint();

		} else { // if u don't u lose money then restart the game
			money -= bet;
			moneyRemaining.setText("You have: $" + Integer.toString((money)));
			JOptionPane.showMessageDialog(null, "You got it wrong! You have $" + money + " left! ");

			clearboard();
			game = false;
			d1.setEnabled(true);
			Play.setEnabled(true);
			d5.setEnabled(true);
			d10.setEnabled(true);
			d25.setEnabled(true);
			d50.setEnabled(true);
			d100.setEnabled(true);
			clearbet.setEnabled(true);
			Hit.setEnabled(false);
			Stand.setEnabled(false);
			Double.setEnabled(false);
			split.setEnabled(false);
			Insure.setEnabled(false);
			panel.repaint();

		}
		bet = 0;
		betvalue.setText(("You are betting: $" + Integer.toString(bet)));
		frame.repaint();
	}

	private void clearboard() // reset eveyrthing
	{
		secondhand1 = false;
		user.clear();
		dealer.clear();
		usersuit.clear();
		dealersuit.clear();

		usertotalvalue = 0;
		dealertotalvalue = 0;
		totalcards = 52;
		userhasace = false;
		dealerhasace = false;

		dealercard1.setText("Dealer's");
		card1.setText("X");
		dealercard2.setText("Dealer's");
		card2.setText("X");
		dealercard3.setText("Dealer's");
		card3.setText("X");
		dealercard4.setText("Dealer's");
		card4.setText("X");
		dealercard5.setText("Dealer's");
		card5.setText("X");
		dealercard6.setText("Dealer's");
		card6.setText("X");

		usercard1.setText("User's");
		usercard111.setText("X");
		usercard2.setText("User's");
		usercard22.setText("X");
		usercard3.setText("User's");
		usercard33.setText("X");
		usercard4.setText("User's");
		usercard44.setText("X");
		usercard5.setText("User's");
		usercard55.setText("X");
		usercard6.setText("User's");
		usercard66.setText("X");
		usercard7.setText("SecondHand's");
		usercard77.setText("X");
		usercard8.setText("SecondHand's");
		usercard88.setText("X");
		usercard9.setText("SecondHand's");
		usercard99.setText("X");
		usercard10.setText("SecondHand's");
		usercard1010.setText("X");
		usercard11.setText("SecondHand's");
		usercard1111.setText("X");
		usercard12.setText("SecondHand's");
		usercard1212.setText("X");

	}

	private void end() {
		// System.out.println("Final result:");
		// printdealer();
		// printuser();

		if (secondhandvalue != 0) {
			bet = bet / 2;
		}

		if (secondhandvalue != 0) {
			// System.out.println(secondhand);
			// System.out.println(secondhandvalue);
		}

		if (usertotalvalue > 21) // always check if you are busted first
		{
			money -= bet;
			moneyRemaining.setText("You have: $" + Integer.toString((money)));
			JOptionPane.showMessageDialog(null, "You lost with your hand!");
		} else if ((usertotalvalue <= 21) && (dealertotalvalue > 21)) // or if the dealer is busted
		{
			money += bet;
			moneyRemaining.setText("You have: $" + Integer.toString((money)));
			JOptionPane.showMessageDialog(null, "You won with your hand!");
		} else if ((usertotalvalue <= 21) && (dealertotalvalue > usertotalvalue)) // if the dealer has more
		{
			money -= bet;
			moneyRemaining.setText("You have: $" + Integer.toString((money)));
			JOptionPane.showMessageDialog(null, "You lost with your hand!");
		} else if ((usertotalvalue <= 21) && (dealertotalvalue < usertotalvalue)) // if the user has more
		{
			money += bet;
			moneyRemaining.setText("You have: $" + Integer.toString((money)));
			JOptionPane.showMessageDialog(null, "You won with your hand!");
		} else if (usertotalvalue == dealertotalvalue) // if it's a tie
		{
			JOptionPane.showMessageDialog(null, "You tied with your hand!");
		}

		if (secondhandvalue != 0) {
			if (secondhandvalue > 21) // always check if you are busted first
			{
				money -= bet;
				moneyRemaining.setText("You have: $" + Integer.toString((money)));
				JOptionPane.showMessageDialog(null, "You lost with your previous hand!");
			} else if ((secondhandvalue <= 21) && (dealertotalvalue > 21)) // or if the dealer is busted
			{
				money += bet;
				moneyRemaining.setText("You have: $" + Integer.toString((money)));
				JOptionPane.showMessageDialog(null, "You won with your previous hand!");
			} else if ((secondhandvalue <= 21) && (dealertotalvalue > secondhandvalue)) // if the dealer has more
			{
				money -= bet;
				moneyRemaining.setText("You have: $" + Integer.toString((money)));
				JOptionPane.showMessageDialog(null, "You lost with your previous hand!");
			} else if ((secondhandvalue <= 21) && (dealertotalvalue < secondhandvalue)) // if the user has more
			{
				money += bet;
				moneyRemaining.setText("You have: $" + Integer.toString((money)));
				JOptionPane.showMessageDialog(null, "You won with your previous hand!");
			} else if (secondhandvalue == dealertotalvalue) // if it's a tie
			{
				moneyRemaining.setText("You have: $" + Integer.toString((money)));
				JOptionPane.showMessageDialog(null, "You tied with your previous hand!");
			}
		}

		if (money == 0) // if u have no money now
		{
			JOptionPane.showMessageDialog(null, "You lost all of your money! Bye");
			System.exit(0);
		}
		bet = 0;
		betvalue.setText(("You are betting: $" + Integer.toString(bet)));
		game = false;
		d1.setEnabled(true);
		Play.setEnabled(true);
		d5.setEnabled(true);
		d10.setEnabled(true);
		d25.setEnabled(true);
		d50.setEnabled(true);
		d100.setEnabled(true);
		clearbet.setEnabled(true);
		Hit.setEnabled(false);
		Stand.setEnabled(false);
		Double.setEnabled(false);
		split.setEnabled(false);
		Insure.setEnabled(false);
		panel.repaint();

	}

	public void fillcardstack() // fill the card stack
	{
		for (int i = 1; i < 14; i++) // 14 cards A 2345678910 JQK
		{
			for (int j = 0; j < 4; j++) // 4 cards for each type of card
			{
				int cards = i; // the card is just the number of its value since it's an int array
				{
					cardstack.add(cards); // There are 4 of each card and there are 13 types of card from 1 - 10 + JQK
				}
			}
		}
	}

	public void fillcardsuit() // same thing but fill an string array of cards with suits
	{
		for (int i = 1; i < 14; i++) {
			cardsuit.add(i + "H");
			cardsuit.add(i + "S");
			cardsuit.add(i + "D");
			cardsuit.add(i + "C");
		}
	}

	public void userdrawtwocards() {

		int whichcard = (int) (Math.random() * ((totalcards - 1) + 1)) + 1; // total cards of 52, user draws one of it
		// int whichcard = 2;
		user.add(cardstack.get(whichcard - 1)); // starts at 0, so when you get the first(1) card it's actually

		// remove it from the card stack and make the total count for cards -1;
		totalcards -= 1; // there is one less card after you draw
		cardstack.remove(whichcard - 1); // remove the top most card, but since index starts at 0 so -1

		/**
		 * Get random suit of whatever value you chose (of the ones that are remaining)
		 */

		usersuit.add(cardsuit.get(whichcard - 1));
		cardsuit.remove((whichcard - 1));

		// thisiswhereyoushowyourcard, rightnowIamjustprintingthecardsuit and user'ssuit
		// System.out.println(usersuit);
		// System.out.println(cardstack);
		// System.out.println(cardsuit);

		whichcard = (int) (Math.random() * ((totalcards - 1) + 1)) + 1; // do it twice cuz u draw 2 cards
		// whichcard = 1;
		user.add(cardstack.get(whichcard - 1));
		totalcards -= 1;
		cardstack.remove(whichcard - 1);

		usersuit.add(cardsuit.get(whichcard - 1));
		cardsuit.remove((whichcard - 1));

		// thisiswhereyoushowyourcard, rightnowIamjustprintingthecardsuit and user'ssuit
		// System.out.println(usersuit);
		// System.out.println(cardstack);
		// System.out.println(cardsuit);

		for (int i = 0; i < user.size(); i++) // go through the 2 cards u got
		{
			if (user.get(i) > 10) // if there is a JQK
			{
				usertotalvalue += 10; // their value is 10
			} else {
				usertotalvalue += user.get(i); // else just add whatever the value is
			}
		}

		if (user.get(0) == user.get(1)) {
			cansplit = true;
		}
		if ((user.get(0) == 1) && (user.get(1) == 1)) // if somehow this guy drew two aces at the beginning
		{
			userhasace = true;
			usertotalvalue = 12;
		} else if ((user.get(0) == 1) || (user.get(1) == 1)) // if one of it is an ace, make it 11, cuz u can never bust
																// with 2 cards;
		{
			userhasace = true;
			usertotalvalue += 10;
		}

		valueofcards.setText("Value of your cards: " + Integer.toString((usertotalvalue)));
		frame.repaint();
		valueofcards.setText("Value of your cards: " + Integer.toString((usertotalvalue)));
		usercard111.setText(printuser(usersuit.get(0)));
		usercard22.setText(printuser(usersuit.get(1)));
		frame.repaint();

	}

	public void dealerdrawtwocards() // same thing as user draw 2 cards
	{

		int whichcard = (int) (Math.random() * ((totalcards - 1) + 1)) + 1; // user draws one card
		dealer.add(cardstack.get(whichcard - 1)); // starts at 0, so when you get the first(1) card it's actually
		dealersuit.add(cardsuit.get(whichcard - 1));
		// arraylist (0)
		// remove it from the card stack and make the total count for cards -1;
		totalcards -= 1;

		cardstack.remove(whichcard - 1);
		cardsuit.remove(whichcard - 1);

		whichcard = (int) (Math.random() * ((totalcards - 1) + 1)) + 1;
		dealer.add(cardstack.get(whichcard - 1));
		totalcards -= 1;
		dealersuit.add(cardsuit.get(whichcard - 1));
		cardsuit.remove(whichcard - 1);
		cardstack.remove(whichcard - 1);

		for (int i = 0; i < dealer.size(); i++) {
			if (dealer.get(i) > 10) {
				dealertotalvalue += 10;
			} else {
				dealertotalvalue += dealer.get(i);
			}
		}

		if ((dealer.get(0) == 1) && (dealer.get(1) == 1)) // if somehow this guy drew two aces at the beginning
		{
			dealerhasace = true;
			dealertotalvalue = 12;
		} else if ((dealer.get(0) == 1) || (dealer.get(1) == 1)) {
			dealerhasace = true;

			dealertotalvalue += 10;

		}

		card1.setText(printdealer(dealersuit.get(0)));
		frame.repaint();

	}

	public String printdealer(String dealersuit) {
		String print = "";//Name of the Card will be held in here
		//System.out.println("Dealer's Cards: ");
		if (dealersuit.length()==3) {
			int num = Integer.parseInt(Character.toString(dealersuit.charAt(0)) + Character.toString(dealersuit.charAt(1)));//Gets the number value of the string
			char suit = dealersuit.charAt(2);//Gets the suit of the string
			if (num == 10) {
				print += "10";
			}
			else if (num == 11) {
				print += "Jack";
			}
			else if (num == 12) {
				print += "Queen";
			}
			else if (num == 13) {
				print += "King";
			}
			if (suit == 'H') {
				print+=" of Hearts";
			}
			else if (suit == 'S') {
				print+= " of Spades";
			}
			else if (suit == 'C') {
				print+= " of Clubs";
			}
			else if (suit == 'D') {
				print+= " of Diamonds";
			}
		}
		else {
			int num = Integer.parseInt(Character.toString(dealersuit.charAt(0)));//Get's the number value of the string
			char suit = dealersuit.charAt(1);//Gets the char value of the string
			if (num == 1) {
				print += "Ace";
			}
			else {
				print += num;
			}
			if (suit == 'H') {
				print+=" of Hearts";
			}
			else if (suit == 'S') {
				print+= " of Spades";
			}
			else if (suit == 'C') {
				print+= " of Clubs";
			}
			else if (suit == 'D') {
				print+= " of Diamonds";
			}
		}
		return print;
	}

	public String printuser(String usersuit) {
			String print = "";
			if (usersuit.length()==3) {
				int num = Integer.parseInt(Character.toString(usersuit.charAt(0)) + Character.toString(usersuit.charAt(1)));
				char suit = usersuit.charAt(2);
				if (num == 10) {
					print += "10";
				}
				else if (num == 11) {
					print += "Jack";
				}
				else if (num == 12) {
					print += "Queen";
				}
				else if (num == 13) {
					print += "King";
				}
				if (suit == 'H') {
					print+=" of Hearts";
				}
				else if (suit == 'S') {
					print+= " of Spades";
				}
				else if (suit == 'C') {
					print+= " of Clubs";
				}
				else if (suit == 'D') {
					print+= " of Diamonds";
				}
			}
			else {
				int num = Integer.parseInt(Character.toString(usersuit.charAt(0)));
				char suit = usersuit.charAt(1);
				if (num == 1) {
					print += "Ace";
				}
				else {
					print += num;
				}
				if (suit == 'H') {
					print+=" of Hearts";
				}
				else if (suit == 'S') {
					print+= " of Spades";
				}
				else if (suit == 'C') {
					print+= " of Clubs";
				}
				else if (suit == 'D') {
					print+= " of Diamonds";
				}
			}
			return print;
	}

	public void hit() {
		int whichcard = (int) (Math.random() * ((totalcards - 1) + 1)) + 1; // user draws one card
		user.add(cardstack.get(whichcard - 1)); // starts at 0, so when you get the first(1) card it's actually
												// arraylist (0)

		if (cardstack.get(whichcard - 1) > 10) // if u get JQK they count as 10
		{
			usertotalvalue += 10;

		} else {
			usertotalvalue += cardstack.get(whichcard - 1);
		}

		if ((cardstack.get(whichcard - 1) == 1) && (userhasace == true)) // if you draw an ace and you already had an
																			// ace from the first
		// two cards you draw
		{
			// it would be 11 + 11 + X and one of it has to be 1
			// 1 + 11 + X, then it depends on the X which we will check later, but this ace
			// will
			// always be 1

			userhasace = true;

		} else if (cardstack.get(whichcard - 1) == 1) // if you draw an ace
		{
			userhasace = true; // make the value 11 first

			usertotalvalue += 10;
		}

		if ((usertotalvalue > 21) && (userhasace == true)) // if you are busted when it is 11, make it back to 10
		{
			usertotalvalue -= 10;
			userhasace = false;
		}

		// remove it from the card stack and make the total count for cards -1;
		usersuit.add(cardsuit.get(whichcard - 1));
		cardsuit.remove(whichcard - 1);
		totalcards -= 1;
		cardstack.remove(whichcard - 1);
		valueofcards.setText("Value of your cards: " + Integer.toString((usertotalvalue)));
		frame.repaint();
		// thisiswhereyoushowyourcard, rightnowIamjustprintingthecardsuit and user'ssuit
		// System.out.println(usersuit);
		// System.out.println(cardsuit);
		// System.out.println(cardstack);

		// this part is very very very very very lame
		// because I am out of brain power to think of a for loop to do this
		// basicall everytime u draw a card, if u have 3 cards on ur hand after ur draw,
		// then the card u just
		// drew is the 3rd card, then set the 3rd label to it
		// sorry galbraith this part is so noob
		if (secondhand1 == false) {
			if (usersuit.size() == 1) {
				usercard111.setText(printuser(usersuit.get(0)));
			}
			if (usersuit.size() == 2) {
				usercard22.setText(printuser(usersuit.get(1)));
			}
			if (usersuit.size() == 3) {
				usercard33.setText(printuser(usersuit.get(2)));
			}
			if (usersuit.size() == 4) {
				usercard44.setText(printuser(usersuit.get(3)));
			}
			if (usersuit.size() == 5) {
				usercard55.setText(printuser(usersuit.get(4)));
			}
			if (usersuit.size() == 6) {
				usercard66.setText(printuser(usersuit.get(5)));
			}
		} else {
			if (usersuit.size() == 1) {
				usercard77.setText(printuser(usersuit.get(0)));
			}
			if (usersuit.size() == 2) {
				usercard88.setText(printuser(usersuit.get(1)));
			}
			if (usersuit.size() == 3) {
				usercard99.setText(printuser(usersuit.get(2)));
			}
			if (usersuit.size() == 4) {
				usercard1010.setText(printuser(usersuit.get(3)));
			}
			if (usersuit.size() == 5) {
				usercard1111.setText(printuser(usersuit.get(4)));
			}
			if (usersuit.size() == 6) {
				usercard1212.setText(printuser(usersuit.get(5)));
			}
		}
		frame.repaint();

	}

	private void computermoves() // same thing to hit but just dealer hit
	{

		while ((dealertotalvalue < usertotalvalue) && (usertotalvalue <= 21)) // if dealer has less than the user,
																				// always draw :) (hardest difficulties)

		{
			int whichcard = (int) (Math.random() * ((totalcards - 1) + 1)) + 1; // dealer draws one card
			dealer.add(cardstack.get(whichcard - 1)); // starts at 0, so when you get the first(1) card it's actually
														// arraylist (0)
			dealersuit.add(cardsuit.get(whichcard - 1));
			if (cardstack.get(whichcard - 1) > 10) {
				dealertotalvalue += 10;
			} else {
				dealertotalvalue += cardstack.get(whichcard - 1);
			}

			if ((cardstack.get(whichcard - 1) == 1) && (dealerhasace == true)) // if you draw an ace and you already had
																				// an ace from the first
			// two cards you draw
			{
				// it would be 11 + 11 + X and one of it has to be 1
				// 1 + 11 + X, then it depends on the X which we will check later, but this ace
				// will
				// always be 1

				dealerhasace = true;

			} else if (cardstack.get(whichcard - 1) == 1) {
				dealerhasace = true;

				dealertotalvalue += 10;
			}

			if ((dealertotalvalue > 21) && (dealerhasace == true)) {
				dealertotalvalue -= 10;
				dealerhasace = false;
			}

			// remove it from the card stack and make the total count for cards -1;
			totalcards -= 1;
			cardstack.remove(whichcard - 1);
			cardsuit.remove(whichcard - 1);

		}
		// I wish I know how to name all the card wisely first so this part could be
		// easier
		if (dealersuit.size() == 1) {
			card1.setText(printdealer(dealersuit.get(0)));
		}
		if (dealersuit.size() == 2) {
			card1.setText(printdealer(dealersuit.get(0)));
			card2.setText(printdealer(dealersuit.get(1)));
		}
		if (dealersuit.size() == 3) {
			card1.setText(printdealer(dealersuit.get(0)));
			card2.setText(printdealer(dealersuit.get(1)));
			card3.setText(printdealer(dealersuit.get(2)));
		}
		if (dealersuit.size() == 4) {
			card1.setText(printdealer(dealersuit.get(0)));
			card2.setText(printdealer(dealersuit.get(1)));
			card3.setText(printdealer(dealersuit.get(2)));
			card4.setText(printdealer(dealersuit.get(3)));
		}
		if (dealersuit.size() == 5) {
			card1.setText(printdealer(dealersuit.get(0)));
			card2.setText(printdealer(dealersuit.get(1)));
			card3.setText(printdealer(dealersuit.get(2)));
			card4.setText(printdealer(dealersuit.get(3)));
			card5.setText(printdealer(dealersuit.get(4)));
		}
		if (dealersuit.size() == 6) {
			card1.setText(printdealer(dealersuit.get(0)));
			card2.setText(printdealer(dealersuit.get(1)));
			card3.setText(printdealer(dealersuit.get(2)));
			card4.setText(printdealer(dealersuit.get(3)));
			card5.setText(printdealer(dealersuit.get(4)));
			card6.setText(printdealer(dealersuit.get(5)));
		}
		frame.repaint();
	}

	private void stand() {
		// do nothing
	}

	public static void main(String[] args) {
		new _BlackJack();
	}

}