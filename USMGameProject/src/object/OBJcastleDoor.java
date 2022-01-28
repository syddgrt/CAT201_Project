package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJcastleDoor extends SuperObject{
	
	public OBJcastleDoor() {
		
	    name = "castleDoor";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/castleDoor.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}

}