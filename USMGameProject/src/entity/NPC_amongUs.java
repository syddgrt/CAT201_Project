package entity;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class NPC_amongUs extends Entity{



	public NPC_amongUs(GamePanel gp) {
		super(gp);
		direction = "left";
		speed = 2;
		
		setRoninGhostImage();
		setDialogue();
		setNPCName();
	}
	
	public void setRoninGhostImage(){
		try {
			left1 = ImageIO.read(getClass().getResourceAsStream("/npc/amongUsLeft1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/npc/amongUsLeft2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/npc/amongUsRight1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/npc/amongUsRight2.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void setDialogue() {
		
		dialogues[0] = "SUS????";
		dialogues[1] = "SUS!!!!";
		//dialogues[0] = "For the doomed and cowardly, only despair\nwill be the path you will lead, for the brave and courageous a \nwealthy price lies ahead";
		
	}
	
	public void setNPCName() {
		
		npc[0] = "AMONGUS" ;
		
	}
	
	public void setAction() {
		
		directionInterval ++;
		if(directionInterval == 120) {
			Random random = new Random(); // Use random
			int i = random.nextInt(100)+1; // Roll a number from 1 - 100
			
			// Worlds most simplest movement A.I
			
			if(i <= 25 && i <=50) {
				direction = "right";
			}
			//50% of the time, movement is left
			if(i > 50 && i <= 100) {
				direction = "left";
			}
			//50% of the time, movement is right
			directionInterval = 0;
		}
		
	}
	
	public void speak() {
		
		super.speak();
	}
}