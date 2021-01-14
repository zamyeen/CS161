import java.awt.*;
import java.io.File;

import javax.imageio.ImageIO;

public class Semi extends Vehicle {

	Image myImage;
	
	public Semi(int newx, int newy) {
		//Call the vehicle method with the x and y values
		super(newx,newy);
		width = 100;
		height = 40;
		speed = 5;
		
		try {
			myImage = ImageIO.read(new File("Truck.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void paintVehicle(Graphics g) {
		g.drawImage(myImage, x,y, null);
	}
	
}
