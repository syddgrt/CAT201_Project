package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJartifact extends SuperObject{
	
	public OBJartifact() {
		
	    name = "artifact";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/eyeOfAgamotto.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}

}
