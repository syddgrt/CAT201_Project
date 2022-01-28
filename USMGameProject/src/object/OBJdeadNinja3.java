package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJdeadNinja3 extends SuperObject{
	
	public OBJdeadNinja3() {
		
	    name = "deadNinja3";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/deadNinja3.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}

}
