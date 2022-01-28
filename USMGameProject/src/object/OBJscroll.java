package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJscroll extends SuperObject{
	
	public OBJscroll() {
		
	    name = "shinobiScroll";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/shinobiScroll.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}

}
