package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJenemyLife extends SuperObject{
	
	public OBJenemyLife() {
		
	    name = "enemyLife";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/enemyLife.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}

}