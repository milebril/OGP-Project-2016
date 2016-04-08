package hillbillies.model;

import java.util.LinkedHashSet;
import java.util.Set;

import ogp.framework.util.ModelException;

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
	
	private Set<Unit> unitsInFaction;
	private Set<Faction> listOfFactions;
	
	/* constructor */
	/**
	 * initialize a new faction with an empty list of units.
	 */
	public Faction() {
		unitsInFaction = new LinkedHashSet<Unit>();
	}
	
	public Faction getFaction(Unit unit) {
		for (Faction f : listOfFactions) {
			for (Unit u : f.unitsInFaction) {
				if (u == unit) {
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
		// if faction is empty remove faction
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
}
