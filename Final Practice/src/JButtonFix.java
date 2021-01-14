import javax.swing.*;

public class JButtonFix {

		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JButton button = new JButton("Button");
	
	public JButtonFix() {	
		frame.setSize(800, 800);
		
		panel.setLayout(null);
		button.setBounds(10, 10, 100, 100);
		panel.add(button);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
}
