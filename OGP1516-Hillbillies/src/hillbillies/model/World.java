package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;
import ogp.framework.util.ModelException;

/**
 * A class that deals with a world and all the properties of that world.
 * 
 * @author Emil Peters
 * @author Sjaan Vandebeek
 *
 * @invar  The amount of units of each world must be a valid amount of units for any
 *         world.
 *       | isValidAmountOfUnits(getAmountOfUnits())
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
	 * @throws ModelException
	 * 		   The given amount of factions is not a valid amount of factions for any
	 *         world.
	 *       | ! isValidFactionAmount(getFactionAmount())
	 * @post   The amount of factions of this new world is equal to the given
	 *         amount of factions.
	 *       | new.getAmountOfFactions() == amountOfFactions
	 * @effect The amount of factions of this new world is set to
	 *         the given amount of factions.
	 *       | this.setFactionAmount(amountOfFactions)
	 *
	 */
	public World(int amountOfUnits,int amountOfFactions) throws ModelException{
		//TODO constructor
		this.setAmountOfUnits(amountOfUnits);
		this.setFactionAmount(amountOfFactions);
	}

////////////////////////////////////////////Amount of units////////////////////////////////////////////
	
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

	/** TO BE ADDED TO CLASS HEADING
	 * @invar  The amount of factions of each world must be a valid amount of factions for any
	 *         world.
	 *       | isValidFactionAmount(getFactionAmount())
	 */
	
	/**
	 * Return the amount of factions of this world.
	 */
	@Basic @Raw
	public int getFactionAmount() {
		return this.amountOfFactions;
	}
	
	/**
	 * Check whether the given amount of factions is a valid amount of factions for
	 * any world.
	 *  
	 * @param  amount of factions
	 *         The amount of factions to check.
	 * @return 
	 *       | result == 
	*/
	public static boolean isValidFactionAmount(int amountOfFactions) {
		if (amountOfFactions <= getMaxAmountOfFactions() && amountOfFactions >= getMinAmountOfFactions())
			return true;
		return false;
	}
	
	/**
	 * Set the amount of factions of this world to the given amount of factions.
	 * 
	 * @param  amountOfFactions
	 *         The new amount of factions for this world.
	 * @post   The amount of factions of this new world is equal to
	 *         the given amount of factions.
	 *       | new.getFactionAmount() == amountOfFactions
	 * @throws ModelException
	 *         The given amount of factions is not a valid amount of factions for any
	 *         world.
	 *       | ! isValidFactionAmount(getFactionAmount())
	 */
	@Raw
	public void setFactionAmount(int amountOfFactions) throws ModelException {
		if (! isValidFactionAmount(amountOfFactions))
			throw new ModelException();
		this.amountOfFactions = amountOfFactions;
	}
	
	/**
	 * return the maximum number of factions for each world.
	 */
	private static int getMaxAmountOfFactions(){
		return 5;
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
	
////////////////////////////////////////////Units in a faction////////////////////////////////////////////
	
////////////////////////////////////////////Random Unit spawnen////////////////////////////////////////////
	
////////////////////////////////////////////Game over////////////////////////////////////////////
	
////////////////////////////////////////////inspect each individual cube////////////////////////////////////////////
	
////////////////////////////////////////////list all Units////////////////////////////////////////////

////////////////////////////////////////////list all factions////////////////////////////////////////////
	
////////////////////////////////////////////list all boulders////////////////////////////////////////////
	
////////////////////////////////////////////list all logs////////////////////////////////////////////
	
////////////////////////////////////////////Advance time////////////////////////////////////////////
}
