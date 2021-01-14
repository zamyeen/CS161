/**Zareef Amyeen
 * May 15, 2018
 * Description: The Graph Creator program is designed to have nodes and edges and create */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GraphCreator implements ActionListener, MouseListener{

	int EdgeCounter = 1;
	char NodeCounter = 'A';
	
	JFrame frame = new JFrame();
	GraphPanel panel = new GraphPanel();
	//For Creating Graph (On the Left)
	JButton nodeButton = new JButton("Node");
	JButton edgeButton = new JButton("Edge");
	
	
	JTextField labelsTF = new JTextField("A");
	//For Connected Nodes (On the Right)
	JTextField firstNode = new JTextField("First");
	JTextField secondNode = new JTextField("Second");
	
	//For Travelling Salesman Problem (At the very bottom)
	JTextField salesmanStartTF = new JTextField("A");
	
	JButton salesmanButton = new JButton("Test Connected");
	JButton connectedButton = new JButton("Test Connected");
	
	//Different Containers
	Container east = new Container();
	Container west = new Container();
	Container south = new Container();
	
	//Used in ActionListener to create Node's and Edges
	final int NODE_CREATE = 0;
	final int EDGE_FIRST = 1;
	final int EDGE_SECOND = 2;
	int state = NODE_CREATE;
	Node first = null;
	
	//ArrayList that has all completed paths from one Node to another Node
	ArrayList<ArrayList<Node>> completed = new ArrayList<ArrayList<Node>>();
	
	//ArrayLists that have path and path lengths
	ArrayList<Integer> pathLengths = new ArrayList<Integer>();
	ArrayList<ArrayList<Node>> paths = new ArrayList<ArrayList<Node>>();
	
	public GraphCreator() {
		//Create initial Border Layout
		frame.setSize(800,600);
		frame.setLayout(new BorderLayout());
		frame.add(panel,BorderLayout.CENTER);
		
		//Creating Edge's and Node Buttons (Putting them into the Frame)
		west.setLayout(new GridLayout(3,1));
		west.add(nodeButton);
		nodeButton.addActionListener(this);
		west.add(edgeButton);
		edgeButton.addActionListener(this);
		edgeButton.setBackground(Color.BLACK);
		west.add(labelsTF);
		frame.add(west, BorderLayout.WEST);
		
		//Creating the Connected Function/Button (Putting them into the Frame)
		east.setLayout(new GridLayout(3,1));
		east.add(firstNode);
		east.add(secondNode);
		east.add(connectedButton);
		connectedButton.addActionListener(this);
		frame.add(east, BorderLayout.EAST);
		
		//Creating the Salesman Function/Button (Putting them into the Frame)
		south.setLayout(new GridLayout(1,2));
		south.add(salesmanStartTF);
		south.add(salesmanButton);
		salesmanButton.addActionListener(this);
		frame.add(south, BorderLayout.SOUTH);
		
		panel.addMouseListener(this);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);		
		}

	public static void main(String[] args) {
		new GraphCreator();
	}

	/**This Method Is NOT Used*/
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	/**This Method Is NOT Used*/
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	/**This Method Is NOT Used*/
	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	/**This Method Is NOT Used*/
	@Override
	public void mousePressed(MouseEvent e) {
			
	}

	
	/**This Method is Used to find the position of the mouse and creates Node's where the mouse was released at
	 * Depending on if State is Edge First or Edge Second (If the button's are pressed) an Edge will be created between two Nodes 
	 * The Edge will be created dependent on if the place clicked is inside the Node*/
	@Override
	public void mouseReleased(MouseEvent e) {
		//System.out.println(e.getX() + "," + e.getY());
		//If Node Button was pressed then create a Node at the specified position that the mouse was released
		if(state == NODE_CREATE) {
			panel.addNode(e.getX(), e.getY(),labelsTF.getText());
			//Create Node at the specific point
			NodeCounter += 1;
			labelsTF.setText(Character.toString((char)((int)labelsTF.getText().charAt(0)+1)));
		}
		//If the Edge Button has been pressed and nothing has been done
		//Find which Node the mouse has been released on and highlight it
		else if(state == EDGE_FIRST) {
			Node n = panel.getNode(e.getX(), e.getY());
			//Find the node that exists at the coordinates that the Mouse was released on
			if (n == null) {
				JOptionPane.showMessageDialog(frame, "No Node has been Pressed!");
			}
			if (n != null) {
				//If it exists keep that node by assigning it to the variable Node first
				first = n;
				//Change the state to Edge Second and highlight the Node
				state = EDGE_SECOND;
				n.setHighlight(true);
			}
		}
		//If the Edge Button has been pressed and one Node has been previously highlighted
		//Connect the two different Nodes (Stop all Highlighting)
		else if (state  == EDGE_SECOND) {
			//Find the node that exists at the coordinates that the Mouse was released on
			Node n = panel.getNode(e.getX(), e.getY());
			if (n == null) {
				JOptionPane.showMessageDialog(frame, "No Node has been Pressed!");
			}
			if (n != null && !first.equals(n)) {
				//If the node exists and is not the one that was previously pressed on then
				String s = labelsTF.getText();
				boolean valid = true;
				//Check if the Edge's text is a Digit
				for (int i = 0; i < s.length(); i++) {
					if (!Character.isDigit(s.charAt(i))) {
						valid = false;
					}
				}
				//If the Edge text IS a Digit then 
				//Stop Highlighting
				//Add in an Edge
				//Make first back to null to restart Edge creation process
				//Make State back to EdgeFirst to restart Edge creation process
				if (valid == true) {
					first.setHighlight(false);
					panel.addEdge(first,n, labelsTF.getText());
					first = null;
					state = EDGE_FIRST;
					EdgeCounter += 1;
					labelsTF.setText(Integer.toString(EdgeCounter));
					panel.stopHighlighting();
				}
				//If the Edge Text is NOT a Digit then create a Message that appears on screen that throws an error
				else {
					JOptionPane.showMessageDialog(frame, "Can only have digits in edge labels.");
				}
				frame.repaint();
				//labelsTF.setText(Character.toString((char)((int)labelsTF.getText().charAt(0)+1)));
			}
		}
		frame.repaint();
	}

	@Override
	/**This Method is used to check which BUTTON has been pressed on
	 * There are 4 Different Buttons
	 * 	The Node Button creates a Node at specified mouse release coordinates
	 * 	The Edge Button highlights a Node (if state is Edge_First)
	 * 	The Edge Button connects two Nodes (if state is Edge_Second)
	 *	The Connected Button checks if two Nodes have a path to get to each other*/
	public void actionPerformed(ActionEvent e) {
		//Highlights the Node Button
		//DeHighlights the Edge Button
		//Makes State Node_Create which means that a Node will be created after the mouse is released
		if (e.getSource().equals(nodeButton)) {
			//System.out.println("Node!");
			nodeButton.setBackground(Color.GREEN);
			edgeButton.setBackground(Color.LIGHT_GRAY);
			labelsTF.setText(Character.toString(NodeCounter));
			state = NODE_CREATE;
		}
		if (e.getSource().equals(edgeButton)) {
			//System.out.println("Edge!");
			//Highlights the Edge Button
			//DeHighlights the Node Button
			//Changes State to Edge_First *Note that an Edge Button will not be pressed another time until an Edge has been created
			edgeButton.setBackground(Color.GREEN);
			nodeButton.setBackground(Color.LIGHT_GRAY);
			state = EDGE_FIRST;
			labelsTF.setText(Integer.toString(EdgeCounter));
			
			panel.stopHighlighting();
			frame.repaint();
		}
		if (e.getSource().equals(connectedButton)) {
			//Checks if there is valid text in the textfield's
			if (panel.nodeExists(firstNode.getText()) == false) {
				JOptionPane.showMessageDialog(frame, "First Node is not in your Graph");
			}
			else if (panel.nodeExists(secondNode.getText()) == false) {
				JOptionPane.showMessageDialog(frame, "Second Node is not in your Graph");
			}
			else {
				Queue queue = new Queue();
				ArrayList<String> connectedList = new ArrayList<String>();
				connectedList.add(panel.getNode(firstNode.getText()).getLabel());
				
				ArrayList<String> edges = panel.getConnectedLabels(firstNode.getText());
				for (int i = 0; i < edges.size(); i++) {
					//check each connected node
					queue.enqueue(edges.get(i));
				}
				while(queue.isEmpty() == false) {
					String currentNode = queue.dequeue();
					if (connectedList.contains(currentNode) == false){
						connectedList.add(currentNode);
					}
					edges = panel.getConnectedLabels(currentNode);
					for (int i = 0; i < edges.size(); i++) {
						if(connectedList.contains(edges.get(i)) == false) {
							queue.enqueue(edges.get(i));
						}
					}
				}
				if (connectedList.contains(secondNode.getText())) {
					JOptionPane.showMessageDialog(frame, "Connected!");
				}
				else {
					JOptionPane.showMessageDialog(frame, "Not Connected.");
				}
			}
		}
		if (e.getSource().equals(salesmanButton)) {
			//Clear both arraylists from everything performed last time
			paths.clear();
			pathLengths.clear();
			//Creates a boolean array the size of the number of Node's to make sure the path's have gone through all of the different Node's
			boolean[] visited = new boolean[panel.getNodeCount()];
			travel(panel.getNode(salesmanStartTF.getText()), new ArrayList<Node>(), 0, visited);
			if (pathLengths.size() > 0) {
				int index = 0;
				int min = Integer.MAX_VALUE;
				for (int i = 0; i < paths.size(); i++) {
					if (pathLengths.get(i) < min) {
						index = i;
						min = pathLengths.get(i);
					}
				} 
				StringBuilder path = new StringBuilder();
				for (int i = 0; i < paths.get(index).size(); i++) {
					path.append(paths.get(index).get(i).getLabel()).append("-");
				}
				path.deleteCharAt(path.length() - 1);
				JOptionPane.showMessageDialog(frame, "The least costly path is " + path + " with the cost of " + min);
			} 
			else {
				JOptionPane.showMessageDialog(frame, "Not all nodes can be visited.");
			}
		}
	}

	public void travel(Node n, ArrayList<Node> path, int total, boolean[] visited) {
		//Adds the node to the path
		path.add(n);
		//Assigns the just added node to true in the boolean visited
		visited[panel.getIndex(n.getLabel())] = true;
		//If the path length has not yet finished to the end length
		if (panel.getNodeCount() == path.size()) {
			ArrayList<Node> pathCopy = new ArrayList<Node>();
			//For every node in path add that node into path copy (to create a copy ArrayList)
			for (int i = 0; i < path.size(); i++) {
				pathCopy.add(path.get(i));
			}
			paths.add(pathCopy);
			pathLengths.add(total);
			return;
		} else { // otherwise call this method on the next adjacent node
			for (Edge e: panel.edgeList) {
				if (e.getOtherEnd(n) != null) {
					if (!visited[panel.getIndex(e.getOtherEnd(n).getLabel())]) {
						travel(e.getOtherEnd(n), path, total + Integer.parseInt(e.getLabel()), visited);
						path.remove(path.size() - 1);
						visited[panel.getIndex(e.getOtherEnd(n).getLabel())] = false;
					}
				}
			}
		}
	}
	
}