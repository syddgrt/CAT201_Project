package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJdeadNinja2 extends SuperObject{
	
	public OBJdeadNinja2() {
		
	    name = "deadNinja2";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/deadNinja2.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}

}
