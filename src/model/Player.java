package model;

public class Player {

	int points = 0;
	private final Map map;

	public Player(Map map) {
		this.map = map;
	}

	public int getPoints() {
		return points;
	}

	public Map getMap() {
		return map;
	}

	public Field hitEnemy(XY xy, Player enemy) {
		Field enemyField = enemy.getMap().getField(xy);

		if (enemyField.getState() == State.ENEMYSHIP) {
			enemyField.setState(State.ENEMYHIT);
			points++;
		} else {
			System.out.println("missed enemy");
			enemyField.setState(State.MISSED);
			System.out.println("enemy field set to " + enemyField.getState());
		}
		return enemy.getMap().getField(xy);
	}

	public Field hitMe(Player me) {

		int x1 = 0;
		int y1 = 0;
		Field field;
		State state;

		do {
			x1 = (int) (Math.random() * 5);
			y1 = (int) (Math.random() * 5);
			XY xy = new XY(x1, y1);
			field = me.getMap().getField(xy);
			state = field.getState();
			System.out.println(state);
		} while (state != State.EMPTY && state != State.SHIP);

		if (state == State.EMPTY) {
			field.setState(State.MISSED);
		} else {
			field.setState(State.HIT);
			points++;
		}
		return field;
	}

	public Ship[] drawShip() {
		// if field is taken
		boolean taken = true;
		int x1 = (int) (Math.random() * 5);
		int y1 = (int) (Math.random() * 5);
		int x2 = 0;
		int y2 = 0;
		int x3 = 0;
		int y3 = 0;
		int x4 = 0;
		int y4 = 0;
		Ship ship1 = null;
		Ship ship2 = null;

		// if 1 horizontal, if 0 vertical
		int direction = (int) Math.random() * 2;

		if (direction == 1) {
			if (x1 < 4) {
				x2 = x1 + 1;
			} else {
				x2 = x1 - 1;
			}
			y2 = y1;
		} else {
			if (y1 < 4) {
				y2 = y1 + 1;
			} else {
				y2 = y1 - 1;
			}
			x2 = x1;
		}

		while (taken) {
			x3 = (int) (Math.random() * 5);
			y3 = (int) (Math.random() * 5);

			if ((x3 == x1 && y3 == y1) || (x3 == x2 && y3 == y2)) {
				System.out.println("(x3 == x1 && y3 == y1) || (x3 == x2 && y3 == y2)");
				System.out.println("x1:" + x1 + " x2: " + x2 + " x3: " + x3 + " x4: " + x4);
				continue;
			}

			// if 1 horizontal, if 0 vertical
			int direction2 = (int) Math.random() * 2;

			if (direction2 == 1) {
				if (x3 < 4) {
					x4 = x3 + 1;
				} else {
					x4 = x3 - 1;
				}
				y4 = y3;
			} else {
				if (y3 < 4) {
					y4 = y3 + 1;
				} else {
					y4 = y3 - 1;
				}
				x4 = x3;
			}

			if ((x4 == x1 && y4 == y1) || (x4 == x2 && y4 == y2) || (x4 == x3 && y4 == y3)) {
				System.out.println("(x4 == x1 && y4 == y1) || (x4 == x2 && y4 == y2) || (x4 == x3 && y4 == y3)");
				System.out.println("x1:" + x1 + " x2: " + x2 + " x3: " + x3 + " x4: " + x4);
				continue;
			}
			taken = false;

			XY xy1 = new XY(x1, y1);
			XY xy2 = new XY(x2, y2);
			XY xy3 = new XY(x3, y3);
			XY xy4 = new XY(x4, y4);

			ship1 = new Ship(xy1, xy2);
			ship2 = new Ship(xy3, xy4);

			map.setShip(ship1, 0, State.ENEMYSHIP);
			map.setShip(ship2, 1, State.ENEMYSHIP);
			System.out.println(ship1);
			System.out.println(ship2);
		}

		return new Ship[] { ship1, ship2 };
	}

}
