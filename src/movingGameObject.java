import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Polygon;



public class movingGameObject extends visibleGameObject{
	///////////////////
	//START VARIABLES//
	///////////////////
	
	float speedX, speedY, maximumHeight, deltaY;
	float health, damage;
	SpriteSheet leftSS, rightSS;
	Animation leftAnimation, rightAnimation;
	enum objectState {Jumping, JumpingLeft, JumpingRight, Falling, Standing, Walking, Attacking, Dead};
	objectState objState;
	String leftFile, rightFile;
	
	//Keep at 0. Regards to speed when falling
	float gravityChange = 0;
	
	///////////////////
	///END VARIABLES///
	///////////////////
	
	public movingGameObject(){
	}
	
	public movingGameObject(boolean isAnch, int ssd, float xPos, float yPos, int ht, int wdth, String LeftFile, String RightFile, String standingFile, float spdX, float spdY, float maxHt, float hlth, float dmg) throws SlickException{
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
		rightSS=new SpriteSheet(rightSS, width, height);
		animation=new Animation(standingSS, spriteSheetDuration);
		leftAnimation=new Animation(leftSS, spriteSheetDuration);
		rightAnimation=new Animation(rightSS, spriteSheetDuration);
		objectPolygon=new Polygon(new float[]{x,y,x+width,y,x+width,y+height,x,y+height});
	}
	public void updateObject(GameContainer gc, int delta, float gravity){

	}
	protected void goRight(){
		x+=speedX;
		objectPolygon.setX(x);
	}
	protected void goLeft(){
		x-=speedX;
		objectPolygon.setX(x);
	}
	public void isHit(float dmg){

		health-=damage;
		if (health<=0){
			health=0;
			objState=objectState.Dead;
		}

	}
	public float getDamage(){
		return damage;
	}

	public void applyGravity(float gravity,BlockMap map) throws SlickException{
		if(!isAnchored){
			y+=(gravityChange + gravity);
			objectPolygon.setY(y);

			
			if (collision(map)){
				y-=(gravityChange + gravity);
				objectPolygon.setY(y);
				objState=objectState.Standing;
				gravityChange = 0;
			} else {
				gravityChange+=.075;
			}
		}
	}

	// vvvvvvvvvvvvv MOVE TO ENEMY CLASS
	protected void jump(float gravity, BlockMap map) throws SlickException{//do i need blockmap? 
		if(objState==objectState.Jumping){

			y-=speedY;
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

		applyGravity(gravity, map);

	}



}
