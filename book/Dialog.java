package book;

import javax.swing.JDialog;

import main.Base_Scene;
import main.Game;

@SuppressWarnings("serial")
public class Dialog extends JDialog {

	Game g;

	public Dialog(Game g,int bn) {
		this.g = g;
		this.setTitle("Personally hit");
		this.setVisible(true);
		this.setSize(Base_Scene.SCREEN_WIDTH, Base_Scene.SCREEN_HEIGHT);
		switch(bn)
		{
		case 1:
			this.add(new NoBook(g, Base_Scene.SCREEN_WIDTH, Base_Scene.SCREEN_HEIGHT));
			break;	
			
		case 2:
			this.add(new Book1(g, Base_Scene.SCREEN_WIDTH, Base_Scene.SCREEN_HEIGHT));
			break;
			
		case 3:
			this.add(new Book2(g, Base_Scene.SCREEN_WIDTH, Base_Scene.SCREEN_HEIGHT));
			break;
		case 4:
			this.add(new Book3(g, Base_Scene.SCREEN_WIDTH, Base_Scene.SCREEN_HEIGHT));
			break;
		case 5:
			this.add(new Exit(g, Base_Scene.SCREEN_WIDTH, Base_Scene.SCREEN_HEIGHT));
			break;
		}
	}

}
