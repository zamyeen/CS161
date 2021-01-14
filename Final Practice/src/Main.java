import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JButton button = new JButton("Button");
    panel.setLayout(null);
    button.setBounds(200, 100, 100, 60);
    panel.add(button);

    frame.add(panel);
    // setLayout(null);
    frame.setDefaultCloseOperation(3);
    frame.setSize(400, 400);
    frame.setVisible(true);
  }
}