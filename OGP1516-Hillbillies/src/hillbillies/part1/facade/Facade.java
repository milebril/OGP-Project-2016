package hillbillies.part1.facade;

import hillbillies.model.Unit;
import ogp.framework.util.ModelException;

public class Facade implements IFacade{
	/* Unit creation */
	/**
	 * Create a new unit with the given attributes.
	 * 
	 * @param name
	 *            The name of the unit.
	 * @param initialPosition
	 *            The initial position of the unit, as an array with 3 elements
	 *            {x, y, z}.
	 * @param weight
	 *            The initial weight of the unit
	 * @param agility
	 *            The initial agility of the unit
	 * @param strength
	 *            The initial strength of the unit
	 * @param toughness
	 *            The initial toughness of the unit
	 * @param enableDefaultBehavior
	 *            Whether the default behavior of the unit is enabled
	 * 
	 * @return The generated unit
	 * 
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */

	@Override
	public Unit createUnit(String name, int[] initialPosition, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) throws ModelException {
		return new Unit (name, initialPosition, weight, agility, strength, toughness, enableDefaultBehavior);
	}
	
	/* Position */
	/**
	 * Get the precise coordinate of the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the position.
	 * @return The coordinate of the center of the unit, as an array with 3
	 *         doubles {x, y, z}.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public double[] getPosition(Unit unit) throws ModelException {
		
		return unit.getPosition();
	}

	/**
	 * Get the coordinate of the cube occupied by the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the cube coordinate.
	 * @return The coordinate of the cube in which the center of the unit lies,
	 *         as an array with 3 integers {x, y, z}.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public int[] getCubeCoordinate(Unit unit) throws ModelException {
		return unit.castDoubleToInt(unit.getPosition());
	}

	/* Name */
	/**
	 * Get the current name of the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the name.
	 * @return The current name of the unit.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public String getName(Unit unit) throws ModelException {
		return unit.getName();
	}

	/**
	 * Set the name of the given unit to the given value.
	 * 
	 * @param unit
	 *            The unit whose name to change.
	 * @param newName
	 *            The new name for the unit.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public void setName(Unit unit, String newName) throws ModelException {
		unit.setName(newName);		
	}
	
	/* Attributes */

	/**
	 * Return the weight attribute of the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the attribute's value
	 * @return The current weight of the unit
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public int getWeight(Unit unit) throws ModelException {
		return unit.getWeight();
	}

	/**
	 * Sets the weight attribute's value of the given unit to the given value.
	 * 
	 * @param unit
	 *            The unit for which to change the attribute's value
	 * @param newValue
	 *            The new weight
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public void setWeight(Unit unit, int newValue) throws ModelException {
		unit.setWeight(newValue);
		
	}

	/**
	 * Return the strength attribute of the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the attribute's value
	 * @return The current strength of the unit
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public int getStrength(Unit unit) throws ModelException {
		return unit.getStrength();
	}

	/**
	 * Sets the strength attribute's value of the given unit to the given value.
	 * 
	 * @param unit
	 *            The unit for which to change the attribute's value
	 * @param newValue
	 *            The new strength
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public void setStrength(Unit unit, int newValue) throws ModelException {
		unit.setStrength(newValue);
	}

	/**
	 * Return the agility attribute of the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the attribute's value
	 * @return The current agility of the unit
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public int getAgility(Unit unit) throws ModelException {
		return unit.getAgility();
	}

	/**
	 * Sets the agility attribute's value of the given unit to the given value.
	 * 
	 * @param unit
	 *            The unit for which to change the attribute's value
	 * @param newValue
	 *            The new agility
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public void setAgility(Unit unit, int newValue) throws ModelException {
		unit.setAgility(newValue);
	}

	/**
	 * Return the toughness attribute of the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the attribute's value
	 * @return The current toughness of the unit
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public int getToughness(Unit unit) throws ModelException {
		return unit.getToughness();
	}

	/**
	 * Sets the toughness attribute's value of the given unit to the given
	 * value.
	 * 
	 * @param unit
	 *            The unit for which to change the attribute's value
	 * @param newValue
	 *            The new toughness
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public void setToughness(Unit unit, int newValue) throws ModelException {
		unit.setToughness(newValue);
		
	}
	/* Points */

	/**
	 * Return the maximum number of hitpoints for the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the maximum number of hitpoints
	 * @return The maximum number of hitpoints for the given unit.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public int getMaxHitPoints(Unit unit) throws ModelException {
		return unit.getMaxHitpoints();
	}

	/**
	 * Return the current number of hitpoints for the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the current number of hitpoints
	 * @return The current number of hitpoints for the given unit.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public int getCurrentHitPoints(Unit unit) throws ModelException {
		return unit.getHitpoints();
	}
	
	/**
	 * Return the maximum number of stamina points for the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the maximum number of stamina
	 *            points
	 * @return The maximum number of stamina points for the given unit.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public int getMaxStaminaPoints(Unit unit) throws ModelException {
		return unit.getMaxStamina();
	}

	/**
	 * Return the current number of stamina points for the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the current number of stamina
	 *            points
	 * @return The current number of stamina points for the given unit.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public int getCurrentStaminaPoints(Unit unit) throws ModelException {
		return unit.getStamina();
	}

	/* Time */

	/**
	 * Advance the state of the given unit by the given time period.
	 * 
	 * @param unit
	 *            The unit for which to advance the time
	 * @param dt
	 *            The time period, in seconds, by which to advance the unit's
	 *            state.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public void advanceTime(Unit unit, double dt) throws ModelException {
		unit.advanceTimeInUnit(dt);		
	}

	/* Basic movement */

	/**
	 * Move the given unit to an adjacent cube.
	 * 
	 * @param unit
	 *            The unit to move
	 * @param dx
	 *            The amount of cubes to move in the x-direction; should be -1,
	 *            0 or 1.
	 * @param dy
	 *            The amount of cubes to move in the y-direction; should be -1,
	 *            0 or 1.
	 * @param dz
	 *            The amount of cubes to move in the z-direction; should be -1,
	 *            0 or 1.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public void moveToAdjacent(Unit unit, int dx, int dy, int dz) throws ModelException {
		unit.startWalking(dx, dy, dz);
		
	}

	/**
	 * Return the current speed of the given unit.
	 * 
	 * @param unit
	 *            The unit for which to retrieve the speed.
	 * @return The speed of the given unit.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public double getCurrentSpeed(Unit unit) throws ModelException {
		return unit.getSpeed();
	}

	/**
	 * Return whether the given unit is currently moving.
	 * 
	 * @param unit
	 *            The unit for which to retrieve the state.
	 * @return true if the unit is currently moving; false otherwise
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public boolean isMoving(Unit unit) throws ModelException {
		return unit.isTheUnitMoving();
	}

	/**
	 * Enable sprinting mode for the given unit.
	 * 
	 * @param unit
	 *            The unit which should start sprinting.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public void startSprinting(Unit unit) throws ModelException {
		unit.startSprinting();
	}

	/**
	 * Disable sprinting mode for the given unit.
	 * 
	 * @param unit
	 *            The unit which should stop sprinting.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public void stopSprinting(Unit unit) throws ModelException {
		unit.stopSprinting();
	}

	/**
	 * Return whether the given unit is currently sprinting.
	 * 
	 * @param unit
	 *            The unit for which to retrieve the state.
	 * @return true if the unit is currently sprinting; false otherwise
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public boolean isSprinting(Unit unit) throws ModelException {
		return unit.isSprinting();
	}

	/* Orientation */

	/**
	 * Return the current orientation of the unit.
	 * 
	 * @param unit
	 *            The unit for which to retrieve the orientation
	 * @return The orientation of the unit, in radians.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public double getOrientation(Unit unit) throws ModelException {
		return unit.getOrientation();
	}

	/* Extended movement */

	/**
	 * Start moving the given unit to the given cube.
	 * 
	 * @param unit
	 *            The unit that should start moving
	 * @param cube
	 *            The coordinate of the cube to move to, as an array of integers
	 *            {x, y, z}.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public void moveTo(Unit unit, int[] cube) throws ModelException {
		unit.startPathfinding( cube);
	}

	/* Working */

	/**
	 * Make the given unit start working.
	 * 
	 * @param unit
	 *            The unit that should start working
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public void work(Unit unit) throws ModelException {
		unit.startWorking();
	}

	/**
	 * Return whether the given unit is currently working.
	 * 
	 * @param unit
	 *            The unit for which to retrieve the state
	 * @return true if the unit is currently working; false otherwise
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public boolean isWorking(Unit unit) throws ModelException {
		return unit.isTheUnitWorking();
	}

	/* Attacking */

	/**
	 * Make the given unit fight with another unit.
	 * 
	 * @param attacker
	 *            The unit that initiates the fight by attacking another unit
	 * @param defender
	 *            The unit that gets attacked and should defend itself
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public void fight(Unit attacker, Unit defender) throws ModelException {
		attacker.startAttacking(defender);
		
	}

	/**
	 * Return whether the given unit is currently attacking another unit.
	 * 
	 * @param unit
	 *            The unit for which to retrieve the state
	 * @return true if the unit is currently attacking another unit; false
	 *         otherwise
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public boolean isAttacking(Unit unit) throws ModelException {
		return unit.isAttacking();
	}

	/* Resting */

	/**
	 * Make the given unit rest.
	 * 
	 * @param unit
	 *            The unit that should start resting
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public void rest(Unit unit) throws ModelException {
		unit.startResting();
	}

	/**
	 * Return whether the given unit is currently resting.
	 * 
	 * @param unit
	 *            The unit for which to retrieve the state
	 * @return true if the unit is currently resting; false otherwise
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public boolean isResting(Unit unit) throws ModelException {
		return unit.isTheUnitResting();
	}

	/* Default behavior */

	/**
	 * Enable or disable the default behavior of the given unit.
	 * 
	 * @param unit
	 *            The unit for which to enable or disable the default behavior
	 * @param value
	 *            true if the default behavior should be enabled; false
	 *            otherwise
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public void setDefaultBehaviorEnabled(Unit unit, boolean value) throws ModelException {
		unit.setDefaultBehaviour(value);
	}

	/**
	 * Returns whether the default behavior of the given unit is enabled.
	 * 
	 * @param unit
	 *            The unit for which to retrieve the default behavior state.
	 * @return true if the default behavior is enabled; false otherwise
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public boolean isDefaultBehaviorEnabled(Unit unit) throws ModelException {
		return unit.isDefaultBehaviourOn();
	}

}
