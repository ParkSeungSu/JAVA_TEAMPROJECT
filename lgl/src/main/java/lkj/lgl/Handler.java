package lkj.lgl;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	private boolean clearing = false;
	LinkedList<GameObject> objects = new LinkedList<GameObject>();
	public int speed=5;
	Game game;
	
	public Handler(Game game) {
		this.game = game;
	}

	public void tick() {
		for (int i = 0; i < objects.size(); i++) {
			GameObject tempObject = objects.get(i);
			tempObject.tick();
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < objects.size(); i++) {
			if (clearing) {
				return;
			}
			GameObject tempObject = objects.get(i);
			tempObject.render(g);
		}
	}

	public void addObject(GameObject object) {
		this.objects.add(object);
	}

	public void removeObject(GameObject object) {

		this.objects.remove(object);
	}

	public void clearEnemys() {
		clearing=true;
		for (int i = 0; i < objects.size(); i++) {
			GameObject tempObject = objects.get(i);
			if (tempObject.getId() == ID.Player) {
				objects.clear();
				if (game.gameState != Game.STATE.End) {
					addObject(new Player((int) tempObject.getX(), (int) tempObject.getY(), ID.Player, this));
				}
				break;
			}
		}
		clearing=false;
	}
}
