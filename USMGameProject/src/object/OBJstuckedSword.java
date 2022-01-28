package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJstuckedSword extends SuperObject{
	
	public OBJstuckedSword() {
		
	    name = "stuckedSword";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/stuckedSword.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}

}
