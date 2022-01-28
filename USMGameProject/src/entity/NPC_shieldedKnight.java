package entity;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class NPC_shieldedKnight extends Entity{


	public NPC_shieldedKnight(GamePanel gp) {
		super(gp);
		direction = "left";
		speed = 2;
		
		setShieldedKnightImage();
		setDialogue();
		setNPCName();
	}
	
	public void setShieldedKnightImage(){
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/npc/shieldedKnightLeft1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/npc/shieldedKnightLeft2.png"));
			//up3 = ImageIO.read(getClass().getResourceAsStream("/npc/runeStone3.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/npc/shieldedKnightLeft1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/npc/shieldedKnightLeft2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/npc/shieldedKnightRight1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/npc/shieldedKnightRight2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/npc/shieldedKnightRight1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/npc/shieldedKnightRight2.png"));
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void setDialogue() {
		
		dialogues[0] = "HAHA I CANT BELIEVE\nI MISSED ONE" ;
		dialogues[1] = "I'LL SPARE YOUR \nLIFE YOUNG SHINOBI";
		dialogues[2] = "YOU ARE JUST NOT\nWORTH MY TIME\n   'PRESS SPACE TO ATTACK'";
		
	}
	
	public void setNPCName() {
		
		npc[0] = "Shielded Knight" ;
		
	}
	public void setAction() {
		
		directionInterval ++;
		if(directionInterval == 120) {
			Random random = new Random(); // Use random
			int i = random.nextInt(100)+1; // Roll a number from 1 - 100
			
			// Worlds most simplest movement A.I
			//50% of the time, movement is right
			if(i <= 25 && i <=50) {
				direction = "left";
			}
			//50% of the time, movement is down
			if(i > 50 && i <= 100) {
				direction = "right";
			}
			
			directionInterval = 0;
		}
		
	}
	
	public void speak() {
		
		super.speak();
	}
}