package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;

public class Unit {
	
	public final static int MIN_VALUE_STRENGTH = 1;
	public final static int MAX_VALUE_STRENGTH = 200;
	public final static int MIN_VALUE_AGILITY = 1;
	public final static int MAX_VALUE_AGILITY = 200;
	public final static int MIN_VALUE_WEIGTH = 1;
	public final static int MAX_VALUE_WEIGTH = 200;
	public final static int MIN_VALUE_TOUGHNESS = 1;
	public final static int MAX_VALUE_TOUGHNESS = 200;
	
	private int weight;
	private int strength;
	private int agility;
	private int toughness;
	
	
	public Unit(int test) {
		return;
	}
	

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
	 *       |if weight <= 1 || weight => 20:
	 *       |	return false
	 *       |else:
	 *       |	return true
	*/
	public static boolean isValidWeight(int weight) {
		if( 1 <= weight && weight <= 20)
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
	}
	
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
	}
		
	
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
	 *       |if (MIN_VALUE_AGILITY <= strength <= MAX_VALUE_AGILITY):
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
	}
}

