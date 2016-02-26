package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;
import static java.lang.Math.PI;

public class Unit {
	
	public final static int MIN_VALUE_STRENGTH = 1;
	public final static int MAX_VALUE_STRENGTH = 200;
	public final static int MIN_VALUE_AGILITY = 1;
	public final static int MAX_VALUE_AGILITY = 200;
	public final static int MIN_VALUE_WEIGTH = 1;
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
	private boolean isWorking = false;
	private boolean isResting = false;
	private boolean isAttacking = false;
	private boolean isDefending = false;
	private boolean defaultBehaviour = false;
	
/////////////////////////////////////////////Constructor/////////////////////////////////////////////	
	
	public Unit (String name, int[] initialPosition, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) {
		this.setWeight(weight);
		this.setStrenght(strength);
		this.setToughness(toughness);
		this.setAgility(agility);
		this.setName(name);
		this.setUnitPosition(putUnitInCenter(castIntToDouble(initialPosition)));
		//TODO hitpoints en stamina worden niet geinitialseerd, dit moet worden nagekeken
		this.setHitpoints(getMaxHitpoints());
		this.increaseStamina(50);
		return ;
	}
	
	public double[] putUnitInCenter(double[] initialPosistion) {
		for (int i = 0; i < initialPosistion.length; i++) {
			initialPosistion[i] += 0.5;
		}
		return initialPosistion;
	}
	
	
/////////////////////////////////////////////weight/////////////////////////////////////////////
	
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
		if( MIN_VALUE_WEIGTH <= weight && weight <= MAX_VALUE_WEIGTH)
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
	public void setStrenght(int strenght) {
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
	public static boolean StringDoesContainOnlyValidCharacters(String name){
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
		if (this.hitpoints + hitpoints > getMaxHitpoints())
			this.hitpoints = getMaxHitpoints();
		else 
			this.hitpoints = this.hitpoints + hitpoints;
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
			this.hitpoints = 0;
		else
			this.hitpoints = this.hitpoints - hitpoints;
		
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
		if (stamina > getMaxStamina())
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
		if (amount > getMaxStamina()) 
			this.stamina = 0;
		else
			this.stamina = this.stamina - amount;
	}

/////////////////////////////////////////////advance time/////////////////////////////////////////////
	public void advanceTimeInUnit(double dt){
		//TODO advance time
	}
	
/////////////////////////////////////////////movement/////////////////////////////////////////////
	public void moveToAdjacent(int dx, int dy, int dz) {
		
	}
	
/////////////////////////////////////////////pat finding/////////////////////////////////////////////
	
/////////////////////////////////////////////actions/////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
	
/////////////////////////////////////////////work/////////////////////////////////////////////
	
/////////////////////////////////////////////Fighting/////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////
	
/////////////////////////////////////////////Attacking/////////////////////////////////////////////



/////////////////////////////////////////////dodging/////////////////////////////////////////////
	
/////////////////////////////////////////////blocking/////////////////////////////////////////////
	
/////////////////////////////////////////////taking damage/////////////////////////////////////////////
	
/////////////////////////////////////////////updating orientation/////////////////////////////////////////////
	
//////////////////////////////////////////////////////////////////////////////////////////////////
	
/////////////////////////////////////////////resting/////////////////////////////////////////////
	
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
	public boolean canRest( ) {
		if (this.isAttacking == true || this.isDefending == true 
				|| (this.hitpoints == getMaxHitpoints() 
				&& this.stamina == getMaxStamina() ))
			return false;
		return true;
	}
	
	/**
	 * Set the property_name_Eng of this object_name_Eng to the given property_name_Eng.
	 * 
	 * @param  propertyName_Java
	 *         The new property_name_Eng for this object_name_Eng.
	 * @post   The property_name_Eng of this new object_name_Eng is equal to
	 *         the given property_name_Eng.
	 *       | new.getPropertyName_Java() == propertyName_Java
	 * @throws ExceptionName_Java
	 *         The given property_name_Eng is not a valid property_name_Eng for any
	 *         object_name_Eng.
	 *       | ! isValidPropertyName_Java(getPropertyName_Java())
	 */
	/*
	@Raw
	public void setPropertyName_Java(property_type_Java propertyName_Java) 
			throws ExceptionName_Java {
		if (! isValidPropertyName_Java(propertyName_Java))
			throw new ExceptionName_Java();
		this.propertyName_Java = propertyName_Java;
	}
*/
	
	public void resting (double dt) /*throws Exception is niet resting*/{
		/*if (! isValidPropertyName_Java(Exception))
			throw new Exception();*/
		if (this.hitpoints <= getMaxHitpoints())
			//TODO increase hitpoints
			return;
		if (this.stamina <= getMaxStamina())
			//TODO increase stamina
			return;
		//TODO stop resting
		return;
	}
/////////////////////////////////////////////default behavior/////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////////////
}

