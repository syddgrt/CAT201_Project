package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJkatana1 extends SuperObject{
	
	public OBJkatana1() {
		
	    name = "katana1";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/katana1.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}

}
