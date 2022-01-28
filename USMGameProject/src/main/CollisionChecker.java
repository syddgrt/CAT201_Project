package main;

import entity.Entity;

public class CollisionChecker {
	
	GamePanel gp;

	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
		
	}
	
	public void checkTile(Entity entity) {
		
		int entityLeftWorldX = (int) (entity.worldX + entity.solidArea.x);
		int entityRightWorldX = (int) (entity.worldX + entity.solidArea.x + entity.solidArea.width);
		
		int entityTopWorldY = (int) (entity.worldY + entity.solidArea.y);
		int entityBottomWorldY = (int) (entity.worldY + entity.solidArea.y + entity.solidArea.height);
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(entity.direction) {
			case "up":
				entityTopRow = (int) ((entityTopWorldY - entity.speed)/gp.tileSize);
				tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
				tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
				if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
					entity.collisionOn = true;
					
				}
				break;
			case "down":
				entityBottomRow = (int) ((entityBottomWorldY + entity.speed)/gp.tileSize);
				tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
				tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
				if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
					entity.collisionOn = true;
				}
				break;
			case "left":
				entityLeftCol = (int) ((entityLeftWorldX - entity.speed)/gp.tileSize);
				tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
				tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
				if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
					entity.collisionOn = true;
				}
				break;
			case "right":
				entityRightCol = (int) ((entityRightWorldX + entity.speed)/gp.tileSize);
				tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
				tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
				if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
					entity.collisionOn = true;
				break;
			}
		
		}

	}
	public int checkObject(Entity entity, boolean player) { //method to check if player collides with object
		
		int index = 100; 
		
		for(int i = 0; i < gp.obj.length; i++) {
			
			if(gp.obj[i] != null) { 
				
				//get entity(player) solid area position
				entity.solidArea.x = (int) (entity.worldX + entity.solidArea.x);
				entity.solidArea.y = (int) (entity.worldY + entity.solidArea.y);
				//get object solid area position
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
				
				if(entity.direction == "up") {
					
					entity.solidArea.y = (int) (entity.solidArea.y - entity.speed);
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) { // intersects method can automatically check wether both entity and object is touching each other
						
						if(gp.obj[i].collision == true) {
							
							entity.collisionOn = true;
						}
						if (player == true) {
							index = i;	
						}
					}
				}
				
				else if(entity.direction == "down") {
					
					entity.solidArea.y = (int) (entity.solidArea.y + entity.speed);
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) { // intersects method can automatically check wether both entity and object is touching each other
						
						if(gp.obj[i].collision == true) {
							
							entity.collisionOn = true;
						}
						if (player == true) {
							index = i;	
						}
						
					}
				}

				else if(entity.direction == "left") {
					
					entity.solidArea.x = (int) (entity.solidArea.x - entity.speed);
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) { // intersects method can automatically check wether both entity and object is touching each other
						
						if(gp.obj[i].collision == true) {
							
							entity.collisionOn = true;
						}
						if (player == true) {
							index = i;	
						}
					}
				}

				else if(entity.direction == "right") {
					
					entity.solidArea.x = (int) (entity.solidArea.x + entity.speed);
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) { // intersects method can automatically check wether both entity and object is touching each other
						
						if(gp.obj[i].collision == true) {
							
							entity.collisionOn = true;
						}
						if (player == true) {
							index = i;	
						}
					}
				}
				// sets solid area x and y both to zero
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
				
				
			}
		}
		
		return index;
		
	}

	//Interact
	public int checkEntity(Entity entity, Entity[] target) {

		int index = 999; 
		
		for(int i = 0; i < target.length; i++) {
			
			if(target[i] != null) { 
				
				//get entity(player) solid area position
				entity.solidArea.x = (int) (entity.worldX + entity.solidArea.x);
				entity.solidArea.y = (int) (entity.worldY + entity.solidArea.y);
				//get object solid area position
				target[i].solidArea.x = (int) (target[i].worldX + target[i].solidArea.x);
				target[i].solidArea.y = (int) (target[i].worldY + target[i].solidArea.y);
				
				if(entity.direction == "up") {
					
					entity.solidArea.y = (int) (entity.solidArea.y - entity.speed);
					if(entity.solidArea.intersects(target[i].solidArea)) { // intersects method can automatically check wether both entity and object is touching each other
					
					entity.collisionOn = true;
					index = i;
					
					}
				}
				
				else if(entity.direction == "down") {
					
					entity.solidArea.y = (int) (entity.solidArea.y + entity.speed);
					if(entity.solidArea.intersects(target[i].solidArea)) { // intersects method can automatically check wether both entity and object is touching each other
						
					entity.collisionOn = true;
					index = i;
					}
				}

				else if(entity.direction == "left") {
					
					entity.solidArea.x = (int) (entity.solidArea.x - entity.speed);
					if(entity.solidArea.intersects(target[i].solidArea)) { // intersects method can automatically check wether both entity and object is touching each other
					
					entity.collisionOn = true;
					index = i;	
					}
				}

				else if(entity.direction == "right") {
					
					entity.solidArea.x = (int) (entity.solidArea.x + entity.speed);
					if(entity.solidArea.intersects(target[i].solidArea)) { // intersects method can automatically check wether both entity and object is touching each other
							
					entity.collisionOn = true;
					index = i;	
				
					}
				}
				// sets solid area x and y both to zero
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				
				target[i].solidArea.x = target[i].solidAreaDefaultX;
				target[i].solidArea.y = target[i].solidAreaDefaultY;
				
				
			}
		}
		
		return index;
	}
	
	public void checkPlayer(Entity entity) {
		
		//get entity(player) solid area position
		entity.solidArea.x = (int) (entity.worldX + entity.solidArea.x);
		entity.solidArea.y = (int) (entity.worldY + entity.solidArea.y);
		//get object solid area position
		gp.player.solidArea.x = (int) (gp.player.worldX + gp.player.solidArea.x);
		gp.player.solidArea.y = (int) (gp.player.worldY + gp.player.solidArea.y);
		
		if(entity.direction == "up") {
			
			entity.solidArea.y = (int) (entity.solidArea.y - entity.speed);
			if(entity.solidArea.intersects(gp.player.solidArea)) { // intersects method can automatically check wether both entity and object is touching each other
			
			entity.collisionOn = true;
			
			}
		}
		
		else if(entity.direction == "down") {
			
			entity.solidArea.y = (int) (entity.solidArea.y + entity.speed);
			if(entity.solidArea.intersects(gp.player.solidArea)) { // intersects method can automatically check wether both entity and object is touching each other
				
			entity.collisionOn = true;
			}
		}

		else if(entity.direction == "left") {
			
			entity.solidArea.x = (int) (entity.solidArea.x - entity.speed);
			if(entity.solidArea.intersects(gp.player.solidArea)) { // intersects method can automatically check wether both entity and object is touching each other
			
			entity.collisionOn = true;
			}
		}

		else if(entity.direction == "right") {
			
			entity.solidArea.x = (int) (entity.solidArea.x + entity.speed);
			if(entity.solidArea.intersects(gp.player.solidArea)) { // intersects method can automatically check wether both entity and object is touching each other
					
			entity.collisionOn = true;
		
			}
		}
		// sets solid area x and y both to zero
		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
	}
}