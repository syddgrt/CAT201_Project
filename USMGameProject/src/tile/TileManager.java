package tile;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;


import main.GamePanel;
import main.Utility;

public class TileManager {
	
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][]; //2D ARRAY
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[60];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap("/map/world1.txt");
	}
	
	public void getTileImage() {
			
			// NOT USED AS 1 DIGIT NUMBER WILL MESS UP THE MAP DESIGN
			setupTile(0,"blank",false);
			setupTile(1,"blank",false);
			setupTile(2,"blank",false);
			setupTile(3,"blank",false);
			setupTile(4,"blank",false);
			setupTile(5,"blank",false);
			setupTile(6,"blank",false);
			setupTile(7,"blank",false);
			setupTile(8,"blank",false);
			setupTile(9,"blank",false);
			
	
			//Single sprite tiles
			
			setupTile(20,"treeTile",true);
			setupTile(25,"earthTile",false);
			setupTile(26,"woodTile",false);
			setupTile(27,"wallTile",false);
			setupTile(28,"wallBrick",true);
			setupTile(30,"magicTree",false);
			setupTile(41,"bloodSplat",false);
			
			//MULTIPLE SPRITE TILES
			
			//water tiles
			setupTile(10,"waterMiddle",true);
			setupTile(11,"waterMiddleTop",true);
			setupTile(12,"waterTopRight",true);
			setupTile(13,"waterMiddleRight0",true);
			setupTile(14,"waterBottomRight",true);
			setupTile(15,"waterMiddleBottom",true);
			setupTile(16,"waterBottomLeft",true);
			setupTile(17,"waterMiddleLeft",true);
			setupTile(18,"waterTopLeft",true);
			setupTile(21,"waterCornerTopLeft",true);
			setupTile(22,"waterCornerTopRight",true);
			setupTile(23,"waterCornerBottomLeft",true);
			setupTile(24,"waterCornerBottomRight",true);
			
			//grassTiles
			setupTile(19,"grassTile",false);
			setupTile(31,"grassTile0",false);
			setupTile(29,"grassTile0",false);
			
			//earthTiles
			setupTile(32,"earth00",false);
			setupTile(33,"earth01",false);
			setupTile(34,"earth02",false);
			setupTile(35,"earth03",false);
			setupTile(36,"earth04",false);
			setupTile(37,"earth05",false);
			setupTile(38,"earth06",false);
			setupTile(39,"earth07",false);
			setupTile(40,"earth08",false);
			
			
			//setupTile(21,"earthTile",true);
//			setupTile(10,"waterTile",true);
//			setupTile(10,"waterTile",true);
//			setupTile(10,"waterTile",true);
			
			
	}
	
	public void setupTile(int index, String imagePath, boolean collision) {
		
		Utility uTool = new Utility();
		
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+imagePath+".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
			
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String fileMap) {
		
		try {
			InputStream is = getClass().getResourceAsStream(fileMap);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				
				
				String line = br.readLine(); //extracting the data in map data
				
				while(col < gp.maxWorldCol) {
					
					String numbers[] = line.split(" "); //for the spaces in map tile
					
					int num = Integer.parseInt(numbers[col]); //change string to integer to use as number(extracting the number)
					
					mapTileNum[col][row] = num;
					col++;
				}
				
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
				
			}
			br.close();
			
		}catch(Exception e) {
			
		}
	}
	
	public void draw(Graphics2D g2) {
		
		int worldCol = 0;
		int worldRow = 0;
		
		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[worldCol][worldRow]; //assign the extracted number from mapTileNum to tileNum
												//map data is already stored in mapTileNum
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			
			double screenX = worldX - gp.player.worldX + gp.player.screenX; //off setting the positions of the camera on the player
			double screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			g2.drawImage(tile[tileNum].image, (int)screenX, (int)screenY, gp.tileSize, gp.tileSize, null);
			worldCol++;
			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
		
		
		
		
		
	}
}
