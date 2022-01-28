package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJdojoDoor extends SuperObject{
	
	public OBJdojoDoor() {
		
	    name = "dojoDoor";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/dojoDoor.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}

}