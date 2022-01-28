package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJwillPower extends SuperObject{
	
	public OBJwillPower() {
		
	    name = "willPower";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/willpowerReal.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
