package entity;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class NPC_runeStone extends Entity{


	public NPC_runeStone(GamePanel gp) {
		super(gp);
		direction = "up";
		speed = 2;
		
		setruneStoneImage();
		setNPCName();
		setDialogue();
	}
	
	public void setruneStoneImage(){
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/npc/willPower1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/npc/willPower2.png"));
			//up3 = ImageIO.read(getClass().getResourceAsStream("/npc/runeStone3.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/npc/willPower1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/npc/willPower2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/npc/willPower1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/npc/willPower2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/npc/willPower1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/npc/willPower2.png"));
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void setDialogue() {
		
		dialogues[0] = "One was blinded \nby sheer hatred... " ;
		dialogues[1] = "Only peace and tranquility \nwithin the nature...";
		dialogues[2] = "May shine a way...";
		//dialogues[0] = "For the doomed and cowardly, only despair\nwill be the path you will lead, for the brave and courageous a \nwealthy price lies ahead";
		
	}
	
	public void setNPCName() {
		
		npc[0] = "Blue wisp" ;
		
	}
	public void setAction() {
		
		directionInterval ++;
		if(directionInterval == 120) {
			Random random = new Random(); // Use random
			int i = random.nextInt(100)+1; // Roll a number from 1 - 100
			
			// Worlds most simplest movement A.I
			//50% of the time, movement is right
			if(i <= 25 && i <=50) {
				direction = "up";
			}
			//50% of the time, movement is down
			if(i > 50 && i <= 100) {
				direction = "down";
			}
			
			directionInterval = 0;
		}
		
	}
	
	public void speak() {
		
		super.speak();
	}
}
