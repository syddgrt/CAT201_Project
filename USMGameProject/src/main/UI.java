package main;

import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.awt.BasicStroke;

import javax.swing.JPanel;
import javax.swing.text.AttributeSet.ColorAttribute;
import javax.swing.text.AttributeSet.FontAttribute;

import object.OBJkey;
import object.OBJlife;
import object.OBJmana;
import object.OBJkatana1;
import object.OBJboots;
import object.OBJcastleKey;
import object.OBJcharacter;
import object.OBJenemyLife;
import object.OBJamongUs;
import object.OBJarmor;
import object.OBJartifact;
import object.OBJscroll;
import object.OBJshieldedKnight;

public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font dialogueFont,samuraiFont,ninjaFont;
	BufferedImage keyImage;
	BufferedImage katana1Image;
	BufferedImage bootsImage;
	BufferedImage armorImage;
	BufferedImage scrollImage;
	
	BufferedImage castleKeyImage;
	BufferedImage artifactImage;
	
	BufferedImage lifeImage;
	BufferedImage manaImage;
	BufferedImage enemyLifeImage;
	
	BufferedImage characterImage;
	BufferedImage shieldedKnightImage;
	BufferedImage AMONGUSImage;
	
	//JPanel healthBarPanel;
	
	public String currentDialogue = "";
	public String currentNPC = "";
	
	public boolean messageOn = false;
	public String message = "";
	//Font arial_40;
	
	public int messageTimer = 0;
	
	public int menuChoice;
	public int keybindingChoice;
	public int loreScreenChoice;
	public int endingChoice = 1;
	public int combatChoice = 1;
	
	public int titleScreenState = 0;
	public int loreScreenState = 1;
	public int keybindingScreenState = 2;
	public int lore2ScreenState = 3;
	
	public int endingScreenState = 0;
	
	public int healthValue = 1;
	public int manaValue = 5;
	public int enemyHealthValue = 5;
	
	public int enemyStatus = 1; //alive
	
	public UI(GamePanel gp) {
		
		this.gp = gp;
		
		
		OBJkey key = new OBJkey();
		keyImage = key.image;
		
		OBJkatana1 katana = new OBJkatana1();
		katana1Image = katana.image;
		
		OBJboots boots = new OBJboots();
		bootsImage = boots.image;
		
		OBJarmor armor = new OBJarmor();
		armorImage = armor.image;
		
		OBJcastleKey castleKey = new OBJcastleKey();
		castleKeyImage = castleKey.image;
		
		OBJartifact artifact = new OBJartifact();
		artifactImage = artifact.image;
		
		OBJscroll scroll = new OBJscroll();
		scrollImage = scroll.image;
		
		OBJlife life = new OBJlife();
		lifeImage = life.image;
		
		OBJenemyLife enemyLife = new OBJenemyLife();
		enemyLifeImage = enemyLife.image;
		
		OBJmana mana = new OBJmana();
		manaImage = mana.image;
		
		OBJcharacter character = new OBJcharacter();
		characterImage = character.image;
		
		OBJshieldedKnight shieldedKnight = new OBJshieldedKnight();
		shieldedKnightImage = shieldedKnight.image;
		
		OBJamongUs AMONGUS = new OBJamongUs();
		AMONGUSImage = AMONGUS.image;
		
//		healthBarPanel = new JPanel();
//		healthBarPanel.setBounds(100,100,300,20);
//		healthBarPanel.setBackground(Color.red);
		
		InputStream is1 = getClass().getResourceAsStream("/font/samurai.ttf");
		try {
			samuraiFont = Font.createFont(Font.TRUETYPE_FONT, is1);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		InputStream is2 = getClass().getResourceAsStream("/font/dialogueFont.ttf");
		try {
			dialogueFont = Font.createFont(Font.TRUETYPE_FONT, is2);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showMessage(String text) {
		
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		if(gp.gameState == 0) { // Game menu page
			drawGameMenu();
			
		}
		if(gp.gameState == 1) { // Game state
			drawPickupItem();
			drawHealthBar();
			if(gp.player.gotWillPower) {
				drawManaBar();
			}	
		}
		// PAUSE 
		if(gp.gameState == 2) { // Pause state
			drawPauseScreen();
		}
		// DIALOGUE
		if(gp.gameState == 3) { // Dialogue screen state
			drawDialogueScreen();
		}
		// COMBAT
		if(gp.gameState == 4) {
			drawCombatScreen();
		}
		if(gp.gameState == 5) {
			drawEndScreen();
		}
	
	}
	
	public void drawGameMenu() {
		
		if(titleScreenState == 0) {
			
			// Display game title name
			g2.setFont(samuraiFont);
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,42F));
			String text = "Chaos Ascendance";
			String choiceText;
			int x = 43;
			int y= 140;
			
			//Shadow
			g2.setColor(Color.red);
			g2.drawString(text,x+2,y+2);
			//Text color
			g2.setColor(Color.white);
			g2.drawString(text,x,y);
			x = 265;
			y = 150;
			g2.drawImage(gp.player.menuImage,x,y,gp.tileSize*4,gp.tileSize*4,null);
			
			// Menu
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,24F));
			
			choiceText = "PLAY";
			x = 300;
			y = y + gp.tileSize*5;
			//Shadow
			g2.setColor(Color.red);
			g2.drawString(choiceText,x+2,y+2);
			//Text
			g2.setColor(Color.white);
			g2.drawString(choiceText,x,y);
			
			if(menuChoice == 0) {
				g2.drawString(">",x-gp.tileSize,y);
			}
			
			choiceText = "CONTROLS";
			x = 300;
			y = y + gp.tileSize;
			//Shadow
			g2.setColor(Color.red);
			g2.drawString(choiceText,x+2,y+2);
			//Text
			g2.setColor(Color.white);
			g2.drawString(choiceText,x,y);
			if(menuChoice == 1) {
				g2.drawString(">",x-gp.tileSize,y);
			}
			
			choiceText = "QUIT";
			x = 300;
			y = y + gp.tileSize;
			//Shadow
			g2.setColor(Color.red);
			g2.drawString(choiceText,x+2,y+2);
			//Text
			g2.setColor(Color.white);
			g2.drawString(choiceText,x,y);
			if(menuChoice == 2) {
				g2.drawString(">",x-gp.tileSize,y);
			}
			//g2.drawString(text)
		}
		else if(titleScreenState == loreScreenState) {
			
			g2.setFont(dialogueFont);
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20F));
			
			g2.setColor(Color.white);
			
			String Lore1 = "It was the year 1612, ";
			String Lore2 = "the Sengoku Era is approaching it's end...";
			String Lore3 = "The word 'Shinobi' is scarcely uttered...";
			String Lore4 = "The remnants of ninjas and shinobis are scattered";
			String Lore5 = "all across the warring states of feudal Japan...";
			String Lore6 = "As siege befalls the sacred dojo that was once called home.";
			String Lore7 = "Lies a young and broken shinobi...";
			
			int x = 0;
			int y = 0;
			//y = y + gp.tileSize*5;
			
			
			g2.drawString(Lore1,x+40,gp.tileSize*2);
			g2.drawString(Lore2,x+40,gp.tileSize*3);
			g2.drawString(Lore3,x+40,gp.tileSize*4);
			g2.drawString(Lore4,x+40,gp.tileSize*5);
			g2.drawString(Lore5,x+40,gp.tileSize*6);
			g2.drawString(Lore6,x+40,gp.tileSize*8);
			g2.drawString(Lore7,x+40,gp.tileSize*9);
			
	
			g2.drawString(">",x-gp.tileSize,y);
			
			
			String keybindingText = "Continue...";
			x = 300;
			y = y + gp.tileSize;
			//Shadow
			g2.setColor(Color.red);
			g2.drawString(keybindingText,x+2,y+2);
			//Text
			g2.setColor(Color.white);
			g2.drawString(keybindingText,x,y);
			
			g2.drawString(">",x-gp.tileSize,y);
		
		}
		else if (titleScreenState == keybindingScreenState) { //control
			g2.setFont(dialogueFont);
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,38F));
			
			g2.setColor(Color.white);
			
			String Control1 = "WASD  - Movement, ";
			String Control2 = "SPACE - Pause and Unpause";
			String Control3 = "E 	 - Interact";
			
			
			int x = 0;
			int y = 0;
			
			g2.drawString(Control1,x+40,gp.tileSize*5);
			g2.drawString(Control2,x+40,gp.tileSize*6);
			g2.drawString(Control3,x+40,gp.tileSize*7);
			
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,38F));
			String keybindingText = "BACK";
			x = 300;
			y = y + gp.tileSize;
			//Shadow
			g2.setColor(Color.red);
			g2.drawString(keybindingText,x-8,y+2);
			//Text
			g2.setColor(Color.white);
			g2.drawString(keybindingText,x-10,y);
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30));
			if(menuChoice == 1) {
				g2.drawString(">",x-gp.tileSize,y-5);
			}
		}
		else if(titleScreenState == lore2ScreenState) {
			
			String Lore1 = "For the doomed and cowardly, ";
			String Lore2 = "only despair will be the path you will lead";
			String Lore3 = "For the brave and courageous ";
			String Lore4 = "a wealthy price lies ahead";
		
			int x = 0;
			int y = 2;
	
			g2.setFont(dialogueFont);
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,25F));
			
			//Shadow
			g2.setColor(Color.red);
			g2.drawString(Lore1,x+40,(gp.tileSize*4)+y);
			g2.drawString(Lore2,x+40,(gp.tileSize*5)+y);
			g2.drawString(Lore3,x+40,(gp.tileSize*7)+y);
			g2.drawString(Lore4,x+40,(gp.tileSize*8)+y);
			
			g2.setColor(Color.white);
			g2.drawString(Lore1,x+39,gp.tileSize*4);		
			g2.drawString(Lore2,x+39,gp.tileSize*5);
			g2.drawString(Lore3,x+39,gp.tileSize*7);		
			g2.drawString(Lore4,x+39,gp.tileSize*8);		
			
			g2.drawString(">",x-gp.tileSize,y);
			
			g2.setFont(dialogueFont);
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20F));
			
			String keybindingText = "Continue...";
			x = 300;
			y = (y + gp.tileSize)-2;
			//Shadow
			g2.setColor(Color.red);
			g2.drawString(keybindingText,x+2,y+2);
			//Text
			g2.setColor(Color.white);
			g2.drawString(keybindingText,x,y);
			
			g2.drawString(">",x-gp.tileSize,y);
			
		}
		
	}
	
	public void drawPauseScreen() {
		
		String text = "PAUSED";
		g2.setFont(g2.getFont().deriveFont(90F));
		g2.setColor(Color.white);
		int x = getPauseTextX(text);
		int y = gp.screenHeight/2;
		
		g2.drawString(text,x,y);
		
	}
	
	public void drawDialogueScreen() {
		//Dialogue Window
		int x = gp.tileSize*2;
		int y = gp.tileSize*8;
		int width = gp.screenWidth - (gp.tileSize*4);
		int height = gp.tileSize*3;
		
		Color colour = new Color(0,0,0,150);
		g2.setColor(colour);
		g2.fillRoundRect(x, (y/7)-30, width, height/3, 40, 40);
		
		// Dialogue box border
		colour = new Color (255,0,0);
		g2.setColor(colour);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, (y/7)-30, width-10, height/3, 10, 10);
		
		
		
		drawSubWindow(x,y,width,height);
		
		x =  x + gp.tileSize;
		y = y + gp.tileSize;
		
		
		g2.setFont(dialogueFont);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,28F));
		
		g2.setColor(Color.white);
		
		for (String line : currentDialogue.split("\n")) {
				g2.drawString(line,x,y);	
			y = y + 40;
		}
		for(String line2 : currentNPC.split("\n")) {
			y = y + gp.tileSize;
			g2.drawString(line2,x-30,(y/9));	
		}
		
		
		//g2.drawString(currentDialogue,x,y);
	}
	
	public void drawHealthBar() {
		
		// Emtpyness bar colour (BLACK)
		g2.setColor(new Color(0, 0, 0));
		g2.fillRect(30,30, gp.tileSize*5, 20);
		
		// Real bar colour (RED)
		g2.setColor(new Color(255,0,30));
		if(healthValue == 0) {
			g2.fillRect(30,30, gp.tileSize/2, 20);	
		}	
		else if(healthValue == 1) {
			g2.fillRect(30,30, gp.tileSize*1, 20);	
		}
		else if(healthValue == 2) {
			
			g2.fillRect(30,30, gp.tileSize*2, 20);
			
		}
		else if(healthValue == 3) {
			
			g2.fillRect(30,30, gp.tileSize*3, 20);
			
		}
		else if(healthValue == 4) {
			
			g2.fillRect(30,30, gp.tileSize*4, 20);
			
		}
		else if(healthValue == 5) {
			
			g2.fillRect(30,30, gp.tileSize*5, 20);
			
		}
		else {
			
			g2.fillRect(30,30, gp.tileSize*5, 20);
		}
		
		g2.setColor(Color.white);
		g2.drawRect(30,30, gp.tileSize*5, 20);
			
		//draw heart image for UI
		g2.drawImage(lifeImage,10, 30-5, (gp.tileSize)+4, 20+9, null);
	}
	
	public void drawManaBar() {
		
		g2.setColor(new Color(0, 0, 0));
		//g2.setStroke(new BasicStroke(2));
		g2.fillRect(300,30, gp.tileSize*5, 20);
		//player power bar
		g2.setColor(new Color(30,0,255));
		
		if(manaValue <= 0) {	
			
		}	
		else if(manaValue == 1) {
			g2.fillRect(300,30, gp.tileSize*1, 20);	
		}
		else if(manaValue == 2) {
			g2.fillRect(300,30, gp.tileSize*2, 20);
		}
		else if(manaValue == 3) {
			g2.fillRect(300,30, gp.tileSize*3, 20);
		}
		else if(manaValue == 4) {
			g2.fillRect(300,30, gp.tileSize*4, 20);
		}
		else if(manaValue == 5) {
			g2.fillRect(300,30, gp.tileSize*5, 20);
		}
		else {
			g2.fillRect(300,30, gp.tileSize*5, 20);
		}
		
		g2.setColor(Color.white);
		g2.drawRect(300,30, gp.tileSize*5, 20);
		
	
		//draw mana image
		g2.drawImage(manaImage,270, 30-4, (gp.tileSize)+4, 20+7, null);
	}
	
	public void drawEnemyHealthBar() {
		
		// Emptyness bar colour (BLACK)
				g2.setColor(new Color(0, 0, 0));
				g2.fillRect(460,30, gp.tileSize*5, 20);
						
				// Real bar colour (RED)
				g2.setColor(Color.gray);
				
				if(enemyHealthValue == 0) {
					g2.fillRect(660,30, gp.tileSize, 20);	
				}
				
				else if(enemyHealthValue == 1) {
					g2.fillRect(620,30, gp.tileSize, 20);	
				}
				else if(enemyHealthValue == 2) {
					
					g2.fillRect(580,30, gp.tileSize*2, 20);
					
				}
				else if(enemyHealthValue == 3) {
					
					g2.fillRect(540,30, gp.tileSize*3, 20);
					
				}
				else if(enemyHealthValue == 4) {
					
					g2.fillRect(500,30, gp.tileSize*4, 20);
					
				}
				else if(enemyHealthValue == 5) {
					
					g2.fillRect(460,30, gp.tileSize*5, 20);
					
				}
				else {
					
					g2.fillRect(460,30, gp.tileSize*5, 20);
				}
				
				
						
				g2.setColor(Color.black);
				g2.drawRect(460,30, gp.tileSize*5, 20);
						
				//draw enemy heart image for UI
				g2.drawImage(enemyLifeImage,650, 30-8, (gp.tileSize)+9, 35, null);
		
	}
	
	
	public void drawPickupItem() {
		
		g2.setFont(dialogueFont); // setfont function 
		g2.setColor(Color.white);
		if(gp.player.gotKey == true) {
			g2.drawImage(keyImage, gp.tileSize/2, (gp.tileSize*7)+35, gp.tileSize+10, gp.tileSize+10, null);
		}
		
		if(gp.player.gotKatana == true) {
			g2.drawImage(katana1Image, gp.tileSize/2, (gp.tileSize*2)-10, gp.tileSize, gp.tileSize, null);
		}
		
		if(gp.player.gotBoots == true) {
			g2.drawImage(bootsImage, gp.tileSize/2, gp.tileSize*3, gp.tileSize, gp.tileSize, null);
		}	
		if(gp.player.gotArmor == true) {
			g2.drawImage(armorImage, (gp.tileSize/2)-4, (gp.tileSize*4)+15, gp.tileSize+10, gp.tileSize+10, null);
		}
		if(gp.player.gotScroll == true) {
			g2.drawImage(scrollImage, (gp.tileSize/2)-2, (gp.tileSize*5)+22, gp.tileSize+10, gp.tileSize+10, null);
		}
		if(gp.player.gotCastleKey == true) {
			g2.drawImage(castleKeyImage, (gp.tileSize/2)-2, (gp.tileSize*6)+30, gp.tileSize+10, gp.tileSize+10, null);
		}
		if(gp.player.gotArtifact == true) {
			g2.drawImage(artifactImage, (gp.tileSize/2)-2, (gp.tileSize*6)+35, gp.tileSize+10, gp.tileSize+10, null);
		}
		
		if(messageOn == true) {
			
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,22F));
			g2.drawString(message,100,100);
			
			messageTimer++;
			if(messageTimer > 500) {
				messageTimer=0;
				messageOn = false;
				
			}
		}
	}
	
	public void drawCombatScreen() {
		
		int x = 1;
		int y = 1;
		//g2.setColor(Color.GRAY);
		g2.fillRect(x, y, 1000, 1000);
		
		g2.setFont(dialogueFont);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,25F));
		
		drawHealthBar();
		drawEnemyHealthBar();
		
		//int x = 0;
		//int y = 2;
		
		g2.setColor(Color.white);
		String combatText1 = "???";	
		//String combatText2 = "VS";	
		String combatText3 = "Shielded Knight";	
		g2.drawString(combatText1,x+35,gp.tileSize*2);	
		//g2.drawString(combatText2,x+380,gp.tileSize*2);	
		g2.drawString(combatText3,x+470,gp.tileSize*2);	
		
		// draw character image
		
		g2.drawImage(characterImage,65, 170, (gp.tileSize*2)+4, (gp.tileSize*2)+4, null);
	
		// draw enemy image
		g2.drawImage(shieldedKnightImage,530, 170, (gp.tileSize*2)+4, (gp.tileSize*2)+4, null);

		g2.setFont(dialogueFont);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,25F));
		
		// Dialogue box
		
		x = gp.tileSize*2;
		y = gp.tileSize*8;
		int width = gp.screenWidth - (gp.tileSize*4);
		int height = gp.tileSize*3;
		

		Color colour = new Color(0,0,0,75);
		g2.setColor(colour);
		g2.fillRoundRect(x-5, y+30, width+20, height-7, 40, 40);
		
		// Dialogue box border
		colour = new Color (0,0,0);
		g2.setColor(colour);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x-5, y+30,  width+20, height-7, 40, 40);
		
		String choice1 = "Attack";
		String choice2 = "Eat a kitkat";
		String choice3 = "Do nothing";
		String choice4 = "Run away!";
		
		
		//g2.setColor(colour);
//		g2.setColor(Color.white);
//		g2.fillRoundRect(x-5, y, width+20, height+7, 40, 40);
		g2.setColor(Color.white);
		//Font arial = new Font("Arial,",Font.PLAIN, 40);
		g2.setFont(dialogueFont);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,25F));
		
		//Attack
		g2.drawString(choice1,x+60,(gp.tileSize*9)+30);		
		if(combatChoice == 1) {
			g2.drawString(">",x+30,(gp.tileSize*9)+30);
		}
		//EatKitkat
		g2.drawString(choice2,x+330,(gp.tileSize*9)+30);
		if(combatChoice == 2) {
			g2.drawString(">",x+300,(gp.tileSize*9)+30);
		}
		//Do nothing
		g2.drawString(choice3,x+60,(gp.tileSize*10)+30);	
		if(combatChoice == 3) {
			g2.drawString(">",x+30,(gp.tileSize*10)+30);
		}
		//Run away
		g2.drawString(choice4,x+330,(gp.tileSize*10)+30);
		if(combatChoice == 4) {
			g2.drawString(">",x+300,(gp.tileSize*10)+30);
		}
		
		
		// Battle prompt
		drawBattlePrompt();
		
		
	}

	void drawBattlePrompt(){
		
		// Dialogue box
		
				int x = gp.tileSize*2;
				int y = gp.tileSize*6;
				int width = gp.screenWidth - (gp.tileSize*4);
				int height = gp.tileSize*3;
				

				Color colour = new Color(0,0,0,75);
				g2.setColor(colour);
				g2.fillRoundRect(x-5, y+25, width+20, height-50, 40, 40);
				
				// Dialogue box border
				colour = new Color (0,0,0);
				g2.setColor(colour);
				g2.setStroke(new BasicStroke(5));
				g2.drawRoundRect(x-5, y+25,  width+20, height-50, 40, 40);
				
				String prompt1 = "You attacked, dealing 2 damage..";
				String prompt2 = "Enemy attacked, dealing 1 damage..";
				String prompt3 = "You ate a kitkat, restores 2 health...";
				String prompt4 = "Enemy ate a kitkat, restores 2 health...";
				String prompt5 = "You did nothing, enemy is confused...";

				
				g2.setColor(Color.white);
				g2.setFont(dialogueFont);
				g2.setFont(g2.getFont().deriveFont(Font.PLAIN,18F));
				
//				Random random = new Random(); // Use random
//				int enemyAI = random.nextInt(2)+1; // Roll a number from 1 - 100
				
				if(gp.keyH.attackPressed == true) {
					g2.drawString(prompt1,x+30,(gp.tileSize*7)+15);	
				}
				else if(gp.keyH.eatPressed == true) {
					g2.drawString(prompt3,x+30,(gp.tileSize*7)+15);	
				}
				else if(gp.keyH.doNothingPressed == true) {
					g2.drawString(prompt5,x+30,(gp.tileSize*7)+15);	
				}
				else if(combatChoice == 4) {
					//nothing
				}
				
				if(gp.keyH.enemyChoice == 1) { // Enemy eat kitkat
					g2.setColor(Color.red);
					g2.drawString(prompt4,x+30,(gp.tileSize*7)+45);	
				}
				else if(gp.keyH.enemyChoice == 2) { // Enemy attacks
					g2.setColor(Color.red);
					g2.drawString(prompt2,x+30,(gp.tileSize*7)+45);	
				}
				
				
				
		
	}

	public void drawSubWindow(int x, int y, int width, int height) {
		
		// Dialogue box
		Color colour = new Color(0,0,0,150);
		g2.setColor(colour);
		g2.fillRoundRect(x, y, width, height, 40, 40);
		
		// Dialogue box border
		colour = new Color (255,0,0);
		g2.setColor(colour);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height, 10, 10);
	}
	
	public int getPauseTextX(String text) {
	
		g2.setFont(samuraiFont);
		g2.setFont(g2.getFont().deriveFont(Font.ITALIC,80F));
		int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
	
	public void drawEndScreen() {
		
		if (endingScreenState == 0) {
			
			System.out.println("Ending Choice : "+endingScreenState);
			String Lore1 = "You cast your gaze upon the relic";
			String Lore2 = "and it felt as if the gaze returned. ";
			String Lore3 = "The relic was an ancient artifact";
			String Lore4 = "capable of immense power of time altering.";
			String Lore5 = "Acknowledging it's power";
			String Lore6 = "You were met with a choice...";
		
			int x = 0;
			int y = 2;

			g2.setFont(dialogueFont);
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,25F));
	
			// Print artifact description
			g2.setColor(Color.white);
			g2.drawString(Lore1,x+39,gp.tileSize*2);		
			g2.drawString(Lore2,x+39,gp.tileSize*3);
			g2.drawString(Lore3,x+39,gp.tileSize*5);		
			g2.drawString(Lore4,x+39,gp.tileSize*6);	
			g2.drawString(Lore5,x+39,gp.tileSize*7);	
			g2.drawString(Lore6,x+39,gp.tileSize*8);	
			
			
			g2.setFont(dialogueFont);
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20F));
			
			String choiceText1 = "Revive your brothers using the relic power...";
			x = 70;
			y = (y + gp.tileSize*9) + 10;
			//Shadow
			g2.setColor(Color.red);
			g2.drawString(choiceText1,x+1,y+1);
			//Text
			g2.setColor(Color.white);
			g2.drawString(choiceText1,x,y);
			
			if(endingChoice == 1) {
				x = 40;
				g2.drawString(">",x,y);
			}
			
			y = gp.tileSize;
		
			String choiceText2 = "Accept and honor the deaths of your fallen brothers...";
			x = 70;
			y = (y + gp.tileSize*9)+10;
			//Shadow
			g2.setColor(Color.red);
			g2.drawString(choiceText2,x+1,y+1);
			//Text
			g2.setColor(Color.white);
			g2.drawString(choiceText2,x,y);
			
			if(endingChoice == 2) {
				x = 40;
				g2.drawString(">",x,y);
			}	
			
		}
		
		
		if (endingScreenState == 1) {
			
			String Lore1 = "You gazed upon the relic once more...";
			String Lore2 = "Deep in your hearts and thoughts";
			String Lore3 = "You wish for the return of your brothers...";
			String Lore4 = "And back to the glorious age of Edo period";
			String Lore5 = "Where you and your shinobi brothers";
			String Lore6 = "Triumphs once more...";
			
			String exit ="Press 'esc' to quit";
		
			
			int x = 0;
			//int y = 2;

			g2.setFont(dialogueFont);
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,25F));
	
			// Print artifact description
			g2.setColor(Color.white);
			g2.drawString(Lore1,x+39,gp.tileSize*2);		
			g2.drawString(Lore2,x+39,gp.tileSize*3);
			g2.drawString(Lore3,x+39,gp.tileSize*5);		
			g2.drawString(Lore4,x+39,gp.tileSize*6);	
			g2.drawString(Lore5,x+39,gp.tileSize*8);	
			g2.drawString(Lore6,x+39,gp.tileSize*9);
			
			//Font arial = new Font("Arial,",Font.PLAIN, 40);
			//g2.setFont(arial);
			//g2.setFont(g2.getFont().deriveFont(Font.PLAIN,25F));
			
			// Shadow
			g2.setColor(Color.red);
			g2.drawString(exit,x+221,(gp.tileSize*10)+1);
			g2.setColor(Color.white);
			g2.drawString(exit,x+220,gp.tileSize*10);
			
		}
		
		if (endingScreenState == 2) {
			
			String Lore1 = "You dug a hole near the riverbank";
			String Lore2 = "under a strong tree...";
			String Lore3 = "Laid down inside is the relic";
			String Lore4 = "where no one could have find it.";
			String Lore5 = "As if this never happened";
			String Lore6 = "You walk into the shadows never to return...";
			
			String exit ="Press 'esc' to quit";
		
		
			int x = 0;
			//int y = 2;

			g2.setFont(dialogueFont);
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,25F));
	
			// Print artifact description
			g2.setColor(Color.white);
			g2.drawString(Lore1,x+39,gp.tileSize*2);		
			g2.drawString(Lore2,x+39,gp.tileSize*3);
			g2.drawString(Lore3,x+39,gp.tileSize*5);		
			g2.drawString(Lore4,x+39,gp.tileSize*6);	
			g2.drawString(Lore5,x+39,gp.tileSize*8);	
			g2.drawString(Lore6,x+39,gp.tileSize*9);
			
			// Shadow
			g2.setColor(Color.red);
			g2.drawString(exit,x+221,(gp.tileSize*10)+1);
			g2.setColor(Color.white);
			g2.drawString(exit,x+220,gp.tileSize*10);
		}
		
		if(endingScreenState == 3) {
			
			String Lore1 = "You lost young shinobi";
			String Lore2 = "Killed in a honour";
			String Lore3 = "Death by combat";
			String Lore4 = "But fret not";
			String Lore5 = "You WILL avenge your brother";
			String Lore6 = "and find true peace indeed.";
			
			String exit ="Press 'esc' to restart";
		
		
			int x = 0;
			//int y = 2;

			g2.setFont(dialogueFont);
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,25F));
	
			// Print artifact description
			g2.setColor(Color.white);
			g2.drawString(Lore1,x+39,gp.tileSize*2);		
			g2.drawString(Lore2,x+39,gp.tileSize*3);
			g2.drawString(Lore3,x+39,gp.tileSize*5);		
			g2.drawString(Lore4,x+39,gp.tileSize*6);	
			g2.drawString(Lore5,x+39,gp.tileSize*8);	
			g2.drawString(Lore6,x+39,gp.tileSize*9);
			
			// Shadow
			g2.setColor(Color.red);
			g2.drawString(exit,x+221,(gp.tileSize*10)+1);
			g2.setColor(Color.white);
			g2.drawString(exit,x+220,gp.tileSize*10);
			
			
			
		}
		
		
	}
	
	
}
