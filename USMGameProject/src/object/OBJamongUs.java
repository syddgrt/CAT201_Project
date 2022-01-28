package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJamongUs extends SuperObject{
	
	public OBJamongUs() {
		
	    name = "AMONGUS";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/amongUsLeft1.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}

}
