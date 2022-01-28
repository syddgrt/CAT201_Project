package main;

import object.OBJwillPower;
import object.OBJkey;
import object.OBJwaterLily;
import entity.NPC_amongUs;
import entity.NPC_roninGhost;
import entity.NPC_runeStone;
import entity.NPC_shieldedKnight;
import object.OBJarmor;
import object.OBJartifact;
import object.OBJboots;
import object.OBJcastleDoor;
import object.OBJcastleKey;
import object.OBJchest;
import object.OBJdeadNinja1;
import object.OBJdeadNinja2;
import object.OBJdeadNinja3;
import object.OBJdojoDoor;
import object.OBJkatana;
import object.OBJstuckedSword;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		
		this.gp = gp;
		
	}
	
	public void placeObject() {
		
		gp.obj[0] = new OBJwillPower();
		gp.obj[0].worldX = (28-1) * gp.tileSize;
		gp.obj[0].worldY = (41-1) * gp.tileSize;
		
		gp.obj[1] = new OBJchest();
		gp.obj[1].worldX = (8-1) * gp.tileSize;
		gp.obj[1].worldY = (36-1) * gp.tileSize;
		
		gp.obj[2] = new OBJkey();
		gp.obj[2].worldX = (24-1) * gp.tileSize;
		gp.obj[2].worldY = (41-1) * gp.tileSize;
		
		gp.obj[3] = new OBJarmor();
		gp.obj[3].worldX = (5-1) * gp.tileSize;
		gp.obj[3].worldY = (45-1) * gp.tileSize;
		
		gp.obj[4] = new OBJkatana();
		gp.obj[4].worldX = (5-1) * gp.tileSize;
		gp.obj[4].worldY = (46-1) * gp.tileSize;
		
		gp.obj[5] = new OBJboots();
		gp.obj[5].worldX = (6-1) * gp.tileSize;
		gp.obj[5].worldY = (46-1) * gp.tileSize;
		
		gp.obj[6] = new OBJwaterLily();
		gp.obj[6].worldX = 38 * gp.tileSize;
		gp.obj[6].worldY = 7 * gp.tileSize;
		
		gp.obj[7] = new OBJdeadNinja1();
		gp.obj[7].worldX = 22 * gp.tileSize;
		gp.obj[7].worldY = 6 * gp.tileSize;
		
		gp.obj[8] = new OBJdeadNinja1();
		gp.obj[8].worldX = 26 * gp.tileSize;
		gp.obj[8].worldY = 9 * gp.tileSize;
		
		gp.obj[9] = new OBJdeadNinja2();
		gp.obj[9].worldX = 27 * gp.tileSize;
		gp.obj[9].worldY = 8 * gp.tileSize;
		
		gp.obj[10] = new OBJdeadNinja2();
		gp.obj[10].worldX = 23 * gp.tileSize;
		gp.obj[10].worldY = 10 * gp.tileSize;
		
		gp.obj[11] = new OBJdeadNinja3();
		gp.obj[11].worldX = 25 * gp.tileSize;
		gp.obj[11].worldY = 7 * gp.tileSize;
		
		gp.obj[12] = new OBJdeadNinja3();
		gp.obj[12].worldX = 22 * gp.tileSize;
		gp.obj[12].worldY = 8 * gp.tileSize;
		
		gp.obj[13] = new OBJstuckedSword();
		gp.obj[13].worldX = 25 * gp.tileSize;
		gp.obj[13].worldY = 5* gp.tileSize;
		
		gp.obj[14] = new OBJstuckedSword();
		gp.obj[14].worldX = 27 * gp.tileSize;
		gp.obj[14].worldY = 9* gp.tileSize;
		
		gp.obj[15] = new OBJstuckedSword();
		gp.obj[15].worldX = 23 * gp.tileSize;
		gp.obj[15].worldY = 6* gp.tileSize;
		
		gp.obj[16] = new OBJstuckedSword();
		gp.obj[16].worldX = 23 * gp.tileSize;
		gp.obj[16].worldY = 11* gp.tileSize;
		
		gp.obj[17] = new OBJstuckedSword();
		gp.obj[17].worldX = 21 * gp.tileSize;
		gp.obj[17].worldY = 8* gp.tileSize;
		
		gp.obj[18] = new OBJcastleKey();
		gp.obj[18].worldX = 37 * gp.tileSize;
		gp.obj[18].worldY = 32* gp.tileSize;
		
		gp.obj[19] = new OBJcastleDoor();
		gp.obj[19].worldX = 35 * gp.tileSize;
		gp.obj[19].worldY = 45* gp.tileSize;
		
		gp.obj[20] = new OBJcastleDoor();
		gp.obj[20].worldX = 45 * gp.tileSize;
		gp.obj[20].worldY = 35* gp.tileSize;
		
		gp.obj[21] = new OBJartifact();
		gp.obj[21].worldX = 45 * gp.tileSize;
		gp.obj[21].worldY = 45* gp.tileSize;
		
		gp.obj[22] = new OBJdojoDoor();
		gp.obj[22].worldX = 10 * gp.tileSize;
		gp.obj[22].worldY = 22* gp.tileSize;
		
			
//		gp.obj[5] = new OBJwaterLily();
//		gp.obj[5].worldX = 38 * gp.tileSize;
//		gp.obj[5].worldY = 7 * gp.tileSize;
		
		//gp.obj[7] = new OBJruneStone();
		//gp.obj[7].worldX = (39-1) * gp.tileSize;
		//gp.obj[7].worldY = (10-1) * gp.tileSize;
		
	}
	
	public void placeNPC() {
		
		gp.npc[0] = new NPC_roninGhost(gp);
		gp.npc[0].worldX = (39-1) * gp.tileSize;
		gp.npc[0].worldY = (10-1) * gp.tileSize;
		
		gp.npc[1] = new NPC_runeStone(gp);
		gp.npc[1].worldX = (34) * gp.tileSize;
		gp.npc[1].worldY = (3) * gp.tileSize;
		
		gp.npc[2] = new NPC_shieldedKnight(gp);
		gp.npc[2].worldX = (37) * gp.tileSize;
		gp.npc[2].worldY = (30) * gp.tileSize;	
		
		gp.npc[3] = new NPC_amongUs(gp);
		gp.npc[3].worldX = (40) * gp.tileSize;
		gp.npc[3].worldY = (35) * gp.tileSize;
		
	}

}
