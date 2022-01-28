package entity;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class NPC_roninGhost extends Entity{



	public NPC_roninGhost(GamePanel gp) {
		super(gp);
		direction = "left";
		speed = 2;
		
		setRoninGhostImage();
		setNPCName();
		setDialogue();
	}
	
	public void setRoninGhostImage(){
		try {
			left1 = ImageIO.read(getClass().getResourceAsStream("/npc/roninSpiritLeft1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/npc/roninSpiritLeft2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/npc/roninSpiritRight1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/npc/roninSpiritRight2.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void setDialogue() {
		
		dialogues[0] = "Young shinobi, pray tell \nhow did you end up here...";
		dialogues[1] = "I have been wandering this \nland for as long as i can \nremember...";
		//dialogues[0] = "For the doomed and cowardly, only despair\nwill be the path you will lead, for the brave and courageous a \nwealthy price lies ahead";
		
	}
	
	public void setNPCName() {
		
		npc[0] = "Ronin Ghost" ;
		
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