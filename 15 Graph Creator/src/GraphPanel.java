import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GraphPanel extends JPanel{

	//Creates an ArrayList of Node's that carry all the different Node's that exist
	ArrayList<Node> nodeList = new ArrayList<Node>();
	ArrayList<Edge> edgeList = new ArrayList<Edge>();
	//The Radius of a Node
	int circleRadius = 20;
	//An Adjacency Matrix that shows which Button's are connected to which
	ArrayList<ArrayList<Boolean>> adjacency = new ArrayList<ArrayList<Boolean>>();
	
	//Calls the super function to run the class
	public GraphPanel() {
		super();
	}
	
	//Goes through the ArrayList of Strings to check if theyare connected
	public ArrayList<String> getConnectedLabels (String label) {
		ArrayList<String> toReturn = new ArrayList<String>();
		int j = getIndex(label);
		for (int i = 0; i < adjacency.size(); i++) {
			if((adjacency.get(j).get(i) == true) && (nodeList.get(i).getLabel().equals(label) == false)) {
				toReturn.add(nodeList.get(i).getLabel());
			}
		}
		return toReturn;
	}
	
	//Given a String (The Node's Text) check at which Node Index in the ArrayList the Node exists and return that integer
	public int getIndex (String s) {
		for (int i = 0; i < nodeList.size(); i++) {
			Node node = nodeList.get(i);
			if (s.equals(node.getLabel())) {
				return i;
			}
		}
		return -1;
	}
	
	//Prints out the Adjacency Matrix
	//If two Nodes are connected those paths will be true
	//Otherwise all Nodes are false (because a Node connected to itself does not count)
	public void printAdjacency() {
		System.out.println();
		for (int i = 0; i < adjacency.size(); i++) {
			for (int j = 0; j < adjacency.size(); j++) {
				System.out.print(adjacency.get(i).get(j)+ "\t");
			}
			System.out.println();
		}
	}
	
	/**
	 * Adjacency Matrix
	 * 	A B C
	 * A1 1 0
	 * B1 1 1
	 * C0 1 1
	 */
	
	//The Add Node Function creates a Node with the x value y value and the text value
	public void addNode(int newx, int newy, String newlabel) {
		nodeList.add(new Node(newx, newy, newlabel));
		int index = nodeList.size()-1;
		//Create a new Node (Boolean Value in the Adjacency Matrix corresponding to this specific Node that was created)
		adjacency.add(new ArrayList<Boolean>());	
		for (int i = 0; i < adjacency.size() - 1; i++) {
			//Go through the adjacency matrix and add in all falses into that entire arraylist
			adjacency.get(i).add(false);
		}
		//adjacency.get(index).add(index, true);
		for (int i = 0; i < adjacency.size(); i++) {
			adjacency.get(adjacency.size()-1).add(false);
		}
		printAdjacency();
	}
	
	//This method adds an edge between two different nodes
	public void addEdge(Node first, Node second, String newlabel) {
		edgeList.add(new Edge(first,second,newlabel));
		int firstIndex = 0;
		int secondIndex = 0;
		for (int i = 0; i < nodeList.size(); i++) {
			if(first.equals(nodeList.get(i))) {
				firstIndex = i;
			}
			if(second.equals(nodeList.get(i))) {
				secondIndex = i;
			}
		}
		adjacency.get(firstIndex).set(secondIndex, true);
		adjacency.get(secondIndex).set(firstIndex, true);
		printAdjacency();
	}
	
	//Check's if the specified Node exists dependent on it's text value
	public boolean nodeExists (String s) {
		for (int i = 0; i < nodeList.size(); i++) {
			if(nodeList.get(i).getLabel().equals(s)) {
				return true;
			}
		}
		return false;
	}
	
	//Get's the node from it's range of x and y values (if the coordinates are within the range of the node)
	public Node getNode(int x, int y) {
		for (int i = 0; i < nodeList.size(); i++) {
			Node node = nodeList.get(i);
			//a^2 + b^2 = c^2
			double radius = Math.sqrt(Math.pow(x-node.getX(),2) + Math.pow(y-node.getY(), 2));
			if (radius < 20) {
				return node;
			}
		}
		return null;
	}
	
	//Get's the node dependent on text value inserted
	public Node getNode(String s) {
		for (int i = 0; i < nodeList.size(); i++) {
			Node node = nodeList.get(i);
			if (s.equals(node.getLabel())) {
				return node;
			}
		}
		return null;
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.drawLine(0, 0, 400, 400);
		for(int i = 0; i < nodeList.size();i++) {
			if(nodeList.get(i).getHighlight() == true) {
				g.setColor(Color.RED);
			}
			else {
				g.setColor(Color.BLACK);
			}
			g.drawOval(nodeList.get(i).getX() - circleRadius
					, nodeList.get(i).getY() - circleRadius
					, circleRadius*2, 
					circleRadius*2);
			g.drawString(nodeList.get(i).getLabel(),
					nodeList.get(i).getX()-circleRadius,
					nodeList.get(i).getY()-circleRadius);
		}
		for (int i = 0; i < edgeList.size(); i++) {
			g.setColor(Color.BLACK);
			Node first = edgeList.get(i).getFirst();
			Node second = edgeList.get(i).getSecond();
			g.drawLine (first.getX(),
						first.getY(),
						second.getX(),
						second.getY());
			g.drawString(edgeList.get(i).getLabel(),
					(first.getX()+second.getX())/2,
					(first.getY()+second.getY())/2);
		}
	}
	//Stops the highlighting of the nodes
	public void stopHighlighting() {
		for (int i = 0; i < nodeList.size(); i++) {
			nodeList.get(i).setHighlight(false);
		}
	}
	
	//Gets the total number of nodes
	public int getNodeCount() {
		return nodeList.size();
	}

}