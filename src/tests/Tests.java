package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import model.Enemy;
import model.Field;
import model.Game;
import model.Map;
import model.Player;
import model.Ship;
import model.State;
import model.XY;

public class Tests {


	@Test
	public void testMapConstrucor1() {

		Map testedMap = new Map(State.EMPTY);

		assertEquals(testedMap.getField(new XY(4, 4)).getXY().getX(), 4);
		assertEquals(testedMap.getField(new XY(4, 4)).getXY().getY(), 4);

	}

	@Test
	public void testMapConstrucor2() {

		Map testedMap = new Map(State.EMPTY);

		assertEquals(testedMap.getField(new XY(0, 0)).getXY().getX(), 0);
		assertEquals(testedMap.getField(new XY(0, 0)).getXY().getY(), 0);

	}

	@Test
	public void testMapStartGame() {

		Game game = new Game();

		Map map = game.getMe().getMap();
		State resultState1 = map.getField(1, 1).getState();
		State resultState2 = map.getField(1, 2).getState();
		assertTrue(resultState1 == State.SHIP);
		assertTrue(resultState2 == State.SHIP);

	}

	@Test
	public void testDrawShip() {
		Map map = new Map(State.ENEMYEMPTY);

		Enemy player = new Enemy(map);
		Ship[] ships = player.drawShip();
		System.out.println(Arrays.toString(ships));
		assertTrue(!ships[0].equals(ships[1]));

	}

	@Test
	public void testEqauXY() {
		XY xy1 = new XY(2,3);
		XY xy2 = new XY(2,3);
		assertTrue(xy1.equals(xy2));

	}

	@Test
	public void testEqauXY2() {
		XY xy1 = new XY(2,3);
		XY xy2 = new XY(1,2);
		assertTrue(!xy1.equals(xy2));

	}

}
