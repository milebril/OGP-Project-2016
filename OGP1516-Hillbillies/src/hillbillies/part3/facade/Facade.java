package hillbillies.part3.facade;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import hillbillies.model.Boulder;
import hillbillies.model.Faction;
import hillbillies.model.Log;
import hillbillies.model.Scheduler;
import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.facade.Facade;
import hillbillies.part2.listener.TerrainChangeListener;
import hillbillies.part3.programs.ITaskFactory;
import hillbillies.part3.programs.TaskParser;
import hillbillies.tests.facade.Part3TestPartial;
import ogp.framework.util.ModelException;
/**
 * Implement this interface to connect your code to the graphical user interface
 * (GUI).
 * 
 * <ul>
 * <li>For separating the code that you wrote from the code that was provided to
 * you, put <b>ALL your code in the <code>src</code> folder.</b> The code that
 * is provided to you stays in the <code>src-provided</code> folder. Only if you
 * modify the provided code, it's advised to move the modified code to the
 * <code>src</code> folder, so that your changes cannot be accidentally
 * overwritten by an updated version of the provided code.</li>
 * 
 * <li>You should at least <b>create the following classes</b> in the package
 * <code>hillbillies.model</code>:
 * <ul>
 * <li>a class {@link Unit} for representing a Hillbilly Unit</li>
 * <li>a class {@link World} for representing a Hillbilly World</li>
 * <li>a class {@link Faction} for representing a unit faction</li>
 * <li>a class {@link Boulder} for representing a boulder object</li>
 * <li>a class {@link Log} for representing a log object</li>
 * <li>a class {@link Task} for representing a task program</li>
 * </ul>
 * You may, of course, add additional classes as you see fit.
 * 
 * <li>You should <b>create a class</b> {@link Facade} that implements this
 * interface. This class should be placed <b>in the package
 * <code>hillbillies.part3.facade</code></b> in your <code>src</code> folder.
 * </li>
 * 
 * <li>The <b>header of that {@link Facade} class</b> should look as follows:
 * <br>
 * <code>public class Facade implements IFacade { ... }</code><br>
 * Consult the <a href=
 * "http://docs.oracle.com/javase/tutorial/java/IandI/createinterface.html">
 * Java tutorial</a> for more information on interfaces, if necessary.</li>
 *
 * <li>Your <code>Facade</code> class should offer a <b>default constructor</b>.
 * </li>
 * 
 * <li><b>Each non-deprecated method</b> defined in this {@link IFacade}
 * interface and the interfaces from part 1 (@link
 * hillbillies.part1.facade.IFacade) and part 2 (
 * {@link hillbillies.part2.facade.IFacade}) must be implemented by your @{link
 * Facade} class. For example, the implementation of
 * {@link #getCubeCoordinate(Unit)} should call one or more methods on the given
 * <code>unit</code> to retrieve its current cube coordinate.</li>
 * 
 * <li>The methods {@link #advanceTime(Unit, double)} and {@link #work(Unit)}
 * from part 1 are <b>deprecated</b> and no longer need to be implemented.</li>
 * 
 * <li>Methods in this interface are <b>only allowed to throw exceptions of
 * type</b> {@link ModelException} (this class is provided). No other exception
 * types are allowed. This exception can only be thrown if (1) calling a method
 * of your <code>Unit</code> class with the given parameters would violate a
 * precondition, or (2) if the method of your <code>Unit</code> class throws an
 * exception (if so, wrap the exception in the {@link ModelException}).</li>
 * 
 * <li>{@link ModelException} should not be used anywhere outside of your
 * {@link Facade} implementation.</b></li>
 * 
 * <li>Your {@link Facade} implementation should <b>only contain trivial
 * code</b> (for example, calling a method, combining multiple return values
 * into an array, creating @Value instances, catching exceptions and wrapping it
 * in a ModelException). All non-trivial code should be placed in the other
 * classes that you create.</li>
 * 
 * <li>The rules described above and the documentation described below for each
 * method apply only to your {@link Facade} class. <b>Your other classes (e.g.,
 * {@link Unit}, {@link World}) should follow the rules described in the
 * assignment.</b></li>
 * 
 * <li><b>Do not modify the signatures</b> of the methods defined in this
 * interface.</li>
 * 
 * </ul>
 *
 */
public class Facade implements IFacade{

	/**
	 * Create a new world of the given size and with the given terrain. To keep
	 * the GUI display up to date, the method in the given listener must be
	 * called whenever the terrain type of a cube in the world changes.
	 * 
	 * @param terrainTypes
	 *            A three-dimensional array (structured as [x][y][z]) with the
	 *            types of the terrain, encoded as integers. The terrain always
	 *            has the shape of a box (i.e., the array terrainTypes[0] has
	 *            the same length as terrainTypes[1] etc.). The integer types
	 *            are as follows:
	 *            <ul>
	 *            <li>0: air</li>
	 *            <li>1: rock</li>
	 *            <li>2: tree</li>
	 *            <li>3: workshop</li>
	 *            </ul>
	 * @param modelListener
	 *            An object with a single method,
	 *            {@link TerrainChangeListener#notifyTerrainChanged(int, int, int)}
	 *            . This method must be called by your implementation whenever
	 *            the terrain type of a cube changes (e.g., as a consequence of
	 *            cave-ins), so that the GUI will correctly update the display.
	 *            The coordinate of the changed cube must be given in the form
	 *            of the parameters x, y and z. You do not need to call this
	 *            method during the construction of your world.
	 * @return
	 * @throws ModelException
	 */
	@Override
	public World createWorld(int[][][] terrainTypes, TerrainChangeListener modelListener) throws ModelException {
		return new World(terrainTypes, modelListener);
	}

	/**
	 * Return the number of cubes in the world in the x-direction.
	 * 
	 * @param world
	 *            The world for which to retrieve the number of cubes.
	 * @return The number of cubes in the x-direction.
	 * @throws ModelException
	 */
	@Override
	public int getNbCubesX(World world) throws ModelException {
		return world.getXLength();
	}

	/**
	 * Return the number of cubes in the world in the y-direction.
	 * 
	 * @param world
	 *            The world for which to retrieve the number of cubes.
	 * @return The number of cubes in the y-direction.
	 * @throws ModelException
	 */
	@Override
	public int getNbCubesY(World world) throws ModelException {
		return world.getYLength();
	}

	/**
	 * Return the number of cubes in the world in the z-direction.
	 * 
	 * @param world
	 *            The world for which to retrieve the number of cubes.
	 * @return The number of cubes in the z-direction.
	 * @throws ModelException
	 */
	@Override
	public int getNbCubesZ(World world) throws ModelException {
		return world.getZLength();
	}

	/**
	 * Advance the state of the given world by the given time period.
	 * 
	 * @param world
	 *            The world for which to advance the time
	 * @param dt
	 *            The time period, in seconds, by which to advance the world's
	 *            state.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 * 
	 * @note This method replaces the {@link #advanceTime(Unit, double)} method
	 *       from part 1.
	 */
	@Override
	public void advanceTime(World world, double dt) throws ModelException {
		world.advanceTimeOfWorld(dt);
	}

	/**
	 * Return the terrain type of the cube at the given coordinates.
	 * 
	 * @param world
	 *            The world from which to retrieve the type.
	 * @param x
	 *            The x-coordinate of the cube
	 * @param y
	 *            The y-coordinate of the cube
	 * @param z
	 *            The z-coordinate of the cube
	 * @return The terrain type of the given cube, encoded as an integer
	 *         according to the values in
	 *         {@link #createWorld(int[][][], TerrainChangeListener)}.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public int getCubeType(World world, int x, int y, int z) throws ModelException {
		return world.getCubeType(x, y, z);
	}

	/**
	 * Set the terrain type of the cube at the given coordinates the given
	 * value.
	 * 
	 * @param world
	 *            The world in which to set the type.
	 * @param x
	 *            The x-coordinate of the cube
	 * @param y
	 *            The y-coordinate of the cube
	 * @param z
	 *            The z-coordinate of the cube
	 * @param value
	 *            The new value of the terrain type of the cube, encoded as an
	 *            integer according to the values in
	 *            {@link #createWorld(int[][][], TerrainChangeListener)}.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public void setCubeType(World world, int x, int y, int z, int value) throws ModelException {
		world.setCubeType(x, y, z, value);
		
	}
	
	/**
	 * Return whether the cube at the given coordinates is solid and connected
	 * to the border of the world.
	 * 
	 * @param world
	 *            The world to which the cube belongs
	 * @param x
	 *            The x-coordinate of the cube
	 * @param y
	 *            The y-coordinate of the cube
	 * @param z
	 *            The z-coordinate of the cube
	 * @return true if the given cube is solid and connected to the border of
	 *         the world; false otherwise.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 * 
	 */
	@Override
	public boolean isSolidConnectedToBorder(World world, int x, int y, int z) throws ModelException {
		return world.isSolidConnectedToBorder(x, y, z);
	}

	/* UNITS */

	/**
	 * Spawn a new unit in the world, according to the rules in the assignment
	 * (section 1.1.2).
	 * 
	 * @param world
	 *            The world in which to spawn a new unit
	 * 
	 * @param enableDefaultBehavior
	 *            Whether the unit should act according to the default behaviour
	 *            or not.
	 * 
	 * @return The newly spawned unit.
	 * 
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public Unit spawnUnit(World world, boolean enableDefaultBehavior) throws ModelException {
		return world.spawnUnit(enableDefaultBehavior);
	}

	/**
	 * Adds the given unit to the given world.
	 * 
	 * @param unit
	 * @param world
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public void addUnit(Unit unit, World world) throws ModelException {
		world.addUnit(unit);
	}

	/**
	 * Return all units that are currently part of the world.
	 * 
	 * @param world
	 *            The world from which to retrieve units
	 * @return A set containing all units from the world.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public Set<Unit> getUnits(World world) throws ModelException {
		return world.getSetOfUnits();
	}

	/**
	 * Return whether the given unit is currently carrying a log.
	 * 
	 * @param unit
	 *            The unit
	 * @return true if the unit is currently carrying a log; false otherwise
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public boolean isCarryingLog(Unit unit) throws ModelException {
		return unit.isCarryingLog();
	}

	/**
	 * Return whether the given unit is currently carrying a boulder.
	 * 
	 * @param unit
	 *            The unit
	 * @return true if the unit is currently carrying a boulder; false otherwise
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public boolean isCarryingBoulder(Unit unit) throws ModelException {
		return unit.isCarryingBoulder();
	}

	/**
	 * Return whether the given unit is currently alive.
	 * 
	 * @param unit
	 *            The unit
	 * @return true if the unit is currently alive; false if it's dead
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public boolean isAlive(Unit unit) throws ModelException {
		return unit.isUnitAlive();
	}

	/**
	 * Return the current number of experience points of the given unit.
	 * 
	 * @param unit
	 * @return The number of experience points.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public int getExperiencePoints(Unit unit) throws ModelException {
		return unit.getExperience();
	}

	/**
	 * Make the given unit start working on the given target cube.
	 * 
	 * @param unit
	 *            The unit that should start working.
	 * @param x
	 *            The x-coordinate of the target cube
	 * @param y
	 *            The y-coordinate of the target cube
	 * @param z
	 *            The z-coordinate of the target cube
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 * 
	 * @note This method replaces the {@link #work(Unit)} method from part 1.
	 */
	@Override
	public void workAt(Unit unit, int x, int y, int z) throws ModelException {
		unit.startWorking(new int[] {x,y,z});	
	}

	/* FACTIONS */
	/**
	 * Return the current faction of the given unit.
	 * 
	 * @param unit
	 *            The unit of which to retrieve the faction.
	 * @return The current faction of the unit.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public Faction getFaction(Unit unit) throws ModelException {
		return unit.getFaction();
	}

	/**
	 * Return the units that are part of the given faction.
	 * 
	 * @param faction
	 *            The faction of which to retrieve the members.
	 * @return The set of units that belong to the faction.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public Set<Unit> getUnitsOfFaction(Faction faction) throws ModelException {
		return faction.getUnitsInFaction();
	}

	/**
	 * Return all the active factions of the given world.
	 * 
	 * @param
	 * @return A set of all active (i.e., non-empty) factions in the world.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public Set<Faction> getActiveFactions(World world) throws ModelException {
		return world.getSetOfFactions();
	}
	
	/* BOULDERS */

	/**
	 * Get the precise coordinates of the given boulder.
	 * 
	 * @param boulder
	 *            The boulder for which to return the position.
	 * @return The coordinate of the center of the boulder, as an array with 3
	 *         doubles {x, y, z}.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public double[] getPosition(Boulder boulder) throws ModelException {
		return boulder.getPosition();
	}

	/**
	 * Return all boulders that are part of the given world.
	 * 
	 * @param world
	 *            The world from which to retrieve the boulders.
	 * @return A set containing all boulders present in the given world (i.e.,
	 *         not picked up, consumed, destroyed, ...).
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public Set<Boulder> getBoulders(World world) throws ModelException {
		return world.getSetOfBoulders();
	}

	/* LOGS */

	/**
	 * Get the precise coordinate of the given log.
	 * 
	 * @param log
	 *            The log for which to return the position.
	 * @return The coordinate of the center of the log, as an array with 3
	 *         doubles {x, y, z}.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public double[] getPosition(Log log) throws ModelException {
		return log.getPosition();
	}

	/**
	 * Return all logs that are part of the given world.
	 * 
	 * @param world
	 *            The world from which to retrieve the logs.
	 * @return A set containing all logs present in the given world (i.e., not
	 *         picked up, consumed, destroyed, ...).
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public Set<Log> getLogs(World world) throws ModelException {
		return world.getSetOfLogs();
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
		unit.advanceTimeOfUnit(dt);		
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
		return unit.isWalking();
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
	@Deprecated
	public void work(Unit unit) throws ModelException {
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
		unit.setDefaultBehavior(value);
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

		return unit.isDefaultBehaviorOn();
	}

	/* Task */
	
	/**
	 * Create a new instance of a Task factory.
	 * 
	 * <p>
	 * This factory is used by the parser ({@link TaskParser}) to construct an
	 * in-memory representation of your program. For example, when reading the
	 * task description
	 * 
	 * <pre>
	 * name: "test"
	 * priority: 1
	 * activities: moveTo here;
	 * </pre>
	 * 
	 * the parser will create a Task object by (conceptually) executing the
	 * following code:
	 * 
	 * <pre>
	 * factory.createTask("test", 1, factory.createMoveTo(factory.createHerePosition()))
	 * </pre>
	 * 
	 * on the returned factory object.
	 * 
	 * <p>
	 * For testing, you may use the methods from {@link TaskParser} yourself, as
	 * demonstrated in the partial test file {@link Part3TestPartial}.
	 * 
	 * @return An instance of ITaskFactory. See the documentation of that
	 *         interface for an explanation of its parameters.
	 */
	@Override
	public ITaskFactory<?, ?, Task> createTaskFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns whether the given task is well-formed.
	 * 
	 * A task is well-formed if
	 * <ul>
	 * <li>it is type-safe</li>
	 * <li>there are no break statements outside loops</li>
	 * <li>variables assigned before they are first used</li>
	 * </ul>
	 * See the assignment text for more details.
	 * 
	 * @param task
	 *            The task to check for well-formedness
	 * 
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 * @note Single-student groups may always return true for this method.
	 */
	@Override
	public boolean isWellFormed(Task task) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Returns the scheduler associated to the given faction.
	 * 
	 * @param faction
	 *            The faction of which to return the scheduler.
	 * 
	 * @return The scheduler associated to the given faction.
	 * 
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public Scheduler getScheduler(Faction faction) throws ModelException {
		return faction.getScheduler();
	}

	/**
	 * Schedule the given task for execution on the given scheduler.
	 * 
	 * @param scheduler
	 *            The scheduler on which the task should be scheduled.
	 * @param task
	 *            The task to schedule.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public void schedule(Scheduler scheduler, Task task) throws ModelException {
		scheduler.addTask(task);
	}

	/**
	 * Replace the given task by another task in the given scheduler.
	 * 
	 * @param scheduler
	 *            The scheduler in which a task should be replaced
	 * @param original
	 *            The task that needs to be replaced.
	 * @param replacement
	 *            The task that will replace the original task.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public void replace(Scheduler scheduler, Task original, Task replacement) throws ModelException {
		scheduler.replaceTask(original, replacement);
	}

	/**
	 * Returns whether the given tasks are all part of the given scheduler.
	 * 
	 * @param scheduler
	 *            The scheduler on which to check
	 * @param tasks
	 *            The tasks to check
	 * @return true if all given tasks are part of the scheduler; false
	 *         otherwise.
	 * 
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.s
	 */
	@Override
	public boolean areTasksPartOf(Scheduler scheduler, Collection<Task> tasks) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Returns an iterator for all tasks currently managed by the given
	 * scheduler.
	 * 
	 * @param scheduler
	 *            The scheduler for which to return an iterator.
	 * 
	 * @return An iterator that yields all scheduled tasks managed by the given
	 *         scheduler, independent of whether they're currently assigned to a
	 *         Unit or not. Completed tasks should not be part of the result.
	 *         The tasks should be delivered by decreasing priority (highest
	 *         priority first).
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public Iterator<Task> getAllTasksIterator(Scheduler scheduler) throws ModelException {
		return scheduler.getIterator();
	}

	/**
	 * Return all schedulers in which the given task is present.
	 * 
	 * @param task
	 *            The task for which to retrieve the schedulers
	 * @return A set of all schedulers that contain the given task.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public Set<Scheduler> getSchedulersForTask(Task task) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Return the unit currently assigned to the given task, if any.
	 * 
	 * @param task
	 *            The task for which to retrieve the unit
	 * @return The unit that is currently assigned to the given task, or null if
	 *         the task is not assigned to a unit.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public Unit getAssignedUnit(Task task) throws ModelException {
		return task.getAssignedUnit();
	}

	/**
	 * Return the task currently assigned to the given unit, if any.
	 * 
	 * @param unit
	 *            The unit for which to retrieve the assigned task
	 * @return The task that is currently assigned to the given unit, or null if
	 *         the unit does not have an assigned task.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public Task getAssignedTask(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Return the name of the given task.
	 * 
	 * @param task
	 *            The task of which to retrieve the name
	 * @return The name of the given task.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public String getName(Task task) throws ModelException {
		return task.getName();
	}

	/**
	 * Return the priority of the given task.
	 * 
	 * @param task
	 *            The task of which to retrieve the priority
	 * @return The priority of the given task.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@Override
	public int getPriority(Task task) throws ModelException {
		return task.getPriority();
	}

	@Override
	@Deprecated
	public Unit createUnit(String name, int[] initialPosition, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) throws ModelException {
		return null;
	}

}
