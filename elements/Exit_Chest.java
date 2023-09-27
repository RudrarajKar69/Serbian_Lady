package elements;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import main.Base_Scene;

public class Exit_Chest extends GameObject{

	boolean unlock=false;
	Random rnd;
	Player p;
	public Exit_Chest(int x, int y, int width, int height, ArrayList<Image> image, boolean alive, ID id,Player p) {
		super(x, y, width, height, image, alive, id);
		this.p=p;
		rnd = new Random();
	}

	@Override
	public void draw(Graphics g) {

		if(!unlock)
			g.drawImage(getImage().get(1), getX(), getY(), getWidth(), getHeight(), null);
		if(unlock)
			g.drawImage(getImage().get(3), getX(), getY(), getWidth(), getHeight(), null);

	}

	@Override
	public void update() {
		
		
		if(AABB(this,p) && !unlock && p.isKey())
		{
			if(p.getDay()==1)
			{
				p.setX(p.getX()-Base_Scene.TILE_SIZE);
				p.setY(p.getY()-Base_Scene.TILE_SIZE);
				unlock = true;
			}
			if(p.getDay()==2)
			{
				
				if(rnd.nextInt(0,4)==1)
				{
					p.setDay(5);
					p.setAlive(false);
				}
				else
				{
					p.setX(p.getX()-Base_Scene.TILE_SIZE);
					p.setY(p.getY()-Base_Scene.TILE_SIZE);
					unlock = true;
				}
			}
			if(p.getDay()==3 || p.getDay()==4)
			{
				if(rnd.nextInt(0,3)==1)
				{
					p.setDay(5);
					p.setAlive(false);
				}
				else
				{
					p.setX(p.getX()-Base_Scene.TILE_SIZE);
					p.setY(p.getY()-Base_Scene.TILE_SIZE);
					unlock = true;
				}
			}
			if(p.getDay()==5)
			{
				if(rnd.nextInt(0,2)==1)
				{
					p.setDay(5);
					p.setAlive(false);
				}
				else
				{
					p.setX(p.getX()-Base_Scene.TILE_SIZE);
					p.setY(p.getY()-Base_Scene.TILE_SIZE);
					unlock = true;
				}
			}
		}
		
		else if(AABB(this,p) && unlock && p.isKey())
		{
			p.setKey(true);
			this.setAlive(false);
		}
		
		else if(AABB(this,p))
		{
			p.setX(p.getX()-Base_Scene.TILE_SIZE);
			p.setY(p.getY()-Base_Scene.TILE_SIZE);
		}
	}
	
	boolean AABB(Exit_Chest base_Enemy, Player e) {
		if (base_Enemy.getX() < e.getX() + e.getWidth() && base_Enemy.getX() + base_Enemy.getWidth() > e.getX()
				&& base_Enemy.getY() < e.getY() + e.getHeight() && base_Enemy.getY() + base_Enemy.getHeight() > e.getY())
			return true;
		return false;

	}

	@Override
	public void update(KeyEvent k, MouseEvent m) {
		// TODO Auto-generated method stub
		
	}

}
