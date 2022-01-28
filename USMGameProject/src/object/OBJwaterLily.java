package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJwaterLily extends SuperObject{
	
	public OBJwaterLily() {
		
	    name = "waterLily";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/waterLily.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}