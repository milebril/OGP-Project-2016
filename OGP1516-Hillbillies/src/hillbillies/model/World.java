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
	 */
	public World(int[][][] terrainTypes, TerrainChangeListener modelListener) throws ModelException{
		this.setTerrainType(terrainTypes);
		setOfFactions = new LinkedHashSet<>(); //initializing setOfFactions on creating world
	}

////////////////////////////////////////////Amount of units////////////////////////////////////////////
	
	/**
	 * Return the amount of units of this world.
	 */
	@Basic @Raw
	public int getAmountOfUnits() {
		return this.ArrayListOfUnits.size();
	}
	
	/**
	 * Return the units of this world.
	 */
	public Set<Unit> getUnits() {
		return this.ArrayListOfUnits;
	}
	
	/**
	 * add the given unit to the list of units in this world.
	 * @param unit
	 * 		  the given world.
	 */
	public void addUnit(Unit unit) {
		if(getAmountOfUnits() < getMaxAmountOfUnits())
			this.ArrayListOfUnits.add(unit);
		//TODO exceptions throwen.
	}
	
	public void removeUnit(Unit unit) {
		this.ArrayListOfUnits.remove(unit);
	//TODO exception throwen als unit niet in lijst zit????????
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
	private Set<Unit> ArrayListOfUnits = new HashSet<Unit>();
		
////////////////////////////////////////////Amount of factions////////////////////////////////////////////
	
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
	
	//TODO zien of we dit nodig hebben
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

////////////////////////////////////////////list all factions////////////////////////////////////////////	
	/**
	 * Return the set of active factions of this world.
	 */
	@Basic @Raw
	public Set<Faction> getActiveFactionList() {
		return this.setOfFactions;
	}
	
	/**
	 * Return the amount of factions of this world.
	 */
	@Basic @Raw
	public int getAmountOfFaction() {
		return getActiveFactionList().size();
	}
	
	/**
	 * Variable registering the list of factions of this world.
	 */
	private Set<Faction> setOfFactions = new HashSet<Faction>();;
		
////////////////////////////////////////////create new faction////////////////////////////////////////////
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
	private void createFaction(Faction f) throws ModelException {
		if (! canCreateFaction())
			throw new ModelException();
		this.setOfFactions.add(f);
	}
		
////////////////////////////////////////////Random Unit spawnen////////////////////////////////////////////
	Random rand = new Random();
	
	public Unit spawnUnit(boolean enableDefaultBehavior) throws ModelException{
		int x = rand.nextInt(getXLength());
		int y = rand.nextInt(getYLength());
		int z = rand.nextInt(getZLength());
		
		System.out.println(x + " " + y + " " + z);
		
		while (!isCubePassable(x, y, z) && (getTerrainType()[x][y][z-1] != 1 || z != 0)) { //TODO !
			x = rand.nextInt(getXLength());
			y = rand.nextInt(getYLength());
			z = rand.nextInt(getZLength());
		}
		
		int weight = rand.nextInt(100);
		int strength = rand.nextInt(75) + 25;
		int agility = rand.nextInt(75) + 25;
		int toughness = rand.nextInt(75) + 25;
		
		Unit newUnit = new Unit("New Unit", new int[] {x, y, z}, weight, agility, strength, toughness, enableDefaultBehavior);
		
		if (getAmountOfFaction() < getMaxAmountOfFactions()) {
			Faction f = new Faction();
			createFaction(f);
			f.addUnitToFaction(newUnit);
			newUnit.setFaction(f);
		} else {
			Faction f = lowestMemberFaction();
			f.addUnitToFaction(newUnit);
			newUnit.setFaction(f);
			//TODO steek de unit in de faciton met het minste leden.
		}
		
		return newUnit;
	}
	
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
	
////////////////////////////////////////////Game over////////////////////////////////////////////
	
////////////////////////////////////////////inspect each individual cube////////////////////////////////////////////
		
////////////////////////////////////////////list all boulders////////////////////////////////////////////
	/**
	 * arraylist that contains all boulders of a given world.
	 */
	public Set<Boulder> arrayListOfBoulder = new HashSet<Boulder>();

	/**
	 * Return the list of boulders of this world.
	 */
	@Basic @Raw
	public Set<Boulder> getArrayListOfBoulders() {
		return this.arrayListOfBoulder;
	}

	/**
	 * Check whether the given list of boulders is a valid list of boulders for
	 * any world.
	 *  
	 * @param  list of boulders
	 *         The list of boulders to check.
	 * @return return true if the length of the arraylist is smaller than getMaxCapacityBoulders().
	*/
	public boolean isValidArrayListOfBoulders() {
		if (arrayListOfBoulder.size() <= getMaxCapacityBoulders()) return true;
		return false;
	}
	
	
	/**
	 * return the maximum capacity of boulders in this world.
	 */
	private int getMaxCapacityBoulders(){
		return 100;
	}

	/**
	 * add the given boulder to the list of boulders.
	 * @param boulder
	 * 		  the boulder to add.
	 */
	public void addBoulder(Boulder boulder){
		if (isValidArrayListOfBoulders())
			arrayListOfBoulder.add(boulder);
		//TODO else exception throwen?
	}
	
	/**
	 * removes the given boulder from the list of boulders.
	 * @param boulder
	 * 		  the boulder to remove.
	 */
	public void removeBoulder(Boulder boulder){
		arrayListOfBoulder.remove(boulder);
	}
////////////////////////////////////////////list all logs////////////////////////////////////////////
	/**
	 * arraylist that contains all the logs of a given world.
	 */
	public Set<Log> arrayListOfLog = new HashSet<Log>();

	/**
	 * Return the list of logs of this world.
	 */
	@Basic @Raw
	public Set<Log> getArrayListOfLog() {
		return this.arrayListOfLog;
	}

	/**
	 * Check whether the given list of logs is a valid list of logs for
	 * any world.
	 *  
	 * @param  list of logs
	 *         The list of logs to check.
	 * @return return true if the length of the arraylist is smaller than getMaxCapacityLogs().
	*/
	public boolean isValidArrayListOfLogs() {
		if (arrayListOfLog.size() <= getMaxCapacityLog()) return true;
		return false;
	}
	
	
	/**
	 * return the maximum capacity of logs in this world.
	 */
	private int getMaxCapacityLog(){
		return 100;
	}

	/**
	 * add the given log to the list of logs.
	 * @param log
	 * 		  the log to add.
	 */
	public void addLog(Log log){
		if (isValidArrayListOfLogs())
			arrayListOfLog.add(log);
		//TODO else exception throwen?
	}
	
	/**
	 * removes the given log from the list of logs.
	 * @param log
	 * 		  the log to remove.
	 */
	public void removeLog(Log log){
		arrayListOfBoulder.remove(log);
		}
	
////////////////////////////////////////////Advance time////////////////////////////////////////////
	/**
	 * advance all objects of the game world over a given time dt.
	 * @param dt
	 * 		  the given time dt.
	 */
	public void advanceTime(double dt) {
		Object[] listOfBoulders = arrayListOfBoulder.toArray();
		for(int i = 0; i < listOfBoulders.length; i++) {
			((Boulder) listOfBoulders[i]).advanceTimeOfBoulder(dt,this.terrainTypes);
		}
		
		Object[] listOfLogs = arrayListOfLog.toArray();
		for(int i = 0; i < listOfLogs.length; i++) {
			((Log) listOfLogs[i]).advanceTimeOfLog(dt,this.terrainTypes);
		}
		Object[] listOfUnits = ArrayListOfUnits.toArray();
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
	}
	
	/**
	 * Variable registering the terrainType of this world.
	 */
	private int[][][] terrainTypes;
	
	/**
	 * return the length of the world on the x-axis.
	 */
	public int getXLength(){
		return getTerrainType().length;
	}
	
	/**
	 * return the length of the world on the y-axis.
	 */
	public int getYLength(){
		return getTerrainType()[0].length;
	}
	
	/**
	 * return the length of the world on the z-axis.
	 */
	public int getZLength(){
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
}
