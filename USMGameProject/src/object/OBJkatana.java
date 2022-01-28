package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJkatana extends SuperObject{
	
	public OBJkatana() {
		
	    name = "katana";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/katana.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}

}
