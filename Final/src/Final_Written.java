import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Final_Written implements ActionListener {
	
	JFrame frame = new JFrame();
	//FinalPanel panel = new FinalPanel();
	JTextField ReadIn = new JTextField();
	JButton go = new JButton();
	
	public Final_Written() {
		frame.setSize(1000, 750);
		frame.setLayout(new BorderLayout());
		//frame.add(panel, BorderLayout.CENTER);
		frame.add(ReadIn, BorderLayout.SOUTH);
		frame.add(go, BorderLayout.NORTH);
		go.addActionListener(this);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main (String[] args) throws IOException{
		new Final_Written();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.equals(go)) {
			try {
				Scanner scanner = new Scanner(new File(ReadIn.getText()));
				scanner.close();
			} catch (FileNotFoundException e1) {
				System.exit(0);
			}
			try {
				PrintWriter out = new PrintWriter (new FileWriter (new File("output.txt")));
				out.close();
				for (int i = 0; i < 101; i++) {
					out.print(Math.random()*100 + " ,");
				}
			} catch (IOException e1) {
				System.exit(0);
			}
			
			
		}
		
	}
}
