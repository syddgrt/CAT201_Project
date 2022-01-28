package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJarmor extends SuperObject{
	
	public OBJarmor() {
		
	    name = "armor";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/armor.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}

}
