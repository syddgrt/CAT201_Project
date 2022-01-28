package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJmana extends SuperObject{
	
	public OBJmana() {
		
	    name = "mana";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/mana.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}

}