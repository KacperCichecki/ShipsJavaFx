package model;

public class Game {

	private Player me;
	private Player enemy;
	private Map myMap;
	private Map enemyMap;

	public Player getMe() {
		return me;
	}

	public void setMe(Player me) {
		this.me = me;
	}

	public Player getEnemy() {
		return enemy;
	}

	public void setEnemy(Player enemy) {
		this.enemy = enemy;
	}

	public Map getMyMap() {
		return myMap;
	}

	public void setMyMap(Map myMap) {
		this.myMap = myMap;
	}

	public Map getEnemyMap() {
		return enemyMap;
	}

	public void setEnemyMap(Map enemyMap) {
		this.enemyMap = enemyMap;
	}

	public Game() {
		startGame();
	}

	public void startGame() {

		myMap = new Map(State.EMPTY);
		me = new Player(myMap);

		XY xy1 = new XY(1, 1);
		XY xy2 = new XY(1, 2);
		XY xy3 = new XY(3, 3);
		XY xy4 = new XY(4, 3);

		Ship ship1 = new Ship(xy1, xy2);
		Ship ship2 = new Ship(xy3, xy4);

		myMap.setShip(ship1, 0, State.SHIP);
		myMap.setShip(ship2, 1, State.SHIP);

		enemyMap = new Map(State.ENEMYEMPTY);
		enemy = new Player(enemyMap);

		enemy.drawShip();
	}

	// return fields hit by me and enemy, first field is enemy's field and
	// second is mine
	public Field[] nextRound(XY xy) {
		Field[] fields = new Field[2];

		fields[0] = me.hitEnemy(xy, enemy);

		State afterMyShot = fields[0].getState();

		if (afterMyShot == State.ENEMYHIT) {
			System.out.println("I have points: " + me.getPoints());
			if (me.getPoints() == 4) {
				endGame("You have won!");
			}
		}

		fields[1] = enemy.hitMe(me);

		State afterEnemyShot = fields[1].getState();

		if (afterEnemyShot == State.HIT) {
			System.out.println("Enemy have points: " + enemy.getPoints());
			if (enemy.getPoints() == 4) {
				endGame("Computer has won!");
			}
		}

		return fields;
	}

	private void endGame(String winner) {

	}

}
