
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Polygon;



public class Player extends movingGameObject {
	///////////////////
	//START VARIABLES//
	///////////////////
	
	// Keep at 0 for declaration of variable. Do not change.
	float jumpChange = 0;
	
	///////////////////
	///END VARIABLES///
	///////////////////
	
	public Player(boolean isAnch, int ssd, float xPos, float yPos, int ht, int wdth, String LeftFile, String RightFile, String standingFile, float spdX, float spdY, float maxHt, float hlth, float dmg) throws SlickException{
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

	public void updatePlayer(GameContainer gc, int delta, BlockMap map, Input input,float gravity) throws SlickException{
		if (objState!=objectState.Dead){

			//Check for Moving Left
			if (input.isKeyDown(Input.KEY_LEFT))
			{
				objDirection=objectDirection.Left;
				goLeft();
				if (collision(map)){
					goRight();
				}
			}

			//Check for Moving Right
			if (input.isKeyDown(Input.KEY_RIGHT))
			{
				objDirection=objectDirection.Right;
				goRight();
				if (collision(map)){
					goLeft();
				}
			}

			//Check for Jump Action (Standing Still)
			if (input.isKeyDown(Input.KEY_SPACE) && (objState!=objectState.Jumping && objState!=objectState.Falling)){
				objState=objectState.Jumping;
				jumpChange = 0;
			}

			//Jump Action
			if(objState==objectState.Jumping){
				y-=(speedY - jumpChange);
				jumpChange+=.1;
				objectPolygon.setY(y);
				deltaY-=speedY;

				if (collision(map)){
					y+=speedY;
					objectPolygon.setY(y);
					objState=objectState.Falling;
					deltaY=0;
				}

				if (deltaY<=-maximumHeight){
					objState=objectState.Falling;
					deltaY=0;
				}
			}
			//End Jump Action


			applyGravity(gravity, map);

		}
	}
	
	public void renderPlayer(GameContainer gc, Graphics g){
		if (objDirection==objectDirection.Left){
			leftAnimation.draw(x,y);
		} else if (objDirection==objectDirection.Right){
			rightAnimation.draw(x,y);
		} else {
			animation.draw(x,y);
		}
	}
	
	public float getHealth() {
		return health;
	}


}
