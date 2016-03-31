package hillbillies.model;

import java.util.*;

/**
 * A class that deals with the faction and all the properties of a faction 
 * in a given game world.
 *
 * @invar  Each faction has a valid amount of units in it.
 *       | this.getAmountOfUnitsInFaction() < getMaxAmountOfUnitsInFaction()
 *       | this.getAmountOfUnitsInFaction() > 0
 *	
 * @author Emil Peters
 * @author Sjaan Vandebeek
 * @version 2.0
 */
public class Faction {
	
	public Set<Unit> unitsInFaction;
	
	/* constructor */
	/**
	 * initialize a new faction with an empty list of units.
	 */
	public Faction() {
		unitsInFaction = new LinkedHashSet<Unit>();
	}
	
	/**
	 * add the given unit to the faction.
	 * @param unit
	 * 		  the given unit.
	 */
	public void addUnitToFaction(Unit unit) {
		if (this.getAmountOfUnitsInFaction() < getMaxAmountOfUnitsInFaction())
			unitsInFaction.add(unit);
		//TODO else throw exception?
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
	}
	
	/**
	 * return the amount of units in a faction.
	 */
	private int getAmountOfUnitsInFaction() {
		return unitsInFaction.size();
	}
	
	/**
	 * return the maximum amount of units in a faction.
	 */
	private static int getMaxAmountOfUnitsInFaction() {
		return 50;
	}
}
