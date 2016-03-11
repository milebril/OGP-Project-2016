package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class that deals with logs and all the properties of the logs
 * in a given game world.
 * 
 * @invar  Each log can have its weight as weight.
 *       | canHaveAsWeight(this.getWeight())
 * @author Emil Peters
 * @author Sjaan Vandebeek
 */
public class Log {
	
	/**
	 * Initialize this new log with given weight.
	 * 
	 * @param  Weight
	 *         The weight for this new log.
	 * @post   The weight of this new log is equal to the given
	 *         weight.
	 *       | new.getWeight() == Weight
	 * @throws IllegalArgumentException
	 *         This new log cannot have the given weight as its weight.
	 *       | ! canHaveAsWeight(this.getWeight())
	 */
	public Log(int weight) throws IllegalArgumentException {
		if (! canHaveAsWeight(weight))
			throw new IllegalArgumentException();
		this.weight = weight;
		//TODO constructor
	}
 
	
	/**
	 * Return the weight of this log.
	 */
	@Basic @Raw @Immutable
	public int getWeight() {
		return this.weight;
	}
	
	/**
	 * Check whether this log can have the given weight as its weight.
	 *  
	 * @param  Weight
	 *         The weight to check.
	 * @return if the weight of the log lays between the minimum and the maximum
	 * 		   weight return true.  
	*/
	@Raw
	public boolean canHaveAsWeight(int weight) {
		if( getMinWeight() <= weight && weight <= getMaxWeight())
			return true;
		return false;
	}
	
	/**
	 * Return the highest weight for all logs
	 */
	private static int getMaxWeight(){
		return 50;
	}
	
	/**
	 * Return the lowest weight for all logs
	 */
	private static int getMinWeight(){
		return 10;
	}
		
	/**
	 * Variable registering the weight of this log.
	 */
	private final int weight;
}
