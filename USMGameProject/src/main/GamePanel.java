package main;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable{
	
	//SCREEN SETTINGS
	final int originalTileSize = 15; //16x16 tile (default size of player character)
	final int scale = 3; //scaling for originalTileSize (Depends on computer screen resolution)
	
	public int tileSize = originalTileSize * scale; //48x48 tile
	
	//4 by 3 ratio
	//size of game screen;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize*maxScreenCol; //(16x3)x16
	public final int screenHeight = tileSize*maxScreenRow; //(16x3)x12
	
	//WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	//Instantiate (calling each class)
	
	//GAME SYSTEM / DRAW / SOUNDS/ UI
	TileManager tileM = new TileManager(this);
	Thread gameThread;
	public KeyHandler keyH = new KeyHandler(this);
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public Sound sound = new Sound();
	public UI ui = new UI(this);
	
	//ENTITY/PLAYER/OBJECT
	public Player player = new Player(this,keyH);
	public SuperObject obj[] = new SuperObject[25];
	public Entity npc[] = new Entity[10];
	
	
	//GAME STATE
	public int gameState;
	//0 = Main Menu
	//1 = Game 
	//2 = Pause game
	//3 = Dialogue
	//4 = Combat
	//5 = Completed game (ending, including death)
	
	
	
	
	
	//set default player position
	int playerX = 300;
	int playerY = 300;
	int playerSpeed = 40;
	
	//FPS
	int FPS = 60;
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); //improve game rendering performance
		this.addKeyListener(keyH); //add key inputs to gamePanel
		this.setFocusable(true); //game panel will prioritizes getting key inputs
	}
	
	public void setupGame() { // Setup Menu
		aSetter.placeObject();
		aSetter.placeNPC();
		
		
		gameState = 0; // Menu
		if(gameState == 0) {
			playSoundEffect(10); // pass index 0 to play sound 10
		}
//		if(gameState == 1) {
//			stopMusic();
//			//playMusic(0);
//		}
//		//if()
	}
	
	public void zoomInOut(int i) {
		
					
		int oldWorldWidth = tileSize * maxWorldCol; 
		tileSize = tileSize + i;
		int newWorldWidth = tileSize * maxWorldCol;
		
		player.speed = (double)newWorldWidth/600;
		
		double multiplier = (double)newWorldWidth/oldWorldWidth;
		
		System.out.println("tileSize: " +tileSize);
		System.out.println("worldWidth: +newWorldWidth");
		System.out.println("tileSize: +tileSize");
		
		double newPlayerWorldX = player.worldX * multiplier;
		double newPlayerWorldY = player.worldY * multiplier;
		
		player.worldX = newPlayerWorldX;
		player.worldY = newPlayerWorldY;
					
		
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this); //passing GamePanel class to gameThread constructor
		gameThread.start();
	}
	
	@Override
	public void run() {
		double drawInterval = 1000000000/FPS; //Divide 1 second(1 billion nanosecond) with 60 FPS
		double delta= 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		
		while(gameThread != null) {
			//every loop adds delta with (currentTime-lastTime) and divide with drawInterval
			//gameLoop
			currentTime = System.nanoTime(); //Check current time
			
			
			delta = delta +(currentTime - lastTime) / drawInterval;
			timer = timer +(currentTime-lastTime);
			
			lastTime = currentTime; //assign lastTime to currentTime
			
			if(delta>=1) {
				update(); //update 
				repaint(); //repaint
				delta--;
			}
			
			if(timer >=1000000000) {
				timer = 0;
			}
		}
	}
	public void update() {
		
		if(gameState == 0) { // Menu
			//stopMusic();
		}
		if(gameState == 1) { // Game
			//stopMusic();
			
			player.update(); //call update from class Player
			
			for(int i = 0; i < npc.length; i++) {
				
				if(npc[i]!=null) {
					npc[i].update();
				}
			
			}

		}
		if(gameState == 2) { // Pause
			//nothing
		}
		
		if(gameState == 3) { //Dialogue
			//nothing
		}
		
		if(gameState == 4) { // Combat
			
		}
		if(gameState == 5) {
			//System.out.println("You have finished the game...");
		}
		
		
	}
		
	public void paintComponent(Graphics g) { //class with plenty of built in functions to draw
		
		super.paintComponent(g);
		
		Graphics2D g2 =(Graphics2D)g; // change Graphics to Graphics2D 
									 // Graphics2D has all and more functions that Graphics
		
		// DEBUG
		// long drawStart = 0;
		// if(keyH.checkDrawTime == true) {
			
		// 	drawStart = System.nanoTime();
		// }
		
		// TITLE SCREEN
		if(gameState == 0 ) { // Main menu
			ui.draw(g2);
			if(ui.titleScreenState == 0) {
				//System.out.println("Main Menu");
			}
			else if(ui.titleScreenState == 1) {
				//System.out.println("Lore");
			}
			else if(ui.titleScreenState == 2) {
				//System.out.println("Controls");
			}
			else if(ui.titleScreenState == 3) {
				//System.out.println("Lore2:" +ui.manaValue);
			}
		}
		
		else if(gameState == 1) {
			
			// TILE
			tileM.draw(g2); //call draw from TileManager(draw tile sprite)
			//System.out.println("manaValue:" +ui.manaValue);
			
			//OBJECT (DRAW OBJECT AFTER TILE)
			for(int i = 0 ; i < obj.length ; i++) {
				 
				if(obj[i] != null) {
					obj[i].draw(g2, this);
				}
			}
			
			//NPC (DRAW NPC AFTER OBJECT/TILE)
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].draw(g2);
				}
			}
			
			if(ui.enemyStatus == 2) {
				npc[2] = null;
			}

			
			
			//UI
			ui.draw(g2);
			
			if(keyH.toggleGUI == false){
				ui.draw(g2);
			}
			//PLAYER (DRAW PLAYER AFTER TILE AND OBJECT)
			player.draw(g2); //call draw from Player(draw player sprite)
			
			
			g2.dispose(); //save more memory
			
		}
		else if(gameState == 2) { // Pause screen
			tileM.draw(g2); //call draw from TileManager(draw tile sprite)
			//OBJECT (DRAW OBJECT AFTER TILE)
			for(int i = 0 ; i < obj.length ; i++) {
				 
				if(obj[i] != null) {
					obj[i].draw(g2, this);
				}
			}
			
			//NPC (DRAW NPC AFTER OBJECT/TILE)
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].draw(g2);
				}
			}
			
			
			//PLAYER (DRAW PLAYER AFTER TILE AND OBJECT)
			player.draw(g2); //call draw from Player(draw player sprite)
			
			ui.draw(g2);
			
			
			
			
			g2.dispose(); //save more memory
			
		}
		else if(gameState == 3) { // Dialogue screen
			
			tileM.draw(g2); //call draw from TileManager(draw tile sprite)
			//OBJECT (DRAW OBJECT AFTER TILE)
			for(int i = 0 ; i < obj.length ; i++) {
				 
				if(obj[i] != null) {
					obj[i].draw(g2, this);
				}
			}
			
			//NPC (DRAW NPC AFTER OBJECT/TILE)
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].draw(g2);
				}
			}
			
			ui.draw(g2);
			
			//PLAYER (DRAW PLAYER AFTER TILE AND OBJECT)
			player.draw(g2); //call draw from Player(draw player sprite)
			
			
			g2.dispose(); //save more memory
			
		}
		
		else if(gameState == 4) { // Combat screen
			ui.draw(g2);
		}
		
		else if(gameState == 5) { // End game screen
			ui.draw(g2);
		}
		
		else if(gameState == 6) { // Death Screen
			ui.draw(g2);
			//gameState = 0;
		}
		
		
	}
	
	public void playMusic(int i) {
		

		sound.setFile(i);
		sound.start();
		sound.loop();
		
	}
	public void stopMusic() {
		sound.stop();
		
	}
	
	public void playSoundEffect(int i) {
		
		sound.setFile(i);
		sound.start();
	}
	public void stopMenuMusic() {
		sound.stop();
	}
	

}
