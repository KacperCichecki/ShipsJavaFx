package model;

import java.util.Arrays;

public class Map {
	private final Field[] fields = new Field[25];
	private Ship[] ships = new Ship[2];

	public Map(State state) {
		for (int i = 0; i < fields.length; i++) {
			fields[i] = new Field(new XY(i % 5, i / 5), state);
		}
	}

	public void setShip(Ship ship, int i, State state) {
		ships[i] = ship;
		fields[ship.getXY(0).getY() * 5 + ship.getXY(0).getX()].setState(state);
		fields[ship.getXY(1).getY() * 5 + ship.getXY(1).getX()].setState(state);
	}



	public Field getField(XY xy) {

		int x = xy.getX();
		int y = xy.getY();

		return fields[y * 5 + x];
	}

	public Field getField(int x, int y) {

		return fields[y * 5 + x];
	}

	public void setFieldState(XY xy, State state) {
		int x = xy.getX();
		int y = xy.getY();

		fields[y * 5 + x].setState(state);
	}

	@Override
	public String toString() {
		return "Map [fields=" + Arrays.toString(fields) + "]";
	}

}
