package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;

public class Unit {
	
	public final static int MIN_VALUE_STRENGTH = 1;
	public final static int MAX_VALUE_STRENGTH = 200;
	
	public Unit(int test) {
		return;
	}
	
	/**
	 * @invar  The strength of each unit must be a valid strength for any
	 *         unit.
	 *       | isValidStrength(strength())
	 */

	/**
	 * Initialize this new unit with given strength.
	 * 
	 * @param  propertyName_Java
	 *         The strength for this new unit.
	 * @post   If the given strength is a valid strength for any unit,
	 *         the strength of this new unit is equal to the given
	 *         strength. Otherwise, the strength of this new unit is equal
	 *         to default_value_Java.
	 *       | if (isValidStrength(propertyName_Java))
	 *       |   then new.strength() == propertyName_Java
	 *       |   else new.strength() == default_value_Java
	 */
	public Unit(property_type propertyName_Java) {
		if (! isValidStrength(propertyName_Java))
			propertyName_Java = default_value_Java;
		setPropertyName_Java(propertyName_Java);
	}
		
	/**
	 * Return the strength of this unit.
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
	 *       | result == 
	*/
	public static boolean isValidWeight(int weight) {
		return 1 <= weight || weight <= 20;
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
	 * Variable registering the strength of this unit.
	 */
	private int weight;
	
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
	 *       | result == if (MIN <= strength <= MAX) then true
	*/
	public static boolean isValidStrength(int strength) {
		return strength <= MAX_VALUE_STRENGTH && strength >= MIN_VALUE_STRENGTH;
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
	 * Variable registering the strength of this unit.
	 */
	private int strength;
	
	
}

