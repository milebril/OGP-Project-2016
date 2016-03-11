package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class that deals with a boulder and all the properties of the boulder 
 * in a given game world.
 * 
 * @invar  Each Boulder can have its weight as weight.
 *       | canHaveAsWeight(this.getWeight())
 * @author Emil Peters
 * @author Sjaan Vandebeek
 *
 */
public class Boulder {

	/**
	 * Initialize this new Boulder with given weight.
	 * 
	 * @param  Weight
	 *         The weight for this new Boulder.
	 * @post   The weight of this new Boulder is equal to the given
	 *         weight.
	 *       | new.getWeight() == Weight
	 * @throws ExceptionName_Java
	 *         This new Boulder cannot have the given weight as its weight.
	 *       | ! canHaveAsWeight(this.getWeight())
	 * @throws ExceptionName_Java
	 */
	public Boulder(int weight) throws IllegalArgumentException {
		if (! canHaveAsWeight(weight))
			throw new IllegalArgumentException();
		this.weight = weight;
		//TODO constructor
	}
 
	
	/**
	 * Return the weight of this Boulder.
	 */
	@Basic @Raw @Immutable
	public int getWeight() {
		return this.weight;
	}
	
	/**
	 * Check whether this Boulder can have the given weight as its weight.
	 *  
	 * @param  Weight
	 *         The weight to check.
	 * @return 
	 *       
	*/
	@Raw
	public boolean canHaveAsWeight(int weight) {
		if( getMinWeight() <= weight && weight <= getMaxWeight())
			return true;
		return false;
	}
	
	/**
	 * Return the highest weight for all boulders
	 */
	private static int getMaxWeight(){
		return 50;
	}
	
	/**
	 * Return the lowest weight for all boulders
	 */
	private static int getMinWeight(){
		return 10;
	}
		
	/**
	 * Variable registering the weight of this Boulder.
	 */
	private final int weight;
}
