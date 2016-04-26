package hillbillies.model;

import java.util.LinkedHashSet;
import java.util.Set;
import be.kuleuven.cs.som.annotate.*;
import ogp.framework.util.ModelException;

/**
 * A class that deals with the faction and all the properties of a faction 
 * in a given game world.
 *
 * @invar  Each faction has a valid amount of units in it.
 *       | this.getAmountOfUnitsInFaction() < getMaxAmountOfUnitsInFaction()
 *       | this.getAmountOfUnitsInFaction() > 0
 *       
 * @invar  The world of each faction must be a valid world for any
 *         faction.
 *       | isValidFaction(getWorld())
 *       
 * @invar  Each Faction can have its scheduler as scheduler.
 *       | canHaveAsScheduler(this.getScheduler())
 *	
 * @author Emil Peters
 * @author Sjaan Vandebeek
 * @version 2.0
 */
public class Faction {
	
	private Set<Unit> unitsInFaction;
	private Set<Faction> listOfFactions;
	
	/* constructor */
	/**
	 * initialize a new faction with an empty list of units.
	 * 
	 * Initialize this new faction with given world.
	 *
	 * @param  world
	 *         The world for this new faction.
	 * @effect The world of this new faction is set to
	 *         the given world.
	 *       | this.setWorld(world)
	 *       
	 * initialize this faction with a new scheduler
	 */
	public Faction(World newWorld) {
		this.setWorld(newWorld);
		unitsInFaction = new LinkedHashSet<Unit>();
		Scheduler Scheduler = new Scheduler();
		assert this.canHaveAsScheduler(Scheduler);
		this.scheduler = Scheduler;
	}
	
	/**
	 * return the faction of the given unit.
	 * @param unit
	 * 		  the given unit
	 */
	public Faction getFaction(Unit unit) {
		for (Faction f : listOfFactions) {
			for (Unit u : f.unitsInFaction) {
				if (u.equals(unit)) {
					return f;
				}
			}
		}
		return null;
	}
	
	/**
	 * add the given unit to the faction.
	 * @param unit
	 * 		  the given unit.
	 * @throws ModelException 
	 * 			is thrown when we can not create a new faction.
	 * 		  | !canAddUnit()
	 * 		
	 */
	public void addUnitToFaction(Unit unit) throws ModelException {
		if (! canAddUnit())
			throw new ModelException();
		this.unitsInFaction.add(unit);
	}
	
	/**
	 * Check whether there can be a unit added to the faction
	 *  
	 * @return if there are less units in the faction than the maximum number of units in a faction allowed, return true.
	 *       | if (this.getAmountOfUnitsInFaction() < this.getMaxAmountOfUnitsInFaction()) result == true
	 *       |		else result == false
	*/
	public boolean canAddUnit() {
		return getAmountOfUnitsInFaction() < getMaxAmountOfUnitsInFaction();
	}
	
	/**
	 * get all the units that are in the faction.
	 * @return return all units in the faction.
	 */
	public Set<Unit> getUnitsInFaction() {
		return unitsInFaction;
	}
	
	/**
	 * remove the given unit from the faction
	 * @param unit
	 * 		  the unit to remove
	 */
	public void removeUnitFromFaction(Unit unit) {
		unitsInFaction.remove(unit);
		if (unitsInFaction.size() == 0);
			this.getWorld().removeFaction(this);
	}
	
	/**
	 * return the amount of units in a faction.
	 */
	public int getAmountOfUnitsInFaction() {
		return unitsInFaction.size();
	}
	/**
	 * return the maximum amount of units in a faction.
	 */
	private static int getMaxAmountOfUnitsInFaction() {
		return 50;
	}
	
	/* world */

	/**
	 * Return the world of this faction.
	 */
	@Basic @Raw
	public World getWorld() {
		return this.world;
	}

	/**
	 * Check whether the given world is a valid world for
	 * any faction.
	 *  
	 * @param  world
	 *         The world to check.
	 * @return 
	 *       | result == 
	*/
	public static boolean isValidFaction(World world) {
		return true;
	}

	/**
	 * Set the world of this faction to the given world.
	 * 
	 * @param  world
	 *         The new world for this faction.
	 * @post   The world of this new faction is equal to
	 *         the given world.
	 *       | new.getWorld() == world
	 * @throws illegalArgumentException
	 *         The given world is not a valid world for any
	 *         faction.
	 *       | ! isValidFaction(getWorld())
	 */
	@Raw
	public void setWorld(World world) 
			throws IllegalArgumentException {
		if (! isValidFaction(world))
			throw new IllegalArgumentException();
		this.world = world;
	}

	/**
	 * Variable registering the world of this faction.
	 */
	private World world;
	
	/* scheduler */
	/**
	 * Return the scheduler of this Faction.
	 */
	@Basic @Raw @Immutable
	public Scheduler getScheduler() {
		return this.scheduler;
	}

	/**
	 * Check whether this Faction can have the given scheduler as its scheduler.
	 *  
	 * @param  scheduler
	 *         The scheduler to check.
	 * @return 
	 *       | result == 
	*/
	@Raw
	public boolean canHaveAsScheduler(Scheduler scheduler) {
		//TODO Hoe controleren voor valid scheduler?
		return true;
	}

	/**
	 * Variable registering the scheduler of this Faction.
	 */
	private final Scheduler scheduler;
}
