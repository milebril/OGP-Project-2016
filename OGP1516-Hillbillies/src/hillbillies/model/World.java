package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.part2.listener.TerrainChangeListener;
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
 *       
 * @invar  The terrainType of each world must be a valid terrainType for any
 *         world.
 *       | isValidTerrainType(getTerrainType())
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
	 * Initialize this new world with given terrainType.
	 *
	 * @param  terrainTypes
	 *         The terrainType for this new world.
	 * @effect The terrainType of this new world is set to
	 *         the given terrainType.
	 *       | this.setTerrainType(terrainTypes)
	 */
	public World(int[][][] terrainTypes, TerrainChangeListener modelListener) throws ModelException{
		this.setTerrainType(terrainTypes);
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
		//TODO deze functie moet verandert worden in een method die een list bij houdt van de units
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
	
////////////////////////////////////////////list all Units////////////////////////////////////////////

	
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
		//TODO deze functie moet verandert worden in een method die een list bij houdt van de factions
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

////////////////////////////////////////////list all factions////////////////////////////////////////////	
	
////////////////////////////////////////////Units in a faction////////////////////////////////////////////
	
////////////////////////////////////////////Random Unit spawnen////////////////////////////////////////////
	
////////////////////////////////////////////Game over////////////////////////////////////////////
	
////////////////////////////////////////////inspect each individual cube////////////////////////////////////////////
		
////////////////////////////////////////////list all boulders////////////////////////////////////////////
	
////////////////////////////////////////////list all logs////////////////////////////////////////////
	
////////////////////////////////////////////Advance time////////////////////////////////////////////
	
////////////////////////////////////////////World dimensions////////////////////////////////////////////
		
	/**
	 * Return the terrainType of this world.
	 */
	@Basic @Raw
	public int[][][] getTerrainType() {
		return this.terrainTypes;
	}
	
	/**
	 * Check whether the given terrainType is a valid terrainType for
	 * any world.
	 *  
	 * @param  terrainType
	 *         The terrainType to check.
	 * @return 
	 *       | result == 
	*/
	public static boolean isValidTerrainType(int[][][] terrainType) {
		//TODO check if valid terrain type
		return true;
	}
	
	/**
	 * Set the terrainType of this world to the given terrainType.
	 * 
	 * @param  terrainType
	 *         The new terrainType for this world.
	 * @post   The terrainType of this new world is equal to
	 *         the given terrainType.
	 *       | new.getTerrainType() == terrainType
	 * @throws IllegalArgumentException
	 *         The given terrainType is not a valid terrainType for any
	 *         world.
	 *       | ! isValidTerrainType(getTerrainType())
	 */
	@Raw
	public void setTerrainType(int[][][] terrainTypes) throws IllegalArgumentException {
		if (! isValidTerrainType(terrainTypes))
			throw new IllegalArgumentException();
		this.terrainTypes = terrainTypes;
		System.out.println(getTerrainType()[0][0][0]);
	}
	
	/**
	 * Variable registering the terrainType of this world.
	 */
	private int[][][] terrainTypes;
	
	/**
	 * return the length of the world on the x-axis.
	 */
	public int getXLength(){
		return terrainTypes.length;
	}
	
	/**
	 * return the length of the world on the y-axis.
	 */
	public int getYLength(){
		return terrainTypes[0].length;
	}
	
	/**
	 * return the length of the world on the z-axis.
	 */
	public int getZLength(){
		return terrainTypes[0][0].length;
	}
	
	public int getCubeType(int x, int y, int z) {
		return getTerrainType()[x][y][z];
	}
}
