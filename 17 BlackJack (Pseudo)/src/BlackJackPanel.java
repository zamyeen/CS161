import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;


public class BlackJackPanel extends JPanel{

	/**ArrayList<Card> userCardList = new ArrayList<Card>();
	ArrayList<Card> dealerCardList = new ArrayList<Card>();*/
	public BlackJackPanel() {
		super();
		
	}

	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		Color DarkYellow = new Color (212, 171, 42); // draw the background of the cards area
		g.setColor(DarkYellow);
		//g.drawRect(this.getWidth()*3/4, 0, this.getWidth()*1/4, this.getHeight());
		//(x,y,width,height)
		g.fillRect(this.getWidth()*7/10, 0, this.getWidth()*3/10, this.getHeight());  // draw the buttons area
		
		g.setColor(Color.WHITE); // because I couldn't figure out how to pull images, I just had to draw all these white
		// rectangles, I am really sorry for making this lame.....
		g.fillRect(20, 50, 100, 200);
		g.fillRect(130, 50, 100, 200);
		g.fillRect(240, 50, 100, 200);
		g.fillRect(350, 50, 100, 200);
		g.fillRect(460, 50, 100, 200);
		g.fillRect(570, 50, 100, 200);
		
		g.fillRect(20, 280, 100, 200);
		g.fillRect(130, 280, 100, 200);
		g.fillRect(240, 280, 100, 200);
		g.fillRect(350, 280, 100, 200);
		g.fillRect(460, 280, 100, 200);
		g.fillRect(570, 280, 100, 200);
		
		g.fillRect(20, 500, 100, 200);
		g.fillRect(130, 500, 100, 200);
		g.fillRect(240, 500, 100, 200);
		g.fillRect(350, 500, 100, 200);
		g.fillRect(460, 500, 100, 200);
		g.fillRect(570, 500, 100, 200);
		
	//	g.fillRect(680, 10, 200, 200);		    
	}
	
	/**public void UserAddCard(String newsuit, int newnumber) {
		userCardList.add(new Card(newsuit,newnumber));
	}
	
	public void DealerAddCard(String newsuit,int newnumber) {
		dealerCardList.add(new Card(newsuit,newnumber));
	}*/
	
}