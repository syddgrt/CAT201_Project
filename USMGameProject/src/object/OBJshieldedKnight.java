package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJshieldedKnight extends SuperObject{
	
	public OBJshieldedKnight() {
		
	    name = "shieldedKnight";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/shieldedKnightLeft1.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}

}
