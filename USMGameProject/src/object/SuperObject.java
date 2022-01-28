package object;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

import main.GamePanel;

import java.awt.Graphics2D;

public class SuperObject {
	
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public Rectangle solidArea = new Rectangle(0,0,48,48);
	public int solidAreaDefaultX = 0;
	public int solidAreaDefaultY = 0;
	
	public int worldX, worldY;
	
	
	public void draw(Graphics2D g2, GamePanel gp) {
		
		double screenX = worldX - gp.player.worldX + gp.player.screenX; //off setting the positions of the camera on the player
		double screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		g2.drawImage(image, (int)screenX, (int)screenY, gp.tileSize, gp.tileSize, null);
	}

}
