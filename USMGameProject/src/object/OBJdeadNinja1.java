package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJdeadNinja1 extends SuperObject{
	
	public OBJdeadNinja1() {
		
	    name = "deadNinja1";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/deadNinja1.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}

}
