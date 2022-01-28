package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJcharacter extends SuperObject{
	
	public OBJcharacter() {
		
	    name = "character";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/whiteNinjaright1.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}

}
