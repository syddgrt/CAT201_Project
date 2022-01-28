package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJlife extends SuperObject{
	
	public OBJlife() {
		
	    name = "life";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/life.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}

}