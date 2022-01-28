package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJboots extends SuperObject{
	
	public OBJboots() {
		
	    name = "boots";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/ninjaBoots.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}

}
