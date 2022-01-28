package entity;

import java.awt.image.BufferedImage;

import main.GamePanel;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Entity {
	
	GamePanel gp;
	public double worldX, worldY;
	
	public double speed;
	
	public BufferedImage up1, up2,up3, down1, down2, down3, left1, left2,left3, right1, right2, right3,menuImage;
	public BufferedImage jutsuWalk;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle solidArea = new Rectangle(0,0,48,49);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	
	//public int enemyStatus = 1; // alive
	//public boolean death = false;
	
	public int directionInterval = 0;
	String dialogues[] = new String[20];
	String npc[] = new String[20];
	
	int dialogueIndex = 0;
	int npcIndex = 0;
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	//boolean castJutsu = false;
	public void setAction() {
		
	}
	public void speak() {
		
		if(dialogues[dialogueIndex] == null) {
			dialogueIndex = 0;
		}
		gp.ui.currentDialogue = dialogues[dialogueIndex];
		gp.ui.currentNPC = npc[npcIndex];
		dialogueIndex++;
		
		if(gp.player.direction == "up") {
			direction = "right";
			
		}
		if(gp.player.direction == "down") {
			direction = "left";
			
		}
		if(gp.player.direction == "left") {
			direction = "right";
			
		}
		if(gp.player.direction == "right") {
			direction = "left";
			
		}
	}
	public void update() {
		setAction();
		
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this,false);
		gp.cChecker.checkPlayer(this);
		
		//if collision false, player can move
		if(collisionOn == false) {
			switch(direction) {
			case "up":
				worldY = worldY - speed;
				break;
			case "down":
				worldY = worldY + speed;
				break;
			case "left":
				worldX = worldX - speed;
				break;
			case "right":
				worldX = worldX + speed;
				break;
			}
		}
		
		spriteCounter++; //in every single frame. counter increased by 1
		if(spriteCounter > 8) { //if hits 10(change the sprite) basically an animation playing speed control
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if(spriteNum == 2)
			{
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		double screenX = worldX - gp.player.worldX + gp.player.screenX; //off setting the positions of the camera on the player
		double screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		switch(direction) {
		case "up":
			if (spriteNum == 1) {
				image = up1;
			}
			
			if (spriteNum ==2) {
				image = up2;
			}
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
			}
			
			if (spriteNum ==2) {
				image = down2;
			}
			break;
		case "left":
			if (spriteNum == 1) {
				image = left1;
			}
			
			if (spriteNum ==2) {
				image = left2;
			}
			break;
		case "right":
			if (spriteNum == 1) {
				image = right1;
			}
			
			if (spriteNum ==2) {
				image = right2;
			}
			break;
		case "upStop":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			if(spriteNum == 3) {
				image = up3;
			}
			break;
		case "downStop":
			if(spriteNum == 1) {
				image = down3;
			}
			if(spriteNum == 2) {
				image = down3;
				
			}
			break;
		case "leftStop":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left1;
				
			}
			break;
		case "rightStop":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right1;
				
			}
			break;
		
		}
		g2.drawImage(image, (int)screenX, (int)screenY, gp.tileSize, gp.tileSize, null);
		
	}
	

}
