package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import entity.Entity;

public class KeyHandler implements KeyListener{

	public boolean upPressed, downPressed, leftPressed, rightPressed, stopPressedUp, stopPressedDown, stopPressedLeft, stopPressedRight, interactPressed;
	public boolean shinobiWalk;

	public boolean toggleGUI = true;
	
	public boolean attackPressed = false;
	public boolean eatPressed = false;
	public boolean doNothingPressed = false;
	
	int enemyChoice;
	GamePanel gp; //use gamePanel class objects
	Entity entity;
	
	boolean checkDrawTime = false;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		if(gp.gameState == 0) { // Game Menu
			
			if(gp.ui.titleScreenState == 0) { // Main Menu
				
				if(code == KeyEvent.VK_W) {
					gp.ui.menuChoice--;
					gp.playSoundEffect(9);
				}
				if(code == KeyEvent.VK_S) {
					gp.ui.menuChoice++;
					gp.playSoundEffect(9);
				}
				if(code == KeyEvent.VK_E) {
					gp.playSoundEffect(2);
					if(gp.ui.menuChoice==0) {
						gp.ui.titleScreenState = 1;//Lore
					}
					if(gp.ui.menuChoice==1) {
						gp.ui.titleScreenState = 2;
					}
					if(gp.ui.menuChoice==2) {
						System.exit(0);
					}
				}
			}
			else if(gp.ui.titleScreenState == 1) { // Lore
				
				if(code == KeyEvent.VK_E) {
					gp.ui.titleScreenState = 3;
					gp.playSoundEffect(2);
				}
			}
			else if(gp.ui.titleScreenState == 2) { // Controls
				if(gp.ui.keybindingChoice == 0) {
					if(code == KeyEvent.VK_E) {
						gp.gameState = 0;
						gp.ui.titleScreenState = 0;
						gp.playSoundEffect(2);
					}
				}
				
			}
			else if(gp.ui.titleScreenState == 3) { // Lore 2
				if(code == KeyEvent.VK_E) {
					gp.gameState = 1; // Play state
					gp.playSoundEffect(2);  // Play sound effect when choose an option
					gp.playMusic(0); // Play game music
					gp.player.direction = "left";
				}
			}
			
			
		}
		if(gp.gameState == 1) { // Game
			
			if(code == KeyEvent.VK_W) {
				upPressed = true;
				
				stopPressedUp = false;
				stopPressedDown = false;
				stopPressedLeft = false;
				stopPressedRight = false;
			}
			if(code == KeyEvent.VK_S) {
				downPressed = true;
				
				stopPressedUp = false;
				stopPressedDown = false;
				stopPressedLeft = false;
				stopPressedRight = false;
			}
			if(code == KeyEvent.VK_A) {
				leftPressed = true;
				
				stopPressedUp = false;
				stopPressedDown = false;
				stopPressedLeft = false;
				stopPressedRight = false;
			}
			if(code == KeyEvent.VK_D) {
				rightPressed = true;
				
				stopPressedUp = false;
				stopPressedDown = false;
				stopPressedLeft = false;
				stopPressedRight = false;
			}
			if(code == KeyEvent.VK_E) {
				interactPressed = true;
			}

			if(code == KeyEvent.VK_B) {
				if(checkDrawTime == false) {
					checkDrawTime = true;
				}
				else if(checkDrawTime == true) {
					checkDrawTime = false;
				}
			}
			
			if(code == KeyEvent.VK_SPACE) {
				
				gp.gameState = 2;
				gp.playSoundEffect(6);

			}

			if(code == KeyEvent.VK_G) {
				if(toggleGUI == true){
					toggleGUI = false;

				}
				else if (toggleGUI == false){
					toggleGUI = true;
				}
				

			}

			if(gp.player.gotArmor && gp.player.gotScroll) {
				
				if(gp.ui.manaValue > 0) {
					
					if(code == KeyEvent.VK_X) {
					
						if(shinobiWalk == false) {
							gp.player.speed = gp.player.speed + 2;
							shinobiWalk = true;
							gp.playSoundEffect(8);
							gp.player.getPlayerImage();
							if(gp.ui.manaValue > 0) {
								gp.ui.manaValue--;
							}
							
					
						}
						else if(shinobiWalk == true) {
							gp.player.speed = gp.player.speed - 2;
							shinobiWalk = false;
							gp.player.getPlayerImage();

						}
					}
					
				}
			}
			
		}
		//PAUSE
		else if(gp.gameState == 2) {
			if(code == KeyEvent.VK_SPACE) {
				gp.playSoundEffect(7);
				gp.gameState = 1;
			}
			
		}
		// DIALOGUE
		else if(gp.gameState == 3) {
			
			if(code == KeyEvent.VK_SPACE) {
				if(gp.ui.enemyHealthValue>0) {
					gp.gameState = 4;
				}
				
			}
			
			if(code == KeyEvent.VK_E) {
				gp.gameState = 1;	
			}
		}
		else if(gp.gameState == 4) { // cOMBAT
			
			if(code == KeyEvent.VK_W) {
				if(gp.ui.combatChoice >1) {
					gp.ui.combatChoice--;
					gp.playSoundEffect(9);
				}
				
			}
			if(code == KeyEvent.VK_S) {
				if(gp.ui.combatChoice <4) {
					gp.ui.combatChoice++;
					gp.playSoundEffect(9);
				}
		
			}
			
			// Combat Choices
			if(gp.ui.combatChoice == 1) { // attack
				if(code == KeyEvent.VK_E) {
					
					attackPressed = true;
					doNothingPressed = false;
					eatPressed = false;
					
					System.out.println("enemyHP"+gp.ui.enemyHealthValue);
					gp.ui.enemyHealthValue = gp.ui.enemyHealthValue - 2;
					gp.playSoundEffect(12); // Swords clashing
					
					//gp.ui.healthValue --; 
						
						if(gp.ui.enemyHealthValue > 0) {
							Random random = new Random(); // Use random
							int i = random.nextInt(2)+1; // Roll a number from 1 - 100
						
							if(i == 1) { // enemy eat kitkat
								enemyChoice = 1;
								if(gp.ui.enemyHealthValue <5) {
									
									gp.ui.enemyHealthValue = gp.ui.enemyHealthValue + 2;
									
									if(gp.ui.healthValue >= 6) {
										gp.ui.healthValue = 5;
									}
								}		
							}
							else if (i == 2) { // enemy attack
								gp.ui.healthValue--;
								enemyChoice = 2;
							}
					}
					
					if(gp.ui.enemyHealthValue <=0) {
						gp.gameState = 1; // enemy death
						gp.ui.enemyStatus = 2; // died
						gp.ui.endingScreenState = 0; //Death ending
						
					}
					
					if(gp.ui.healthValue <=0) {
						gp.gameState = 5; // Ending screen
						//gp.ui.endingChoice = 3; //Death ending
						gp.ui.endingScreenState = 3; //Death ending
	
					}
					
				}
			}
			else if(gp.ui.combatChoice == 2) { //eat kitkat
				if(code == KeyEvent.VK_E) {
					
					attackPressed = false;
					doNothingPressed = false;
					eatPressed = true;

					if(gp.ui.healthValue <5) {
						
						gp.ui.healthValue = gp.ui.healthValue + 2;
						gp.playSoundEffect(13); // nyom nyom
						if(gp.ui.healthValue >= 6) {
							gp.ui.healthValue = 5;
						}
						
						
					}
					if(gp.ui.enemyHealthValue > 0) {
						Random random = new Random(); // Use random
						int i = random.nextInt(2)+1; // Roll a number from 1 - 100
						
						if(i == 1) { // enemy eat kitkat
	
							enemyChoice = 1;
							gp.ui.enemyHealthValue = gp.ui.enemyHealthValue + 2;
							
							if(gp.ui.enemyHealthValue >= 6) {
								gp.ui.enemyHealthValue = 5;
							}
						}
						else if (i == 2) { // enemy attack
							
							enemyChoice = 2;
							gp.ui.healthValue--;
						}
					}
				}
				
				
			}
			else if(gp.ui.combatChoice == 3) { // do nothing
				if(code == KeyEvent.VK_E) {

					attackPressed = false;
					doNothingPressed = true;
					eatPressed = false;

					System.out.println("Literally nothing happened...");
					
				}
				
;			}
			else if(gp.ui.combatChoice == 4) { // run away
				if(code == KeyEvent.VK_E) {
				gp.gameState = 1;
				gp.playSoundEffect(14);
				}
			}
			
			
		}
		else if(gp.gameState == 5) { // Ending
			
			if(code == KeyEvent.VK_W) {
				if(gp.ui.endingChoice >1) {
					gp.ui.endingChoice--;
					gp.playSoundEffect(9);
				}
				
			}
			if(code == KeyEvent.VK_S) {
				if(gp.ui.endingChoice <2) {
					gp.ui.endingChoice++;
					gp.playSoundEffect(9);
				}
		
			}
			if(code == KeyEvent.VK_E) {
				gp.playSoundEffect(2);
				if(gp.ui.endingChoice == 1) {
					gp.ui.endingScreenState = 1; // first ending
				}
				if(gp.ui.endingChoice == 2) {
					gp.ui.endingScreenState = 2; // second ending
				}
//				if(gp.ui.endingChoice == 3) {
//					gp.ui.endingScreenState = 3; // Bad ending
//				}
			}
			
			if(gp.ui.endingScreenState == 1) {
				if(code == KeyEvent.VK_ESCAPE) {
					System.exit(1);
				}
			}
			else if(gp.ui.endingScreenState == 2) {
				if(code == KeyEvent.VK_ESCAPE) {
					System.exit(1);
				}
			}
			
			if(gp.ui.endingScreenState == 3) {
				if(code == KeyEvent.VK_ESCAPE) {
					gp.gameState = 0;
				}
			}
			
			
		}
			
	}
		
	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			stopPressedUp = true;
			upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			stopPressedDown = true;
			downPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			stopPressedLeft = true;
			leftPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			stopPressedRight = true;
			rightPressed = false;
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
