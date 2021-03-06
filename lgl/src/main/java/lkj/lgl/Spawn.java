package lkj.lgl;

import java.util.Random;

public class Spawn {
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	private int scoreKeep = 0;
	private Game game;
	private int levelKeep = 1;

	public Spawn(Handler handler, HUD hud, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
	}

	public void tick() {
		scoreKeep++;

		if (scoreKeep >= 200) {
			scoreKeep = 0;
			levelKeep += 1;
			hud.setLevel(hud.getLevel() + 1);
			if (game.diff == 0) {
				if (hud.getLevel() == 2) {
					handler.addObject(
							new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
				} else if (levelKeep == 3) {
					handler.addObject(
							new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));

				} else if (levelKeep == 4) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.FastEnemy, handler));

				} else if (levelKeep == 5) {
					handler.addObject(new smartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.SmartEnemy, handler));

				} else if (levelKeep == 6) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.FastEnemy, handler));
					handler.addObject(
							new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
				} else if (levelKeep == 7) {
					handler.addObject(new smartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.SmartEnemy, handler));

				} else if (levelKeep == 10) {
					handler.clearEnemys();
					handler.addObject(new EnemyBoss((Game.WIDTH / 2) - 48, -120, ID.EnemyBoss, handler));

				} else if (levelKeep == 20) {
					handler.clearEnemys();
					handler.addObject(new smartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.SmartEnemy, handler));
					handler.addObject(new smartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.SmartEnemy, handler));

				}
			} else if (game.diff == 1) {
				if (levelKeep == 2) {
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.HardEnemy, handler));
				} else if (levelKeep == 3) {
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.HardEnemy, handler));

				} else if (levelKeep == 4) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.FastEnemy, handler));

				} else if (levelKeep == 5) {
					handler.addObject(new smartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.SmartEnemy, handler));

				} else if (levelKeep == 6) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.FastEnemy, handler));
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.HardEnemy, handler));
				} else if (levelKeep == 7) {
					handler.addObject(new smartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
							ID.SmartEnemy, handler));

				} else if (levelKeep == 10) {
					handler.clearEnemys();
					handler.addObject(new EnemyBoss((Game.WIDTH / 2) - 48, -120, ID.EnemyBoss, handler));

				}
			}
			if (levelKeep > 22) {
				levelKeep = 1;
				handler.clearEnemys();
			}
		}
	}
}
