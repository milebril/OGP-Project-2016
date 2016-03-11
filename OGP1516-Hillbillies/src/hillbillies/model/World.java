package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class that deals with a world and all the properties of that world.
 * 
 * @author Emil Peters
 * @author Sjaan Vandebeek
 */
public class World {
	
////////////////////////////////////////////Constructor////////////////////////////////////////////

	/**
	 * Initialize this new world with given amount of units.
	 * 
	 * @param  amountOfUnits
	 *         The amount of units for this new world.
	 * @pre    The given amount of units must be a valid amount of units for any world.
	 *       | isValidAmountOfUnits(amount of units)
	 * @post   The amount of units of this new world is equal to the given
	 *         amount of units.
	 *       | new.getAmountOfUnits() == amountOfUnits
	 *       
	 * Initialize this new world with given amount of factions.
	 * 
	 * @param  amountOfFactions
	 *         The amount of factions for this new world.
	 * @pre    The given amount of factions must be a valid amount of factions for any world.
	 *       | isValidAmountOfFactions(amount of factions)
	 * @post   The amount of factions of this new world is equal to the given
	 *         amount of factions.
	 *       | new.getAmountOfFactions() == amountOfFactions
	 */
	public World(int amountOfUnits,int amountOfFactions){
		//TODO constructor
		this.setAmountOfUnits(amountOfUnits);
		this.setAmountOfFactions(amountOfFactions);
	}

////////////////////////////////////////////Amount of units////////////////////////////////////////////
	/**
	 * @invar  The amount of units of each world must be a valid amount of units for any
	 *         world.
	 *       | isValidAmountOfUnits(getAmountOfUnits())
	 */
	
	/**
	 * Return the amount of units of this world.
	 */
	@Basic @Raw
	public int getAmountOfUnits() {
		return this.amountOfUnits;
	}
	
	/**
	 * Check whether the given amount of units is a valid amount of units for
	 * any world.
	 *  
	 * @param  amount of units
	 *         The amount of units to check.
	 * @return return true if the amount of units lays between the maximum 
	 * 		   number of units and the minimum number of units.
	*/
	public static boolean isValidAmountOfUnits(int amountOfUnits) {
		if (amountOfUnits <= getMaxUnitCapacity() && amountOfUnits >= getMinUnitCapacity())
			return true;
		return false;
	}
	
	/**
	 * Set the amount of units of this world to the given amount of units.
	 * 
	 * @param  amountOfUnits
	 *         The new amount of units for this world.
	 * @pre    The given amount of units must be a valid amount of units for any
	 *         world.
	 *       | isValidAmountOfUnits(amountOfUnits)
	 * @post   The amount of units of this world is equal to the given
	 *         amount of units.
	 *       | new.getAmountOfUnits() == amountOfUnits
	 */
	@Raw
	public void setAmountOfUnits(int amountOfUnits) {
		assert isValidAmountOfUnits(amountOfUnits);
		this.amountOfUnits = amountOfUnits;
	}
	
	/**
	 * return the maximum number of units for each world.
	 */
	private static int getMaxUnitCapacity(){
		return 100;
	}
	
	/**
	 * return the minimum number of units for each world.
	 */
	private static int getMinUnitCapacity(){
		return 0;
	}
	
	/**
	 * Variable registering the amount of units of this world.
	 */
	private int amountOfUnits;
	
////////////////////////////////////////////Amount of factions////////////////////////////////////////////
	/**
	 * @invar  The amount of factions of each world must be a valid amount of factions for any
	 *         world.
	 *       | isValidAmountOfFactions(getAmountOfFactions())
	 */
	
	/**
	 * Return the amount of factions of this world.
	 */
	@Basic @Raw
	public int getAmountOfFactions() {
		return this.amountOfFactions;
	}
	
	/**
	 * Check whether the given amount of factions is a valid amount of factions for
	 * any world.
	 *  
	 * @param  amount of factions
	 *         The amount of factions to check.
	 * @return return true if the amount of factions lays between the maximum 
	 * 		   number of factions and the minimum number of factions.
	*/
	public static boolean isValidAmountOfFactions(int amountOfFactions) {
		if (amountOfFactions <= getMaxAmountOfFactions() && amountOfFactions >= getMinAmountOfFactions())
			return true;
		return false;
	}
	
	/**
	 * Set the amount of factions of this world to the given amount of factions.
	 * 
	 * @param  amountOfFactions
	 *         The new amount of factions for this world.
	 * @pre    The given amount of factions must be a valid amount of factions for any
	 *         world.
	 *       | isValidAmountOfFactions(amountOfFactions)
	 * @post   The amount of factions of this world is equal to the given
	 *         amount of factions.
	 *       | new.getAmountOfFactions() == amountOfFactions
	 */
	@Raw
	public void setAmountOfFactions(int amountOfFactions) {
		assert isValidAmountOfFactions(amountOfFactions);
		this.amountOfFactions = amountOfFactions;
	}
	
	/**
	 * return the maximum number of factions for each world.
	 */
	private static int getMaxAmountOfFactions(){
		return 100;
	}
	
	/**
	 * return the minimum number of factions for each world.
	 */
	private static int getMinAmountOfFactions(){
		return 0;
	}
	
	/**
	 * Variable registering the amount of factions of this world.
	 */
	private int amountOfFactions;	
////////////////////////////////////////////next////////////////////////////////////////////	
}
