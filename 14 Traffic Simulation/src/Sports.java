import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class Sports extends Vehicle {

	Image myImage;
	
	public Sports(int newx, int newy) {
		super(newx,newy);
		width = 40;
		height = 21;
		speed = 12;
		try {
			myImage = ImageIO.read(new File("Sports.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void paintVehicle(Graphics g) {
		g.drawImage(myImage, x,y, null);
	}
	
}
