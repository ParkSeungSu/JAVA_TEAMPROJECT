package lkj.lgl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import lkj.lgl.Game.STATE;

public class Menu extends MouseAdapter {
	private Game game;
	private Handler handler;
	private Random r = new Random();
	private HUD hud;

	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		if (game.gameState == STATE.Menu) {
			// select button
			if (mouseOver(mx, my, 210, 150, 200, 64)) {
				game.gameState = STATE.Select;
				AudioPlayer.getSound("menu_sound").play();
				return;
			}
			// help button
			if (mouseOver(mx, my, 210, 250, 200, 64)) {
				game.gameState = STATE.Help;
				AudioPlayer.getSound("menu_sound").play();	
			}
			// exit button
			if (mouseOver(mx, my, 210, 350, 200, 64)) {
				System.exit(1);
			}
		}
		// back button in help window
		if (game.gameState == STATE.Help) {
			if (mouseOver(mx, my, 210, 350, 200, 64)) {
				game.gameState = STATE.Menu;
				AudioPlayer.getSound("menu_sound").play();
				return;
			}
		}
		// back button in End window
		if (game.gameState == STATE.End) {
			if (mouseOver(mx, my, 210, 350, 200, 64)) {
				game.gameState = STATE.Menu;
				hud.setLevel(1);
				hud.setScore(0);
				AudioPlayer.getSound("menu_sound").play();
			}
		}
		if (game.gameState == STATE.Select) {
			// normal play button
			if (mouseOver(mx, my, 210, 150, 200, 64)) {
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(
						new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
				game.diff=0;
				AudioPlayer.getSound("menu_sound").play();
			}
			// hard play button
			if (mouseOver(mx, my, 210, 250, 200, 64)) {
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(
						new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
				game.diff=1;
				AudioPlayer.getSound("menu_sound").play();
			}

			// back button
			if (mouseOver(mx, my, 210, 350, 200, 64)) {
				game.gameState = STATE.Menu;
				AudioPlayer.getSound("menu_sound").play();
				return;
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
	}

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	public void tick() {

	}

	public void render(Graphics g) {
		if (game.gameState == STATE.Menu) {
			Font fnt = new Font("algerian", 1, 50);
			Font fnt2 = new Font("algerian", 1, 30);

			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Wave", 240, 70);

			g.setFont(fnt2);
			g.setColor(Color.white);

			g.drawRect(210, 150, 200, 64);
			g.drawString("Play", 270, 190);

			g.drawRect(210, 250, 200, 64);
			g.drawString("Help", 270, 290);

			g.drawRect(210, 350, 200, 64);
			g.drawString("Exit", 270, 390);
		} else if (game.gameState == STATE.Help) {
			Font fnt = new Font("algerian", 1, 50);
			Font fnt2 = new Font("algerian", 1, 30);
			Font fnt3 = new Font("맑은 고딕", 1, 20);
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Help", 240, 70);

			g.setFont(fnt3);
			g.drawString("WASD 를 사용하세요.", 10, 150);
			g.drawString("적들을 피해 최대한 많은 점수를 얻으세요.", 10, 190);
			g.drawString("버티세요. :)", 10, 230);

			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 270, 390);
		} else if (game.gameState == STATE.End) {
			Font fnt = new Font("algerian", 1, 50);
			Font fnt2 = new Font("algerian", 1, 30);
			Font fnt3 = new Font("맑은 고딕", 1, 20);
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Game Over!", 240, 70);

			g.setFont(fnt3);
			g.drawString("아쉬워라....", 10, 150);
			g.drawString("당신의 점수는 : " + hud.getScore(), 10, 190);

			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Again", 255, 390);
		} else if (game.gameState == STATE.Select) {
			Font fnt = new Font("algerian", 1, 50);
			Font fnt2 = new Font("algerian", 1, 30);

			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("SELECT DIFFICULTY", 60, 70);

			g.setFont(fnt2);
			g.setColor(Color.white);

			g.drawRect(210, 150, 200, 64);
			g.drawString("Nomal", 270, 190);

			g.drawRect(210, 250, 200, 64);
			g.drawString("Hard", 270, 290);

			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 270, 390);
		}
	}
}
