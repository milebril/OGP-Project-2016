package hillbillies.model;

import java.util.*;
import be.kuleuven.cs.som.annotate.*;
import hillbillies.part2.listener.TerrainChangeListener;
import hillbillies.util.ConnectedToBorder;
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
 *       
 * @invar  The list of boulders of each world must be a valid list of boulders for any
 *         world.
 *       | isValidArrayListOfBoulders(getArrayListOfBoulders())
 *       
 * @invar  The list of logs of each world must be a valid list of logs for any
 *         world.
 *       | isValidArrayListOfLogs(getArrayListOfLogs())
 *       
 * @invar  The amount of factions of each world must be a valid amount of factions for any
 *         world.
 *       | isValidFactionAmount(getFactionAmount())
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
	 *       
	 * @effect create a new list that contains all the logs in a world.
	 * 
	 * @effect create a new list that contains all the boulders in a world.
	 */
	public World(int[][][] terrainTypes, TerrainChangeListener modelListener) throws ModelException{
		this.setTerrainType(terrainTypes);
		this.setOfUnits = new LinkedHashSet<>(); //initializing setOfUnits on creating world
		this.setOfFactions = new LinkedHashSet<>(); //initializing setOfFactions on creating world
		this.setOfBoulders = new LinkedHashSet<>(); //initializing setOfBoulders on creating world
		this.setOfLogs = new LinkedHashSet<>(); //initializing setOfLogs on creating world
		this.TerrainChangeListener = modelListener;
	}

////////////////////////////////////////////List of all units////////////////////////////////////////////
	
	/**
	 * Return the amount of units of this world.
	 */
	@Basic @Raw
	public int getAmountOfUnits() {
		return this.setOfUnits.size();
	}
	
	/**
	 * Return the units of this world.
	 */
	public Set<Unit> getSetOfUnits() {
		return this.setOfUnits;
	}
	
	/**
	 * add the given unit to the list of units in this world.
	 * @param unit
	 * 		  the given world.
	 * @throws ModelException
	 * 		| ! getAmountOfUnits() < getMaxAmountOfUnits()
	 */
	public void addUnit(Unit unit) throws ModelException{
		if(getAmountOfUnits() < getMaxAmountOfUnits()) {
			this.setOfUnits.add(unit);
			
		} else
			throw new ModelException();
	}
	
	public void removeUnit(Unit unit) {
		this.setOfUnits.remove(unit);
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
		if (amountOfUnits <= getMaxAmountOfUnits() && amountOfUnits >= getMinAmountOfUnits())
			return true;
		return false;
	}
		
	/**
	 * return the maximum number of units for each world.
	 */
	private static int getMaxAmountOfUnits(){
		return 100;
	}
	
	/**
	 * return the minimum number of units for each world.
	 */
	private static int getMinAmountOfUnits(){
		return 0;
	}
	
	/**
	 * Variable registering the amount of units of this world.
	 */
	private Set<Unit> setOfUnits;

////////////////////////////////////////////list of all factions////////////////////////////////////////////	
	/**
	 * Return the set of active factions of this world.
	 */
	@Basic @Raw
	public Set<Faction> getSetOfFactions() {
		return this.setOfFactions;
	}
	
	/**
	 * Return the amount of factions of this world.
	 */
	@Basic @Raw
	public int getAmountOfFaction() {
		return getSetOfFactions().size();
	}
		
	/**
	 * Check whether there can be a faction created in the world
	 *  
	 * @return if there are less faction than the maximum number of factions return true.
	 *       | if (this.getAmountOfFaction < getMaxAmountOfFactions()) result == true
	 *       |		else result == false
	*/
	private boolean canCreateFaction() {
		if (this.getAmountOfFaction() < getMaxAmountOfFactions())
			return true;
		return false;
	}

	/**
	 * Create a new faction
	 * 
	 * @param The faction to create.
	 * @post   A new faction is created and added to the setOfFactions
	 *       | this.setOfFactions.add(
	 * @throws ModelException
	 *         is thrown when we can not create a new faction.
	 *       | ! canCreateFaction()
	 */
	@Raw
	public void createFaction(Faction f) throws ModelException {
		if (! canCreateFaction())
			throw new ModelException();
		this.setOfFactions.add(f);
	}
	
	/**
	 * remove the given faction from the list of factions.
	 * @param faction
	 * 		  the given faction.
	 */
	public void removeFaction(Faction faction){
		this.setOfFactions.remove(faction);
	}
	
	/**
	 * return the maximum number of factions for each world.
	 */
	private static int getMaxAmountOfFactions() {
		return 5;
	}
	
	/**
	 * Set that contains all the factions of a world
	 */
	private Set<Faction> setOfFactions = new HashSet<Faction>();
	
////////////////////////////////////////////list of all boulders////////////////////////////////////////////
	/**
	* set that contains all boulders of a given world.
	*/
	public Set<Boulder> setOfBoulders;
	
	/**
	* Return the set of boulders of this world.
	*/
	@Basic @Raw
	public Set<Boulder> getSetOfBoulders() {
		return this.setOfBoulders;
	}
	
	/**
	* Check whether the given set of boulders is a valid set of boulders for
	* any world.
	* 
	* @return return true if the length of the set is smaller than getMaxCapacityBoulders().
	*/
	public boolean isValidArrayListOfBoulders() {
		if (setOfBoulders.size() <= getMaxCapacityBoulders()) 
			return true;
		return false;
	}
	
	/**
	* return the maximum capacity of boulders in this world.
	*/
	private int getMaxCapacityBoulders() {
		return 100;
	}
	
	/**
	* add the given boulder to the set of boulders.
	* @param boulder
	* 		  the boulder to add.
	*/
	public void addBoulder(Boulder boulder) {
		if (setOfBoulders.size() < getMaxCapacityBoulders())
			setOfBoulders.add(boulder);
	}
	
	/**
	* removes the given boulder from the set of boulders.
	* @param boulder
	* 		  the boulder to remove.
	*/
	public void removeBoulder(Boulder boulder) {
		setOfBoulders.remove(boulder);
	}
	
////////////////////////////////////////////list of all logs////////////////////////////////////////////
	/**
	* set that contains all the logs of a given world.
	*/
	public Set<Log> setOfLogs;
	
	/**
	* Return the set of logs of this world.
	*/
	@Basic @Raw
	public Set<Log> getSetOfLogs() {
		return this.setOfLogs;
	}
	
	/**
	* Check whether the given set of logs is a valid set of logs for
	* any world.
	*  
	* @return return true if the length of the set is smaller than getMaxCapacityLogs().
	*/
	public boolean isValidArrayListOfLogs() {
		if (setOfLogs.size() <= getMaxCapacityLog()) 
			return true;
		return false;
	}
	
	
	/**
	* return the maximum capacity of logs in this world.
	*/
	private int getMaxCapacityLog() {
		return 100;
	}
	
	/**
	* add the given log to the set of logs.
	* @param log
	* 		 the log to add.
	*/
	public void addLog(Log log) {
		if (setOfLogs.size() < getMaxCapacityLog())
			setOfLogs.add(log);
	}
	
	/**
	* removes the given log from the set of logs.
	* @param log
	* 		  the log to remove.
	*/
	public void removeLog(Log log){
		setOfLogs.remove(log);
	}
	
////////////////////////////////////////////Random Unit spawnen////////////////////////////////////////////
	/**
	 * Random used in the random unit spawning
	 */
	Random rand = new Random();
	
	/**
	 * Creates a random unit to the world
	 * @param enableDefaultBehavior
	 * 		whether DefaultBegavior is enabled on creation
	 * @return
	 * 		Returns a new unit, which had random coords on passable terrain
	 * 		With random values for it's weight, strenght, agility, toughness
	 * 		And is in a faction.
	 * 		
	 * @throws ModelException
	 */
	public Unit spawnUnit(boolean enableDefaultBehavior) throws ModelException {
		int x = rand.nextInt(getXLength());
		int y = rand.nextInt(getYLength());
		int z = rand.nextInt(getZLength());
		
		System.out.println(x + " " + y + " " + z);
		
		while (!(isCubePassable(x, y, z) && (z != 0 || getCubeType(x, y, z-1) != 1))) {
			x = rand.nextInt(getXLength());
			y = rand.nextInt(getYLength());
			z = rand.nextInt(getZLength());
		}
		
		int weight = rand.nextInt(100);
		int strength = rand.nextInt(75) + 25;
		int agility = rand.nextInt(75) + 25;
		int toughness = rand.nextInt(75) + 25;
		
		Unit newUnit = new Unit("New Unit", new int[] {x, y, z}, weight, agility, strength, toughness, enableDefaultBehavior, this);
		
		if (getAmountOfFaction() < getMaxAmountOfFactions()) {
			Faction f = new Faction(this);
			createFaction(f);
			f.addUnitToFaction(newUnit);
			newUnit.setFaction(f);
		} else {
			Faction f = lowestMemberFaction();
			f.addUnitToFaction(newUnit);
			newUnit.setFaction(f);
		}
		
		addUnit(newUnit);
		
		return newUnit;
	}
	
	/**
	 * Return the faction with the fewest members.
	 */
	private Faction lowestMemberFaction() {
		Faction min = null;
		for (Faction f : setOfFactions) {
			min = f;
			if (f.getUnitsInFaction().size() < min.getUnitsInFaction().size()) {
				min = f;
			}
		}
		
		return min;
	}
		
////////////////////////////////////////////Advance time////////////////////////////////////////////
	/**
	 * advance all objects of the game world over a given time dt.
	 * @param dt
	 * 		  the given time dt.
	 */
	public void advanceTimeOfWorld(double dt) {
		Object[] listOfBoulders = this.getSetOfBoulders().toArray();
		for(int i = 0; i < listOfBoulders.length; i++) {
			((Boulder) listOfBoulders[i]).advanceTimeOfBoulder(dt,this.terrainTypes);
		}
		
		Object[] listOfLogs = this.getSetOfLogs().toArray();
		for(int i = 0; i < listOfLogs.length; i++) {
			((Log) listOfLogs[i]).advanceTimeOfLog(dt,this.terrainTypes);
		}
		Object[] listOfUnits = this.getSetOfUnits().toArray();
		for(int i = 0; i < listOfUnits.length; i++) {
			((Unit) listOfUnits[i]).advanceTimeOfUnit(dt);
		}
	}
	
////////////////////////////////////////////World dimensions////////////////////////////////////////////
	
	/**
	 * Checks wheter the cube at given coords is passable
	 * @param x
	 * @param y
	 * @param z
	 * 		The (x, y, z) coords of the cube to check
	 * @return Returns true if the terrain is air or a workbench
	 * 		| if getTerrainType()[x][y][z] = 0 or getTerrainType()[x][y][z] = 3
	 * 			Then return == true
	 * 		  else return == false
	 *  		
	 */
	public boolean isCubePassable(int x, int y, int z) {
		return getTerrainType()[x][y][z] == 0 || getTerrainType()[x][y][z] == 3;
	}
		
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
	public boolean isValidTerrainType(int[][][] terrainType) {
		for ( int i = 0; i < terrainType.length ; i++){
			for ( int j = 0; j < terrainType[0].length ; j++){
				for ( int k = 0; k < terrainType[0][0].length ; k++){
					if (terrainType[i][j][k] != 1 && terrainType[i][j][k] != 2 && 
							terrainType[i][j][k] != 3 && terrainType[i][j][k] != 0)
						return false;
				}				
			}
		}
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
		if (! isValidTerrainType(terrainTypes)) throw new IllegalArgumentException();
		this.terrainTypes = terrainTypes;
	}
	
	/**
	 * Variable registering the terrainType of this world.
	 */
	private int[][][] terrainTypes;
	
	/**
	 * return the length of the world on the x-axis.
	 */
	public int getXLength() {
		return getTerrainType().length;
	}
	
	/**
	 * return the length of the world on the y-axis.
	 */
	public int getYLength() {
		return getTerrainType()[0].length;
	}
	
	/**
	 * return the length of the world on the z-axis.
	 */
	public int getZLength() {
		return getTerrainType()[0][0].length;
	}
	
	public int getCubeType(int x, int y, int z) {
		return getTerrainType()[x][y][z];
	}
	
	/**
	 * set the given cube to the given type
	 */
	public void setCubeType(int x, int y, int z, int value) {
		this.terrainTypes[x][y][z] = value;
	}
	
	public ConnectedToBorder connect;
	
	/**
	 * Check whether the given cube is solid connected to a border of the world.
	 *
	 * @param x
	 * @param y
	 * @param z
	 * 		The (x,y,z) coordinates of the cube to check.
	 * @return returns true when the cube is solid connected to a border.
	 * 		| result == isSolidConnectedToBorder(x, y, z)
	 * 
	 */
	public boolean isSolidConnectedToBorder(int x, int y, int z) {
		connect = new ConnectedToBorder(getXLength(), getYLength(), getZLength());
		return connect.isSolidConnectedToBorder(x, y, z);
	}
	
	/**
	 * variable registering the terrainchanglistener of a world.
	 */
	public TerrainChangeListener TerrainChangeListener;
}
