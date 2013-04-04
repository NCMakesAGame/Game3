import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.*;



public class GameCont extends BasicGame{
	
	Player player;
	Enemy birdEnemy;
	static int WIDTH=1024;
	static int HEIGHT =760;
    float gravity=3.7f;
    
    BlockMap map;
   TiledMapPlus backMap;
    
    boolean moveCamera=false;
    Camera camera;
    visibleGameObject blood;
    HUD hud;
	public GameCont()
	{
		super("Slick2d game");
	}
	
	@Override
	public void init(GameContainer gc) 
			throws SlickException{
		
		
		//Bird=new Image("MainBird.png");
		
		player=new Player(false, 150, 300,200,64,32, "Leftss.png", "Rightss.png","Rightss.png", 3.5f, 9.0f,200.0f, 100,10); 
		backMap=new TiledMapPlus("/FirstArea.tmx");
		map=new BlockMap("/FirstArea.tmx");
		camera=new Camera(gc,map);
		birdEnemy=new Enemy(false, 150, 200f,200f,96,64, "MainBird.png","MainBird.png","MainBird.png",2.0f,10.0f,200.0f,  300, 4);
		blood=new visibleGameObject(true, 500, 1000.0f, 1000.0f, 32, 32,"blood.png", false );
		hud=new HUD();
		
	}
	@Override
	public void update(GameContainer gc, int delta)
			throws SlickException{
		
		Input input=gc.getInput();
		
		player.updatePlayer(gc, delta, map, input, gravity);
		camera.centerOn(player.getX(), player.getY());
		birdEnemy.updateEnemy(gc, delta, player.getX(), player.getY(), map, gravity);
		//check whats on screen, add to list, pass to player
		birdEnemy.checkIfOnScreen(gc);
		//if (checkForHit()){
			//blood.setX(player.getX());
	//		blood.setY(player.getY());
	//	}
		
		hud.setValues(player.getX(), player.getY(), camera.getX(), camera.getY(), player.getHealth());
		
		
}
	public boolean checkForHit(){
		if (player.getPolygon().contains(birdEnemy.getPolygon())){
			//player.isHit(birdEnemy.getDamage());
			return true;
			}
		return false;
	}
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		/*if (moveCamera)
		{
			g.translate(-x, 0);
		}*/
		backMap.getLayer("Back").render(0, 0, 0, 0, WIDTH/32, (WIDTH/32)*(HEIGHT/32), false, 500,500);
		camera.drawMap();
		camera.translateGraphics();
		
		
		
	//	Bird.draw(x,y,scale);
		
		birdEnemy.renderEnemy(gc, g);
		blood.renderObj(gc, g);
		player.renderPlayer(gc, g);
		hud.drawHud(g);
	}
	
	public static void main(String[] args) throws SlickException
	{
		AppGameContainer app=new AppGameContainer(new GameCont());
		app.setDisplayMode(800, 600, false);
		app.setVSync(true);
		app.start();
		
	}

}
