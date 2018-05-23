package com.merp.game.base;

import com.merp.game.ships.Ship;
import com.merp.game.utils.ResultState;
import com.merp.game.utils.Type;

public class QCell implements ICell {
	private final Location location;
	private final Type cellType;
	private int totalHit = 0;
	private Ship ship;

	public QCell(final Location location) {
		this.cellType = Type.Q;
		this.location = location;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}

	@Override
	public Location getCellPostion() {
		return this.location;
	}

	@Override
	public Type getCellType() {
		return this.cellType;
	}

	@Override
	public String getCellLocation() {
		return Location.getLocationString(location);
	}

	@Override
	public ResultState hitCell() {
		ResultState result = ResultState.NO_HIT;
		totalHit++;
		if (totalHit > cellType.getMaxHit()) {
			result = ResultState.NO_HIT;
		} else if (totalHit == cellType.getMaxHit()) {
			result = ResultState.CELL_DESTROYED;
		} else {
			result = ResultState.PARTIALLY_HIT;
		}
		result = ship.updateCell(this, result);
		return result;
	}

}