//import java.awt.Color;
import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;



public class HUD {
	
	///////////////////
	//START VARIABLES//
	///////////////////

	//Newest Version of Testing!
	//Newer code 2!
	
	private float posX;
	private float posY;
	private float camX;
	private float camY;
	private float healthP;
	Font awtFont = new Font("Alias", Font.PLAIN, 24);
	TrueTypeFont font = new TrueTypeFont(awtFont, true);
	Color transColor = new Color(0f, 0f, 0f, .75f);
	
	///////////////////
	///END VARIABLES///
	///////////////////
	
	//Default Construct
	public HUD() {
		posX = 0;
		posY = 0;
		camX = 0;
		camY = 0;
		healthP = 0;
	}
	
	//Value Setting
	public void setValues(float pX, float pY, float cX, float cY, float hlth) {
		posX = pX;
		posY = pY;
		camX = cX;
		camY = cY;
		healthP = hlth;
	}
	
	public void setPosX(float x) {
		posX = x;
	}
	public void setPosY(float y) {
		posY = y;
	}
	public void setCamX(float x) {
		camX = x;
	}
	public void setCamY(float y) {
		camY = y;
	}
	
	public void drawHud(Graphics g) {
		this.drawPos(g);
		
		this.drawHealthBar(g);
		//this.drawBackgrounds(g);
		this.drawHealth(g);
	}
	
	public void drawHealthBar(Graphics g) {
		g.setColor(Color.black);
		g.drawRect(posX, posY - 6, 31, 4);
		g.setColor(Color.red);
		g.fillRect(posX + 1, posY - 5, (30 * (healthP / 100)), 3);
		}
	
	public void drawBackgrounds(Graphics g) {
		g.setColor(transColor);
		g.fillRoundRect(camX + 725, camY + 550, 200, 100, 20);
		g.setColor(Color.white);
		//g.drawRect(camX + 725, camY + 550, 200, 100);
		
	}
	
	public void drawPos(Graphics g) {
		g.setColor(Color.white);
		String location = new String("X: " + Float.toString(posX) + ", Y: " + Float.toString(posY));
		g.drawString(location, camX + 550, camY);
	}
	
	public void drawHealth(Graphics g) {
		String health = new String(Float.toString(healthP));
		//g.drawString(health, camX + 550, camY + 350);
		font.drawString(camX + 730, camY + 560, health, Color.red);
	}

}
