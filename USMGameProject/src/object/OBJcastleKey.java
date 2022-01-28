package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJcastleKey extends SuperObject{
	
	public OBJcastleKey() {
		
	    name = "castleKey";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/castleKey.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}

}