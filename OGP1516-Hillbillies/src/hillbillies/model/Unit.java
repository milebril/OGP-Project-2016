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
	public final static int MAX_VALUE_COORDINATE_GAMEWORLD = 50;	
	
	/*
	 * Variable registering the property_name_Eng of this object_name.
	 */
	private int weight;
	private int strength;
	private int agility;
	private int toughness;
	private String name;
	private float orientation = (float) (PI / 2);
	private int positionX;
	private int positionY;
	private int positionZ;
	private int hitpoints;
	private int stamina;
	
/////////////////////////////////////////////Constructor/////////////////////////////////////////////	
	
	public Unit(int strenght) {
		return;
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
	 *       |if weight <= 1 || weight => 200:
	 *       |	return false
	 *       |else:
	 *       |	return true
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
	 *       |   then new.getWeight() == weight
	 */
	@Raw
	public void setWeight(int weight) {
		if (isValidWeight(weight))
			this.weight = weight;
		// else:
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
	 *       |if (MIN_VALUE_STRENGTH <= strength <= MAX_VALUE_STRENGTH):
	 *       |	then return true;
	 *       |else:
	 *       |	then return false;
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
		// else
	}
		
/////////////////////////////////////////////agility/////////////////////////////////////////////
	
	/**
	 * @invar  The agility of each unit must be a valid agility for any
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
	 *       |if (MIN_VALUE_AGILITY <= agility <= MAX_VALUE_AGILITY):
	 *       |	then return true;
	 *       |else:
	 *       |	then return false;
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
	 *       |if (MIN_VALUE_TOUGHNESS <= toughness <= MAX_VALUE_TOUGHNESS):
	 *       |	then return true;
	 *       |else:
	 *       |	then return false;
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
	 * @throws NoValidLength
	 *         The given name is not a valid name for any
	 *         unit because the length is too short.
	 *       |   
	 * @throws ContainsInvalidCharacter
	 *         The given name is not a valid name for any
	 *         unit because there is an invalid character in the name.	
	 *       |
	 */
	@Raw
	public void setName(String name) throws IllegalNameException {
		if (!isValidName(name))
			throw new IllegalNameException(name);
		
		this.name = name;
	}
	
/////////////////////////////////////////////position/////////////////////////////////////////////
		
	/**
	 * Return the Position of this Unit.
	 */
	@Basic @Raw
	public int[] getPosition() {
		int[] position = {this.positionX , this.positionY, this.positionZ};
		return position;
	}
	
	/**
	 * Check whether the given position is a valid position for
	 * any Unit.
	 *  
	 * @param  position
	 *         The position to check.
	 * @return if position is a valid position return true else return false
	 *       | 
	*/
	public static boolean isValidPosition(int[] position) {
		for (int i=0; i< position.length; i++) {
			if (position[i] < MIN_VALUE_COORDINATE_GAMEWORLD || position[i] > MAX_VALUE_COORDINATE_GAMEWORLD)
				return false;
		}
		return false;
	}
	
	/**
	 * Set the position of this unit to the given position.
	 * 
	 * @param  position
	 *         The new position for this unit.
	 *         An array of integers contains the x,y and z coordinate of the unit
	 * @post   The property_name_Eng of this new object_name_Eng is equal to
	 *         the given property_name_Eng.
	 *       | new.getPropertyName_Java() == propertyName_Java
	 * @throws ExceptionName_Java
	 *         The given property_name_Eng is not a valid property_name_Eng for any
	 *         object_name_Eng.
	 *       | ! isValidPropertyName_Java(getPropertyName_Java())
	 */
	@Raw
	public void setPropertyName_Java(int[] position) /*throws ExceptionName_Java*/ {
		/*
		if (! isValidPropertyName_Java(propertyName_Java))
			throw new ExceptionName_Java();
		*/
		this.positionX = position[0];
		this.positionY = position[1];
		this.positionZ = position[2];
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
	 *       |if (0 <= orientation <= PI):
	 *       |	then return true;
	 *       |else:
	 *       |	then return false;
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
/////////////////////////////////////////////current health/////////////////////////////////////////////
	/**
	 * @invar  Each object_name can have its property_name_Eng as property_name_Eng.
	 *       | canHaveAsPropertyName_Java(this.getPropertyName_Java())
	 */

	/**
	 * Return the hitpoints of this unit.
	 */
	@Basic @Raw @Immutable
	public int getHitpoints() {
		return this.hitpoints;
	}
	
	public int getMaxHitpoints() {
		return (int) 200 * (this.weight/100) * (this.toughness/100);
	}
	
	/**
	 * 
	 * @param amount
	 * 
	 * @pre ...
	 * 		|amount > 0
	 * @post ...
	 * 		| if (this.hitpoints + amount > getMaxHitpoints()) Then new.hitpoints == getMaxHitpoints()
	 * 		|		Else new.hitpoints == this.hitpoints + amount
	 * 
	 */
	public void increaseHitpoints(int amount) {
		assert amount > 0;
		if (amount > getMaxHitpoints())
			this.hitpoints = getMaxHitpoints();
		else 
			this.hitpoints = this.hitpoints + amount;
	}
	
	/**
	 * 
	 * @param amount
	 * @pre ...
	 * 		|amount > 0
	 * @post ...
	 * 		| if (amount > getHitpoints()) Then new.hitpoints == 0
	 * 		|		Else new.hitpoints == this.getHitpoints - amount
	 * 
	 */
	public void decreaseHitpoints(int amount) {
		assert amount > 0;
		if (amount > getHitpoints()) 
			this.hitpoints = 0;
		else
			this.hitpoints = this.hitpoints - amount;
		
	}
	
/////////////////////////////////////////////current stamina/////////////////////////////////////////////

	/**
	 * Return the stamina of this unit.
	 */
	@Basic @Raw @Immutable
	public int getStanima() {
		return this.stamina;
	}
	
	public int getMaxStamina() {
		return (int) 200 * (this.weight/100) * (this.toughness/100);
	}
	
	/**
	 * 
	 * @param amount
	 * 
	 * @pre ...
	 * 		|amount > 0
	 * @post ...
	 * 		| if (this.stamina + amount > getMaxStamina()) Then new.stamina == getMaxStamina()
	 * 		|		Else new.stamina == this.stamina + amount
	 * 
	 */
	public void increaseStamina(int amount) {
		assert amount > 0;
		if (amount > getMaxStamina())
			this.stamina = getMaxStamina();
		else 
			this.stamina = this.stamina + amount;
	}
	
	/**
	 * 
	 * @param amount
	 * @pre ...
	 * 		|amount > 0
	 * @post ...
	 * 		| if (amount > getStamina()) Then new.stamina == 0
	 * 		|		Else new.stamina == this.getStamina - amount
	 * 
	 */
	public void decreaseStamina(int amount) {
		assert amount > 0;
		if (amount > getMaxStamina()) 
			this.stamina = 0;
		else
			this.stamina = this.stamina - amount;
	}
/////////////////////////////////////////////Game time/////////////////////////////////////////////
	
	
/////////////////////////////////////////////movement/////////////////////////////////////////////
	
/////////////////////////////////////////////pat finding/////////////////////////////////////////////
	
/////////////////////////////////////////////actions/////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
	
/////////////////////////////////////////////work/////////////////////////////////////////////
	
/////////////////////////////////////////////Fighting/////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////
	
/////////////////////////////////////////////dodging/////////////////////////////////////////////
	
/////////////////////////////////////////////blocking/////////////////////////////////////////////
	
/////////////////////////////////////////////taking damage/////////////////////////////////////////////
	
/////////////////////////////////////////////updating orientation/////////////////////////////////////////////
	
//////////////////////////////////////////////////////////////////////////////////////////////////
	
/////////////////////////////////////////////resting/////////////////////////////////////////////
	
/////////////////////////////////////////////default behavior/////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////////////
}

