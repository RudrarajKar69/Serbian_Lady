package elements;

import java.awt.Image;
import java.util.ArrayList;

public class Enemy extends Base_Enemy {

	public Enemy(int x, int y, int width, int height, ArrayList<Image> image, boolean alive, ID id, Player p, int Score) {
		super(x, y, width, height, image, alive, id,p,Score);
		shootProb=100;
	}
}