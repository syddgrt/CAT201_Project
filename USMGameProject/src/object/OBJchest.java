package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJchest extends SuperObject{
	
	public OBJchest() {
		
	    name = "chest";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}