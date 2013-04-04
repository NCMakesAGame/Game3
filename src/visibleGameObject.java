import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Polygon;


public class visibleGameObject {
	boolean onScreen;
	boolean isAnchored;
	float x, y;
	int height, width, spriteSheetDuration;
	Polygon objectPolygon;
	String file;
	SpriteSheet standingSS;
	Animation animation;
	enum objectDirection {Left, Right};
	objectDirection objDirection;
	
	public visibleGameObject(){
		onScreen=false;
		
	}
	public visibleGameObject(boolean isAnch,int ssd, float xPos, float yPos, int ht, int wdth, String ss, boolean direction) throws SlickException{//true right false left
		onScreen=false;
		if (direction){
			objDirection=objectDirection.Right;
		}
		else
			objDirection=objectDirection.Left;
		isAnchored=isAnch;
		spriteSheetDuration=ssd;
		x=xPos;
		y=yPos;
		height=ht;
		width=wdth;
		file=ss;
		standingSS=new SpriteSheet(file,width, height);
		animation=new Animation(standingSS,spriteSheetDuration);
		objectPolygon=new Polygon(new float[]{x,y,x+width,y,x+width,y+height,x,y+height});
		
	}
	public void checkIfOnScreen(GameContainer gc){
		//if (x<gc.get)
	}
	public void update(GameContainer gc, int delta, float gravity, BlockMap map) throws SlickException{
	
	}
	
	
	public void setX(float xpos){
		x=xpos;
	}
	public void setY(float ypos){
		y=ypos;
	}
	public boolean collision(BlockMap map) throws SlickException//do i need to pass blockmap?
	{
		for (int i=0; i<BlockMap.entities.size();i++){
			Block entity1=(Block) BlockMap.entities.get(i);
			if (entity1.poly.intersects(objectPolygon)){
				return true;				
			}
		}
		return false;
	}
	public Polygon getPolygon(){
		return objectPolygon;
	}
	public void renderObj(GameContainer gc, Graphics g) throws SlickException
	{
		animation.draw(x,y);
	}
	
	
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	
	
}
