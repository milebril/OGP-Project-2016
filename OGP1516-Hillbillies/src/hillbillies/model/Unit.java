package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;
import static java.lang.Math.PI;

import java.util.Arrays;
import java.util.Random;
/**
 * A class that deals with a unit and all the actions that they can complete 
 * in the given game world.
 * 
 * @version 2.0
 * @author Emil Peters
 * @author Sjaan Vandebeek
 *
 */
public class Unit {
	
	public final static int MIN_VALUE_STRENGTH = 1;
	public final static int MAX_VALUE_STRENGTH = 200;
	public final static int MIN_VALUE_AGILITY = 1;
	public final static int MAX_VALUE_AGILITY = 200;
	public int MIN_VALUE_WEIGTH = (getStrength() + getAgility()) / 2;
	public final static int MAX_VALUE_WEIGTH = 200;
	public final static int MIN_VALUE_TOUGHNESS = 1;
	public final static int MAX_VALUE_TOUGHNESS = 200;
	public final static int MIN_VALUE_COORDINATE_GAMEWORLD = 0;
	public final static int MAX_VALUE_COORDINATE_GAMEWORLD = 49;
	
	/*
	 * Variable registering the property_name_Eng of this object_name.
	 */
	private int weight;
	private int strength;
	private int agility;
	private int toughness;
	private String name;
	private float orientation = (float) (PI / 2);
	private double[] unitPosition;
	private int hitpoints;
	private int stamina;
	private boolean isSprinting = false;
	private boolean isWorking = false;
	private boolean isResting = false;
	private boolean isAttacking = false;
	private boolean isDefending = false;
	private boolean defaultBehaviour = false;
	private boolean isWalking = false;
	private double timeLeftWorking;
	private double[] walkingTo = {0,0,0};
	private double baseForWalkingSpeed;
	private double counterForHitpoints = 0;
	private double counterForStamina = 0;
	private double counterForRunning = 0;
	private boolean isPathfinding = false;
	private int[] pathfindingTo = {0,0,0};
	private Unit defenderClone;
	private double unitLifetimeInSeconds = 0;
	
	/*constructor*/
	
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
	 * @throws IllegalNameException 
	 * 
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
	 * @throws illegalStateException
	 * 
	 */
	public Unit (String name, int[] initialPosition, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) throws IllegalNameException {
		this.setWeight(weight);
		this.setStrength(strength);
		this.setToughness(toughness);
		this.setAgility(agility);
		this.setName(name);
		this.setUnitPosition(putUnitInCenter(castIntToDouble(initialPosition)));
		this.increaseHitpoints(getMaxHitpoints());
		this.increaseStamina(getMaxStamina());
		this.setDefaultBehaviour(enableDefaultBehavior);
		return ;
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
	
	
/////////////////////////////////////////////weight/////////////////////////////////////////////
	/* weight*/
	/**
	 * @invar  The weight of each unit must be a valid weight for any
	 *         unit.
	 *       | isValidWeigth(weight())
	 */
	
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
	 * @return 
	 *       |if weight <= 1 || weight => 200 Then return == true
	 *       |		Else return == false
	*/
	public static boolean isValidWeight(int weight) {
		if( getMinWeight() <= weight && weight <= getMaxWeight())
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
	 *         weight.
	 *       | if (isValidWeight(weight))
	 *       |   Then new.getWeight() == weight
	 */
	@Raw
	public void setWeight(int weight) {
		if (isValidWeight(weight))
			this.weight = weight;
	}
	
	/**
	 * Return the lowest weight for all units
	 */
	private static int getMinWeight(){
		return 25;
	}
	
	/**
	 * Return the highest weight for all units
	 */
	private static int getMaxWeight(){
		return 100;
	}
/////////////////////////////////////////////strength/////////////////////////////////////////////
	
	/**
	 * @invar  The strength of each unit must be a valid strength for any
	 *         unit.
	 *       | isValidStrength(strength())
	 */
	 
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
	 *       |if (MIN_VALUE_STRENGTH <= strength <= MAX_VALUE_STRENGTH) Then return == true
	 *       |		Else return == false
	*/
	public static boolean isValidStrength(int strength) {
		if( strength <= MAX_VALUE_STRENGTH && strength >= MIN_VALUE_STRENGTH)
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
	 *         strength.
	 *       | if (isValidStrength(strength))
	 *       |   then new.getStrength() == strength
	 */
	@Raw
	public void setStrength(int strenght) {
		if (isValidStrength(strenght))
			this.strength = strenght;
	}
		
/////////////////////////////////////////////agility/////////////////////////////////////////////
	
	/**
	 * @invar The agility of each unit must be a valid agility for any
	 *         unit.
	 *       | isValidStrength(strength())
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
	 *       |if (MIN_VALUE_AGILITY <= agility <= MAX_VALUE_AGILITY) Then return == true
	 *       |		Else return == false
	*/
	public static boolean isValidAgility(int agility) {
		if( agility <= MAX_VALUE_AGILITY && agility >= MIN_VALUE_AGILITY)
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
	 *         agility.
	 *       | if (isValidAgility(agility))
	 *       |   then new.getagility() == agility
	 */
	@Raw
	public void setAgility(int agility) {
		if (isValidAgility(agility))
			this.agility = agility;
		// else
	}
	
/////////////////////////////////////////////toughness/////////////////////////////////////////////	
	
	/**
	 * @invar  The toughness of each unit must be a valid toughness for any
	 *         unit.
	 *       | isValidToughness(toughness())
	 */
	
	/**
	 * Return the agility of this unit.
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
	 *       |if (MIN_VALUE_TOUGHNESS <= toughness <= MAX_VALUE_TOUGHNESS) Then return == true
	 *       |		Else return == false
	*/
	public static boolean isValidToughness(int toughness) {
		if( toughness <= MAX_VALUE_TOUGHNESS && toughness >= MIN_VALUE_TOUGHNESS)
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
	 *         toughness.
	 *       | if (isValidToughness(toughness))
	 *       |   then new.getToughness() == toughness
	 */
	@Raw
	public void setToughness(int toughness) {
		if (isValidToughness(toughness))
			this.toughness = toughness;
		// else
	}
	
/////////////////////////////////////////////name/////////////////////////////////////////////

	
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
	 * @return 
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
	
/////////////////////////////////////////////position in doubles/////////////////////////////////////////////
		
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
	 * @param arrayInDouble
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
	 * @return if the unit's position is in the given world we return True, else fasle
	 *       | if (unitPosition < 0 && unitPositio > 50) Then false
	 *       |		else false
	*/
	public static boolean isValidPosition(double[] unitPosition) {
		int N = unitPosition.length;
		if (N > 3)
			return false;
		for (int i=0; i < unitPosition.length; i++) {
			if (unitPosition[i] < MIN_VALUE_COORDINATE_GAMEWORLD || unitPosition[i] > MAX_VALUE_COORDINATE_GAMEWORLD)
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
		if (! isValidPosition(position))
			throw new IllegalArgumentException(); //exception;
		this.unitPosition = position;	

	}
 
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
		
		// van <0 naar positief ??????
		
	}
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
	 * @return 
	 *       | result == 
	*/
	public static boolean isValidHitpoints(int hitpoints) {
		return hitpoints > 0;
	}
	
	/**
	 * Check the maximum value of hitpoints of this unit
	 * 
	 * @return Returns the maximum health of the given unit
	 */
	public int getMaxHitpoints() {
		return (int) (200 * ((double) this.weight/100) * ((double) this.toughness/100));
	}
	
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
	 *       |if (amount > getHitpoints()) Then new.hitpoints == 0
	 * 		 |		Else new.hitpoints == this.getHitpoints - amount
	 * 
	 */
	public void decreaseHitpoints(int hitpoints) {
		assert isValidHitpoints(hitpoints);
		if (hitpoints > getHitpoints()) 
			setHitpoints(0);
		else
			setHitpoints(getHitpoints() - hitpoints);
		
	}
	
/////////////////////////////////////////////current stamina/////////////////////////////////////////////
	
	/**
	 * @invar  The Stamina of each unit must be a valid Stamina for any
	 *         unit.
	 *       | isValidStamina(getStamina())
	 */


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
	
	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	/**
	 * Check whether the given Stamina is a valid Stamina for
	 * any unit.
	 *  
	 * @param  Stamina
	 *         The Stamina to check.
	 * @return 
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

/////////////////////////////////////////////advance time/////////////////////////////////////////////
	private double attTime = 0;
	private double unitLifetime = 0;
	
	public void advanceTimeInUnit(double dt){
		unitLifetime += dt;
		if (unitLifetime >= 1) {
			unitLifetimeInSeconds++;
			unitLifetime = 0;
		}
		
		if (this.isResting == true && this.isWalking == false)
			this.resting(dt);
		else if (this.isWorking == true && this.isWalking == false)
			this.working(dt);
		else if (this.isWalking == true)
			this.walking(dt);
		else if (this.isPathfinding == true)
			this.pathfinding(dt);
		else if (this.isAttacking == true && this.isWalking == false) {
			attTime += dt;
			updateOrientation(defenderClone);
			if (attTime >= 1) {
				attacking(defenderClone);
				attTime = 0;
			}
		}
		else if(defaultBehaviour == true){
			this.defaultBehaviour(dt);
		} else if ((Math.round(unitLifetimeInSeconds) % 20) == 0 && unitLifetimeInSeconds > 1 && canRest()) {
			System.out.println("resting");
			startResting();
		}
	}
	
/////////////////////////////////////////////movement/////////////////////////////////////////////
	public void startWalking(int dx, int dy, int dz) {
		if (this.isWalking == true)
			return;
		this.isWalking = true;
		this.isWorking = false;
		stopResting();
		double[] pos = getPosition();
		
		this.walkingTo[0] = pos[0] + dx;
		this.walkingTo[1] = pos[1] + dy;
		this.walkingTo[2] = pos[2] + dz;
				
		this.baseForWalkingSpeed = 1.5 * (((double) getStrength() + (double) getAgility()) / (200 * ((double) getWeight() / 100)));
	}
	
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
			this.isWalking = false;
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
	
	public boolean isTheUnitMoving(){
		return this.isWalking;
	}
		
	private void canMove(double dx, double dy, double dz, double vx, double vy, double vz, double dt){
		if(Math.abs(dx) <= Math.abs(vx) * dt && Math.abs(dy) <= Math.abs(vy) * dt && Math.abs(dz) <= Math.abs(vz) * dt){
			setUnitPosition(this.walkingTo.clone());
			this.isWalking = false;
		}
	}
	
	private void updateOrientation(double vx, double vy){
		this.orientation = ((float) Math.atan2(vy, vx)); 
		}
	
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
	
	public boolean isSprinting() {
		return this.isSprinting;
	}
	
	public void startSprinting(){
		this.isSprinting = true;
	}
	
	public void stopSprinting() {
		this.isSprinting = false;
	}
	
/////////////////////////////////////////////path finding/////////////////////////////////////////////
	
	public void startPathfinding(int[] cube){
		if (this.isPathfinding == true) {
			this.isPathfinding = false;
			return;
		}
		this.isPathfinding = true;
		stopResting();
		this.isWorking = false;
		this.pathfindingTo = cube.clone();
	}
	
	private void pathfinding (double dt) {
		if (this.isWalking == true) {
			return;
		} else {
			double[] moveTo = castIntToDouble(pathfindingTo);
			putUnitInCenter(moveTo);
			int dx = 0, dy = 0, dz = 0;
			System.out.print(" " + moveTo[0] + " " + moveTo[1] + " " + moveTo[2] + "\n");
			System.out.print(" " + getPosition()[0] + " " +  getPosition()[1] + " " +  getPosition()[2] + "\n");
			
			if (getPosition()[0] == moveTo[0] && getPosition()[1] == moveTo[1] && getPosition()[2] == moveTo[2]){
				System.out.println("kaka");
				this.isPathfinding = false;
				stopSprinting();
				return;
			}
			
			if (getPosition()[0] == moveTo[0]) {
				dx = 0;
			} else if (getPosition()[0] < moveTo[0]) {
				dx = 1;
			} else {
				dx = -1;
			}
			
			if (getPosition()[1] == moveTo[1]) {
				dy = 0;
			} else if (getPosition()[1] < moveTo[1]) {
				dy = 1;
			} else {
				dy = -1;
			}
			
			if (getPosition()[2] == moveTo[2]) {
				dz = 0;
			} else if (getPosition()[2] < moveTo[2]) {
				dz = 1;
			} else {
				dz = -1;
			}
			
			startWalking(dx, dy, dz);
			}
	}
/////////////////////////////////////////////actions/////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
	
/////////////////////////////////////////////work/////////////////////////////////////////////
	
	/**
	 * Return the state of working of this unit.
	 */
	@Basic @Raw
	public boolean isTheUnitWorking() {
		return this.isWorking;
	}
	
	/**
	 * Checks if the unit can work. This is possible when it's not in combat or not working
	 */
	private boolean canWork( ) {
		if (isAttacking() == true || this.isDefending == true || this.timeLeftWorking == 0)
			return false;
		return true;
	}
	
	/**
	 * The actual working method. Where we keep working while working is True and the time to work is greater then timeLeftWorking
	 * @param dt
	 */
	private void working(double dt){
		if(this.canWork() == true){ 
			if (this.timeLeftWorking == 0){
				this.stopWorking();
			}else{
				this.timeLeftWorking = this.timeLeftWorking -  dt;
				if(this.timeLeftWorking <= 0){
					this.timeLeftWorking = 0;
				}
			}
		}else{
			this.stopWorking();
		}
	}
	
	/**
	 * The unit stops working
	 */
	private void stopWorking(){
		this.isWorking = false;
	}
	
	/**
	 * The unit start working
	 * @return The unit stops walking and resting, and start working for a certain amount of seconds
	 * 		new.isWorking == true
	 * 		new.isResting == false
	 * 		new.isWalking == false
	 * 		new.timeLeftWorking = (500 / getStrenght())
	 */
	public void startWorking(){
		this.isWorking = true;
		stopResting();
		this.isPathfinding = false;
		this.timeLeftWorking = (500 / getStrength());
	}
/////////////////////////////////////////////Fighting/////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////
	
/////////////////////////////////////////////Attacking////////////////////////////////////////////
	
	public void startAttacking(Unit defender) {
		if (targetOnValidPosition(defender)) {
			stopWorking();
			stopResting();
			this.isPathfinding = false;
			defenderClone = defender;
			this.isAttacking = true;
		}
		
	}
	
	private void attacking(Unit defender) {
		this.isAttacking = true;
		defender.isDefending = true;
		defender.defending(defender);
		stopAttacking();
		defender.isDefending = false;
	}
	
	private void defending(Unit defender) {
		if (succesfullDodge(defender)) {
			dodgethis(defender);
		} else if (succesfullBlock(defender)) {
			blockthis(defender);
		} else {
			takeDamage(defender);
		}
	}
	
	private boolean targetOnValidPosition(Unit defender) {
		double dx = this.getPosition()[0] - defender.getPosition()[0];
		double dy = this.getPosition()[1] - defender.getPosition()[1];
		
		if (Math.abs(dx) <= 1 && Math.abs(dy) <= 1)
				return true;
		return false;
	}
	
	private void updateOrientation(Unit defender) {
		this.orientation = (float) Math.atan2(defender.getPosition()[1] - this.getPosition()[1],
				defender.getPosition()[0] - this.getPosition()[0]);
		defender.orientation = (float) Math.atan2(this.getPosition()[1] - defender.getPosition()[1],
				this.getPosition()[0] - defender.getPosition()[0]);
	}
	
	public boolean isAttacking() {
		return this.isAttacking;
	}
	
	private void stopAttacking() {
		this.isAttacking = false;
	}
/////////////////////////////////////////////dodging/////////////////////////////////////////////
	private double dodgeChance(Unit defender) {
		return 0.2 * ((double) defender.getAgility()/(double) this.getAgility());
	}
	
	private boolean succesfullDodge(Unit defender) {
		Random random = new Random();
		int chance = random.nextInt(99);
		System.out.println(chance);
		if (chance < 100 * dodgeChance(defender)) 
			return true;
		else
			return false;
	}
	
	private void moveToRandomAdjecant() {
		Random rand = new Random();
		double newX = rand.nextInt(2) - 1;
		double newY = rand.nextInt(2) - 1;
		
		System.out.println(newX + " " + newY );
		
		double[] newPos = getPosition().clone();
		newPos[0] += newX;
		newPos[1] += newY;
		
		if(isValidPosition(newPos) && (newPos[0] != getPosition()[0] && newPos[1] != getPosition()[1])) {
			System.out.println("in de check");
			this.setUnitPosition(newPos);
		} else {
			moveToRandomAdjecant();
		}
		
	}
	
	private void dodgethis(Unit defender) {
			System.out.println("dodge!");
			moveToRandomAdjecant();
	}
	
/////////////////////////////////////////////blocking/////////////////////////////////////////////
	private double blockChance(Unit defender) {
		return 0.25 * ((defender.getStrength() + defender.getStamina()) / (this.getStrength() + this.getStamina()));
	}
	
	private boolean succesfullBlock(Unit defender) {
		Random random = new Random();
		int chance = random.nextInt(99);
		System.out.println(chance);
		if (chance < 100 * blockChance(defender)) 
			return true;
		else
			return false;
	}
	
	private void blockthis(Unit defender){
		System.out.println("block");
		if (succesfullBlock(defender)) {
			stopAttacking();
		}
	}
/////////////////////////////////////////////taking damage/////////////////////////////////////////////
	private void takeDamage(Unit defender) {
		defender.decreaseHitpoints((int) Math.round((double) this.stamina / 10));
	}
	
	
/////////////////////////////////////////////resting/////////////////////////////////////////////
	
	private int totalHPRestored = 0;
	
	/**
	 * Return the state of resting of this unit.
	 */
	@Basic @Raw
	public boolean isTheUnitResting() {
		return this.isResting;
	}
	
	/**
	 * Check if the unit is able to rest.
	 *  
	 *
	 * @return if the unit can rest return true
	 *       | result == 
	*/
	private boolean canRest( ) {
		if (this.isAttacking == true || this.isDefending == true 
				|| (getHitpoints() >= getMaxHitpoints() && getStamina() >= getMaxStamina() ))
			return false;
		return true;
	}
	
	private void resting (double dt) {
		this.isResting = true;
		stopWorking();
		
		if (getHitpoints() < getMaxHitpoints()){
			restoreHitpoints(dt);
			return;
		}else if (this.stamina < getMaxStamina()){
			restoreStamina(dt);
			return;
		}
		this.stopResting();
		return;
	}
	
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
	
	private void restoreStamina (double dt){
		double staminaToRestore = 5 * ((double) this.toughness / 100) * dt;
		counterForStamina += staminaToRestore;
		if(counterForStamina >= 1) {
			int value = (int)counterForStamina;
			counterForStamina -= value;
			increaseStamina(value);
		}
	}

	public void startResting (){
		if (getHitpoints() == getMaxHitpoints()) {
			totalHPRestored = -5;
		}
		this.isPathfinding = false;
		this.isWorking = false;
		this.isResting = true;
	}
	
	private void stopResting (){
		if (totalHPRestored >= 0 || totalHPRestored == -5)
			this.isResting = false;
	}
	
/////////////////////////////////////////////default behavior/////////////////////////////////////////////

	public boolean isDefaultBehaviourOn(){
		return this.defaultBehaviour;
	}
	
	public void setDefaultBehaviour(boolean value){
		this.defaultBehaviour = value;
	}
	
	private void defaultBehaviour(double dt){
		Random random = new Random();
		int ActionChance = random.nextInt(3);
		if(ActionChance == 1){
			int[] cube = {0,0,0};
			cube[0] = random.nextInt(50);
			cube[1] = random.nextInt(50);
			cube[2] = random.nextInt(50);
			int SprintChance = random.nextInt(2);
			if (SprintChance == 1)
				this.startSprinting();
			this.startPathfinding(cube);
		}
		else if(ActionChance == 2){
			this.startWorking();
		}
		else if(ActionChance == 3){
			this.startResting();
		}
	}
//////////////////////////////////////////////////////////////////////////////////////////////////
}

