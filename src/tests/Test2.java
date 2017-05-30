package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import model.Map;
import model.Player;
import model.State;
import model.XY;

public class Test2 {

	@Test
	public void testHitEnemy() {

		Map myMap = new Map(State.EMPTY);
		Player me = new Player(myMap);

		Map enMap = new Map(State.ENEMYEMPTY);
		Player en = new Player(enMap);
		en.drawShip();

		XY xy = new XY(2, 3);

		if (me.hitEnemy(xy, en)) {
			System.out.println("hit");
			assertTrue("state not enemyhit ", en.getMap().getField(xy).getState() == State.ENEMYHIT);
		} else {
			System.out.println("not hit");
			assertTrue("state not missed ", en.getMap().getField(xy).getState() == State.MISSED);
		}
	}

}
