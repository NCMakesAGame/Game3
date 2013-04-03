import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Polygon;


public class Enemy extends movingGameObject{
	
	
	public Enemy(boolean isAnch, int ssd, float xPos, float yPos, int ht, int wdth, String LeftFile, String RightFile, String standingFile, float spdX, float spdY, float maxHt, int hlth, int dmg) throws SlickException{
		isAnchored=isAnch;
		spriteSheetDuration=ssd;
		x=xPos;
		y=yPos;
		height=ht;
		width=wdth;
		file=standingFile;
		leftFile=LeftFile;
		rightFile=RightFile;
		speedX=spdX;
		speedY=spdY;
		maximumHeight=maxHt;
		health=hlth;
		damage=dmg;
		standingSS=new SpriteSheet(file, width, height);
		leftSS=new SpriteSheet(leftFile, width, height);
		rightSS=new SpriteSheet(rightFile, width, height);
		animation=new Animation(standingSS, spriteSheetDuration);
		leftAnimation=new Animation(leftSS, spriteSheetDuration);
		rightAnimation=new Animation(rightSS, spriteSheetDuration);
		objectPolygon=new Polygon(new float[]{x,y,x+width,y,x+width,y+height,x,y+height});
	}
		
		
	
	
	private boolean tooFar(float playerX){
		if (playerX-x>500 ||playerX-x<-500){
			objState=objectState.Standing;
			return true;
		}
		objState=objectState.Attacking;
		return false;
	}
	
	public void updateEnemy(GameContainer c, int delta, float playerX, float playerY, BlockMap map, float gravity) throws SlickException{
		//ai logic
		if (!tooFar(playerX)){
			if (playerX<x){
					objDirection=objectDirection.Left;
						goLeft();
					
				if (collision(map)){
					goRight();
							if(objState!=objectState.Jumping && objState!=objectState.Falling){
						objState=objectState.Jumping;
					}
				}
			}
			else if (playerX>x){
				objDirection=objectDirection.Right;//right
				goRight();
				if(collision(map)){
					goLeft();
					if(objState!=objectState.Jumping && objState!=objectState.Falling){
						objState=objectState.Jumping;
					}
					
				}
			}
		}
		jump(gravity, map);
		
	}
	
	
	public void renderEnemy(GameContainer gc, Graphics g) throws SlickException{
		if (objState==objectState.Standing){
			animation.draw(x,y);
		}
		else if (objDirection==objectDirection.Left)
			leftAnimation.draw(x,y);
		else
			rightAnimation.draw(x,y);
	}
}


 