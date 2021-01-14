//This class is used for only getting and setting 
public class Node {

	//Important values of a Node (x,y,label,highlighted)
	int x;
	int y;
	String label;
	boolean highlighted = false;
	
	public Node(int newx, int newy, String newlabel) {
		x = newx;
		y = newy;
		label = newlabel;
		highlighted = false;
	}
	
	public boolean getHighlight() {
		return highlighted;
	}
	
	public void setHighlight (boolean highlight) {
		this.highlighted = highlight;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
}