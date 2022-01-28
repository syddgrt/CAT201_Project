package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJkey extends SuperObject{
	
	public OBJkey() {
		
	    name = "key";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}

}