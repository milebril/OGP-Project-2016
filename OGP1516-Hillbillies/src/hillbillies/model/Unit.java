package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.item.Boulder;
import hillbillies.model.item.Item;
import hillbillies.model.item.Log;
import ogp.framework.util.ModelException;

import static java.lang.Math.PI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

/**
 * A class that deals with a unit and all the actions that they can complete 
 * in the given game world.
 * 
 * @invar  The weight of each unit must be a valid weight for any
 *         unit.
 *       | isValidWeigth(getWeight())
 * @invar  The strength of each unit must be a valid strength for any
 *         unit.
 *       | isValidStrength(getStrength())
 * @invar The agility of each unit must be a valid agility for any
 *         unit.
 *       | isValidAgility(getAgility())
 * @invar  The toughness of each unit must be a valid toughness for any
 *         unit.
 *       | isValidToughness(getToughness())
 * @invar  The name of each unit must be a valid name for any
 *         unit.
 *       | isValidName(getName())
 * @invar  The position of each unit must be a valid position for any
 *         unit.
 *       | isValidPosition(getPosition())
 * @invar  The orientation of each unit must be a valid orientation for any
 *         unit.
 *       | isValidOrientation(getOrientation())
 * @invar  The hitpoints of each unit must be a valid hitpoints for any
 *         unit.
 *       | isValidHitpoints(getHitpoints())     
 * @invar  The Stamina of each unit must be a valid Stamina for any
 *         unit.
 *       | isValidStamina(getStamina())
 	 
 * @invar  The unit is alive
 *       |	this.isAlive == true 
 * @invar  The property_name_Eng of each object_name_Eng must be a valid property_name_Eng for any
 *         object_name_Eng.
 *       | isValidPropertyName_Java(getPropertyName_Java())
 * @invar  The experience of each Unit must be a valid experience for any
 *         Unit.
 *       | isValidExperience(getExperience())
 * @invar  The faction of each unit must be a valid faction for any
 *         unit.
 *       | isValidFaction(getFaction())
 * @invar  The task of each unit must be a valid task for any
 *         unit.
 *       | isValidTask(getTask())
 *       
 * @version 2.0
 * @author Emil Peters
 * @author Sjaan Vandebeek
 *
 */

public class Unit {
	
	private UnitBehaviour behaviour;

/////////////////////////////////////////////Constructor/////////////////////////////////////////////
	/**
	 * initialize this new unit with the given name, position, weight, agility,
	 * strength, toughness and default behavior.
	 * 
	 * @param name
	 * 		  The Name for this new unit.
	 * @param initialPosition
	 * 		  The position for this new unit.
	 * @param weight
	 * 		  The weight for this new unit.
	 * @param agility
	 * 		  The agility for this new unit.
	 * @param strength
	 * 		  The strength for this new unit.
	 * @param toughness
	 * 	      The toughness for this new unit.
	 * @param enableDefaultBehavior
	 * 		  The default behavior for this new unit.
	 * @throws ModelException 
	 * @throws illegalPositionException
	 * 
	 * 
	 * @post The lowest possible value for weight is the agility of the new unit multiplied
	 * 		 with the strength of the new unit divided by 2.
	 * 		|new.weight >= new.strenght * new.agility / 2 
	 * @post The lowest possible value for weight is 25
	 * 		|new.weight >= 25
	 * @post The highest possible value for weight is 100
	 *      |new.weight() <= 100
	 * @post If the given weight is in range 25 ... 100 and in range new.strength * new.agilty / 2
	 * 		 ... 100 the weight of the new unit is equal to the given weight. If the given weight is 
	 * 		 smaller as 25 or smaller as  new.strength * new.agilty / 2 then the new weight is the 
	 * 		 largest value chosen between 25 new.strength * new.agilty / 2. If the given weight is 
	 * 		larger as 100 the weight of the new unit is equal to 100.
	 * 		|if ( new.weight >= 25 && new.weight >= new.strenght * new.agility / 2 && new.getweight <= 100)
	 * 		|	then new.Weigth == weight
	 * 		|if (new.weight >= 100)
	 * 		|	then new.weight == 100
	 * 		|if (new.weight <= 25 || new.weight <= new.strength * new.agilty / 2)
	 * 		|	then if (25 <= new.strength * new.agilty / 2)
	 * 		|			then new.weight = 25
	 * 		| 		 if (25 > new.strength * new.agilty / 2)
	 * 		|			then new.weight = new.strength * new.agilty / 2
	 * 
	 * @post The lowest possible value for agility is 25
	 * 		|new.getMinAgility() = 25
	 * @post The highest possible value for agility is 100
	 * 		|new.getMaxAgility() = 100
	 * @post If the given agility is in range getMinAgility() ... getMaxAgility()
	 * 		 the agility of the new unit is equal to the given agility.
	 * 		 If the given agility is smaller as getMinAgility() then the new
	 * 		 agility is equal to getMinAgility(). If the given agility is larger as
	 * 		 getMaxAgility() the agility of the new unit is equal to getMaxAgility().
	 * 		|if ( new.getMinAgility() >= 25 && new.getMaxAgility() <= 100)
	 * 		|	then new.agilty == agility
	 * 		|if (new.getMaxAgility() >= 100)
	 * 		|	then new.agility == getMaxAgility()
	 * 		|if (new.getMinAgility <= 25)
	 * 		|	then new.agility == getMinAgility()
	 * 
	 * @post The lowest possible value for strength is 25
	 * 		|new.getMinStrength() = 25
	 * @post The highest possible value for strength is 100
	 * 		|new.getMaxStrength() = 100
	 * @post If the given strength is in range getMinStrength() ... getMaxStrength()
	 * 		 the Strength of the new unit is equal to the given Strength.
	 * 		 If the given Strength is smaller as getMinStrength() then the new
	 * 		 strength is equal to getMinStrength(). If the given strength is larger as
	 * 		 getMaxStrength() the weight of the new unit is equal to getMaxStrength().
	 * 		|if ( new.getMinStrength() >= 25 && new.getMaxStrength() <= 100)
	 * 		|	then new.strength == strength
	 * 		|if (new.getMaxStrength() >= 100)
	 * 		|	then new.strength == getMaxStrength()
	 * 		|if (new.getMinStrength <= 25)
	 * 		|	then new.strength == getMinStrength()
	 * 
	 * @post The lowest possible value for toughness is 25
	 * 		|new.getMinToughness() = 25 
	 * @post The highest possible value for toughness is 100
	 * 		|new.getMaxToughness() = 100 
	 * @post If the given toughness is in range getMinToughness() ... getMaxToughness()
	 * 		 the toughness of the new unit is equal to the given toughness.
	 * 		 If the given toughness is smaller as getMinToughness() then the new
	 * 		 toughness is equal to getMinToughness(). If the given toughness is larger as
	 * 		 getMaxToughness() the toughness of the new unit is equal to getMaxToughness().
	 * 		|if ( new.getMinToughness() >= 25 && new.getMaxToughness() <= 100)
	 * 		|	then new.toughness == toughness
	 * 		|if (new.getMaxToughness() >= 100)
	 * 		|	then new.toughness == getMaxToughness()
	 * 		|if (new.getMinToughness <= 25)
	 * 		|	then new.toughness == getMinToughness()
	 * 
	 * @post The value for getMaxHitpoints() is ...
	 * 		|new.getMaxHitpoints() = 200 * (new.weight/100) * (new.toughness/100)
	 * @post The hitpoints of the new unit must be equal to getMaxHitpoints()
	 * 		|new.hitpoints = getMaxHitpoints
	 * 
	 * @post The value for getMaxStamina() is ...
	 * 		|new.getMaxStamina() = 200 * (new.weight/100) * (new.toughness/100)
	 * @post the stamina of the new unit must be equal to getMaxStamina()
	 * 		|new.Stamina = getMaxStamina()
	 * 
	 * @post The default behavior of the unit is equal to enableDefaultBehavior
	 * 		|new.defaultBehavior = enableDefaultBehavior
	 * 
	 * Initialize this new object_name_Eng with given property_name_Eng.
	 *
	 * @param  propertyName_Java
	 *         The property_name_Eng for this new object_name_Eng.
	 * @effect The property_name_Eng of this new object_name_Eng is set to
	 *         the given property_name_Eng.
	 *       | this.setPropertyName_Java(propertyName_Java)
	 *       
	 * Initialize this new Unit with given experience.
	 *
	 * @param  experience
	 *         The experience for this new Unit.
	 * @effect The experience of this new Unit is set to
	 *         the given experience.
	 *       | this.setExperience(experience)
	 *   
	 * Initialize this new unit with given faction.
	 *
	 * @param  faction
	 *         The faction for this new unit.
	 * @effect The faction of this new unit is set to
	 *         the given faction.
	 *       | this.setFaction(faction)
	 *       
	 * @throws illegalStateException
	 * 
	 */
	public Unit (String name, int[] initialPosition, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) throws IllegalArgumentException, ModelException {	
		/* Weight */
		if (weight > 100)
			this.setWeight(100);
		else if (weight < 25 || weight <(int)((double) this.strength * (double) this.agility / 2)){
			if (weight < (int)((double) this.strength * (double) this.agility / 2))
				this.setWeight((int)((double) this.strength * (double) this.agility / 2));
			else this.setWeight(25);
		}
		else{ 
			this.setWeight(weight);
			}
		
		/* Strength */
		if (strength < 25)
			this.setStrength(25);
		else if (strength > 100)
			this.setStrength(100);
		else
			this.setStrength(strength);
			
		/* Toughness */
		if (toughness < 25)
			this.setToughness(25);
		else if (toughness > 100)
			this.setToughness(100);
		else
			this.setToughness(toughness);
		
		/* Agility */
		if (agility < 25)
			this.setAgility(25);
		else if (agility > 100)
			this.setAgility(100);
		else
			this.setAgility(agility);
		
		/* Name */
		if (! isValidName(name)) 
			throw new IllegalNameException(name);
		else this.setName(name);
				
		/* Position */
		double[] position = putUnitInCenter(castIntToDouble(initialPosition));
		this.setUnitPosition(position);
		
		/* Hitpoints */
		this.increaseHitpoints(getMaxHitpoints());
		
		/* Stamina */
		this.increaseStamina(getMaxStamina());
		
		/* Default Behavior */
		this.setDefaultBehavior(enableDefaultBehavior);
		
		/* Experience */
		this.setExperience(0);
	}
		
/////////////////////////////////////////////World/////////////////////////////////////////////
	/**
	 * set the world of this unit to the given world.
	 * @param world
	 * 		  the given world
	 */
	public static void setWorld(World worldForUnits) {
		world = worldForUnits;
	}
	
	/**
	 * return the world of the unit.
	 */
	@Basic
	public static World getWorld() {
		return world;
	}
	
	/**
	 * variable registering the world of the unit
	 */
	private static World world;
	
	private static int getTerrainType(int[] coordinate){
		return getWorld().getTerrainType()[coordinate[0]][coordinate[1]][coordinate[2]];
	}
	
/////////////////////////////////////////////weight/////////////////////////////////////////////	

	/**
	 * Return the weight of this unit.
	 */
	@Basic @Raw
	public int getWeight() {
		return this.weight;
	}
		
	/**
	 * Check whether the given weight is a valid amount for
	 * any unit.
	 *  
	 * @param  weight
	 *         The weight to check.
	 * @return return true when the weight is a valid weight else return false.
	 *       |if weight <= getMinWeight() || weight => getMaxWeight() Then return == true
	 *       |		Else return == false
	*/
	public boolean isValidWeight(int weight) {
		if( this.getMinWeight() <= weight && weight <= getMaxWeight())
			return true;
		return false;
	}
	
	/**
	 * Set the weight of this Unit to the given weight.
	 * 
	 * @param  weight
	 *         The new weight for this Unit.
	 * @post   If the given weight is a valid weight for this Unit,
	 *         the weight of this new Unit is equal to the given
	 *         weight. If the given weight is not a valid weight then 
	 *         set the weight for the unit equal to the minimum weight.
	 *       | if (isValidWeight(weight))
	 *       |   Then new.getWeight() == weight
	 *       | else new.getWeight == new.getMinWeight()
	 */
	@Raw
	public void setWeight(int weight) {
		if (isValidWeight(weight))
			this.weight = weight;
		else this.weight = getMinWeight();
	}
	
	/**
	 * Return the lowest weight for all units
	 */
	private int getMinWeight(){
		return (int)(((double) this.strength + (double) this.agility) / 2);
	}
	
	/**
	 * Return the highest weight for all units
	 */
	private static int getMaxWeight(){
		return 100;
	}

	/**
	 * variables registering the weight of a unit.
	 */
	private int weight;
	
	/* Strength */
	/**
	 * Return the strength of this unit.
	 */
	@Basic @Raw
	public int getStrength() {
		return this.strength;
	}
	
	/**
	 * Check whether the given strength is a valid strength for
	 * any unit.
	 *  
	 * @param  strength
	 *         The strength to check.
	 * @return if a valid strength return true, else return false
	 *       |if (getMinStrenght() <= strength <= getMaxStrength()) 
	 *       |	Then return == true
	 *       |Else return == false
	*/
	public static boolean isValidStrength(int strength) {
		if( strength >= getMinStrength() && strength <= getMaxStrength())
			return true;
		return false;
	}
	
	/**
	 * Set the strength of this unit to the given strength.
	 * 
	 * @param  strength
	 *         The new strength for this unit.
	 * @post   If the given strength is a valid strength for any unit,
	 *         the strength of this new unit is equal to the given
	 *         strength. if the given strength is not a valid strength set 
	 *         the strength to the minimum strength.
	 *       | if (isValidStrength(strength))
	 *       |   then new.getStrength() == strength
	 *       | else new.getstrength() == getMinStrength()
	 */
	@Raw
	public void setStrength(int strenght) {
		if (isValidStrength(strenght))
			this.strength = strenght;
		else this.strength = getMinStrength();
	}
	
	/**
	 * Return the lowest strength for all units
	 */
	private static int getMinStrength(){
		return 1;
	}
	
	/**
	 * Return the highest strength for all units
	 */
	private static int getMaxStrength(){
		return 200;
	}
	
	/**
	 * variables registering the strength of this unit.
	 */
	private int strength;
	
/////////////////////////////////////////////Agility/////////////////////////////////////////////
	
	/**
	 * @invar The agility of each unit must be a valid agility for any
	 *         unit.
	 *       | isValidAgility(getAgility())
	 */
	
	/**
	 * Return the agility of this unit.
	 */
	@Basic @Raw
	public int getAgility() {
		return this.agility;
	}
	
	/**
	 * Check whether the given agility is a valid agility for
	 * any unit.
	 *  
	 * @param  agility
	 *         The agility to check.
	 * @return if a valid agility return true, else return false
	 *       |if (getMinAgility() <= agility <= getMaxAgility()) Then return == true
	 *       |		Else return == false
	*/
	public static boolean isValidAgility(int agility) {
		if( agility >= getMinAgility() && agility <= getMaxAgility())
			return true;
		return false;
	}
	
	/**
	 * Set the Agility of this unit to the given agility.
	 * 
	 * @param  agility
	 *         The new agility for this unit.
	 * @post   If the given agility is a valid agility for any unit,
	 *         the agility of this new unit is equal to the given
	 *         agility. if the given agility is not a valid agility set 
	 *         the agility to the minimum agility.
	 *       | if (isValidAgility(agility))
	 *       |   then new.getagility() == agility
	 */
	@Raw
	public void setAgility(int agility) {
		if (isValidAgility(agility))
			this.agility = agility;
		else this.agility = getMinAgility();
	}
	
	/**
	 * Return the lowest agility for all units
	 */
	private static int getMinAgility(){
		return 1;
	}
	
	/**
	 * Return the highest agility for all units
	 */
	private static int getMaxAgility(){
		return 200;
	}
	
	/**
	 * variables registering the agility of this unit.
	 */
	private int agility;
	
/////////////////////////////////////////////Toughness/////////////////////////////////////////////
	
	/**
	 * Return the toughness of this unit.
	 */
	@Basic @Raw
	public int getToughness() {
		return this.toughness;
	}
	
	/**
	 * Check whether the given toughness is a valid toughness for
	 * any unit.
	 *  
	 * @param  toughness
	 *         The toughness to check.
	 * @return if a valid toughness return true, else return false
	 *       |if (getMinToughness() <= toughness <= getMaxToughness) Then return == true
	 *       |		Else return == false
	*/
	public static boolean isValidToughness(int toughness) {
		if( toughness <= getMaxToughness() && toughness >= getMinToughness())
			return true;
		return false;
	}
	
	/**
	 * Set the toughness of this unit to the given toughness.
	 * 
	 * @param  toughness
	 *         The new toughness for this unit.
	 * @post   If the given toughness is a valid toughness for any unit,
	 *         the toughness of this new unit is equal to the given
	 *         toughness.  if the given toughness is not a valid toughness set 
	 *         the toughness to the minimum toughness.
	 *       | if (isValidToughness(toughness))
	 *       |   then new.getToughness() == toughness
	 */
	@Raw
	public void setToughness(int toughness) {
		if (isValidToughness(toughness))
			this.toughness = toughness;
		else this.toughness = getMinToughness();
	}
	
	/**
	 * Return the lowest toughness for all units
	 */
	private static int getMinToughness(){
		return 1;
	}
	
	/**
	 * Return the highest toughness for all units
	 */
	private static int getMaxToughness(){
		return 200;
	}
	
	/**
	 * variables registering the toughness of this unit.
	 */
	private int toughness;
	
/////////////////////////////////////////////Name/////////////////////////////////////////////
	
	/**
	 * Return the name of this unit.
	 */
	@Basic @Raw
	public String getName() {
		return this.name;
	}
	
	/**
	 * Check whether the given name is a valid name for
	 * any unit.
	 *  
	 * @param  name
	 *         The name to check.
	 * @return if the name is a valid name return true.
	 *       |if is validName is true then return true
	 *       |else return false
	*/
	public static boolean isValidName(String name) {
		if (name.length() < 2 || StringDoesContainOnlyValidCharacters(name) == false)
			return false;
		return true;
	}
	
	/**
	 * Checks if a string contains only valid characters.
	 * 
	 * @param string 
	 * 		  The string to check.
	 * @return if a string contains invalid character return false.
	 * 		   if the string only contains valid characters return true.	  
	 *		| if (!(Character.isLetter(c) || Character.isSpaceChar(c) || c == 34 || c == 44 )) Then return false
	 *		|		else return true
	 */
	private static boolean StringDoesContainOnlyValidCharacters(String name){
		if (Character.isLowerCase(name.charAt(0)))
			return false;
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if (!(Character.isLetter(c) || Character.isSpaceChar(c) || c == '\"' || c == '\'' )) {
				return false;
			}	
		}
		
		return true;
	}	

	/**
	 * Set the name of this unit to the given name.
	 * 
	 * @param  name
	 *         The new name for this unit.
	 * @post   The name of this new unit is equal to
	 *         the given name.
	 *       | new.getName() == string name
	 * @throws IllegalNameException
	 * 		Exception thrown when an invalid name is given
	 * 		| ! isValidName(name)
	 */
	@Raw
	public void setName(String name) throws IllegalNameException {
		if (!isValidName(name))
			throw new IllegalNameException(name);
		this.name = name;
	}
	
	/**
	 * a string registering the name of this unit.
	 */
	private String name;	
	
/////////////////////////////////////////////Position/////////////////////////////////////////////
	
	/**
	 * Return the Position of this Unit.
	 */
	@Basic @Raw
	public double[] getPosition() {
		return this.unitPosition;
	}
	
	/**
	 * cast an array of integers in an array of doubles
	 * @param arrayInInt
	 * @return arrayInDouble
	 */
	public double[] castIntToDouble (int[] arrayInInt){
		double[] arrayInDouble = {(double) arrayInInt[0],(double) arrayInInt[1],(double) arrayInInt[2]};
		return arrayInDouble;
	}
	
	/**
	 * cast an array of doubles in an array of integers
	 * @param arrayInDoubles
	 * @return arrayInInt
	 */
	public int[] castDoubleToInt (double[] arrayInDoubles){
		int[] arrayInInt = {(int) arrayInDoubles[0],(int) arrayInDoubles[1],(int) arrayInDoubles[2]};
		return arrayInInt;
	}
	
	/**
	 * Check whether the given position is a valid position for
	 * any Unit.
	 *  
	 * @param  position
	 *         The position to check.
	 * @return if the units position is in the given world we return True, else false
	 *       | if (unitPosition < 0 && unitPositio > 50) Then false
	 *       |		else false
	*/
	public static boolean isValidPosition(double[] unitPosition) {
		int N = unitPosition.length;
		if (N != 3)
			return false;
		for (int i=0; i < unitPosition.length; i++) {
			if (unitPosition[i] < getMinValueCoordinate() || unitPosition[i] > getMaxValueCoordinate())
				return false;
		} 
		
		return true;
	}
	
	/**
	 * Set the position of this unit to the given position.
	 * 
	 * @param  position
	 *         The new position for this unit.
	 *         An array of doubles contains the x,y and z coordinate of the unit
	 * @post   The position of this new unit is equal to the given position.
	 *       | new.getPosition() == Position
	 * @throws IllegalArgumentException
	 *         The given position is not a valid position for any
	 *         unit.
	 *       | ! isValidPosition(getPosition())
	 */
	@Raw
	public void setUnitPosition(double[] position) throws IllegalArgumentException {
		if (getWorld() != null && ! isValidPosition(position))
			throw new IllegalArgumentException(); 
		this.unitPosition = position;	

	}
	
	/**
	 * Return the value of the lowest coordinate value.
	 */
	private static int getMinValueCoordinate(){
		return 0;
	}
	
	/**
	 * Return the value of the lowest coordinate value.
	 */
	private static int getMaxValueCoordinate(){
		return getWorld().getXLength();
	}
 
	/**
	 * array registering the position of a unit in doubles. 
	 */
	private double[] unitPosition;
	
/////////////////////////////////////////////Orientation/////////////////////////////////////////////
	
	/**
	 * Return the orientation of this unit.
	 */
	@Basic @Raw
	public float getOrientation() {
		return this.orientation;
	}
	
	/**
	 * Check whether the given orientation is a valid orientation for
	 * any unit.
	 *  
	 * @param  orientation
	 *         The orientation to check.
	 * @return if a valid orientation return true, else return false
	 *       |if (0 <= orientation <= PI) Then return true
	 *     	 |		Else return false
	*/
	public static boolean isValidOrientation(int orientation) {
		if( orientation <= 2*PI && orientation >= 0)
			return true;
		return false;
	}
	
	/**
	 * Set the orientation of this unit to the given orientation.
	 * 
	 * @param  orientation
	 *         The new orientation for this unit.
	 * @param  
	 * @post   If the given orientation is a valid orientation for any unit,
	 *         the orientation of this new unit is equal to the given
	 *         orientation.
	 *       | if (isValidOrientation(orientation))
	 *       |   then new.getOrientation() == orientation
	 */
	@Raw
	public void setOrientation(float orientation) {
		if (this.orientation >= (2*PI))
			this.orientation = (float) (orientation % (2*PI));
		float deler;
		float restdeler;
		if (this.orientation < 0){
			restdeler = (float)((Math.abs(orientation) % (2*PI)));
			deler = (float)((Math.abs(orientation) - restdeler) % (2*PI)) +1;
			this.orientation = (float) ((float) deler * 2 * PI + orientation);
		}		
	}
	
	/**
	 * variable registering the orientation of a unit.
	 */
	private float orientation = (float) (PI / 2);
	
/////////////////////////////////////////////Hitpoints/////////////////////////////////////////////
	
	/**
	 * @invar  The hitpoints of each unit must be a valid hitpoints for any
	 *         unit.
	 *       | isValidHitpoints(getHitpoints())
	 */


	/**
	 * Return the hitpoints of this unit.
	 */
	@Basic @Raw
	public int getHitpoints() {
		return this.hitpoints;
	}
	
	/**
	 * Check whether the given hitpoints is a valid hitpoints for
	 * any unit.
	 *  
	 * @param  hitpoints
	 *         The hitpoints to check.
	 * @return if hitpoints is a valid value for all units return true 
	 *       | if 
	*/
	public boolean isValidHitpoints(int hitpoints) {
		return hitpoints > 0 /*&& hitpoints <= this.getMaxHitpoints()*/;
	}
	
	/**
	 * Check the maximum value of hitpoints of this unit
	 * 
	 * @return Returns the maximum health of the given unit
	 */
	public int getMaxHitpoints() {
		return (int) (200 * ((double) this.weight/100) * ((double) this.toughness/100));
	}
	
	/**
	 * Set the hitpoints of this unit to the given hitpoints.
	 * 
	 * @pre The given hitpoints must be a valid value for all units.
	 * 	   |isValidHitpoint(hitpoints) == True
	 * @post the hitpoints of this unit is equal to the given hitpoints.
	 * 		|this.hitpoints == hitpoints
	 * @param hitpoints
	 * 		  The given hitpoints.
	 */
	public void setHitpoints(int hitpoints) {
		this.hitpoints = hitpoints;
	}
	
	/**
	 * Increase the hitpoints of this unit with the given hitpoints.
	 * 
	 * @param  hitpoints
	 *         The amount of hitpoints to increase for this unit.
	 * @pre    The given hitpoints must be a valid hitpoints for any
	 *         unit.
	 *       | isValidHitpoints(hitpoints)
	 * @post   The hitpoints of this unit is equal to the old hitpoints plus the given hitpoints.
	 *       | if (this.hitpoints + hitpoints > getMaxHitpoints()) Then new.hitpoints == getMaxHitpoints()
	 * 		 |		Else new.hitpoints == this.hitpoints + hitpoints
	 */
	public void increaseHitpoints(int hitpoints) {
		assert isValidHitpoints(hitpoints);
		if (getHitpoints() + hitpoints > getMaxHitpoints())
			setHitpoints(getMaxHitpoints());
		else 
			setHitpoints(getHitpoints() + hitpoints);

	}
	
	
	/**
	 * Decrease the hitpoints of this unit with the given hitpoints.
	 * 
	 * @param  hitpoints
	 *         The amounts of hitpoints to decrease for this unit.
	 * @pre    The given hitpoints must be a valid hitpoints for any unit.
	 *       | isValidHitpoints(hitpoints)
	 * @post   The hitpoints of this unit is equal to the old hitpoints minus the given one.
	 *       |if (amount >= getHitpoints()) Then new.hitpoints == 0
	 * 		 |		Else new.hitpoints == this.getHitpoints - amount
	 * 
	 */
	public void decreaseHitpoints(int hitpoints) {
		assert isValidHitpoints(hitpoints);
		if (hitpoints >= getHitpoints()) {
			setHitpoints(0);
			die();
		} else
			setHitpoints(getHitpoints() - hitpoints);
		
	}
	
	/**
	 * variable registering the hitpoints of a unit.
	 */
	private int hitpoints;
	
/////////////////////////////////////////////Stamina/////////////////////////////////////////////
	
	/**
	 * Return the Stamina of this unit.
	 */
	@Basic @Raw
	public int getStamina() {
		return this.stamina;
	}
	
	/**
	 * Check the maximum value of stamina of this unit
	 * 
	 * @return Returns the maximum stamina of the given unit
	 */
	public int getMaxStamina() {
		return (int) (200 * ((double) this.weight/100) * ((double) this.toughness/100));
	}
	
	/**
	 * Set the stamina of this unit to the given stamina.
	 * 
	 * @pre The given stamina must be a valid value for all units.
	 * 	   |isValidStamina(stamina) == True
	 * @post the stamina of this unit is equal to the given stamina.
	 * 		|this.stamina == stamina
	 * @param stamina
	 * 		  The given stamina.
	 */
	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	/**
	 * Check whether the given Stamina is a valid Stamina for
	 * any unit.
	 *  
	 * @param  Stamina
	 *         The Stamina to check.
	 * @return return true if the stamina is bigger then zero.
	 *       | result == amount > 0
	*/
	public static boolean isValidStamina(int stamina) {
		return stamina > 0;
	}
		
	/**
	 * Increase the Stamina of this unit to the given Stamina.
	 * 
	 * @param  stamina
	 *         The new Stamina for this unit.
	 * @pre    The given Stamina must be a valid Stamina for any unit.
	 *       | isValidStamina(stamina)
	 * @post   The Stamina of this unit is equal to the the old stamina plus given Stamina.
	 *       | if (this.stamina + amount > getMaxStamina()) Then new.stamina == getMaxStamina()
	 * 		 |		Else new.stamina == this.stamina + amount
	 * 
	 */
	public void increaseStamina(int stamina) {
		assert isValidStamina(stamina);
		if (getStamina() + stamina >= getMaxStamina())
			this.stamina = getMaxStamina();
		else 
			this.stamina = this.stamina + stamina;
	}
	
	/**
	 * Decreases the Stamina of this unit to the given Stamina.
	 * 
	 * @param  stamina
	 *         The Stamina to decrease with for this unit.
	 * @pre    The given Stamina must be a valid Stamina for any unit.
	 *       | isValidStamina(stamina)
	 * @post   The Stamina of this unit is equal to the the old stamina minus given Stamina.
	 *       | if (amount > getStamina()) Then new.stamina == 0
	 * 		 |		Else new.stamina == this.getStamina - amount
	 * 
	 */
	public void decreaseStamina(int amount) {
		assert amount > 0;
		if (amount > getStamina()) 
			this.stamina = 0;
		else
			this.stamina = this.stamina - amount;
	}

	/**
	 * variable registering the stamina of a unit.
	 */
	private int stamina;
	
/////////////////////////////////////////////Advance Time/////////////////////////////////////////////
	
	/**
	 * a method that moves the unit through the time over a period dt.
	 * @param dt
	 * 		  the period dt.
	 */
	public void advanceTimeOfUnit(double dt){
		defBehaviourDel += 10;
		unitLifetime += dt;
		if (unitLifetime >= 1) {
			unitLifetimeInSeconds++;
			unitLifetime = 0;
		}
		//TODO Falling werkt niet, array out of bounds!!!!
		if ( this.canFall(getWorld().getTerrainType()))
			this.fall(dt, getWorld().getTerrainType());
		else if (this.behaviour == UnitBehaviour.RESTING)
			this.resting(dt);
		else if (this.behaviour == UnitBehaviour.WORKING)
			this.working(dt);
		else if (this.behaviour == UnitBehaviour.WALKING)
			this.walking(dt);
		else if (this.behaviour == UnitBehaviour.FIGHTING) {
			attTime += dt;
			updateOrientation(defenderClone);
			if (attTime >= 1) {
				attacking(defenderClone);
				attTime = 0;
			}
		}
		else if(defaultBehaviour == true){
			this.defaultBehavior(dt);
		} else if ((Math.round(unitLifetimeInSeconds) % 20) == 0 && unitLifetimeInSeconds > 1 && canRest()) {
			startResting();
		}
		
		if (this.isCarryingItem()) {
			this.carriedItem.advanceTimeOfItem(dt, getWorld().getTerrainType());
		}
	}
	
	/**
	 * variable registering the lifetime of a unit in doubles.
	 */
	private double unitLifetimeInSeconds = 0;
	
	/**
	 * boolean registering if a unit is sprinting or not.
	 */
	private boolean isSprinting = false;
	
	/**
	 * boolean registering if a unit is defending or not.
	 */
	private boolean isDefending = false;
	
	/**
	 * boolean registering if the default behavior of a unit is on or not.
	 */
	private boolean defaultBehaviour = false;
	
	/**
	 *Variable registering the attacking time of this unit.
	 */
	private double attTime = 0;
	
	/**
	 *Variable registering the life time of this unit.
	 */
	private double unitLifetime = 0;
	
	
	/* Movement */
	/**
	 * Start walking to the given coordinates.
	 * 
	 * @param dx
	 * 		 |The x coordinate 
	 * @param dy
	 * 		 |The y coordinate
	 * @param dz
	 * 		 |The z coordinate
	 */
	public void startWalking(int dx, int dy, int dz) {
		if (isAttacking() || isDefending() || isFalling())
			return;
		if (dx == 0 && dy ==0 && dz == 0) {
			return;
		}
		behaviour = UnitBehaviour.WALKING;
		stopPathfinding();
		
		double[] pos = getPosition();
		
		this.walkingTo[0] = pos[0] + dx;
		this.walkingTo[1] = pos[1] + dy;
		this.walkingTo[2] = pos[2] + dz;
				
		this.baseForWalkingSpeed = 1.5 * (((double) getStrength() + (double) getAgility()) / (200 * ((double) getWeight() / 100)));
	}
	
	/**
	 * check if the unit is walking.
	 * 
	 * @return the true if the unit is moving, return false
	 * 			false if the unit isn't moving.
	 */
	public boolean isWalking(){
		return behaviour == UnitBehaviour.WALKING;
	}
	
	/**
	 * return the current speed of the unit.
	 * 
	 * @return the current speed of the unit
	 */
	public double getSpeed(){
		double speed = this.baseForWalkingSpeed;
		if(this.isSprinting() == true)
			speed = speed * 2;
		if (this.walkingTo[2]>this.unitPosition[2])
			speed = speed * 0.5;
		if (this.walkingTo[2]<this.unitPosition[2])
			speed = speed * 1.2;
		return speed;
	}
	
	/**
	 * return if the unit is sprinting or not.
	 * 
	 * @return the state of sprinting of the unit
	 */
	public boolean isSprinting() {
		return this.isSprinting;
	}
	
	/**
	 * start sprinting
	 */
	public void startSprinting(){
		this.isSprinting = true;
	}
	
	 /**
	  * Stop sprinting
	  */
	public void stopSprinting() {
		this.isSprinting = false;
	}
	
	/**
	 * move the unit in the direction of the destination over a time period dt.
	 * 
	 * @param dt
	 * 		  the given time period dt.
	 */
	private void walking(double dt){
		
		double dx = (this.walkingTo[0] - this.unitPosition[0]);
		double dy = (this.walkingTo[1] - this.unitPosition[1]);
		double dz = (this.walkingTo[2] - this.unitPosition[2]);
		
		double speed = getSpeed();
		
		double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
		
		double vx = speed * (dx / distance);
		double vy = speed * (dy / distance);
		double vz = speed * (dz / distance);
		
		if (!isValidPosition(walkingTo)){
			stopWalking();
			return;
		}
		if (this.isSprinting == true){
			counterForRunning += dt;
			if (counterForRunning >= 0.1){
				int value = (int) (counterForRunning / 0.1);
				this.decreaseStamina(value);
				counterForRunning = counterForRunning - (0.1 *value);
			}
			if (getStamina() <= 0) {
				stopSprinting();
			}
		}
		
		this.unitPosition[0] += vx * dt;
		this.unitPosition[1] += vy * dt;
		this.unitPosition[2] += vz * dt;
		updateOrientation(vx, vy);
		canMove(dx, dy, dz, vx, vy, vz, dt);
	}
	
	/**
	 * check if the unit can move.
	 */
	private void canMove(double dx, double dy, double dz, double vx, double vy, double vz, double dt){
		if(Math.abs(dx) <= Math.abs(vx) * dt && Math.abs(dy) <= Math.abs(vy) * dt && Math.abs(dz) <= Math.abs(vz) * dt){
			setUnitPosition(this.walkingTo.clone());
			stopWalking();
		}
	}
	
	/**
	 * update the orientation of the unit.
	 * @param vx
	 * 		  the x coordinate for the orientation
	 * @param vy
	 * 		  the y coordinate for the orientation
	 */
	private void updateOrientation(double vx, double vy){
		this.orientation = ((float) Math.atan2(vy, vx)); 
		}
	
	/**
	 * make the unit stop walking.
	 */
	private void stopWalking(){
		behaviour = UnitBehaviour.NO_JOB;
		
		if (wantToWork) {
			startWorking(castDoubleToInt(getPosition()));
		}
	}
	
	/**
	 * array registering the destination of a unit in doubles.
	 */
	private double[] walkingTo = {0,0,0};
	
	/**
	 * variable registering the base of the walking speed.
	 */
	private double baseForWalkingSpeed;
	
	/**
	 * variable that is used when a unit is running.
	 */
	private double counterForRunning = 0;
	
/////////////////////////////////////////////Path Finding/////////////////////////////////////////////
	
	/**
	 * start walking to the given position
	 * @param cube
	 * 		  a list of integers that consist the x, y and z 
	 * 		  Coordinate of the final destination.
	 */
	public void startPathfinding(int[] cube){
		if (isAttacking() || isDefending() || isFalling())
			return;
		this.isPathfinding = true;
		behaviour = UnitBehaviour.NO_JOB;
		findPath(putUnitInCenter(castIntToDouble(cube)));
		walkPath();
	}
	
	/**
	 * @param initialPosistion
	 * 		The cubes position, where we need to put the unit in the center
	 * @return
	 * 		The new units position, in the center of a cube
	 */
	public double[] putUnitInCenter(double[] initialPosistion) {
		for (int i = 0; i < initialPosistion.length; i++) {
			initialPosistion[i] += 0.5;
		}
		return initialPosistion;
	}
	
	/**
	 * move the cube in the direction of the final destination 
	 * over a time interval dt.
	 * @param dt
	 * 		  the given time interval
	 */
	
	/**
	 * make a unit stopPathfinding.
	 */
	private void stopPathfinding(){
		this.isPathfinding = false;
	}
	
	/**
	 * return if a unit is pathfinding or not.
	 */
	private boolean isPathfinding(){
		return this.isPathfinding;
	}
	
	/**
	 * boolean registering if a unit is pathfinding or not.
	 */
	private boolean isPathfinding = false;

/////////////////////////////////////////////New Path Finding/////////////////////////////////////////////
		
	private Node bestNode;
	
	public void walkPath() {
		List<double[]> path= path();
		for (double[] pos : path) {
			startWalking((int) (-getPosition()[0] + pos[0]),(int) (-getPosition()[1] + pos[1]),(int) (-getPosition()[2] + pos[2]));
		}
		
	}
	
	class Node implements Comparable<Node>{
		public double[] position;
		private Node parent = null;
		private int distance, moves;
		private int fcost;
		
		public Node(double[] pos, int moves, int dist, Node parent) {
			this.moves = moves;
			this.position = pos;
			this.parent = parent;
			this.distance = dist;
			
			this.fcost = this.distance + this.moves;
		}
		
		@Override
		public int compareTo(Node node) {
			return this.fcost - node.fcost;
		}
	}
	
	public void findPath(double[] goal) {
		PriorityQueue<Node> openPQ =  new PriorityQueue<Node>();
		PriorityQueue<Node> closedPQ =  new PriorityQueue<Node>();
		
		openPQ.add(new Node(getPosition(), 0, 0, null));
		int counter = 0;
		
		while (!openPQ.isEmpty()) {
			Node current = openPQ.poll();

			
			if (Arrays.equals(current.position, goal)) {
				this.bestNode = current;
				break;
			}
			
			Set<int[]> neighbors = getAdjacentCubes(castDoubleToInt(current.position));
			for (int[] i : neighbors) {
				double [] nodePos = putUnitInCenter(castIntToDouble(i));
				if (! isNeighbouringSolidTerrain(i)) //TODO deze if weglaten, en we kunnen overal lopen
					continue;
				
				Node temp = new Node(nodePos, current.moves + 1, getDistance(nodePos, goal), current);
				if (sameQueueLowerF(openPQ, temp)) {
					continue;
				} else if (sameQueueLowerF(closedPQ, temp)) {
					continue;
				} else {
					openPQ.add(temp);
				}
			}
			closedPQ.add(current);
			
			counter++;
			
			if (counter > 5000) {
				break;
			}
			
		}
		
		if (bestNode == null) {
			stopPathfinding();
			behaviour = UnitBehaviour.NO_JOB;
			wantToWork = false;
		}
	
	}
	
	public boolean sameQueueLowerF(PriorityQueue<Node> queue, Node node) {
		for (Node n : queue) {
			if (Arrays.equals(n.position, node.position) && n.fcost < node.fcost) {
				return true;
			}
		}
		return false;
	}
	
	public static int getDistance(double[] nodePos, double[] goal) {
		
		int distX = (int) Math.abs(nodePos[0] - goal[0]);
        int distY = (int) Math.abs(nodePos[1] - goal[1]);
        int distZ = (int) Math.abs(nodePos[2] - goal[2]);

        return distX + distY + distZ;
	}
	
	public static Set<int[]> getAdjacentCubes(int[] position) {
		Set<int[]> adjacentCubes = new LinkedHashSet<>();
		
		int X = position[0];
		int Y = position[1];
		int Z = position[2];
		
		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				for (int z = -1; z <= 1; z++) {
					if (x != 0 | y != 0 | z != 0) {
						int[] adjacentPosition = {X + x, Y + y, Z + z};
						if (isInsideWorld(adjacentPosition))
							adjacentCubes.add(adjacentPosition);

					}
				}
			}
		}
		
		return adjacentCubes;
	}
	
	public List<double[]> path()
	{
		List<double[]> l = new ArrayList<double[]>();
		Node cur = this.bestNode;
		while (cur != null) {
			l.add(cur.position);
			cur = cur.parent;
		}
		Collections.reverse(l);
		
		return l;
	}
	
	private boolean isNeighbouringSolidTerrain(int[] cube) {
			
		if (cube[2] == 0)
			return true;

		Set<int[]> adjacentCubes = getAdjacentCubes(cube);
		
		for (int[] ac: adjacentCubes) {
			try{
				if (!getWorld().isCubePassable(ac[0], ac[1], ac[2]))
					return true;
			} catch (IndexOutOfBoundsException e) {};
		}
		return false;
			
	}
	
	public static boolean isInsideWorld(int[] position) {
		if (position[0] <= getWorld().getXLength() && position[1] <= getWorld().getYLength() && position[2] <= getWorld().getZLength() &&
				position[0] >= 0 && position[1] >= 0 && position[2] >= 0)
			return true;
		return false;
	}
	
/////////////////////////////////////////////Work/////////////////////////////////////////////
	
	/**
	 * Return the state of working of this unit.
	 */
	@Basic @Raw
	public boolean isTheUnitWorking() {
		//return this.isWorking;
		return behaviour == UnitBehaviour.WORKING;
	}
	
	/**
	 * Checks if the unit can work. This is possible when it's not in combat or not working
	 */
	private boolean canWork( ) {
		if (isAttacking() == true || this.isDefending == true)
			return false;
		return true;
	}
	
	private boolean wantToWork = false;
	
	/**
	 * Let the unit start working
	 * @return The unit stops walking and resting, and start working for a certain amount of seconds
	 * 		new.isWorking == true
	 * 		new.isResting == false
	 * 		new.isWalking == false
	 * 		new.timeLeftWorking = (500 / getStrenght())
	 */
	public void startWorking(int[] positionOfCube){
		System.out.println("[startWorking]");
		
		if (!(Arrays.equals(castDoubleToInt(getPosition()), positionOfCube))) {
			wantToWork = true;
			startPathfinding(positionOfCube);
			return;
		}
		
		if(isAttacking() == true || isDefending() == true || isFalling() == true) {
			return;
		}
		
		behaviour = UnitBehaviour.WORKING;
		whatWorkToDo();
		this.timeLeftWorking = (500 / getStrength());
	}
	
	enum workJob {
		MAKE_ARMOUR, DROP_LOG, DROP_BOULDER, PICKUP_LOG, PICKUP_BOULDER, MINE_ROCK, CHOP_LOG, WAIT, NO_WORK
	}
	
	private workJob work;
	
	public void whatWorkToDo() {
		if (getTerrainType(castDoubleToInt(this.getPosition())) == 3 &&
				logAtCurrentPos() && boulderAtCurrentPos()) {
			work = workJob.MAKE_ARMOUR;
		} else if (isCarryingBoulder()) {
			work = workJob.DROP_BOULDER;
		} else if (isCarryingLog()) {
			work = workJob.DROP_LOG;
		} else if (boulderAtCurrentPos()) {
			work = workJob.PICKUP_BOULDER;
		} else if (logAtCurrentPos()) {
			work = workJob.PICKUP_LOG;
		} else if (getTerrainType(castDoubleToInt(this.getPosition())) == 2) {
			work = workJob.CHOP_LOG;
		} else if (getTerrainType(castDoubleToInt(this.getPosition())) == 1) {
			work = workJob.MINE_ROCK;
		} else {
			work = workJob.NO_WORK;
		}
		
	}
	
	/**
	 * The actual working method. Where we keep working while working is True and the time to work is greater then timeLeftWorking
	 * @param dt
	 */
	private void working(double dt){
		if (canWork()) {
			if (this.timeLeftWorking == 0) {
				try {
					setExperience(10);
				} catch (ModelException e) {
					
				}
				stopWorking();
			} else {
				this.timeLeftWorking -= dt;
				if(this.timeLeftWorking <= 0){
					this.timeLeftWorking = 0;
				}
				//The working
				switch(work) {
				case NO_WORK:
					stopWorking();
					break;
				case MAKE_ARMOUR:
					getWorld().removeBoulder(getBoulderAtPosition(getPosition()[0], getPosition()[1], getPosition()[2]));
					getWorld().removeLog(getLogAtPosition(getPosition()[0], getPosition()[1], getPosition()[2]));
					this.setToughness(getToughness() + 5); 
					this.setWeight(getWeight() + 5); 
					work = workJob.WAIT;
					break;
				case DROP_BOULDER:
					getWorld().addBoulder((Boulder) this.carriedItem);
					stopCarryingItem();
					work = workJob.WAIT;
					break;
				case DROP_LOG:
					getWorld().addLog((Log) this.carriedItem);
					stopCarryingItem();
					work = workJob.WAIT;
					break;
				case PICKUP_BOULDER:
					Boulder b = getBoulderAtPosition(getPosition()[0], getPosition()[1], getPosition()[2]);
					b.startBeingCarried(this);
					getWorld().removeBoulder(b);
					work = workJob.WAIT;
					break;
				case PICKUP_LOG:
					Log l = getLogAtPosition(getPosition()[0], getPosition()[1], getPosition()[2]);
					l.startBeingCarried(this);
					getWorld().removeLog(l);
					work = workJob.WAIT;
					break;
				case CHOP_LOG:
					int[][][] terrainTypes = getWorld().getTerrainType();
					terrainTypes[(int) this.getPosition()[0]][(int) this.getPosition()[1]][(int) this.getPosition()[2]] = 0;
					getWorld().setTerrainType(terrainTypes);
					Log NewLog = new Log(getPosition());
					getWorld().addLog(NewLog);
					getWorld().getTerrainChangeListener().notifyTerrainChanged((int) this.getPosition()[0], (int) this.getPosition()[1], (int) this.getPosition()[2]);
					work = workJob.WAIT;
					break;
				case MINE_ROCK:
					int[][][] terrainTypes2 = getWorld().getTerrainType();
					terrainTypes2[(int) this.getPosition()[0]][(int) this.getPosition()[1]][(int) this.getPosition()[2]] = 0;
					getWorld().setTerrainType(terrainTypes2);
					Boulder NewBoulder = new Boulder(getPosition());
					getWorld().addBoulder(NewBoulder);
					getWorld().getTerrainChangeListener().notifyTerrainChanged((int) this.getPosition()[0], (int) this.getPosition()[1], (int) this.getPosition()[2]);
					work = workJob.WAIT;
					break;
				case WAIT:
					break;
				} 
			}
		} else {
			stopWorking();
		}
	}
	
	private Log getLogAtPosition(double x, double y, double z) {
		for (Log l : getWorld().getSetOfLogs()) {
			if (l.getPosition()[0] == x && l.getPosition()[1] == y && l.getPosition()[2] == z) {
				return l;
			}
		}
		return null;
	}
	
	private Boulder getBoulderAtPosition(double x, double y, double z) {
		for (Boulder b : getWorld().getSetOfBoulders()) {
			if (b.getPosition()[0] == x && b.getPosition()[1] == y && b.getPosition()[2] == z) {
				return b;
			}
		}
		return null;
	}
	
	private boolean logAtCurrentPos() {
		for (Log l : getWorld().getSetOfLogs()) {
			if (l.getPosition()[0] == getPosition()[0] && l.getPosition()[1] == getPosition()[1] && l.getPosition()[2] == getPosition()[2]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean boulderAtCurrentPos() {
		for (Boulder b : getWorld().getSetOfBoulders()) {
			if (b.getPosition()[0] == getPosition()[0] && b.getPosition()[1] == getPosition()[1] && b.getPosition()[2] == getPosition()[2]) {
				return true;
			}
		}
		return false;
	}
				
	/**
	 * The unit stops working
	 * 
	 * @post the state of working is false
	 * 		|this.isWorking == false
	 */
	private void stopWorking(){
		behaviour = UnitBehaviour.NO_JOB;
		work = workJob.NO_WORK;
		wantToWork = false;
	}

	/**
	 * variable registering the time left that a unit has to work.
	 */
	private double timeLeftWorking;
	
/////////////////////////////////////////////Carrying Item/////////////////////////////////////////////
	
	/**
	 * stop carrying the boulder that the unit is carrying.
	 */
	public void stopCarryingItem(){
		this.carriedItem.stopBeingCarried();
		this.carriedItem = null;
	}
	
	/**
	 * start carrying the given boulder.
	 * @param b
	 * 		  the given boulder.
	 */
	public void startCarryingItem(Item i) {
		if (castDoubleToInt(getPosition()) == castDoubleToInt(i.getPosition()) || isFalling())
			return;
		this.carriedItem = i;
	}
	
	/**
	 * Returns whether a unit is carrying a boulder
	 */
	public boolean isCarryingBoulder() {
		if(this.carriedItem != null && this.carriedItem instanceof Boulder)
			return true;
		return false;
	}
	
	/**
	 * Returns whether a unit is carrying a log
	 */
	public boolean isCarryingLog() {
		if(this.carriedItem != null && this.carriedItem instanceof Log)
			return true;
		return false;
	}
	
	/**
	 * Returns whether a unit is carrying an item
	 */
	public boolean isCarryingItem() {
		if(this.carriedItem != null)
			return true;
		return false;
	}
	
	
	/**
	 * Variable registering the item that the unit is carrying.
	 */
	private Item carriedItem = null;
	
/////////////////////////////////////////////Fighting/////////////////////////////////////////////
	
	/**
	 * Let the unit start attacking.
	 */
	public void startAttacking(Unit defender) {
		if (isFalling() || defender.isFalling()) return;
		if (targetOnValidPosition(defender) && targetFromDifferentFaction(defender)) {
			behaviour = UnitBehaviour.FIGHTING;
			stopPathfinding();
			defender.behaviour = UnitBehaviour.NO_JOB;
			defender.stopPathfinding();
			
			defenderClone = defender;
		}
		
	}
	
	/**
	 * let the attacker attack the defender
	 * @param defender
	 * 		  the defending unit
	 */
	private void attacking(Unit defender) {
		defender.behaviour = UnitBehaviour.DEFENDING;
		try {
			defender.defending(defender);
		} catch (ModelException e) { 
			
		}
		stopAttacking();
		stopDefending();
	}
	
	/**
	 * let the defending unit defend himself
	 * @param defender
	 * 		  the defending unit
	 * @throws ModelException When invalid experience
	 */
	private void defending(Unit defender) throws ModelException {
		if (succesfullDodge(defender)) {
			dodgethis(defender);
			defender.setExperience(20);
		} else if (succesfullBlock(defender)) {
			blockthis(defender);
			defender.setExperience(20);
		} else {
			takeDamage(defender);
			this.setExperience(20);
		}
	}
	
	/**
	 * return if a unit is defending or not.
	 */
	private boolean isDefending(){
		return behaviour == UnitBehaviour.DEFENDING;
	}
	
	/**
	 * make a unit stop defending
	 */
	private void stopDefending() {
		behaviour = UnitBehaviour.NO_JOB;
	}
	
	/**
	 * check whether the defending unit is in range of the attacker.
	 * @param defender
	 * 		 the defending unit
	 */
	private boolean targetOnValidPosition(Unit defender) {
		double dx = this.getPosition()[0] - defender.getPosition()[0];
		double dy = this.getPosition()[1] - defender.getPosition()[1];
		
		if (Math.abs(dx) <= 1 && Math.abs(dy) <= 1)
				return true;
		return false;
	}
	
	private boolean targetFromDifferentFaction(Unit defender) {
		return ! (getFaction().equals(defender.getFaction()));
	}
	
	/**
	 * update the orientation of the defender and the attacker
	 * @param defender
	 * 		  the defending unit
	 */
	private void updateOrientation(Unit defender) {
		this.orientation = (float) Math.atan2(defender.getPosition()[1] - this.getPosition()[1],
				defender.getPosition()[0] - this.getPosition()[0]);
		defender.orientation = (float) Math.atan2(this.getPosition()[1] - defender.getPosition()[1],
				this.getPosition()[0] - defender.getPosition()[0]);
	}
	
	/**
	 * check if a unit is attacking.
	 * @return if the unit is attacking return true.
	 */
	public boolean isAttacking() {
		return behaviour == UnitBehaviour.FIGHTING;
	}
	
	/**
	 * make a unit stop attacking
	 */
	private void stopAttacking() {
		behaviour = UnitBehaviour.NO_JOB;
	}
	
	/**
	 * a unit that is a clone of the defender
	 */
	private Unit defenderClone;
	
/////////////////////////////////////////////Dodging/////////////////////////////////////////////
	
	/**
	 * return the chance that a defending unit can dodge the attack.
	 * @param defender
	 * 		  the defending unit
	 * @return return the chance of dodging an attack.
	 */
	private double dodgeChance(Unit defender) {
		return 0.2 * ((double) defender.getAgility()/(double) this.getAgility());
	}
	
	/**
	 * return if the defending unit will dodge the attack.
	 */
	private boolean succesfullDodge(Unit defender) {
		Random random = new Random();
		int chance = random.nextInt(99);
		if (chance < 100 * dodgeChance(defender)) 
			return true;
		else
			return false;
	}
	
	/**
	 * move the unit to a random adjacent location.
	 */
	private void moveToRandomAdjecant() {
		Random rand = new Random();
		double newX = rand.nextInt(2) - 1;
		double newY = rand.nextInt(2) - 1;		
		double[] newPos = getPosition().clone();
		newPos[0] += newX;
		newPos[1] += newY;
		
		if(isValidPosition(newPos) && (newPos[0] != getPosition()[0] && newPos[1] != getPosition()[1]) &&
				getWorld().isCubePassable((int) newPos[0], (int) newPos[1], (int) getPosition()[2])) {			
			this.setUnitPosition(newPos);
		} else {
			moveToRandomAdjecant();
		}
		
	}
	
	/**
	 * execute the dodge.
	 */
	private void dodgethis(Unit defender) {
			moveToRandomAdjecant();
			
	}
	
/////////////////////////////////////////////Blocking/////////////////////////////////////////////
	
	/**
	 * return the chance that a defending unit can dodge the attack.
	 * @param defender
	 * 		  the defending unit
	 * @return return the chance of dodging an attack.
	 */
	private double blockChance(Unit defender) {
		return 0.25 * ((defender.getStrength() + defender.getStamina()) / (this.getStrength() + this.getStamina()));
	}
	
	/**
	 * return if the defending unit will dodge the attack.
	 */
	private boolean succesfullBlock(Unit defender) {
		Random random = new Random();
		int chance = random.nextInt(99);
		if (chance < 100 * blockChance(defender)) 
			return true;
		else
			return false;
	}
	
	/**
	 * make the defending unit block the attack
	 * @param defender
	 * 		  the unit that will block the attack
	 */
	private void blockthis(Unit defender){
		if (succesfullBlock(defender)) {
			stopAttacking();
		}
	}
	
/////////////////////////////////////////////Taking Damage/////////////////////////////////////////////
	
	/**
	 * decrease the hitpoints of the defending unit.
	 * @param defender
	 * 		  the defending unit.
	 */
	private void takeDamage(Unit defender) {
		defender.decreaseHitpoints((int) Math.round((double) this.stamina / 10));
	}
	
	
/////////////////////////////////////////////Resting/////////////////////////////////////////////
	
	/**
	 * Return the state of resting of this unit.
	 */
	@Basic @Raw
	public boolean isTheUnitResting() {
		return behaviour == UnitBehaviour.RESTING;
	}
	
	/**
	 * make the unit start resting
	 * 
	 * @post the state of resting of the unit is true
	 * 		| this.isResting == true
	 */
	public void startResting (){
		if (isFalling() || isAttacking() || isDefending())
			return;
		if (getHitpoints() == getMaxHitpoints()) {
			totalHPRestored = -5;
		}
		stopPathfinding();
		behaviour = UnitBehaviour.RESTING;
	}
	
	/**
	 * Check if the unit is able to rest.
	 *
	 * @return if the unit can rest return true
	*/
	private boolean canRest( ) {
		if (isAttacking() || isDefending() || isFalling()
				|| (getHitpoints() >= getMaxHitpoints() && getStamina() >= getMaxStamina() ))
			return false;
		return true;
	}
	
	/**
	 * make the unit rest over a time interval dt.
	 * @param dt
	 * 		  the time interval dt
	 */
	private void resting (double dt) {
		if (getHitpoints() < getMaxHitpoints()){
			restoreHitpoints(dt);
			return;
		}else if (this.stamina < getMaxStamina()){
			restoreStamina(dt);
			return;
		}
		stopResting();
	}
	
	/**
	 *  restore the hitpoints of a resting unit.
	 */
	private void restoreHitpoints (double dt){	
		double hitpointsToRestore = 5 * ((double) getToughness() / 200) * dt;
		counterForHitpoints += hitpointsToRestore;
		if(counterForHitpoints >= 1) {
			int value = (int) counterForHitpoints;
			counterForHitpoints -= value;
			increaseHitpoints(value);
			totalHPRestored++;
		}
	}
	
	/**
	 *  restore the stamina of a resting unit.
	 */
	private void restoreStamina (double dt){
		double staminaToRestore = 5 * ((double) this.toughness / 100) * dt;
		counterForStamina += staminaToRestore;
		if(counterForStamina >= 1) {
			int value = (int)counterForStamina;
			counterForStamina -= value;
			increaseStamina(value);
		}
	}
	
	/**
	 * make this unit stop resting
	 */
	private void stopResting (){
		if (totalHPRestored >= 0 || totalHPRestored == -5)
			behaviour = UnitBehaviour.NO_JOB;
	}
	
	/**
	 * Variable registering the total hitpoints that are restored of this unit.
	 */
	private int totalHPRestored = 0;
	
	/**
	 * variable registering the hitpoints that hase to be restored
	 */
	private double counterForHitpoints = 0;
	
	/**
	 * variable registering the stamina that hase to be restored
	 */
	private double counterForStamina = 0;
	
/////////////////////////////////////////////Default Behavior/////////////////////////////////////////////
	
	/**
	 * check if the default behavior of this unit is on.
	 * @return unit.defaultBehavior
	 */
	public boolean isDefaultBehaviorOn(){
		return this.defaultBehaviour;
	}
	
	/**
	 * set the default behavior to the given Boolean value
	 * @param value
	 * 		  the given boolean value
	 */
	public void setDefaultBehavior(boolean value){
		this.defaultBehaviour = value;
	}
	
	private double defBehaviourDel = 10;
	
	/**
	 * if default behavior is enabled start doing an action.
	 */
	private void defaultBehavior(double dt){
		if (defBehaviourDel <= 10) {
			return;
		}
		
		Random random = new Random();
		int ActionChance = random.nextInt(4);
		int[] cube = null;
		if (ActionChance == 1)
			cube = getRandomPosRadius8(ActionChance);
		if(ActionChance == 1){
			int SprintChance = random.nextInt(2);
			if (SprintChance == 1)
				this.startSprinting();
			this.startPathfinding(cube);
		}
		else if(ActionChance == 2){
			this.startWorking(castDoubleToInt(getPosition()));
		}
		else if(ActionChance == 3){
			if (canRest())
				this.startResting();
		} else if (ActionChance == 4) {
			for (Unit unit : getWorld().getSetOfUnits()) {
				if (targetOnValidPosition(unit) && targetFromDifferentFaction(unit)) {
					startAttacking(unit);
				}
			}
		}
	}
	
	private int[] getRandomPosRadius8(int work) {
		Random random = new Random();
		double[] cube = {getPosition()[0],getPosition()[1],getPosition()[2]};
		cube[0] += random.nextInt(16) - 8;
		cube[1] += random.nextInt(16) - 8;
		cube[2] += random.nextInt(16) - 8;
		
		int[] intCube = castDoubleToInt(cube);
		int counter = 0;
		while (!isInsideWorld(intCube)) {
			counter++;
			if (counter > 10) {
				intCube[0] = (int) getPosition()[0];
				intCube[1] = (int) getPosition()[1];
				intCube[2] = (int) getPosition()[2];
				return intCube;
			}
			intCube[0] += random.nextInt(16) - 8;
			intCube[1] += random.nextInt(16) - 8;
			intCube[2] += random.nextInt(16) - 8;
		}
		
		
		return intCube;
	}
	
/////////////////////////////////////////////Death/////////////////////////////////////////////
	
	/**
	 * Return the vivacity of this unit.
	 */
	@Basic @Raw
	public boolean isUnitAlive() {
		return this.isAlive;
	}
	
	/**
	 * The given unit dies.
	 * 
	 * @post The given unit's hitpoints are 0, and the unit dies
	 *       | new.isAlive == false
	 */
	@Raw
	public void die() {
		this.isAlive = false;
		getWorld().removeUnit(this);
		getFaction().removeUnitFromFaction(this);
		if (isCarryingItem()) {
			stopCarryingItem();
		}
	}
	
	/**
	 * Variable registering the vivacity of this unit.
	 * From the moment a Unit is created this is true.
	 */
	private boolean isAlive = true;
	
/////////////////////////////////////////////Falling/////////////////////////////////////////////
	
	/**
	 * check if a unit can fall.
	 */
	private boolean canFall(int[][][] terrainTypes){
		
		if (this.getPosition()[2] <= 0.5) {
			this.stopFalling();
			return false;
		}
		int[] position = castDoubleToInt(this.getPosition());
		if (position[2] - 1 >= 0) {
			if ((terrainTypes[position[0]][position[1]][position[2]-1] == 1 ||
					terrainTypes[position[0]][position[1]][position[2]-1] == 2) &&
					getPosition()[2] % position[2] <= 0.5) {
				this.stopFalling();
				return false;
			}
		if(position[0] > 0){
			if (terrainTypes[position[0]-1][position[1]][position[2]] == 1 || terrainTypes[position[0]-1][position[1]][position[2]] == 2){
				this.stopFalling();
				return false;
			}
		}
		if(position[0] < getWorld().getXLength()){
			if (terrainTypes[position[0]+1][position[1]][position[2]] == 1 || terrainTypes[position[0]-1][position[1]][position[2]] == 2){
				this.stopFalling();
				return false;
			}
		}
		if(position[1] > 0){
			if (terrainTypes[position[0]-1][position[1]-1][position[2]] == 1 || terrainTypes[position[0]-1][position[1]][position[2]] == 2){
				this.stopFalling();
				return false;
			}
		}
		if(position[1] > getWorld().getYLength()){
			if (terrainTypes[position[0]-1][position[1]+1][position[2]] == 1 || terrainTypes[position[0]-1][position[1]][position[2]] == 2){
				this.stopFalling();
				return false;
			}
		}
		}

		this.startFalling();
		return true;
	}
	
	/**
	 * Variable registering the speed when the unit falls
	 */
	private final int velocityOfFalling = 3;
	
	/**
	 * move a unit over a given time period dt.
	 */
	private void fall(double dt, int[][][] terrainTypes) {		
		this.setUnitPosition(new double[] {getPosition()[0], getPosition()[1], getPosition()[2] - velocityOfFalling * dt});
		this.setNumberOfZLevelsFallen(this.numberOfZLevelsFallen + velocityOfFalling * dt);
	}
	
	/**
	 * Return if is unit is falling or not.
	 */
	@Basic @Raw
	public boolean isFalling() {
		return this.isFalling;
	}
	
	/**
	 * make the unit stops falling
	 */
	private void stopFalling() {
		this.isFalling = false;
		decreaseHitpoints((int)(this.numberOfZLevelsFallen * 10));
		setNumberOfZLevelsFallen(0);
	}
	
	/**
	 * make the unit start falling
	 */
	private void startFalling() {
		this.isFalling = true;
		stopWorking();
		stopAttacking();
		stopPathfinding();
		stopWalking();
		stopResting();		
	}
		
	/**
	 * Variable registering if a unit is falling or not.
	 */
	private boolean isFalling = false;
	
	/**
	 * variable registering how many levels a units has fallen.
	 */
	private double numberOfZLevelsFallen = 0;
	
	private double getNumbersOfZLevelsFallen(){
		return this.numberOfZLevelsFallen;
	}
	
	private void setNumberOfZLevelsFallen(double number){
		this.numberOfZLevelsFallen = number;
	}
	
/////////////////////////////////////////////Experience/////////////////////////////////////////////
	
	/**
	 * Return the experience of this Unit.
	 */
	@Basic @Raw
	public int getExperience() {
		return this.experience;
	}
	
	/**
	 * Check whether the given experience is a valid experience for
	 * any Unit.
	 *  
	 * @param  experience
	 *         The experience to check.
	 * @return 
	 *       | result == experience >= 0
	*/
	public static boolean isValidExperience(int experience) {
		return experience >= 0;
	}
	
	/**
	 * Increases the experience of this Unit to the given experience.
	 * 
	 * @param  experience
	 *         The new experience for this Unit.
	 * @post   The experience of this new Unit is equal to
	 *         the given experience.
	 *       | new.getExperience() == old.experience + experience
	 * @throws ModelException
	 *         The given experience is not a valid experience for any
	 *         Unit.
	 *       | ! isValidExperience(getExperience())
	 */
	@Raw
	public void setExperience(int experience) throws ModelException {
		if (! isValidExperience(experience))
			throw new ModelException();
		this.expTillNextLevel += experience;
		this.experience += experience;
		
		for (int i = expTillNextLevel; i >= 10; i -= 10) {
			expTillNextLevel -= 10;
			increaceRandomStat();
		}
	}
	
	/**
	 * Every time 10 xp is reached a unit get's a random attribute get's increased by 1.
	 */
	private void increaceRandomStat() {
		Random rand = new Random();
		int r = rand.nextInt(2);
		switch (r) {
		case 0:
			setStrength(getStrength() + 1);
		case 1:
			setAgility(getAgility() + 1); 
		case 2:
			setToughness(getToughness() + 1); 
		}
	}
	
	/**
	 * Variable registering the experience of this Unit.
	 */
	private int experience;
	
	/**
	 * variable registering the experience needed for the next level.
	 */
	private int expTillNextLevel;
	
/////////////////////////////////////////////Faction/////////////////////////////////////////////
	
	/**
	 * Return the faction of this unit.
	 */
	@Basic @Raw
	public Faction getFaction() {
		return this.faction;
	}

	/**
	 * Check whether the given faction is a valid faction for
	 * any unit.
	 *  
	 * @param  faction
	 *         The faction to check.
	 * @return A unit must always belong to a faction
	 *       | if (faction is null) result == false
	 *       |		else result == true
	*/
	public static boolean isValidFaction(Faction faction) {
		return (faction != null);
	}

	/**
	 * Set the faction of this unit to the given faction.
	 * 
	 * @param  faction
	 *         The new faction for this unit.
	 * @post   The faction of this new unit is equal to
	 *         the given faction.
	 *       | new.getFaction() == faction
	 * @throws ModelException
	 *         The given faction is not a valid faction for any
	 *         unit.
	 *       | ! isValidFaction(getFaction())
	 */
	@Raw
	public void setFaction(Faction faction) 
			throws ModelException {
		if (! isValidFaction(faction))
			throw new ModelException();
		this.faction = faction;
	}

	/**
	 * Variable registering the faction of this unit.
	 */
	private Faction faction;
	
	
///////////////////////////////////////////// Task /////////////////////////////////////////////
		//TODO default behavior aanpassen	
	/**
	 * Return the task of this unit.
	 */
	@Basic @Raw
	public Task getTask() {
		return this.task;
	}
	
	/**
	 * Check whether the given task is a valid task for
	 * any unit.
	 *  
	 * @param  task
	 *         The task to check.
	 * @return 
	 *       | result == 
	*/
	public static boolean isValidTask(Task task) {
		return true;
		//TODO is vallid task
	}
	
	/**
	 * Set the task of this unit to the given task.
	 * 
	 * @param  task
	 *         The new task for this unit.
	 * @pre    The given task must be a valid task for any
	 *         unit.
	 *       | isValidTask(task)
	 * @post   The task of this unit is equal to the given
	 *         task.
	 *       | new.getTask() == task
	 */
	@Raw
	public void setTask(Task task) {
		assert isValidTask(task);
		this.task = task;
	}
	
	/**
	 * Variable registering the task of this unit.
	 */
	private Task task = null;
	
}