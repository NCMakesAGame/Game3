import java.util.ArrayList;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class BlockMap {
	public static TiledMap map;
	public static int mapWidth;
	public static int mapHeight;
	private int square[] = {1,1,15,1,15,15,1,15}; //square shaped tile
	public static ArrayList<Object> entities;
 
	public BlockMap(String ref) throws SlickException {
		entities = new ArrayList<Object>();
		map = new TiledMap(ref);
		mapWidth = map.getWidth() * map.getTileWidth();
		mapHeight = map.getHeight() * map.getTileHeight();
 
		for (int x = 0; x < map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {
				int tileID = map.getTileId(x, y, 0);//collision layer tiles
				if (tileID == 1) {//use gettileid
				entities.add(new Block(x * 32, y * 32, square, "square")
                    );
				}
			}
		}
	}
}