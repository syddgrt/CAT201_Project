package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	
	KeyHandler keyH; //declare variable of type class object
	
	
	public final int screenX;
	public final int screenY;
	
	public boolean gotKey = false;
	public boolean gotKatana = false;
	public boolean gotArmor = false;
	public boolean gotBoots = false;
	public boolean gotScroll = false;
	public boolean gotWillPower = false;
	
	public boolean gotCastleKey = false;
	public boolean gotArtifact = false;
	//public boolean shinobiJutsu = false;
	
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp); //pass gp so that Entity can use gp superclass
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2;
		screenY = gp.screenHeight/2;
		
		solidArea = new Rectangle();
		solidArea.x = 7;
		solidArea.y = 15;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 28;
		solidArea.height = 28;
		
		
		
		setDefaultValues();
	
		getPlayerImage();
		
		//pickupObject();
	
	}

	
	public void setDefaultValues() {
		
		worldX = 130;
		worldY = 220;
		//worldX = 2000;
		//worldY = 1400;
		
		speed = 4;
		direction = "left";
	}
	public void getPlayerImage() {
		try {
			//Import sprite !
			if(gotArmor == false) {
				up1 = ImageIO.read(getClass().getResourceAsStream("/player/ninjaUp1.png"));
				up2 = ImageIO.read(getClass().getResourceAsStream("/player/ninjaUp2.png"));
				up3 = ImageIO.read(getClass().getResourceAsStream("/player/ninjaUp3.png"));
				down1 = ImageIO.read(getClass().getResourceAsStream("/player/ninjaDown1.png"));
				down2 = ImageIO.read(getClass().getResourceAsStream("/player/ninjaDown2.png"));
				down3 = ImageIO.read(getClass().getResourceAsStream("/player/ninjaDown3.png"));
				left1 = ImageIO.read(getClass().getResourceAsStream("/player/ninjaLeft1.png"));
				left2 = ImageIO.read(getClass().getResourceAsStream("/player/ninjaLeft2.png"));
				right1 = ImageIO.read(getClass().getResourceAsStream("/player/ninjaRight1.png"));
				right2 = ImageIO.read(getClass().getResourceAsStream("/player/ninjaRight2.png"));
				menuImage = ImageIO.read(getClass().getResourceAsStream("/player/Menuimage.png"));
			}
			if(gotArmor == true) {
				
				up1 = ImageIO.read(getClass().getResourceAsStream("/player/whiteNinjaUp1.png"));
				up2 = ImageIO.read(getClass().getResourceAsStream("/player/whiteNinjaUp2.png"));
				up3 = ImageIO.read(getClass().getResourceAsStream("/player/whiteNinjaUp3.png"));
				down1 = ImageIO.read(getClass().getResourceAsStream("/player/whiteNinjaDown1.png"));
				down2 = ImageIO.read(getClass().getResourceAsStream("/player/whiteNinjaDown2.png"));
				down3 = ImageIO.read(getClass().getResourceAsStream("/player/whiteNinjaDown3.png"));
				left1 = ImageIO.read(getClass().getResourceAsStream("/player/whiteNinjaLeft1.png"));
				left2 = ImageIO.read(getClass().getResourceAsStream("/player/whiteNinjaLeft2.png"));
				right1 = ImageIO.read(getClass().getResourceAsStream("/player/whiteNinjaRight1.png"));
				right2 = ImageIO.read(getClass().getResourceAsStream("/player/whiteNinjaRight2.png"));
				
				if(gotScroll == true) {
					if(keyH.shinobiWalk == true) {
						up1 = ImageIO.read(getClass().getResourceAsStream("/player/shinobiWalk.png"));
						up2 = ImageIO.read(getClass().getResourceAsStream("/player/shinobiWalk.png"));
						up3 = ImageIO.read(getClass().getResourceAsStream("/player/shinobiWalk.png"));
						down1 = ImageIO.read(getClass().getResourceAsStream("/player/shinobiWalk.png"));
						down2 = ImageIO.read(getClass().getResourceAsStream("/player/shinobiWalk.png"));
						down3 = ImageIO.read(getClass().getResourceAsStream("/player/shinobiWalk.png"));
						left1 = ImageIO.read(getClass().getResourceAsStream("/player/shinobiWalk.png"));
						left2 = ImageIO.read(getClass().getResourceAsStream("/player/shinobiWalk.png"));
						right1 = ImageIO.read(getClass().getResourceAsStream("/player/shinobiWalk.png"));
						right2 = ImageIO.read(getClass().getResourceAsStream("/player/shinobiWalk.png"));
					}
					else 
						System.out.println("bodo");
				}
			}
			
			
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	

	public void update() { //gets called 60 times/s
		
		if (keyH.upPressed == true || keyH.downPressed == true || 
				keyH.leftPressed == true || keyH.rightPressed == true || keyH.stopPressedUp == true || keyH.stopPressedDown == true 
				|| keyH.stopPressedLeft == true || keyH.stopPressedRight == true || keyH.interactPressed == true) {
			
			if(keyH.upPressed == true) {	
				direction ="up";
			}
				
			else if (keyH.downPressed == true) {
				
				direction = "down";
					
			}
			else if (keyH.leftPressed == true) {
				
				direction = "left";
			
			}
			else if (keyH.rightPressed == true) {
				
				direction = "right";
					
			}
			else if (keyH.stopPressedUp == true) {
				
				direction ="upStop";
				
			}
			else if (keyH.stopPressedDown == true) {
				
				direction ="downStop";
				
			}
			
			else if (keyH.stopPressedLeft == true) {
				
				direction ="leftStop";
				
			}
			else if (keyH.stopPressedRight == true) {
	
				direction ="rightStop";
				
			}
				
			//Checking collision
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			//Checking object collision
			int objIndex = gp.cChecker.checkObject(this,true);
			pickupObject(objIndex);
			//Checking entity collision
			int entityIndex = gp.cChecker.checkEntity(this, gp.npc);
			interact(entityIndex);
			
			//if collision false, player can move
			if(collisionOn == false && keyH.interactPressed == false) {
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
			
			gp.keyH.interactPressed = false;
			
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
		
			
		
	}
	
	public void pickupObject(int i) {
		
		if (i !=100) 
		{
			
			String objectName = gp.obj[i].name; 
			
			if(objectName == "key"){
				
				gotKey = true;
				gp.obj[i] = null; //delete object that the player touches.
				gp.playSoundEffect(1); // Keys sound effect
				gp.ui.showMessage("A key? In the middle of a dense forest?");
				
			}
			
			if(objectName == "castleKey"){
				
				gotCastleKey = true;
				gp.obj[i] = null; //delete object that the player touches.
				gp.playSoundEffect(1); // Keys sound effect
				gp.ui.showMessage("Another key?...");
				
			}
			
			else if(objectName == "dojoDoor"){
				
				if(gotKey == true) {
					
					gp.obj[i] = null;
					gp.playSoundEffect(5);  // play sound effects for opening door
			
				}
				
			}
			
			else if(objectName == "castleDoor"){
				
				if(gotCastleKey == true) {
					
					gp.obj[i] = null;
					gp.playSoundEffect(5);  // play sound effects for opening door
					gotCastleKey = false;
					
				}
				
			}
			
			else if(objectName == "chest"){
				
				if(gotKey == true) {
					
					gp.obj[i] = null;
					gp.playSoundEffect(5);  // play sound effects for opening chest
					gotScroll = true;
					//getPlayerImage();
					gp.ui.showMessage("A ninja scroll, it says press 'X' to cast Jutsu...");
					
				}
				else 
					gp.ui.showMessage("Hmmm, i wonder what it contains...");
			}
			else if	(objectName == "boots")
			{
				gotBoots = true;
				gp.playSoundEffect(3); // Wearing boots sound effect
				gp.ui.showMessage("Hmm, what a nice pair of boots");
				speed = speed + 1;
				gp.obj[i] = null;
			}
			else if	(objectName == "katana")
			{
				gotKatana = true;
				gp.playSoundEffect(2);  // play sound effects for sword
				gp.ui.showMessage("Masamune grade steel...");
				gp.obj[i] = null;
			}
			else if	(objectName == "willPower")
			{
				gotWillPower = true;
				gp.playSoundEffect(11); //Play sound to show your will power has been amplified
				gp.ui.showMessage("YOU OBTAIN WILLPOWAHHHHH!!!!");
				//speed = speed + 2;
				gp.obj[i] = null;
			}
			else if	(objectName == "armor")
			{
				gotArmor = true;
				gp.playSoundEffect(3);  // play sound effects when obtaining a gear
				gp.ui.showMessage("White ninja yoroi, amazing craftsmanship...");
				gp.obj[i] = null;
				
				getPlayerImage();
			}
			else if(objectName == "artifact"){
				
				gotArtifact = true;
				gp.obj[i] = null; //delete object that the player touches.
				gp.playMusic(10); //Ending music
				gp.ui.showMessage("Strange looking relic...");
				gp.gameState = 5;
				
			}
		
		}
	}
	
	public void interact(int i) {
		
		if(i !=999) {
			
			if(gp.keyH.interactPressed == true) {
				gp.gameState = 3; // dialogue
				gp.npc[i].speak();
			}
			
		}
		gp.keyH.interactPressed = false;
	}
	public void draw(Graphics2D g2) {
	
		
		BufferedImage image = null;
		
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
				image = up3;
			}
			if(spriteNum == 2) {
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
		g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);
		
		
	}

}
